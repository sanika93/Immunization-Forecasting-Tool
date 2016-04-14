package com.cerner.immunizationForecast.beans;

import java.sql.Time;
import java.util.Date;

public class ViewAppointment {
	
	private String PatientName;
	private String VaccineName;
	private int Dose;
	private Date AppointmentDate;
	private Time AppointmentTime;
	
	public String getPatientName() {
		return PatientName;
	}
	public void setPatientName(String patientName) {
		PatientName = patientName;
	}
	public String getVaccineName() {
		return VaccineName;
	}
	public void setVaccineName(String vaccineName) {
		VaccineName = vaccineName;
	}
	public Date getAppointmentDate() {
		return AppointmentDate;
	}
	public void setAppointmentDate(Date appointmentDate) {
		AppointmentDate = appointmentDate;
	}
	public int getDose() {
		return Dose;
	}
	public void setDose(int dose) {
		Dose = dose;
	}
	public Time getAppointmentTime() {
		return AppointmentTime;
	}
	public void setAppointmentTime(Time appointmentTime) {
		AppointmentTime = appointmentTime;
	}
	

}
