package com.cerner.immunizationForecast.beans;

public class Allergy {
	
	private int allergyID;
	private int vaccineID;
	private String allergyName ;
	private boolean active;
	//Model class 
	//Getters and setters for First name last name 
	public void setAllergyID( int allergyID){
		this.allergyID=allergyID;
		
	}
	public void setVaccineID( int vaccineID){
		this.vaccineID=vaccineID;
		
	}
	public void setAllergyName( String allergyName){
		this.allergyName=allergyName;
		
	}
	public void setActive(boolean active){
		this.active=active;
		
	}
	public int setAllergyID(){
		return allergyID;
		
	}
	public int getVaccineID( ){
		return vaccineID;
		
	}
	public String getAllergyName( ){
		return allergyName;
		
	}
	public boolean getActive(){
		return active;
		
	}
	

}
