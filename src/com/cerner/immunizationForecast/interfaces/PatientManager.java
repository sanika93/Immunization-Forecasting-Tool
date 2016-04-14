package com.cerner.immunizationForecast.interfaces;

import java.util.ArrayList;

import com.cerner.immunizationForecast.beans.Patient;
import com.cerner.immunizationForecast.beans.Patient_Allergy;
import com.cerner.immunizationForecast.beans.Patient_Parameter;

public interface PatientManager {
	
	public boolean storePatientDetails(Patient p);
	public Patient getPatientDetails(int patientID);
	public boolean storePatientAllergy(Patient_Allergy a);
	public Patient_Allergy getPatientAllergy(int patientID);
	public boolean storePatientParameter(Patient_Parameter p);
	public ArrayList<Patient_Parameter> getPatientParameter(int patientID);

}
