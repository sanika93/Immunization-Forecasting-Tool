package com.cerner.immunizationForecast.beans;

/*Java Bean for Patient Allergy*/

public class Patient_Allergy {
	
	private int patientID;
	private int allergyID;
	private int dose;
	private String description;
	
	
	public int getPatientID() {
		return patientID;
	}
	public void setPatientID(int patientID) {
		this.patientID = patientID;
	}
	public int getAllergyID() {
		return allergyID;
	}
	public void setAllergyID(int vaccineID) {
		this.allergyID = vaccineID;
	}
	public int getDose() {
		return dose;
	}
	public void setDose(int dose) {
		this.dose = dose;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	
	

}
