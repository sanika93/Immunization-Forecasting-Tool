package com.cerner.immunizationForecast.operationalClasses;

import java.io.IOException;
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
import com.cerner.immunizationForecast.beans.Vaccination_History;

public class TDapLogic {
	
	int flag=0;
	

	public String getTyp(int diff)
	{
		if(diff>0)
			return "Overdue";//returns type as overdue if the current date has surpassed the forecast date
		else if(diff<0)
			return "Normal";//returns type as normal if the current date does not surpass the forecast date
		else
			return "Urgent";//returns type as urgent if the current date and forecast date are same
	}

	public java.util.Date addDays(java.util.Date date,int noOfDays)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, noOfDays); // add the specified number of days to the date received 
		date = calendar.getTime();
		return date;
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
	
	public Forecast createBean(int vId,int uId,String vaccName,int dose,Date forecastDate,String status,String type)
	{
		//uses the parameters to create and set values for a forecast bean and returns it
		Forecast forecastBean= new Forecast();
		forecastBean.setVaccineID(vId);
		forecastBean.setPatientID(uId);
		forecastBean.setVaccineName(vaccName);
		forecastBean.setDose(dose);
		forecastBean.setForecast_Date(forecastDate);
		forecastBean.setStatus(status);
		forecastBean.setType(type);
		return forecastBean;
	}

	public Date calculateDate(int userId,int vaccineId,int dose,Date dobDate, Date admin_date, int doseindex)
	{
		Date adminDate=getAdminDate(userId, vaccineId, 1);
		
		if(adminDate==null)
		{
			return null;
		}
		
		else
		{
		
			int ageAtWhichDoseTaken=(daysDiffBoth(dobDate,adminDate))/365;
		
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

	
	public int daysDiff(java.util.Date date)

    {

       Date currentDate= now();//get current date and time

       int diffInDays=(int) ((currentDate.getTime()-date.getTime()) / (1000 * 60 * 60 * 24));//computes the difference in days
       System.out.println(diffInDays);
        return diffInDays;//return diffInDays;
    }
	
	public int daysDiffBoth(java.util.Date date, java.util.Date datenew)

    {

      

       int diffInDays=(int) ((datenew.getTime()-date.getTime()) / (1000 * 60 * 60 * 24));//computes the difference in days
       System.out.println(diffInDays);
        return diffInDays;//return diffInDays;
    }


	
	public ArrayList<Vaccination_History> getLastDTapDose(int userId)
	{
		SingletonDB newsdb= SingletonDB.getInstance();//Get the instance of singleton class
		Connection conn=newsdb.createConnection();//establish connection
		Statement stmtnew=null;
		int vaccineId=100;
		ResultSet mostRecentDose = null; //used to store the most recent dose of DTap
		ArrayList<Vaccination_History> vh=new ArrayList<>();
		try 
		{
			stmtnew =((java.sql.Connection)conn).createStatement();
		} 
		catch (SQLException e) 
		{
			
			e.printStackTrace();
		}
		
		
		String getLastDose="Select max(dose) as dose,Admin_Date from vaccination_history where VaccineID='"+vaccineId+"' and PatientID='"+userId+"'";
		
		try
		{
		 mostRecentDose=stmtnew.executeQuery(getLastDose);
		 while(mostRecentDose.next())
		 {
			 Vaccination_History history=new Vaccination_History();
			 history.setDose(mostRecentDose.getInt("dose"));
			 history.setAdmin_date(mostRecentDose.getDate("Admin_Date"));
			 vh.add(history);
		 }

		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		finally
		{
			
			newsdb.closeConnection(conn);//close the db connection
		}
		
		return vh; // returns arraylist consisting of dose number and admin date
		
	}
	
	public boolean getFirstDose(int userId,int vaccineId)
	{   // checks whether the first dose of dtap has been administered or not
		SingletonDB sdbnew1= SingletonDB.getInstance();//Get the instance of singleton class
		Connection conn1=sdbnew1.createConnection();//establish connection
		Statement stmtnew1=null;
		
		ResultSet firstDose = null;
		boolean status=false;
		try 
		{
			stmtnew1 =((java.sql.Connection)conn1).createStatement();
		} 
		catch (SQLException e) 
		{
			
			e.printStackTrace();
		}
		
		
		String checkFirstDose="Select * from vaccination_history where VaccineID='"+vaccineId+"' and PatientID='"+userId+"' and dose='1'";
		
		try
		{
		 firstDose=stmtnew1.executeQuery(checkFirstDose);
		 status=firstDose.next();

		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		finally
		{
			try 
			{
				stmtnew1.close();
			} 
			catch (SQLException e) 
			{
				
				e.printStackTrace();
			}
			sdbnew1.closeConnection(conn1);//close the db connection
		}

		return status;
		
		
	}
	
	public int getParameterId(int vaccineId, String paramName)
	{
		SingletonDB sdbCheck= SingletonDB.getInstance();//Get the instance of singleton class
		Connection connect=sdbCheck.createConnection();//establish connection
		PreparedStatement ps=null;
		
		int paramId=0;
		
		String getQuery="Select ParameterID from parameter where VaccineID=? and ParameterName=?";
		
		try {
			ps=connect.prepareStatement(getQuery);
			ps.setInt(1,vaccineId);
			ps.setString(2, paramName);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				paramId=rs.getInt("ParameterID");
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		{
			try 
			{
				ps.close();
			} 
			catch (SQLException e) 
			{
				
				e.printStackTrace();
			}
			sdbCheck.closeConnection(connect);//close the db connection
		}
		
		
		return paramId;
		
	}
	
	public char checkPregnancy(int userId,int paramId)
	{
		
		SingletonDB sdbCheck1= SingletonDB.getInstance();//Get the instance of singleton class
		Connection connect1=sdbCheck1.createConnection();//establish connection
		PreparedStatement ps1=null;
		
		char active='\0';
		
		String getQuery="Select Value from patient_parameter where PatientID=? and ParameterID=?";
		
		try {
			ps1=connect1.prepareStatement(getQuery);
			ps1.setInt(1,userId);
			ps1.setInt(2, paramId);
			ResultSet rs=ps1.executeQuery();
			if(rs.next())
			{
				char[] value = new char[1];
	            rs.getCharacterStream("Value").read(value);
	            active=value[0];
			}
		} catch (SQLException | IOException e) {
			
			e.printStackTrace();
		}
		
		{
			try 
			{
				ps1.close();
			} 
			catch (SQLException e) 
			{
				
				e.printStackTrace();
			}
			sdbCheck1.closeConnection(connect1);//close the db connection
		}
		
		
		return active;
	}
	
	public Date getAdminDate(int userId,int vaccineId,int dose)
	{   // returns the admin date for a particular dose
		Date adminDate=null;
		
		SingletonDB sdb= SingletonDB.getInstance();//create instance of the singleton class
		Connection con=sdb.createConnection();//invoke method of singleton class to establish connection with the db
		PreparedStatement stmt=null;		
		
		try
		{
			String dateOfBirth=null;
			
			String getQuery="Select Admin_Date from vaccination_history where VaccineID=? and PatientID=? and Dose=?";//sql select string
			stmt=con.prepareStatement(getQuery);//initialize stmt (PreparedStatement)
			stmt.setInt(1, vaccineId); //set the value for the first ? as the patient id passed to this method in sql string
			stmt.setInt(2, userId);
			stmt.setInt(3, dose);
			ResultSet rs=stmt.executeQuery();
			if(rs.next())
			{
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//set the date format
				dateOfBirth = rs.getString("Admin_Date");
				try 
				{
					adminDate = dateFormat.parse(dateOfBirth);
				} 
				catch (ParseException e) 
				{
					
					System.out.println("Error");
					e.printStackTrace();
				}
				System.out.println(adminDate);
				
			}			
		}
		catch(SQLException exc)
		{
			exc.printStackTrace();
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
			sdb.closeConnection(con);
		}
		
      return adminDate;
		
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
					DateFormat format = new SimpleDateFormat("yyyy-mm-dd");
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
					forecastBean=createBean(vaccineId, userId,"TDap",1,date,"Recommended TDap every 10 years followed by Td booster",getTyp(daysDiff(date)));
					TDapForecastList.add(forecastBean);
					Forecast forecastbeansecond=new Forecast();
				    Date newdate=addDays(date,10*365);
					forecastbeansecond=createBean(vaccineId, userId, "Td Booster",2, newdate, "Recommoneded", getTyp(daysDiff(date)));
					TDapForecastList.add(forecastbeansecond);
					
					return TDapForecastList;
					
				
					}
					else
					{
						date=addDays(date,11*365);
						Forecast forecastBean= new Forecast();//create forecast bean
						forecastBean=createBean(vaccineId, userId,"TDap",dose++,date,"Recommended TDap every 10 years followed by Td booster",getTyp(daysDiff(date)));
						TDapForecastList.add(forecastBean);
						Forecast forecastbeansecond=new Forecast();
					    Date newdate=addDays(date,10*365);
						forecastbeansecond=createBean(vaccineId, userId, "Td Booster",dose++, newdate, "Recommoneded", getTyp(daysDiff(date)));
						TDapForecastList.add(forecastbeansecond);
						
						return TDapForecastList;
					}
				}
			}
			
			
			
			if(gender=='F')
			{
				int getPregnancyId=getParameterId(vaccineId,"Pregnancy");
				char checkIfpregnant=checkPregnancy(userId,getPregnancyId);
				
				if(checkIfpregnant=='t')
				{
					dose=dose+1;
					date=addDays(date,30);
					Forecast forecastbean=new Forecast();
					forecastbean=createBean(vaccineId, userId, "TDap", dose, date, "Recommended", getTyp(daysDiff(date)));
					TDapForecastList.add(forecastbean);
				}
			}
		
			if(age>=7 && age<=10)  //checks whether patient is between 7-10 years
			{
				
				  //checks if all doses of dtap have been taken
					ArrayList<Vaccination_History> lastDtapDose=new ArrayList<>();
					lastDtapDose=getLastDTapDose(userId);
					
					if(lastDtapDose.get(0).getDose()<5) // if all dtap doses are not taken then recommends the first tdap dose in catch-up
					{
						dose+=1;
						Forecast forecastbean=new Forecast();
						forecastbean=createBean(vaccineId, userId,"TDap",dose,addDays(lastDtapDose.get(0).getAdmin_date(),60),"Recommended",getTyp(daysDiff(addDays(lastDtapDose.get(0).getAdmin_date(),60))));
						TDapForecastList.add(forecastbean);
						dose+=1;
						Forecast forecastbeanNew=new Forecast();
						forecastbeanNew=createBean(vaccineId, userId, "TDap",dose, addDays(dobDate,11*365), "TDap Booster", getTyp(daysDiff(addDays(dobDate,11*365))));
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
				forecastBean=createBean(vaccineId, userId,"TDap",doseIndex,admin_date,"Recommended",getTyp(daysDiff(admin_date)));
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
