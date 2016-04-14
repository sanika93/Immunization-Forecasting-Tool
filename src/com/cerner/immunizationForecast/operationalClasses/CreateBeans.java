/* Author:Srividya Shastry*/
/* Owner : Cerner		  */
/* Team: Vikings		  */

package com.cerner.immunizationForecast.operationalClasses;

import java.sql.Date;

import com.cerner.immunizationForecast.beans.Patient;

public class CreateBeans
{
	//Function that uses the setter method of the Patient model to create a bean object
	
	public Patient createPatientBean(int PatientID,String fname,String mname,String lname,Date dateOfBirth,char gender,String address,String contactNumber,String email,float weight)
	{
		
		if((PatientID == 0)||(fname == null)||(lname == null)||(dateOfBirth == null)||(gender == '\0' )||(address == null)||(contactNumber == null)||(email == null)||(weight == 0))
		{
			return null;
		}
		//creating an object of the model class
				Patient patientBean=new Patient();
				
		//Setting the parameters of the model object
				patientBean.setPatientID(PatientID);
				patientBean.setFirstName(fname);
				patientBean.setMiddleName(mname);
				patientBean.setLastName(lname);
				patientBean.setDateOfBirth(dateOfBirth);
				patientBean.setGender(gender);
				patientBean.setWeight(weight);
				patientBean.setEmailID(email);
				patientBean.setContactNumber(contactNumber);
				patientBean.setAddress(address);	
		
				return patientBean;
	}

}
