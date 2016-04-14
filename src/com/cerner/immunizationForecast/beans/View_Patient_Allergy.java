package com.cerner.immunizationForecast.beans;

public class View_Patient_Allergy {
	
	private String allergyname;
	private int dose;
	private String description;
	private String vaccineName;
	
		
	public String getAllergyName() {
		return allergyname;
	}
	public void setAllergyName(String allergyname) {
		this.allergyname = allergyname;
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
	public String getVaccineName() {
		return vaccineName;
	}
	public void setVaccineName(String vaccineName) {
		this.vaccineName = vaccineName;
	}
}	
