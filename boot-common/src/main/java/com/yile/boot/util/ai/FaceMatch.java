package com.yile.boot.util.ai;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yile.boot.util.ai.util.AccessTokenUtil;
import com.yile.boot.util.ai.util.Base64Util;
import com.yile.boot.util.ai.util.FileUtil;
import com.yile.boot.util.ai.util.GsonUtils;
import com.yile.boot.util.ai.util.HttpUtil;

import net.sf.json.JSONObject;

/**
 * 百度AI人脸识别
 * @author Administrator
 *
 */
public class FaceMatch {
	
	public static String match(String filePath1, String filePath2) {
		try {
			byte[] bytes1 = FileUtil.readFileByBytes(filePath1);
	        byte[] bytes2 = FileUtil.readFileByBytes(filePath2);
	        
	        String result = matchImage(bytes1, bytes2);
	        return result;
		} catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
	
	public static Double getMatchScore(String filePath1, String filePath2){
		double score = 0.0;        
		
        String result = match(filePath1, filePath2);
        
        JSONObject jsonObject =JSONObject.fromObject(result);
        int errorCode = jsonObject.getInt("error_code");
        if(errorCode == 0){
        	JSONObject resultObj = jsonObject.getJSONObject("result");
            score = resultObj.getDouble("score");
        } else {
        	String errorMsg = jsonObject.getString("error_msg");
        	System.out.println("errorMsg: " + errorMsg);
        	throw new RuntimeException(errorMsg);
        }
        
        return score;
    }
	
	public static Double getMatchScore(byte[] bytes1, byte[] bytes2){
		double score = 0.0;
		        
        String result = matchImage(bytes1, bytes2);
        JSONObject jsonObject =JSONObject.fromObject(result);
        int errorCode = jsonObject.getInt("error_code");
        if(errorCode == 0){
        	JSONObject resultObj = jsonObject.getJSONObject("result");
            score = resultObj.getDouble("score");
        } else {
        	String errorMsg = jsonObject.getString("error_msg");
        	System.out.println("errorMsg: " + errorMsg);
        	throw new RuntimeException(errorMsg);
        }
        
        return score;
    }	
	
	public static String matchImage(byte[] bytes1, byte[] bytes2) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/match";
        try {
            String image1 = Base64Util.encode(bytes1);
            String image2 = Base64Util.encode(bytes2);

            List<Map<String, Object>> images = new ArrayList<>();

            Map<String, Object> map1 = new HashMap<>();
            map1.put("image", image1);
            map1.put("image_type", "BASE64");
            map1.put("face_type", "LIVE");
            map1.put("quality_control", "NONE");
            map1.put("liveness_control", "NONE");

            Map<String, Object> map2 = new HashMap<>();
            map2.put("image", image2);
            map2.put("image_type", "BASE64");
            map2.put("face_type", "LIVE");
            map2.put("quality_control", "NONE");
            map2.put("liveness_control", "NONE");

            images.add(map1);
            images.add(map2);

            String param = GsonUtils.toJson(images);
            String accessToken = AccessTokenUtil.getAuth();
            String result = HttpUtil.post(url, accessToken, "application/json", param);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
    	File file = new File("d:\\p1.jpg");
    	System.out.println(file);
    	byte[] byte1 = FileUtil.readFileByBytes(file);
    	byte[] byte2 = FileUtil.readFileByBytes(file);
    	 
    	double score = FaceMatch.getMatchScore(byte1, byte2);
    	System.out.println(score);
    }
}
