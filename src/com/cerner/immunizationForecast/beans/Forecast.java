package com.cerner.immunizationForecast.beans;

import java.util.Date;

public class Forecast {

	private int patientID;
	private int vaccineID;
	private int dose;
	private Date Forecast_Date;
	private String type;
	private String status;
	private String vaccineName;
	private String vName;
	
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
	public Date getForecast_Date() {
		return Forecast_Date;
	}
	public void setForecast_Date(Date forecast_Date) {
		Forecast_Date = forecast_Date;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getVaccineName() {
		return vaccineName;
	}
	public void setVaccineName(String vaccineName) {
		this.vaccineName = vaccineName;
	}
	public String getvName() {
		return vName;
	}
	public void setvName(String vName) {
		this.vName = vName;
	}
}
