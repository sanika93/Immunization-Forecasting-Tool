package com.cerner.immunizationForecast.operationalClasses;

import java.sql.*;
import java.util.ArrayList;

import com.cerner.immunizationForecast.beans.Patient;
import com.cerner.immunizationForecast.beans.Patient_Allergy;
import com.cerner.immunizationForecast.beans.Patient_Parameter;
import com.cerner.immunizationForecast.interfaces.PatientManager;


public class PatientHandler implements PatientManager {
	
	
	private Statement stmt;
	private Connection conn = null;
	private PreparedStatement preparedStatement = null;
	private Patient patient;
	private Patient_Parameter patientParameterbean;
	private ResultSet rs =null;
	
	SingletonDB tmp=SingletonDB.getInstance();
	
	public boolean storePatientDetails(Patient p)
	{
		int updatecount = 0;							//variable that holds the number of rows inserted
		
	    conn = tmp.createConnection();
		
	    try
	    {
	    	String query="Insert into Patient values (?,?,?,?,?,?,?,?,?,?)";
	    	preparedStatement=conn.prepareStatement(query);
	    	
			//setting values for the parameters			
			preparedStatement.setInt(1, p.getPatientID());
			preparedStatement.setString(2,p.getFirstName());
			preparedStatement.setString(3,p.getMiddleName());
			preparedStatement.setString(4,p.getLastName());
			preparedStatement.setDate(5,p.getDateOfBirth());
			preparedStatement.setString(6,String.valueOf(p.getGender()));
			preparedStatement.setString(7,p.getAddress());
			preparedStatement.setString(8,p.getContactNumber());
			preparedStatement.setString(9,p.getEmailID());
			preparedStatement.setFloat(10,p.getWeight());
			
			updatecount= preparedStatement.executeUpdate();			//execute the prepared statement
		} 
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			tmp.closeStatement(preparedStatement);
			tmp.closeConnection(conn);
		}
	    
		if(updatecount>0)
		{
			return true;
		}
		
		   return false;
	}

	@Override
	public Patient getPatientDetails(int patientID)
	{
		patient = new Patient();
		conn = tmp.createConnection();
		
		try												
		{
			String query = "Select * from Patient where PatientID  = ?";
			preparedStatement = conn.prepareStatement(query);	
		
			preparedStatement.setInt(1,patientID);			//setting the parameter for the Prepared Statement
			rs= preparedStatement.executeQuery();
			
			if(rs.next())								//Inserting data into the patientbean
			{
				//setting the model object
				patient.setPatientID(rs.getInt("patientID"));
				patient.setFirstName(rs.getString("Fname"));
				patient.setMiddleName(rs.getString("Mname"));
				patient.setLastName(rs.getString("Lname"));
				patient.setDateOfBirth(rs.getDate("DOB"));
				patient.setGender(rs.getString("Gender").charAt(0));
				patient.setAddress(rs.getString("Address"));
				patient.setContactNumber(rs.getString("ContactNO"));
				patient.setEmailID(rs.getString("EmailID"));
				patient.setWeight(rs.getFloat("weight"));
			}
		}	
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		finally
		{
			tmp.closeStatement(preparedStatement);
			tmp.closeConnection(conn);
		}
		
		return patient;
	}
	
	

	@Override
	public boolean storePatientAllergy(Patient_Allergy a) {
		
				boolean flag=false;
				
				conn=tmp.createConnection();//connection
				PreparedStatement ps=null;
				int updatecount=0;
				try
				{
					String query="Insert into Patient_Allergy values(?,?,?,?)";//sql query
					
					ps=conn.prepareStatement(query) ;
				}
				
				catch(Exception e)
				{
					e.printStackTrace();
				}
				
				try
				{
					//setting values for the Patient_Allergy
					ps.setInt(3,a.getPatientID());
					ps.setInt(4,a.getAllergyID());
					ps.setInt(1,a.getDose());
					ps.setString(2,a.getDescription());						
					
					
				updatecount=ps.executeUpdate();//execute update
					tmp.closeStatement(stmt);//closing statement
				}
				
				catch(Exception e)
				{
					e.printStackTrace();
				}
				
				finally
				{
					tmp.closeConnection(conn);//closing connection
				}
				
				if(updatecount>0)
				{
					flag=true;
					
				}
			
				
				return flag;

		
	}

	@Override
	public Patient_Allergy getPatientAllergy(int patientID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	
		public boolean storePatientParameter(Patient_Parameter p) {
			
			int updatecount = 0;							//variable that holds the number of rows inserted
			
		    conn = tmp.createConnection();
			
		    try
		    {
		    	String query="Insert into Patient_Parameter values (?,?,?)";
		    	preparedStatement=conn.prepareStatement(query);
		    
				//setting values for the parameters			
				preparedStatement.setInt(1, p.getPatientID());
				preparedStatement.setInt(2,p.getParameterID());
				preparedStatement.setString(3,String.valueOf(p.getValue()));
				
				updatecount= preparedStatement.executeUpdate();			//execute the prepared statement
			} 
			catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally
			{
				tmp.closeStatement(preparedStatement);
				tmp.closeConnection(conn);
			}
			if(updatecount>0)
			{
				return true;
			}
			
			return false;
		}
		
	

	@Override
	public ArrayList<Patient_Parameter> getPatientParameter(int patientID) {
	
		ArrayList<Patient_Parameter> patientParameterlist= new ArrayList<Patient_Parameter>();
		
		conn = tmp.createConnection();
				
		try												// prepared statement for select query
		{
			String query = "Select * from Patient_Parameter where PatientID  = ?";
			preparedStatement = conn.prepareStatement(query);	
			
			preparedStatement.setInt(1,patientID);			//setting the parameter for the Prepared Statement
			rs= preparedStatement.executeQuery();
			
			while(rs.next())								//Inserting data into the patientbean
			{
				//setting the model object
				patientParameterbean = new Patient_Parameter();
				patientParameterbean.setPatientID(rs.getInt("patientID"));
				patientParameterbean.setParameterID(rs.getInt("parameterID"));
				patientParameterbean.setValue(rs.getString("Value").charAt(0));
				patientParameterlist.add(patientParameterbean);
			}
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			tmp.closeStatement(preparedStatement);
			tmp.closeConnection(conn);
		}
		
		return patientParameterlist;
	}
	
	public boolean addAppointments(int userID, int doctorID, String vaccine,
			int dose, Timestamp appointmentDateTime) {
		
		PreparedStatement ps=null;
		int updatecount=0;
	
		// TODO Auto-generated method stub
		
			//If dose to be entered is not the dose just after the one that is stored in the database
			//Then ask the user to enter the most recent dose after the one that was entered
			
				
			conn = (Connection) tmp.createConnection();
			try
			{
				String query="Insert into Appointment (patientID,DoctorID,AppointmentTime,VaccineName,Dose) values(?,?,?,?,?)";
				ps=(PreparedStatement) conn.prepareStatement(query) ;
				ps.setInt(1,userID);
				ps.setInt(2,doctorID);
				ps.setTimestamp(3,appointmentDateTime);
				ps.setString(4,vaccine);
				
				ps.setInt(5,dose);
				
				updatecount=ps.executeUpdate();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				return false;
			}
			
			
			finally
			{
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}
				tmp.closeConnection(conn);
			}
			
			if(updatecount==0)
			{
				
				return false;
			}
			
		
			
			
		return true;
	}

}
