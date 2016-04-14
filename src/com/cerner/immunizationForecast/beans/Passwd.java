package com.cerner.immunizationForecast.beans;

public class Passwd {
	private String userName;
	private String originalPw;
	private String newPw;
	private String confNewPw;
	private int uId;
	public String getOriginalPw() {
		return originalPw;
	}
	public void setOriginalPw(String originalPw) {
		this.originalPw = originalPw;
	}
	public String getNewPw() {
		return newPw;
	}
	public void setNewPw(String newPw) {
		this.newPw = newPw;
	}
	public String getConfNewPw() {
		return confNewPw;
	}
	public void setConfNewPw(String confNewPw) {
		this.confNewPw = confNewPw;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getuId() {
		return uId;
	}
	public void setuId(int uId) {
		this.uId = uId;
	}
	
}
