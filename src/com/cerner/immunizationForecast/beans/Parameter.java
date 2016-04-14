package com.cerner.immunizationForecast.beans;

/*Java Bean for Parameter*/

public class Parameter {
	
	private int parameterID;
	private int vaccineID;
	private String parameterName;
	private boolean active;
	
	public int getParameterID() {
		return parameterID;
	}
	public void setParameterID(int parameterID) {
		this.parameterID = parameterID;
	}
	public int getVaccineID() {
		return vaccineID;
	}
	public void setVaccineID(int vaccineID) {
		this.vaccineID = vaccineID;
	}
	public String getParameterName() {
		return parameterName;
	}
	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	

}
