/* Author:Srividya Shastry*/
/* Owner : Cerner		  */
/* Team: Vikings		  */

package com.cerner.immunizationForecast.beans;

	
import java.sql.Date;

/*Java Bean for Patient */
/*This table stores the demographics of the patient */

	public class Patient {
		 
		   	private int PatientID;
		   	private String Fname;
		   	private String Lname;
		   	private String Mname;	
		   	private Date DateOfBirth;
		   	private char Gender;
		   	private String Contactnumber;
		   	private String EmailID;
		   	private String Address;
		   	private float Weight;
		   	
			public int getPatientID()
			{
				return PatientID;
			}
			public void setPatientID(int patientID) 
			{
				PatientID = patientID;
			}
			public String getFirstName() 
			{
				return Fname;
			}
			public void setFirstName(String fname)
			{
				Fname = fname;
			}
			public String getMiddleName()
			{
				return Mname;
			}
			public void setMiddleName(String mname)
			{
				Mname = mname;
			}
			public String getLastName()
			{
				return Lname;
			}
			public void setLastName(String lname)
			{
				Lname = lname;
			}
			public Date getDateOfBirth() 
			{
				return DateOfBirth;
			}
			public void setDateOfBirth(Date dateOfBirth)
			{
				DateOfBirth = dateOfBirth;
			}
			public char getGender()
			{
				return Gender;
			}
			public void setGender(char gender)
			{
				Gender = gender;
			}
			public String getContactNumber()
			{
				return Contactnumber;
			}
			public void setContactNumber(String contactnumber)
			{
				Contactnumber = contactnumber;
			}
			public String getEmailID() 
			{
				return EmailID;
			}
			public void setEmailID(String emailID)
			{
				EmailID = emailID;
			}
			public String getAddress() 
			{
				return Address;
			}
			public void setAddress(String address)
			{
				Address = address;
			}
			public float getWeight() 
			{
				return Weight;
			}
			public void setWeight(float weight)
			{
				Weight = weight;
			}


	}			