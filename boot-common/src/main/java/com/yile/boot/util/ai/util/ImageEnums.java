package com.yile.boot.util.ai.util;

public class ImageEnums {
	/**
	 * 图片类型
	 */
	public enum ImageType {
		BASE64("BASE64"),
		URL("URL"),
		FACE_TOKEN("FACE_TOKEN");

	    private String value;

	    ImageType(String value) {
	        this.value = value;
	    }

	    public String getValue() {
	        return value;
	    }
	}
	
	/**
	 * 人脸的类型
	 */
	public enum FaceType {
		LIVE("LIVE"),
		IDCARD("IDCARD"),
		WATERMARK("WATERMARK"),
		CERT("CERT");

	    private String value;

	    FaceType(String value) {
	        this.value = value;
	    }

	    public String getValue() {
	        return value;
	    }
	}
	
	/**
	 * 图片质量控制
	 */
	public enum QualityControl {
		NONE("NONE"),
		LOW("LOW"),
		NORMAL("NORMAL"),
		HIGH("HIGH");

	    private String value;

	    QualityControl(String value) {
	        this.value = value;
	    }

	    public String getValue() {
	        return value;
	    }
	}
	
	/**
	 * 活体检测控制
	 */
	public enum LivenessControl {
		NONE("NONE"),
		LOW("LOW"),
		NORMAL("NORMAL"),
		HIGH("HIGH");

	    private String value;

	    LivenessControl(String value) {
	        this.value = value;
	    }

	    public String getValue() {
	        return value;
	    }
	}

}
	
