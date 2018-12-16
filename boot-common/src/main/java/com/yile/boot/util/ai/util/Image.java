package com.yile.boot.util.ai.util;

public class Image {
	private String image;
	private String imageType;
	private String faceType;
	private String qualityControl;
	private String livenessControl;
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getImageType() {
		return imageType;
	}
	public void setImageType(String imageType) {
		this.imageType = imageType;
	}
	public String getFaceType() {
		return faceType;
	}
	public void setFaceType(String faceType) {
		this.faceType = faceType;
	}
	public String getQualityControl() {
		return qualityControl;
	}
	public void setQualityControl(String qualityControl) {
		this.qualityControl = qualityControl;
	}
	public String getLivenessControl() {
		return livenessControl;
	}
	public void setLivenessControl(String livenessControl) {
		this.livenessControl = livenessControl;
	}
}
