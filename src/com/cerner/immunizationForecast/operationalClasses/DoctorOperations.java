package com.cerner.immunizationForecast.operationalClasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import com.cerner.immunizationForecast.beans.Doctor;
import com.cerner.immunizationForecast.beans.Patient;
import com.cerner.immunizationForecast.beans.ViewAppointment;
import com.cerner.immunizationForecast.interfaces.DoctorManager;

public class DoctorOperations implements DoctorManager {

	@Override
	public ArrayList<Doctor> getDoctorNames() {
		
		SingletonDB sdb= SingletonDB.getInstance();//create instance of the singleton class
		Connection con=sdb.createConnection();//invoke method of singleton class to establish connection with the db
		Statement stmt=null;
		ArrayList<Doctor> docNames=new ArrayList<>();
		
		try {
			stmt =((java.sql.Connection) con).createStatement();
			} 
		catch (SQLException e) 
			{
		
				e.printStackTrace();
			}
		
		String getNames="Select Fname from Doctor";
		
		try {
			ResultSet names=stmt.executeQuery(getNames);
			
			while(names.next())
			{
				Doctor doctor=new Doctor();
				doctor.setFname(names.getString("Fname"));
				docNames.add(doctor);
			}
			
			
			} 
		catch (SQLException e) 
			{
			
				e.printStackTrace();
			}
		
		finally
		{
			sdb.closeConnection(con);
		}
		
		
		
		
		return docNames;
	}

	@Override
	public int getDoctorID(String docName) {
		
		SingletonDB sdb= SingletonDB.getInstance();//create instance of the singleton class
		Connection con=sdb.createConnection();//invoke method of singleton class to establish connection with the db
		Statement stmt=null;
		int docID=0;
		
		

		try {
			stmt =((java.sql.Connection) con).createStatement();
			} 
		catch (SQLException e) 
			{
		
				e.printStackTrace();
			}
		
		String getIDs="Select DoctorID from Doctor where Fname='"+docName+"'";
		
		try {
			ResultSet ids=stmt.executeQuery(getIDs);
			
			if(ids.next())
			{
				
				docID=ids.getInt("DoctorID");
			}
			
			
			} 
		catch (SQLException e) 
			{
			
				e.printStackTrace();
			}
		
		finally
		{
			sdb.closeConnection(con);
		}
	
		
		
		
		return docID;
	}

	@Override
	public boolean insertPatientDoctorDetails(int patientID, int doctorID,String active) {
		
		SingletonDB sdb= SingletonDB.getInstance();//create instance of the singleton class
		Connection con=sdb.createConnection();//invoke method of singleton class to establish connection with the db
		PreparedStatement stmt=null;
		int rs=0;
		
		String insertQuery="Insert into patientdoctor (PatientID, DoctorID, Active) values (?,?,?)";//sql select string
		try {
			stmt=con.prepareStatement(insertQuery);
			stmt.setInt(1, patientID);
			stmt.setInt(2, doctorID);
			stmt.setString(3, active);
			rs=stmt.executeUpdate();
			
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		finally
		{
			sdb.closeConnection(con);
		}
		
		if(rs>0)
		return true;
		else
		return false;
	}
	
	public int getDoctorDetails(String doctorName) {
		
		ResultSet name=null;
		int executeCount=0;
		
		SingletonDB sdb= SingletonDB.getInstance();//create instance of the singleton class
		Connection con=sdb.createConnection();
		Statement cst=null;
		
		
		String getUserId="Select DoctorID from Doctor where Fname='"+doctorName+"'";
		
		try {
			cst =((java.sql.Connection) con).createStatement();
			} 
		catch (SQLException e) 
			{
		
				e.printStackTrace();
			}
		
		try {
			
			name=cst.executeQuery(getUserId);
			
			if(name.next())
			{
				executeCount=name.getInt("DoctorID");
			}
			
			 
			} 
		catch (SQLException e) 
			{
			
				e.printStackTrace();
			}
		
			finally 
			{
				sdb.closeConnection(con);
			}
		
			if(executeCount!=0)
			{
				return executeCount;
			
			}
		
			else
			{
				return -1;
			}
		
		
	}

	public ArrayList<ViewAppointment> getDoctorAppointments(int doctorID)
	{
			
		SingletonDB sdb= SingletonDB.getInstance();//create instance of the singleton class
		Connection con=sdb.createConnection();
		ResultSet rsAppointment=null;
		Statement stmt=null;
		ArrayList<ViewAppointment> listOfAppoinments=new ArrayList<ViewAppointment>();
		
		String getAppointmentDetails="Select PatientID,VaccineName,Dose,Date,Time from appointmenttime,patientvaccineappointment where PatientID=Patient_ID and appointmenttime.AppointmentNo=patientvaccineappointment.AppointmentNo and appointmenttime.DoctorID='"+doctorID+"'" ;
	
		try {
			stmt =((java.sql.Connection) con).createStatement();
			} 
		
		catch (SQLException e) 
		{
	
			e.printStackTrace();
		}
	
		try {
			rsAppointment=stmt.executeQuery(getAppointmentDetails);
			
			while(rsAppointment.next())
			{
				ViewAppointment newAppointment=new ViewAppointment();
				PatientHandler patientName=new PatientHandler();
				Patient patient=new Patient();
				patient=patientName.getPatientDetails(rsAppointment.getInt("PatientID"));
				newAppointment.setPatientName(patient.getFirstName());
				newAppointment.setVaccineName(rsAppointment.getString("VaccineName"));
				newAppointment.setDose(rsAppointment.getInt("Dose"));
				newAppointment.setAppointmentDate(rsAppointment.getDate("Date"));
				newAppointment.setAppointmentTime(rsAppointment.getTime("Time"));
				listOfAppoinments.add(newAppointment);
				
			}
			
			
			
		}
		
		catch (SQLException e) 
		{
		
			e.printStackTrace();
		}
	
		finally 
		{
			sdb.closeStatement(stmt);
			sdb.closeConnection(con);
		}
	
		
		if(rsAppointment!=null)
		return listOfAppoinments;
		else
		return null;
		
		
	}
	
	

}
