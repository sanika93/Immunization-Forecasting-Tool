package com.cerner.immunizationForecast.beans;

public class PatientVaccineAppointment {
	
	private int PatientID;
	private String VaccineName;
	private int Dose;
	public String getVaccineName() {
		return VaccineName;
	}
	public void setVaccineName(String vaccineName) {
		VaccineName = vaccineName;
	}
	public int getPatientID() {
		return PatientID;
	}
	public void setPatientID(int patientID) {
		PatientID = patientID;
	}
	public int getDose() {
		return Dose;
	}
	public void setDose(int dose) {
		Dose = dose;
	}

}
