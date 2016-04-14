package com.cerner.immunizationForecast.beans;

import java.sql.Time;
import java.util.Date;

public class AppointmentTime {
	
	
	private int PatientID;
	private int DoctorID;
	private Date AppointmentDate;
	private Time AppointmentTimeSlot;
	
	public int getPatientID() {
		return PatientID;
	}
	public void setPatientID(int patientID) {
		PatientID = patientID;
	}
	public int getDoctorID() {
		return DoctorID;
	}
	public void setDoctorID(int doctorID) {
		DoctorID = doctorID;
	}
	
	public Date getAppointmentDate()
	{
		return AppointmentDate;
	}
	
	public void  setAppointmentDate(Date AppointmentDate )
	{
		this.AppointmentDate=AppointmentDate;
	}
	public Time getAppointmentTimeSlot() {
		return AppointmentTimeSlot;
	}
	public void setAppointmentTimeSlot(Time appointmentTimeSlot) {
		AppointmentTimeSlot = appointmentTimeSlot;
	}

	
	
}
