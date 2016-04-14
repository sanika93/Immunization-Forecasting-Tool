package com.cerner.immunizationForecast.operationalClasses;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.sql.Date;


public class DateCalculations
{
	
	private java.util.Date utildate=null; 

	
	public Date StringtoSql( String DateinString)						//Function that converts a string to sqlDate
	{

		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");   //Format in which the input is taken from the user
		
			try 
			{
				utildate = format.parse(DateinString);					//Convert string to utilDate
			} 
		
			catch (ParseException e)
			{
			// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
			java.sql.Date DateofBirth = new Date(utildate.getTime()); 	//Convert utilDate to sqlDate
			
			System.out.println(DateinString);
			System.out.println(utildate);
			System.out.println(DateofBirth);
			
		return DateofBirth;
	}
	
	
}
