package com.yile.boot.util.ai;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yile.boot.util.ai.util.AccessTokenUtil;
import com.yile.boot.util.ai.util.Base64Util;
import com.yile.boot.util.ai.util.FileUtil;
import com.yile.boot.util.ai.util.GsonUtils;
import com.yile.boot.util.ai.util.HttpUtil;
import com.yile.boot.util.ai.util.ImageEnums.FaceType;

import net.sf.json.JSONObject;

public class FaceMatch {
	
	public static String match(String filePath1, String filePath2, String faceType1, String faceType2) {
		try {
			byte[] bytes1 = FileUtil.readFileByBytes(filePath1);
	        byte[] bytes2 = FileUtil.readFileByBytes(filePath2);
	        
	        String result = matchImage(bytes1, bytes2, faceType1, faceType2);
	        return result;
		} catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
	
	public static double getMatchScore(String filePath1, String filePath2, String faceType1, String faceType2){
		double score = 0.0;        
		
        String result = match(filePath1, filePath2, faceType1,  faceType2);
        
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
	
	public static double getMatchScore(byte[] bytes1, byte[] bytes2){
		double score = 0.0;
		        
        String result = matchImage(bytes1, bytes2, FaceType.LIVE.getValue(), FaceType.LIVE.getValue());
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
	
	public static double getMatchScore(byte[] bytes1, byte[] bytes2, String faceType1, String faceType2){
		double score = 0.0;
		        
        String result = matchImage(bytes1, bytes2, faceType1, faceType2);
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
	
	public static String matchImage(byte[] bytes1, byte[] bytes2, String faceType1, String faceType2) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/match";
        try {
            String image1 = Base64Util.encode(bytes1);
            String image2 = Base64Util.encode(bytes2);

            List<Map<String, Object>> images = new ArrayList<>();

            Map<String, Object> map1 = new HashMap<>();
            map1.put("image", image1);
            map1.put("image_type", "BASE64");
            map1.put("face_type", faceType1);
            map1.put("quality_control", "NONE");
            map1.put("liveness_control", "NONE");

            Map<String, Object> map2 = new HashMap<>();
            map2.put("image", image2);
            map2.put("image_type", "BASE64");
            map2.put("face_type", faceType2);
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
    	double score = FaceMatch.getMatchScore("d:\\d1.jpg", "d:\\d2.jpg", FaceType.LIVE.getValue(), FaceType.CERT.getValue());
    	System.out.println(score);
    }
}
