package com.cerner.immunizationForecast.operationalClasses;

import java.util.Date;

import com.cerner.immunizationForecast.beans.Vaccination_History;


public class AddVaccinationHistory {




	public boolean addVaccinesAdministered(int patientID,String vaccineName,int dose,Date adminDate,String status) {
		// TODO Auto-generated method stub
		boolean success;
		VaccineHandler vaccinehandler= new VaccineHandler();
		int vaccineId=vaccinehandler.getVaccineID(vaccineName);
		
		Vaccination_History vaccineHistory = new Vaccination_History();
		vaccineHistory.setPatientID(patientID);
		vaccineHistory.setVaccineID(vaccineId);
		vaccineHistory.setDose(dose);
		vaccineHistory.setStatus(status);
		java.sql.Date admin_date= convertJavaDateToSqlDate(adminDate);
		vaccineHistory.setAdmin_date(admin_date);
		vaccineHistory.setVaccineName(vaccineName);
		if (status.equals("Save")){
			
			//For adding more vaccinations to history
			 success=vaccinehandler.storeVaccinationHistory(vaccineHistory);
		}
		else  {
			//For updating the dates of the vaccination history
			 success=vaccinehandler.updateVaccinationHistory(vaccineHistory);
		}
		
		

		
		return success;
	}
	public java.sql.Date convertJavaDateToSqlDate(java.util.Date date) {
		//Function to convert util.date to sql.date for storing in db
	    return new java.sql.Date(date.getTime());
	}
	
}
