package com.cerner.immunizationForecast.beans;

public class Doctor {
	
	private int DoctorID;
	private String Fname;
	private String Mname;
	private String Lname;
	private char Gender;
	private String Address;
	private int ContactNo;
	private String EmailID;
	
	public int getDoctorID() {
		return DoctorID;
	}
	public void setDoctorID(int doctorID) {
		DoctorID = doctorID;
	}
	public String getFname() {
		return Fname;
	}
	public void setFname(String fname) {
		Fname = fname;
	}
	public String getMname() {
		return Mname;
	}
	public void setMname(String mname) {
		Mname = mname;
	}
	public String getLname() {
		return Lname;
	}
	public void setLname(String lname) {
		Lname = lname;
	}
	public char getGender() {
		return Gender;
	}
	public void setGender(char gender) {
		Gender = gender;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public int getContactNo() {
		return ContactNo;
	}
	public void setContactNo(int contactNo) {
		ContactNo = contactNo;
	}
	public String getEmailID() {
		return EmailID;
	}
	public void setEmailID(String emailID) {
		EmailID = emailID;
	}

}
