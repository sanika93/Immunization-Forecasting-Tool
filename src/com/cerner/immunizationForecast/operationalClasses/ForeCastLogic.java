package com.cerner.immunizationForecast.operationalClasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.cerner.immunizationForecast.beans.Forecast;


public class ForeCastLogic
{
	public String getTyp(int diff)
	{
		if(diff>0)
			return "Overdue";//returns type as overdue if the current date has surpassed the forecast date
		else if(diff<0)
			return "Normal";//returns type as normal if the current date does not surpass the forecast date
		else
			return "Urgent";//returns type as urgent if the current date and forecast date are same
	}
	
	public Forecast createBean(int vaccineId,int patientId,String vaccName,String vaccineBrandName,int dose,Date forecastDate,String status,String type)
	{
		//uses the parameters to create and set values for a forecast bean and returns it
		Forecast forecastBean= new Forecast();
		forecastBean.setVaccineID(vaccineId);
		forecastBean.setPatientID(patientId);
		forecastBean.setVaccineName(vaccName);
		forecastBean.setvName(vaccineBrandName);
		forecastBean.setDose(dose);
		forecastBean.setForecast_Date(forecastDate);
		forecastBean.setStatus(status);
		forecastBean.setType(type);
		return forecastBean;
	}
	
	public Date now() 
	{
		//returns the current date
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//specifies the format required
		String dtString = sdf.format(cal.getTime());
		Date dt=null;
		try 
		{
			dt=sdf.parse(dtString);
		} 
		catch (ParseException e) 
		{
			e.printStackTrace();
		}
		return dt;
	}
	
	
	public java.util.Date addDays(java.util.Date date,int noOfDays)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, noOfDays); // add the specified number of days to the date received 
		date = calendar.getTime();
		return date;
	}	
	
	public int daysDiff(java.util.Date date)
	{
		Date currentDate= now();//get current date and time
		int diffInDays=(int) ((currentDate.getTime()-date.getTime()) / (1000 * 60 * 60 * 24));//computes the difference in days
		return diffInDays;//return diffInDays;
	}
	
	public int diffInDays(java.util.Date newDate,java.util.Date oldDate)
	{
		int diffInDays=(int) ((newDate.getTime()-oldDate.getTime()) / (1000 * 60 * 60 * 24));//computes the difference in days
		return diffInDays;//return diffInDays;
	}
	
	public int diffInMonths(java.util.Date newDate,java.util.Date oldDate)
	{
		int diffInDays=(int) ((newDate.getTime()-oldDate.getTime()) / (1000 * 60 * 60 * 24));//computes the difference in days
		return (diffInDays)/30;//return diffInDays;
	}
	
	public int diffInWeeks(java.util.Date newDate,java.util.Date oldDate)
	{
		int diffInDays=(int) ((newDate.getTime()-oldDate.getTime()) / (1000 * 60 * 60 * 24));//computes the difference in days
		return (diffInDays)/7;//return diffInDays;
	}
	
	/*public ArrayList<Forecast> createHepBForecast(int userId,int vaccineId)
	{
		SingletonDB sdb= SingletonDB.getInstance();//Get the instance of singleton class
		Connection conForForecast=sdb.createConnection();//establish connection	
		int dose=0;
		int count=0;
		Date dateOfBirth=null;
		Date currentDate=null;
		Date admDate=null;
		Date forecastDate=null;
		String vaccineName=null;
		vaccineName="Monovalent Hep B";
		DbOperations dBOps = new DbOperations();//creates an object of the operational class that contains the method to get DOB
		dateOfBirth=dBOps.getDOB(userId);
		int ageInDays=daysDiff(dateOfBirth);
		ArrayList<Forecast> hepBForecastList = new ArrayList<Forecast>();//list of forecast beans for vaccine Hib
		count=dBOps.getCountOfImmunizations(userId, vaccineId);
		if(vaccineName.equals("Monovalent Hep B"))
		{
			if(ageInDays==0)
			{
				if(count==0)
					admDate=dateOfBirth;
				else
					admDate=dBOps.getAdminDate(userId, vaccineId, count);
				int dMax=3;
				for(int doseIndex=count+1;doseIndex<=dMax;doseIndex++)
				{
					switch(doseIndex)
					{
						case 1:
							hepBForecastList.add(createBean(vaccineId,userId,"","HepB",doseIndex,dateOfBirth,"",getTyp(daysDiff(dateOfBirth))));
							break;
						case 2:
							forecastDate=addDays(admDate, 60);
							hepBForecastList.add(createBean(vaccineId,userId,"","HepB",doseIndex,forecastDate,"",getTyp(daysDiff(forecastDate))));
							break;
						case 3:
							forecastDate=addDays(admDate, 180);
							hepBForecastList.add(createBean(vaccineId,userId,"","HepB",3,forecastDate,"",getTyp(daysDiff(forecastDate))));
							break;
					}
				}
			}
		}
		else if(vaccineName.equals("Pediarix"))
		{
			if(ageInDays==0)
			{
				if(count==0)
					admDate=dateOfBirth;
				else
					admDate=dBOps.getAdminDate(userId, vaccineId, count);
				int dMax=3;
				for(int doseIndex=count+1;doseIndex<=dMax;doseIndex++)
				{
					switch(doseIndex)
					{
						case 1:
							hepBForecastList.add(createBean(vaccineId,userId,"","HepB",doseIndex,dateOfBirth,"",getTyp(daysDiff(dateOfBirth))));
							break;
						case 2:
							forecastDate=addDays(admDate, 60);
							hepBForecastList.add(createBean(vaccineId,userId,"","HepB",doseIndex,forecastDate,"",getTyp(daysDiff(forecastDate))));
							break;
						case 3:
							forecastDate=addDays(admDate, 180);
							hepBForecastList.add(createBean(vaccineId,userId,"","HepB",doseIndex,forecastDate,"",getTyp(daysDiff(forecastDate))));
							break;
					}
				}
			}
		}
		else if(vaccineName.equals("Comvax"))
		{
			if(ageInDays==0)
			{
				if(count==0)
					admDate=dateOfBirth;
				else
					admDate=dBOps.getAdminDate(userId, vaccineId, count);
				int dMax=3;
				for(int doseIndex=count+1;doseIndex<=dMax;doseIndex++)
				{
					switch(doseIndex)
					{
						case 1:
							hepBForecastList.add(createBean(vaccineId,userId,"","HepB",doseIndex,dateOfBirth,"",getTyp(daysDiff(dateOfBirth))));
							break;
						case 2:
							forecastDate=addDays(admDate, 60);
							hepBForecastList.add(createBean(vaccineId,userId,"","HepB",doseIndex,forecastDate,"",getTyp(daysDiff(forecastDate))));
							break;
						case 3:
							forecastDate=addDays(admDate, 180);
							hepBForecastList.add(createBean(vaccineId,userId,"","HepB",3,forecastDate,"",getTyp(daysDiff(forecastDate))));
							break;
					}
				}
			}
		}
		return hepBForecastList;
	}*/
	
	public ArrayList<Forecast> createHibForecast(int userId,int vaccineId)
	{
		//Haemophilus influenza type B vaccine is a conjugate vaccine developed for the prevention of invasive disease caused by Haemophilus influenza type b bacteria.
		SingletonDB sdb= SingletonDB.getInstance();//Get the instance of singleton class
		Connection conForForecast=sdb.createConnection();//establish connection	
		int dose=0;
		int count=0;
		Date date=null;
		Date forecastDate=null;
		DbOperations dBOps = new DbOperations();//creates an object of the operational class that contains the method to get DOB
		date=dBOps.getDOB(userId);
		int age=(daysDiff(date))/365;
		ArrayList<Forecast> hibForecastList = new ArrayList<Forecast>();//list of forecast beans for vaccine Hib
		if(dBOps.getCountOfImmunizations(userId, vaccineId)==1)//check whether only one immunization of hib has happened
		{
			Date adminDate=dBOps.getAdminDate(userId, vaccineId,1);
			int months=diffInMonths(adminDate,date);//gets age in months
			if(months>11&&months<15)//create forecast for final dose after 28 days and set status as "Final Booster Dose" as part of catch up
			{
				hibForecastList.add(createBean(vaccineId,userId,"PedvaxHIB","Hib",2,addDays(now(),28),"Final Booster Dose","Normal"));
				return hibForecastList;
			}
			else if(months>15&&dBOps.hasParameter(userId)!=true)
			{
				//create forecast for two doses after 28 days and set status for final dose as "Final Booster Dose" as part of catch up
				Date newForecastDate=addDays(adminDate,28);
				hibForecastList.add(createBean(vaccineId,userId,"PedvaxHIB","Hib",2,newForecastDate,"Recommended",getTyp(daysDiff(newForecastDate))));
				hibForecastList.add(createBean(vaccineId,userId,"PedvaxHIB","Hib",3,newForecastDate,"Final Booster Dose",getTyp(daysDiff(newForecastDate))));
				return hibForecastList;
			}
		}
		else if(count==0&&(daysDiff(date))/30>14)
		{
			//if age in months is > 14 and has never been immunized then creates a final dose for Hib and set status as "final Booster Dose"
			hibForecastList.add(createBean(vaccineId,userId,"PedvaxHIB","Hib",1,now(),"Final Booster Dose","Urgent"));
			return hibForecastList;
		}	
		
		if(age>=4&&dBOps.hasParameter(userId)==true)
		{
			//if patients is aged 5 years or more and has any parameter set to true then recommends an immediate dose of "Hib" with the reason
			forecastDate=now();
			hibForecastList.add(createBean(vaccineId,userId,"PedvaxHIB","Hib",0,forecastDate,"This is an extra dose because of your health condition","Urgent"));
		}
		final int DMAX=3;//Maximum number of doses in Hib
		PreparedStatement forecastStatement=null;
		try
		{
			String getAdministrations="Select Dose,Admin_Date,VaccineName from vaccination_history where VaccineID=? and PatientID=?";
			forecastStatement=conForForecast.prepareStatement(getAdministrations);
			forecastStatement.setInt(1,vaccineId);
			forecastStatement.setInt(2,userId);
			ResultSet mostRecentAdm=forecastStatement.executeQuery();
			boolean flag=false;
			int maxDose=0;
			while(mostRecentAdm.next())
			{
				dose=mostRecentAdm.getInt("Dose");
				if(dose>maxDose)
				{
					maxDose=dose;//sets the max dose even if the previous immunization are off order as count cannot be considered
					date=mostRecentAdm.getDate("Admin_Date");//sets the most recent administration date 					
				}
				String vccName=mostRecentAdm.getString("VaccineName");//get the vaccine name for each administration				
				if(vccName.equals("PedvaxHIB")||vccName.equals("Comvax"))//if any administration used the vaccines "PedvaxHIB" or "Comvax" then set the flag to true
					flag=true;
				else
					flag=false;
			}			
			if(maxDose!=0)//checks whether at least one administration has happened
			{
				if(age>1&&age<4)//if the condition is true then create immunizations based on previous dose
				{
					for(int newMax=maxDose+1;newMax<=3;newMax++)
					{
						if(newMax-1==1)//if only one immunization had happened then create a new immunization with current date as forecasted date and type as "Urgent"
						{
							forecastDate=now();
							hibForecastList.add(createBean(vaccineId,userId,"PedvaxHIB","Hib",newMax,forecastDate,"Please Consult your doctor soon","Urgent"));
						}
						else if(newMax-1==2)//if two immunizations have happened then create a final immunization with forecasted date as 60 days from now and status as "A dose of Hiberix (PRP-T) is recommended"
						{
							forecastDate=addDays(now(),60);
							int dateDiff=daysDiff(forecastDate);
							hibForecastList.add(createBean(vaccineId,userId,"PedvaxHIB","Hib",newMax,forecastDate,"A dose of Hiberix (PRP-T) is recommended",getTyp(dateDiff)));
						}
					}
				}
				else
				{
					for(int doseIndex=maxDose+1;doseIndex<=DMAX;doseIndex++)
					{
						switch(doseIndex)
						{	
							//each case is designed with respect to the cdc schedule
							case 1:
								forecastDate=addDays(date,60);													
								break;
								
							case 2:
								forecastDate=addDays(date,120);
								break;
							
							case 3:
								if(!flag)//creates the final vaccination forecast depending on the flag set recently
									forecastDate=addDays(date,180);
								else
									forecastDate=addDays(date,240);
								break;					
						}
						hibForecastList.add(createBean(vaccineId,userId,"PedvaxHIB","Hib",doseIndex,forecastDate,"Recommended",getTyp(daysDiff(forecastDate))));
					}
				}
			}
			else
			{
				//if the patient has never been immunized, then set the date of reference as date of birth
				dose=0;
				forecastDate=addDays(date,44);	//if patient was not immunized till now then set the 1 dose forecast as early as 6 weeks with status as "Safe but not recommended"
				hibForecastList.add(createBean(vaccineId,userId,"PedvaxHIB","Hib",1,forecastDate,"Safe But Not Recommended",getTyp(daysDiff(forecastDate))));
				for(int doseIndex=dose+1;doseIndex<=DMAX;doseIndex++)
				{
					switch(doseIndex)//creates the forecast 
					{	
						//each case is designed with respect to the cdc schedule
						case 1:	
							//Create a forecast with "Empty status"
							forecastDate=addDays(date,60);						
							break;
						
						case 2:
							forecastDate=addDays(date,120);	
							break;
						
						case 3:
							forecastDate=addDays(date,180);	
							break;					
					}
					hibForecastList.add(createBean(vaccineId,userId,"PedvaxHIB","Hib",doseIndex,forecastDate,"Recommended",getTyp(daysDiff(forecastDate))));
				}
			}				
			return hibForecastList;//return the forecasted list to the servlet
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			sdb.closeStatement(forecastStatement);
			sdb.closeConnection(conForForecast);//close the db connection
		}		
		return hibForecastList;
	}
	
	public ArrayList<Forecast> createPCV13Forecast(int userId,int vaccineId)
	{
		DbOperations dBOps = new DbOperations();
		Date dateOfBirth=dBOps.getDOB(userId);
		Date forecastDate=null;
		String status="Recommended";
		final int DMAX=4;
		ArrayList<Forecast> pCV13ForecastList = new ArrayList<Forecast>();//list of forecast beans for vaccine Hib
		int count=dBOps.getCountOfImmunizations(userId, vaccineId);
		if(count==0)//forecast a dose with "Safe but not recommended" as status
		{
			pCV13ForecastList.add(createBean(vaccineId,userId,"","PCV13",1,addDays(dateOfBirth,42),"Safe But Not Recommended",getTyp(daysDiff(addDays(dateOfBirth,42)))));				
		}
		for(int dose=count+1;dose<=DMAX;dose++)
		{
			switch(dose)//creates the forecast
			{	
				//each case is designed with respect to the cdc schedule
				case 1:					
					forecastDate=addDays(dateOfBirth,60);						
					break;
				
				case 2:
					forecastDate=addDays(dateOfBirth,120);	
					break;
					
				case 3:
					forecastDate=addDays(dateOfBirth,180);	
					break;
					
				case 4:
					forecastDate=addDays(dateOfBirth,455);	
					break;					
			}
			if(dose==4)
				status="Final Booster Dose";//Final Dose
			else
				status="Recommended";
			pCV13ForecastList.add(createBean(vaccineId,userId,"","PCV13",dose,forecastDate,status,getTyp(daysDiff(forecastDate))));					
		}
		return pCV13ForecastList;
	}
	
	public ArrayList<Forecast> createDtapForecast(int userId,int vaccineId)
	{
		DbOperations dBOps = new DbOperations();
		Date date=dBOps.getDOB(userId);
		Date adminDate=null;
		Date forecastDate=null;
		final int DMAX=5;
		int age=(daysDiff(date))/365;
		ArrayList<Forecast> dTapForecastList = new ArrayList<Forecast>();//list of forecast beans for vaccine Hib
		int count=dBOps.getCountOfImmunizations(userId, vaccineId);
		if(age>6)
			return dTapForecastList;
		if(count==4)
		{
			adminDate=dBOps.getAdminDate(userId, vaccineId, 4);
			int diff=diffInDays(adminDate, date);
			if(diff<4)
				return dTapForecastList;
		}
		if(count==0)
		{
			for(int dose=1;dose<=DMAX;dose++)
			{
				switch(dose)//creates the forecast
				{	
					//each case is designed with respect to the cdc schedule
					case 1:
						dTapForecastList.add(createBean(vaccineId,userId,"","DTap",dose,addDays(date,42),"Safe But Not Recommended",getTyp(daysDiff(addDays(date,42)))));
						forecastDate=addDays(date,60);						
						break;
					
					case 2:
						forecastDate=addDays(date,120);	
						break;
						
					case 3:
						forecastDate=addDays(date,180);	
						break;
						
					case 4:
						dTapForecastList.add(createBean(vaccineId,userId,"","DTap",dose,addDays(date,365),"Safe But Not Recommended",getTyp(daysDiff(addDays(date,365)))));
						forecastDate=addDays(date,455);	
						break;
					
					case 5:
						forecastDate=addDays(date,(365*4));	
						break;					
				}
				dTapForecastList.add(createBean(vaccineId,userId,"","DTap",dose,forecastDate,"Recommended",getTyp(daysDiff(forecastDate))));
			}
		}
		else if(count==1)//if person had a single DTap immunization then follow the cdc schedule as below
		{
			Date adminDateDose1=dBOps.getAdminDate(userId, vaccineId, 1);
			int admInWeeks=diffInWeeks(adminDateDose1,date);
			if(admInWeeks>6&&admInWeeks<8)
			{
				for(int dose=2;dose<=DMAX;dose++)
				{
					switch(dose)//creates the forecast
					{	
						//each case is designed with respect to the cdc schedule
						case 2:
							forecastDate=addDays(adminDateDose1,30);	
							break;
							
						case 3:
							forecastDate=addDays(adminDateDose1,60);	
							break;
							
						case 4:
							dTapForecastList.add(createBean(vaccineId,userId,"","DTap",dose,addDays(date,365),"Safe But Not Recommended",getTyp(daysDiff(addDays(date,365)))));
							forecastDate=addDays(adminDateDose1,240);	
							break;
						
						case 5:
							forecastDate=addDays(adminDateDose1,(420));	
							break;					
					}
					dTapForecastList.add(createBean(vaccineId,userId,"","DTap",dose,forecastDate,"Recommended",getTyp(daysDiff(forecastDate))));
				}
			}
			else
			{
				for(int dose=2;dose<=DMAX;dose++)
				{
					switch(dose)//creates the forecast
					{	
						//each case is designed with respect to the cdc schedule
						case 2:
							forecastDate=addDays(date,120);	
							break;
							
						case 3:
							forecastDate=addDays(date,180);	
							break;
							
						case 4:
							dTapForecastList.add(createBean(vaccineId,userId,"","DTap",dose,addDays(date,365),"Safe But Not Recommended",getTyp(daysDiff(addDays(date,365)))));
							forecastDate=addDays(date,455);	
							break;
						
						case 5:
							forecastDate=addDays(date,(365*4));	
							break;					
					}
					dTapForecastList.add(createBean(vaccineId,userId,"","DTap",dose,forecastDate,"Recommended",getTyp(daysDiff(forecastDate))));
				}
			}
			return dTapForecastList;
		}
		else if(count==3)
		{
			adminDate=dBOps.getAdminDate(userId, vaccineId, 3);
			forecastDate=addDays(adminDate,180);
			dTapForecastList.add(createBean(vaccineId,userId,"","DTap",4,forecastDate,"Safe But Not Recommended",getTyp(daysDiff(forecastDate))));
			forecastDate=addDays(date,365);
			dTapForecastList.add(createBean(vaccineId,userId,"","DTap",4,forecastDate,"Recommended",getTyp(daysDiff(forecastDate))));
			forecastDate=addDays(date,(4*365));
			dTapForecastList.add(createBean(vaccineId,userId,"","DTap",5,forecastDate,"Recommended",getTyp(daysDiff(forecastDate))));
			
		}
		return dTapForecastList;
	}
	
	
	public ArrayList<Forecast> createRotaVirusForecast(int userID)
	//Function that performs the Forecast for RotaVirus
	{
		DbOperations dBOps = new DbOperations();
		Date DOB=dBOps.getDOB(userID);
		int rv1Id=dBOps.getVaccineId("Rotavirus1");
		int rv5Id=dBOps.getVaccineId("Rotavirus5");
		String vaccineName=null;
		
		
		String status="Recommended";
		int vaccineID=0;
		int maxDoseVaccine;//Maximum dose of RV1 or RV5
		ArrayList<Forecast> rotaForecast=new ArrayList<Forecast>();//List for storing the forecast
		if(daysDiff(DOB)>105)
		{
			rotaForecast=null;
			return rotaForecast;
		}
		else
		{
			/**/
			//Check if the person has SCID//use general function
			boolean scid=dBOps.checkForSCID(userID);
			//boolean allergy =checkForAllergy(userID);
			//To get the last vaccination dose no with date
			int maxDoseTakenRv1=dBOps.getCountOfImmunizations(userID, rv1Id);
			int maxDoseTakenRv5=dBOps.getCountOfImmunizations(userID, rv5Id);
			Date admin_date=null;
			Date admin_dateRv1=dBOps.getAdminDate(userID, rv1Id, maxDoseTakenRv1);//last date of administration obtained from the function;
			Date admin_dateRv5=dBOps.getAdminDate(userID, rv5Id, maxDoseTakenRv1);//last date of administration obtained from the function;
			if(maxDoseTakenRv1==0&&maxDoseTakenRv1==0)
			{
				rotaForecast.add(createBean(rv1Id, userID, "", "RotaVirus-1", maxDoseTakenRv1+1, addDays(DOB, 42), "Safe But Not Recommended", getTyp(daysDiff(addDays(DOB, 42)))));
			}
			else
			{
				if(maxDoseTakenRv1!=0)
				{
					vaccineName="Rotavirus-1";
					vaccineID=rv1Id;
					admin_date = admin_dateRv1;
				}
				else if(maxDoseTakenRv5!=0)
				{
					vaccineName="Rotavirus-5";
					vaccineID=rv5Id;
					admin_date = admin_dateRv5;
				}
				else
				{
					vaccineName="Rotavirus-5";
					vaccineID=rv5Id;
					if(admin_dateRv1.after(admin_dateRv5))
					{
						admin_date = admin_dateRv1;
					}
					else
					{
						admin_date = admin_dateRv5;
					}
				}
			}
			if(vaccineID==rv1Id)
			{//TO check if it is RV-1 orRV-5
				vaccineName="Rotavirus-1";
				maxDoseVaccine=2;			
			}
			else//RV-5
			{
				vaccineName="Rotavirus-5";
				maxDoseVaccine=3;				
			}				
			while(maxDoseTakenRv1<maxDoseVaccine)
			{
				maxDoseTakenRv1=maxDoseTakenRv1+1;
				switch(maxDoseTakenRv1)
				{
					case 1 :admin_date=addDays(DOB,60);						
							break;
					case 2 :admin_date=addDays(admin_date,60);
							break;
					case 3:admin_date=addDays(admin_date,60);													
							break;					
				}
				//If the person suffers from SCID then this 
				//vaccination is not recommended display in the first immunization
				if(scid==true)
					status="Not recommended due to SCID";					
				rotaForecast.add(createBean(vaccineID, userID, "", vaccineName, maxDoseTakenRv1, admin_date, status, getTyp(daysDiff(admin_date))));				
			}
		
			return rotaForecast;
		 }

	}
	public Date calculateDate(int userId,int vaccineId,int dose,Date dobDate, Date admin_date, int doseindex)
	{
		
		DbOperations dBOps= new DbOperations();
		Date adminDate=dBOps.getAdminDate(userId, vaccineId, 1);		
		if(adminDate==null)
		{
			return null;
		}		
		else
		{
		
			int ageAtWhichDoseTaken=(diffInDays(adminDate,dobDate))/365;		
			if(doseindex==3)
			{
		
				if(ageAtWhichDoseTaken<1)
				admin_date=addDays(admin_date,28);
				
				else
				admin_date=addDays(admin_date,180);
			
		
			
			}
			else if(doseindex==4)
			{
				if(ageAtWhichDoseTaken<1)
					admin_date=addDays(admin_date,180);
				else
					admin_date=addDays(admin_date,28);
			
				return admin_date;
			
			}
		
			return admin_date;
		}
	}
	
	public ArrayList<Forecast> createTdapForecast(int userId,int vaccineId)
	{
		
		SingletonDB sdb= SingletonDB.getInstance();//Get the instance of singleton class
		Connection con=sdb.createConnection();//establish connection
		Statement stmt=null;		
		try 
		{
			stmt =((java.sql.Connection)con).createStatement();
		} 
		catch (SQLException e) 
		{
			
			e.printStackTrace();
		}
		int dose=0;
		Date date=null;
		Date dobDate=null;
		DbOperations toGetDOB = new DbOperations();//creates an object of the operational class that contains the method to get DOB
		dobDate=toGetDOB.getDOB(userId); //gets the date of birth
		int age=(daysDiff(dobDate))/365; // calculates the age of the patient
		char gender='\0';
		gender=toGetDOB.getGender(userId);		
		ArrayList<Forecast> TDapForecastList = new ArrayList<Forecast>();//list of forecast beans for vaccine DTap
		final int TMAX=4;
		try
		{
			String getQry="Select max(Dose) as dose,Admin_Date from vaccination_history where VaccineID='"+vaccineId+"'and PatientID='"+userId+"'";
			ResultSet mostRecentAdm=stmt.executeQuery(getQry);//retrieves the most recent Dtap immunization details
			if(mostRecentAdm.next()&&mostRecentAdm.getString("Admin_Date")!=null)//checks whether a recent immunization has happened
			{
					dose=mostRecentAdm.getInt("dose");//get the most recent dose for the forecast
					String dt=mostRecentAdm.getString("Admin_Date");
					System.out.println(dt);
					DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					date=format.parse(dt);//format the administration date 	
					System.out.println(date);
			}
			else
			{
				//if the patient has never been immunized, then set the date of reference as date of birth
				dose=0;
				date=toGetDOB.getDOB(userId);//invokes service method
				
				if(age>=11 && age<=18)
				{
					if(gender=='m')
					{
					date=addDays(date,11*365);
					Forecast forecastBean= new Forecast();//create forecast bean
					forecastBean=createBean(vaccineId, userId,"","TDap",1,date,"Recommended TDap every 10 years followed by Td booster",getTyp(daysDiff(date)));
					TDapForecastList.add(forecastBean);
					Forecast forecastbeansecond=new Forecast();
				    Date newdate=addDays(date,10*365);
					forecastbeansecond=createBean(vaccineId, userId, "","TDap",2, newdate, "Final Booster Dose", getTyp(daysDiff(date)));
					TDapForecastList.add(forecastbeansecond);
					
					return TDapForecastList;
					
				
					}
					else
					{
						date=addDays(date,11*365);
						Forecast forecastBean= new Forecast();//create forecast bean
						forecastBean=createBean(vaccineId, userId,"","TDap",dose++,date,"Recommended TDap every 10 years followed by Td booster",getTyp(daysDiff(date)));
						TDapForecastList.add(forecastBean);
						Forecast forecastbeansecond=new Forecast();
					    Date newdate=addDays(date,10*365);
						forecastbeansecond=createBean(vaccineId, userId, "","TDap",dose++, newdate, "Final Booster Dose", getTyp(daysDiff(date)));
						TDapForecastList.add(forecastbeansecond);
						
						return TDapForecastList;
					}
				}
			}
						
			
			if(gender=='F')
			{
				int getPregnancyId=toGetDOB.getParameterId(vaccineId,"Pregnancy");
				char checkIfpregnant=toGetDOB.checkPregnancy(userId,getPregnancyId);
				
				if(checkIfpregnant=='t')
				{
					dose=dose+1;
					date=addDays(date,30);
					Forecast forecastbean=new Forecast();
					forecastbean=createBean(vaccineId, userId, "", "TDap",dose, date, "Recommended", getTyp(daysDiff(date)));
					TDapForecastList.add(forecastbean);
				}
			}
		
			if(age>=7 && age<=10)  //checks whether patient is between 7-10 years
			{
					int maxDose=toGetDOB.getCountOfImmunizations(userId, toGetDOB.getVaccineId("DTaP"));
				  //checks if all doses of dtap have been taken
					Date dTaPAdminDate= toGetDOB.getAdminDate(userId, vaccineId, maxDose);					
					if(maxDose<5) // if all dtap doses are not taken then recommends the first tdap dose in catch-up
					{
						dose+=1;
						Forecast forecastbean=new Forecast();
						forecastbean=createBean(vaccineId, userId,"","TDap",dose,addDays(dTaPAdminDate,60),"Recommended",getTyp(daysDiff(dTaPAdminDate)));
						TDapForecastList.add(forecastbean);
						dose+=1;
						Forecast forecastbeanNew=new Forecast();
						forecastbeanNew=createBean(vaccineId, userId, "","TDap",dose, addDays(dobDate,11*365), "Final Booster Dose", getTyp(daysDiff(addDays(dobDate,11*365))));
						TDapForecastList.add(forecastbeanNew);
				    }	
				
			
			}
			
			
			Date admin_date=date; // stores the most recent admin date
			for(int doseIndex=dose+1;doseIndex<=TMAX;doseIndex++) // computes the forecast for all further doses
			{
				
				
				switch(doseIndex)//creates the forecast
				{	
					//each case is designed with respect to the cdc schedule
					case 1:					
						admin_date=addDays(date,60);						
						break;
					
					case 2:
						admin_date=addDays(admin_date,28);
						break;
					
					case 3:
							Date admin_datenew=calculateDate(userId,vaccineId,1,dobDate,admin_date,doseIndex);
							if(admin_datenew==null)
							{
								admin_date=addDays(admin_date,28);
							}
							else
							{
								admin_date=admin_datenew;
							}
						break;
					
					case 4:
						     Date admin_datenew1=calculateDate(userId,vaccineId,1,dobDate,admin_date,doseIndex);
						     if(admin_datenew1==null)
							{
									admin_date=addDays(admin_date,28);
							}
						     else
						     {
						    	 admin_date=admin_datenew1;
						     }
						break;
										
				}
				
				Forecast forecastBean= new Forecast();//create forecast bean
				forecastBean=createBean(vaccineId, userId,"","TDap",doseIndex,admin_date,"Recommended",getTyp(daysDiff(admin_date)));
				TDapForecastList.add(forecastBean);//add forecast bean to the forecasted list
			}
			
			
		
			
        }
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				stmt.close();
			} 
			catch (SQLException e) 
			{
				
				e.printStackTrace();
			}
			sdb.closeConnection(con);//close the db connection
		}
		
		return TDapForecastList;
		
	}
}