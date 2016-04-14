package com.cerner.immunizationForecast.beans;

public class Patient_Parameter {

	private int PatientID;
	private int ParameterID;
	private char Value;
	
	public int getPatientID() {
		return PatientID;
	}
	
	public int getParameterID() {
		return ParameterID;
	}

	public char getValue() {
		return Value;
	}

	public void setValue(char value) {
		Value = value;
	}

	public void setParameterID(int parameterID) {
		ParameterID = parameterID;
	}

	public void setPatientID(int patientID) {
		PatientID = patientID;
	}


}
