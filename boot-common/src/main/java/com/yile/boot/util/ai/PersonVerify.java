package com.yile.boot.util.ai;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.yile.boot.util.ai.util.AccessTokenUtil;
import com.yile.boot.util.ai.util.Base64Util;
import com.yile.boot.util.ai.util.FileUtil;
import com.yile.boot.util.ai.util.GsonUtils;
import com.yile.boot.util.ai.util.HttpUtil;

import net.sf.json.JSONObject;

public class PersonVerify {
    public static String personVerify(String image, String idCardNumber, String name) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/person/verify";
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("image", image);
            map.put("image_type", "BASE64");
            map.put("id_card_number", idCardNumber);
            map.put("name", new String(name.getBytes("utf-8"), "utf-8"));
            map.put("quality_control", "NONE");
            map.put("liveness_control", "NONE");

            String param = GsonUtils.toJson(map);

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = AccessTokenUtil.getAuth();;

            String result = HttpUtil.post(url, accessToken, "application/json", param);
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static double getPersonVerifyScore(byte[] bytes, String idCardNumber, String name) {
		double score = 0.0;        
		
		String image = Base64Util.encode(bytes);
        String result = personVerify(image, idCardNumber, name);
        
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

    public static void main(String[] args) throws IOException {
    	byte[] bytes1 = FileUtil.readFileByBytes("d:\\d1.jpg");
    	
    	String name="邓松";    	
    	double score = PersonVerify.getPersonVerifyScore(bytes1, "460025199208200014", name);
    	System.out.println(score);
    }
}
