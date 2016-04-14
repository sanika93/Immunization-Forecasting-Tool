package com.cerner.immunizationForecast.interfaces;




import java.util.ArrayList;

import com.cerner.immunizationForecast.beans.Vaccination_History;


public interface VaccineManager
{
	
	/*public boolean storeVaccine(Vaccine v);
	public Vaccine getVaccine(int VaccineID);
	
	public boolean storeParameter(Parameter p);
	public ArrayList<Parameter> getParameter(int VaccineID);
	
	public boolean storeAllergy(Allergy p);
	public ArrayList<Allergy> getAllergy(int VaccineID);*/
	public int getVaccineID(String vaccinename);
	public boolean storeVaccinationHistory(Vaccination_History vh);
	public boolean updateVaccinationHistory(Vaccination_History vh);
	public boolean deleteVaccinationHistory(Vaccination_History vh);
	public ArrayList<Vaccination_History> viewVaccinesAdministered(int patientID);
	public ArrayList<Vaccination_History> getLastDose(int userId,int vaccineID);

	/*public ArrayList<Vaccination_History> getVaccinationHistory(int PatientID);
	
	public boolean storeForecast(Forecast f);
	public ArrayList<Forecast> getForecast(int PatientID);*/
	
}
