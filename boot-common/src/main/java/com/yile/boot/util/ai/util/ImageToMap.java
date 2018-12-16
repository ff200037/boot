package com.yile.boot.util.ai.util;

import java.util.HashMap;
import java.util.Map;

public class ImageToMap {
	public static Map<String, Object> imageToMap(Image image){
		Map<String, Object> map = new HashMap<>();
		map.put("image", image.getImage());
        map.put("image_type", image.getImageType());
        map.put("face_type", image.getFaceType());
        map.put("quality_control", image.getQualityControl());
        map.put("liveness_control", image.getLivenessControl());
		return map;
	}
}
