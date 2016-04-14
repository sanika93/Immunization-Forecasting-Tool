package com.cerner.immunizationForecast.operationalClasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.cerner.immunizationForecast.beans.Vaccination_History;


public class ViewVaccinationHistory {
	public ArrayList<Vaccination_History> viewVaccinesAdministered(int patientID){
				//int patientID = request.getParameter("patientID");
				SingletonDB tmp=SingletonDB.getInstance();//object of the singleton class 
				Connection conn=tmp.createConnection(); //For creating connection
				
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
}
