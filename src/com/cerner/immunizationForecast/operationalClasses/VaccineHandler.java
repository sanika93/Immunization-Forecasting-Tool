package com.cerner.immunizationForecast.operationalClasses;




import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.cerner.immunizationForecast.beans.Vaccination_History;
import com.cerner.immunizationForecast.beans.View_Patient_Allergy;
import com.cerner.immunizationForecast.interfaces.VaccineManager;
import com.mysql.jdbc.Connection;

public class VaccineHandler implements VaccineManager
{
	private int updatecount=0;
	private boolean flag=true;
	private Connection con;
	private PreparedStatement ps;
	
	
	Vaccination_History vh;
	
		
	SingletonDB single=SingletonDB.getInstance();	
	public int getVaccineID(String vaccinename)
	{
		con = (Connection) single.createConnection();
		int vaccineID = 0;		
		try												
		{
			String query="Select VaccineID from Vaccine where VaccineName= ?";			
			ps = con.prepareStatement(query);	
		
			ps.setString(1,vaccinename);			//setting the parameter for the Prepared Statement
			ResultSet rs= ps.executeQuery();
			
			while(rs.next())
			{
					vaccineID = rs.getInt("VaccineID");
			}
						
			rs.close();    
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
				
		finally
		{
			single.closeStatement(ps);
			single.closeConnection(con);
		}
		
		return vaccineID;
	}
	
	public Date getPreviousDoseDate(int patientID, int vaccineID, int dose)
	{
		
		SingletonDB sdb= SingletonDB.getInstance();//create instance of the singleton class
		Connection con=(Connection) sdb.createConnection();//invoke method of singleton class to establish connection with the db
		PreparedStatement stmt=null;	
		Date dt=null;
		
		try
		{
			String previousadmin_date=null;
			
			String getQuery="Select Admin_Date from Vaccination_History where PatientID=? and VaccineID=? and Dose=?";//sql select string
			stmt=con.prepareStatement(getQuery);//initialize stmt (PreparedStatement)
			stmt.setInt(1, patientID);
			stmt.setInt(2, vaccineID);
			stmt.setInt(3, dose);
			ResultSet rs=stmt.executeQuery();
			if(rs.next())
			{
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//set the date format
				previousadmin_date = rs.getString("Admin_Date");
				try 
				{
					dt = dateFormat.parse(previousadmin_date);
				} 
				catch (ParseException e) 
				{
					// TODO Auto-generated catch block
					
					e.printStackTrace();
				}
				
				
				
			}
		
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		finally
		{
			sdb.closeStatement(stmt);
			sdb.closeConnection(con);
		}
		
		return dt;
		}

	
	public ArrayList<Vaccination_History> getLastDose(int userId,int vaccineId)
	//Function to retrieve the last dose administered to any vaccine 
	{
		SingletonDB newsdb= SingletonDB.getInstance();//Get the instance of singleton class
		Connection conn=(Connection) newsdb.createConnection();//establish connection
		PreparedStatement stmtnew=null;
		
		ResultSet mostRecentDose = null;
		ArrayList<Vaccination_History> vh=new ArrayList<>();
		String getLastDose="Select max(dose) as dose,Admin_Date from vaccination_history where VaccineID=? and PatientID=?";

		
	
		
		try
		{
			stmtnew=conn.prepareStatement(getLastDose);
			stmtnew.setInt(1,vaccineId);
			stmtnew.setInt(2,userId);
			mostRecentDose=stmtnew.executeQuery();
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
		return vh;
	}
	public boolean storeVaccinationHistory(Vaccination_History vh)
	{
		
		//If dose to be entered is not the dose just after the one that is stored in the database
		//Then ask the user to enter the most recent dose after the one that was entered
		ArrayList<Vaccination_History> listLastDose = new ArrayList<Vaccination_History>(); 
		listLastDose= getLastDose(vh.getPatientID(), vh.getVaccineID());
		int lastDose=listLastDose.get(0).getDose();
		Date lastDoseDate=null;
		if(lastDose==0)
		{
			lastDoseDate=vh.getAdmin_date();
		}
		else
		{
		lastDoseDate=listLastDose.get(0).getAdmin_date();
		}
		if(lastDose+1!=vh.getDose() || lastDoseDate.after(vh.getAdmin_date()))
			return false;
		
			
		con = (Connection) single.createConnection();
		try
		{
			String query="Insert into Vaccination_History values(?,?,?,?,?)";
			ps=con.prepareStatement(query) ;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		
		try
		{
			ps.setInt(1,vh.getDose());
			ps.setInt(2,vh.getPatientID());
			ps.setInt(3,vh.getVaccineID());
			ps.setDate(4,(java.sql.Date) vh.getAdmin_date());
			ps.setString(5,vh.getVaccineName());
			
			updatecount=ps.executeUpdate();
			System.out.println("Success");
			
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
			single.closeConnection(con);
		}
		
		if(updatecount==0)
		{
			
			flag=false;
			return flag;
		}
		
	
		return flag;
		}
	public boolean deleteVaccinationHistory(Vaccination_History vh){
		con = (Connection) single.createConnection();
		
		try
		{
			String query="Delete from Vaccination_History where patientID=? AND vaccineID=? AND dose=?";
			ps=con.prepareStatement(query) ;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		try
		{
			
			ps.setInt(1,vh.getPatientID());
			ps.setInt(2,100);
			ps.setInt(3,vh.getDose());
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
			}
			single.closeConnection(con);
		}
		
		if(updatecount==0)
		{
			
			flag=false;
			return flag;
		}
	
		return flag;
		
	

	}
	public boolean updateVaccinationHistory(Vaccination_History vh)
	
	{
		con = (Connection) single.createConnection();
		
			
		if(vh.getDose()>1)
		{
		Date getPreviousDate=getPreviousDoseDate(vh.getPatientID(), vh.getVaccineID(),vh.getDose()-1);
		 
		if(vh.getAdmin_date().before(getPreviousDate))
			return false;
		}
	
		try
		{
			String query="Update Vaccination_History set Admin_Date =? where PatientID=? and VaccineID=? and Dose=?";
			ps=con.prepareStatement(query) ;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		try
		{
			ps.setDate(1, (java.sql.Date) vh.getAdmin_date());
			ps.setInt(2,vh.getPatientID());
			ps.setInt(3,vh.getVaccineID());
			ps.setInt(4,vh.getDose());
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
			}
			single.closeConnection(con);
		}
		
		if(updatecount==0)
		{
			
			flag=false;
			return flag;
		}
	
		
		return flag;
	}
	public ArrayList<Vaccination_History> viewVaccinesAdministered(int patientID){
		//int patientID = request.getParameter("patientID");
		SingletonDB tmp=SingletonDB.getInstance();//object of the singleton class 
		Connection conn=(Connection) tmp.createConnection(); //For creating connection
		
		ResultSet rs = null;//For the history details from database 
		//Arraylist for vaccination history
		ArrayList<Vaccination_History>  vaccineHistory = new ArrayList<Vaccination_History>();
		tmp.createConnection();
		
		String selectSQL = "SELECT * from vaccination_history where patientID= ?";
		PreparedStatement preparedStatement;
		try 
		{
			
			preparedStatement = conn.prepareStatement(selectSQL);
			preparedStatement.setInt(1, patientID);
			rs = preparedStatement.executeQuery();
		} catch (SQLException e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	    
		try{
			while(rs.next())
	         {
				Vaccination_History vaccination_history= new Vaccination_History();//Object for Model class vaccination History
	               
                int vaccineID= rs.getInt("vaccineID");
       		    vaccination_history.setVaccineID(vaccineID);
                String vaccineName= rs.getString("vaccineName");
                vaccination_history.setVaccineName(vaccineName) ;
                String admin_date=rs.getString("admin_date");
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date date=null;
                try {
					date=format.parse(admin_date);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}//format the administration date 
                
                vaccination_history.setAdmin_date(date);
                int dose =rs.getInt("dose");
                vaccination_history.setDose(dose);
                vaccineHistory.add(vaccination_history);

	         }
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				conn.close();
			} catch (SQLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return vaccineHistory;
}
	
	public  ArrayList<View_Patient_Allergy> getPatientAllergy(int patientID)
	{
		SingletonDB tmp=SingletonDB.getInstance();
		Connection conn=(Connection) tmp.createConnection(); 
		DbOperations dBOps = new DbOperations();
		conn = (Connection) tmp.createConnection();
		ResultSet rs=null;
		ResultSet rs1=null;
		int vaccineID=0;
		ArrayList<View_Patient_Allergy>  patientallergyList = new ArrayList<View_Patient_Allergy>();		
		String query="Select * from Patient_Allergy where PatientID= ?";
		PreparedStatement ps= null;
		try 
		{
			   
				ps = conn.prepareStatement(query);
				ps.setInt(1, patientID);
				rs = ps.executeQuery();
	    } 
		catch (SQLException e) 
		{
				
				e.printStackTrace();
		}
		String allergyname=null; 
		try
		{			 
			while(rs.next()) 
			{	    
				int allergyid =  rs.getInt("AllergyID");				 
				String statement ="Select AllergyName,VaccineID from Allergy where AllergyID= ?";				
				try 
				{
					     ps = conn.prepareStatement(statement);
					     ps.setInt(1, allergyid);
					     rs1 = ps.executeQuery();
				 } 
				catch (SQLException e) {
						
						e.printStackTrace();
				 }   
				 if(rs1.next()) 
				 {	
					allergyname = rs1.getString("AllergyName");
					vaccineID=rs1.getInt("VaccineID");
				 } 				
				 View_Patient_Allergy vpa=new View_Patient_Allergy();
				 //using the java bean
				 
	              vpa.setAllergyName(allergyname);	
	              vpa.setVaccineName(dBOps.getVaccineName(vaccineID));
				  vpa.setDose(rs.getInt("dose"));
				  vpa.setDescription(rs.getString("description"));
				  patientallergyList.add(vpa);
			}
			return patientallergyList;
			 
		}
		
	catch(Exception e)
		{
			e.printStackTrace();
			return patientallergyList;
		}
		
	finally
		{
			single.closeConnection(conn);
			single.closeStatement(ps);
		}
	}	
}
	
	   
	   
	    
	    
	    