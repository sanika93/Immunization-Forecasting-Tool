package com.cerner.immunizationForecast.interfaces;

import java.util.ArrayList;

import com.cerner.immunizationForecast.beans.Doctor;
import com.cerner.immunizationForecast.beans.ViewAppointment;

public interface DoctorManager {
	
	public ArrayList<Doctor> getDoctorNames();
	public int getDoctorID(String docName);
	public boolean insertPatientDoctorDetails(int patientID, int doctorID,String active);
	public ArrayList<ViewAppointment> getDoctorAppointments(int doctorID);

}
