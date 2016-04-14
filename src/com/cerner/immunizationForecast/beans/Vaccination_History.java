package com.cerner.immunizationForecast.beans;

import java.util.Date;

public class Vaccination_History {
	
	private int patientID;
	private int vaccineID;
	private int dose;
	private Date admin_date ;
	private String vaccineName;
	private String status;
	//Model class 
	public int getPatientID() {
		return patientID;
	}
	public void setPatientID(int patientID) {
		this.patientID = patientID;
	}
	public int getVaccineID() {
		return vaccineID;
	}
	public void setVaccineID(int vaccineID) {
		this.vaccineID = vaccineID;
	}
	public int getDose() {
		return dose;
	}
	public void setDose(int dose) {
		this.dose = dose;
	}
	public Date getAdmin_date() {
		return admin_date;
	}
	public void setAdmin_date(Date admin_date) {
		this.admin_date = admin_date;
	}
	public String getVaccineName() {
		return vaccineName;
	}
	public void setVaccineName(String vaccineName) {
		this.vaccineName = vaccineName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
