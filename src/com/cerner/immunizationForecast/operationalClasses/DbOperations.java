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
import java.util.Date;

public class DbOperations {
	
	public ArrayList<String> getAllParameters()
	{
		ArrayList<String> allParameterList = new ArrayList<String>(); 		//List that stores all the health conditions that we need to know about a patient
		SingletonDB single= SingletonDB.getInstance();//create instance of the singleton class
		Connection con=single.createConnection();//invoke method of singleton class to establish connection with the db
		Statement statement=null;
		ResultSet rs=null;
		con = single.createConnection();		
		try
		{
			statement = con.createStatement();
			String query="Select distinct ParameterName from Parameter where Active = 't'";			
			rs= statement.executeQuery(query);
			
			//adding each Parametername of the ResultSet to the arraylist
			while(rs.next())
			{
				allParameterList.add(rs.getString("parameterName"));
			}
						
			rs.close();    
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
				
		finally
		{
			single.closeStatement(statement);
			single.closeConnection(con);
		}
		return allParameterList;
	}
	
	public int getVaccineId(String vaccineName)
	{
		SingletonDB sdb= SingletonDB.getInstance();//create instance of the singleton class
		Connection con=sdb.createConnection();//invoke method of singleton class to establish connection with the db
		PreparedStatement vaccineIdStmnt=null;
		ResultSet vaccineSet=null;		
		String getParam="Select VaccineID from vaccine where VaccineName=? and Active=?";
		try
		{
			vaccineIdStmnt=con.prepareStatement(getParam);
			vaccineIdStmnt.setString(1,vaccineName);
			vaccineIdStmnt.setString(2,String.valueOf('t'));
			vaccineSet=vaccineIdStmnt.executeQuery();
			if(vaccineSet.next())
			{
				//checks whether the patient has parameters that have been set to true and sets the number of parameters retrieved
				return vaccineSet.getInt("VaccineID");
			}
		}
		catch(SQLException sqlException)
		{
			sqlException.printStackTrace();
		}
		finally
		{
			sdb.closeConnection(con);
			sdb.closeStatement(vaccineIdStmnt);
		}
		return 0;
	}
	
	/**
	 * Author: Gaurav S C
	 * @param vaccineID
	 * @return
	 */
	public String getVaccineName(int vaccineID)
	{
		SingletonDB sdb= SingletonDB.getInstance();//create instance of the singleton class
		Connection con=sdb.createConnection();//invoke method of singleton class to establish connection with the db
		PreparedStatement vaccineNameStmnt=null;
		ResultSet vaccineSet=null;		
		String getParam="Select VaccineName from vaccine where VaccineID=? and Active=?";
		try
		{
			vaccineNameStmnt=con.prepareStatement(getParam);
			vaccineNameStmnt.setInt(1,vaccineID);
			vaccineNameStmnt.setString(2,String.valueOf('t'));
			vaccineSet=vaccineNameStmnt.executeQuery();
			if(vaccineSet.next())
			{
				//checks whether the patient has parameters that have been set to true and sets the number of parameters retrieved
				return vaccineSet.getString("VaccineName");
			}
		}
		catch(SQLException sqlException)
		{
			sqlException.printStackTrace();
		}
		finally
		{
			sdb.closeConnection(con);
			sdb.closeStatement(vaccineNameStmnt);
		}
		return null;
	}
	
	public boolean checkForSCID(int userID)
    {
    	boolean status = false;
    	SingletonDB sdb= SingletonDB.getInstance();//create instance of the singleton class
    	Connection con=(Connection) sdb.createConnection();//invoke method of singleton class to establish connection with the db
    	PreparedStatement stmt=null;		
    	try
    	{
    		String getQuery="SELECT PatientID FROM Patient_Parameter where PatientID=? and ParameterID=? and value=?";//sql select string
    		stmt=con.prepareStatement(getQuery);//initialize stmt (PreparedStatement)
    		stmt.setInt(1, userID);//set the value for the first ? as the patient id passed to this method in sql string
    		stmt.setInt(2,1000);//Checking for SCID
    		stmt.setString(3,String.valueOf('t'));
    		ResultSet rs=stmt.executeQuery();
    		if(rs.next())
    		{
    			status= true;
    		}	
    		else 
    		{
    			status=false;
    		}
    	}
    	catch(SQLException exc)
    	{
    		return false;
    	}
    	finally
    	{
    		try 
    		{
    			stmt.close();
    			sdb.closeConnection(con);
    		} 
    		catch (SQLException e) 
    		{
    				// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    			
    	}
     	return status;
    }
	
	public boolean hasParameter(int patientId)
	{
		SingletonDB sdb= SingletonDB.getInstance();//create instance of the singleton class
		Connection conToGetParameter=sdb.createConnection();//invoke method of singleton class to establish connection with the db
		PreparedStatement parameterStmnt=null;
		ResultSet parameterSet=null;		
		String getParam="Select ParameterID from patient_parameter where PatientID=? and value=?";
		try
		{
			parameterStmnt=conToGetParameter.prepareStatement(getParam);
			parameterStmnt.setInt(1,patientId);
			parameterStmnt.setString(2,String.valueOf('t'));
			parameterSet=parameterStmnt.executeQuery();
			if(parameterSet.next())
			{
				//checks whether the patient has parameters that have been set to true and sets the number of parameters retrieved
				return true;
			}
		}
		catch(SQLException sqlException)
		{
			sqlException.printStackTrace();
		}
		finally
		{
			sdb.closeStatement(parameterStmnt);
			sdb.closeConnection(conToGetParameter);
		}
		return false;
	}
	
	public int getParameterID(String parameterName)
	{
		SingletonDB single= SingletonDB.getInstance();//create instance of the singleton class
		Connection con=single.createConnection();//invoke method of singleton class to establish connection with the db
		PreparedStatement preparedStatement=null;
		ResultSet rs=null;
		con = single.createConnection();		
		int parameterID=0;
				
		try												
		{
			String query="Select ParameterID from Parameter where ParameterName= ?";			
			preparedStatement = con.prepareStatement(query);	
		
			preparedStatement.setString(1,parameterName);			//setting the parameter for the Prepared Statement
			rs= preparedStatement.executeQuery();
			
			if(rs.next())
			{
				parameterID = rs.getInt("parameterID");
			}
						
			rs.close();    
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
				
		finally
		{
			single.closeStatement(preparedStatement);
			single.closeConnection(con);
		}
		return parameterID;
	}
	
	public int getCountOfImmunizations(int patientId,int vaccineId)
	{
		//returns the count of result set
		SingletonDB sdb= SingletonDB.getInstance();//create instance of the singleton class
		Connection conToGetLastDoseNumber=sdb.createConnection();//invoke method of singleton class to establish connection with the db
		PreparedStatement vaccHistoryStmt=null;
		ResultSet immList=null;	
		try
		{
			String getQuery="Select count(Dose) as count from vaccination_history where PatientID=? and VaccineID=?";//sql select string
			vaccHistoryStmt=conToGetLastDoseNumber.prepareStatement(getQuery);
			vaccHistoryStmt.setInt(1,patientId);
			vaccHistoryStmt.setInt(2,vaccineId);
			immList=vaccHistoryStmt.executeQuery();
			if(immList.next())
			{
				return immList.getInt("count");
			}			
		}
		catch(SQLException sqlException)
		{
			sqlException.printStackTrace();
		}
		finally
		{
			sdb.closeStatement(vaccHistoryStmt);
			sdb.closeConnection(conToGetLastDoseNumber);
		}	
		return 0;
	}
	
	public Date getAdminDate(int patientId,int vaccineId,int doseNumber)
	{
		SingletonDB sdb= SingletonDB.getInstance();//create instance of the singleton class
		Connection con=sdb.createConnection();//invoke method of singleton class to establish connection with the db
		PreparedStatement vaccHistoryStmt=null;
		ResultSet immList=null;	
		try
		{
			String getQuery="Select Admin_Date from vaccination_history where PatientID=? and VaccineID=? and Dose=?";//sql select string
			vaccHistoryStmt=con.prepareStatement(getQuery);
			vaccHistoryStmt.setInt(1,patientId);
			vaccHistoryStmt.setInt(2,vaccineId);
			vaccHistoryStmt.setInt(3,doseNumber);
			immList=vaccHistoryStmt.executeQuery();
			if(immList.next())
				return immList.getDate("Admin_Date");	//returns the most recent administrations dat
		}
		catch(SQLException sqlException)
		{
			sqlException.printStackTrace();
		}
		finally
		{
			sdb.closeStatement(vaccHistoryStmt);
			sdb.closeConnection(con);
		}
		return null;
	}
	
	public Date getDOB(int patientId)
	{
		SingletonDB sdb= SingletonDB.getInstance();//create instance of the singleton class
		Connection conToGetDOB=sdb.createConnection();//invoke method of singleton class to establish connection with the db
		PreparedStatement dobStmnt=null;		
		try
		{
			String dateOfBirth=null;
			Date dt=null;
			String getQuery="Select DOB from patient where PatientID=?";//sql select string
			dobStmnt=conToGetDOB.prepareStatement(getQuery);//initialize stmt (PreparedStatement)
			dobStmnt.setInt(1, patientId);//set the value for the first ? as the patient id passed to this method in sql string
			ResultSet dobResultSet=dobStmnt.executeQuery();
			if(dobResultSet.next())
			{
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//set the date format
				dateOfBirth = dobResultSet.getString("DOB");
				try 
				{
					dt = dateFormat.parse(dateOfBirth);
				} 
				catch (ParseException parseException) 
				{
					parseException.printStackTrace();
				}
				//format the date of birth retrieved from the patient table
				return dt;//return dob
			}			
		}
		catch(SQLException sqlException)
		{
			sqlException.printStackTrace();
		}
		finally
		{
			sdb.closeStatement(dobStmnt);
			sdb.closeConnection(conToGetDOB);
		}
		return null;
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
		} catch (Exception e) {
			
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
	
	public char getGender(int userId)
	{
		
		SingletonDB sdb= SingletonDB.getInstance();//create instance of the singleton class
		Connection con=sdb.createConnection();//invoke method of singleton class to establish connection with the db
		PreparedStatement stmt=null;
		
		char gender='\0';
		
		try
		{
			
			String getQuery="Select Gender from patient where PatientID=?";//sql select string
			stmt=con.prepareStatement(getQuery);//initialize stmt (PreparedStatement)
			stmt.setInt(1,userId);//set the value for the first ? as the patient id passed to this method in sql string
			ResultSet rs=stmt.executeQuery();
			if(rs.next())
			{
				char[] value = new char[1];
	            rs.getCharacterStream("Gender").read(value);
	            gender=value[0];
			}
			
		} 
		catch (Exception e) 
		{
			
			e.printStackTrace();
		}
		
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
		
		
		return gender;
	}
	
	public boolean checkIfPatientIDExists(int userID)
	{
		SingletonDB sdb= SingletonDB.getInstance();//create instance of the singleton class
		Connection con=sdb.createConnection();//invoke method of singleton class to establish connection with the db
		PreparedStatement stmt=null;
		
		
		try
		{
			
			String getQuery="Select PatientID from patient where PatientID=?";//sql select string
			stmt=con.prepareStatement(getQuery);//initialize stmt (PreparedStatement)
			stmt.setInt(1,userID);//set the value for the first ? as the patient id passed to this method in sql string
			ResultSet rs=stmt.executeQuery();
			if(rs.next())
			{
				return true;
			}
			
		} 
		catch (Exception e) 
		{
			
			e.printStackTrace();
		}
		
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
		
		
		return false;
	}

}
