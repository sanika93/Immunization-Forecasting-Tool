package com.cerner.immunizationForecast.servlets;


import java.io.IOException;
import java.sql.Connection;
//import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cerner.immunizationForecast.beans.Patient_Allergy;
import com.cerner.immunizationForecast.operationalClasses.PatientHandler;
import com.cerner.immunizationForecast.operationalClasses.SingletonDB;

/**
 * Servlet implementation class AddAllergy
 */
@WebServlet("/AddAllergy")
public class AddAllergy extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddAllergy() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession(false);//gets the previously created session
		int patientID=(int)session.getAttribute("usrId");//gets the session attribute
		
		int allergyID=0;
		//getting parameters from the InsertAllergy.jsp page
		String allergyname = request.getParameter("Select Allergy");		
		int dose = Integer.parseInt(request.getParameter("Dose"));
	    String description = request.getParameter("descrip");
	    System.out.println(description);
		
		//object of class vaccineHandler
		
		ResultSet rs = null;// for the result of execute query command
		SingletonDB temp=SingletonDB.getInstance(); //Only object of the singleton class singletonDB
		Connection conn=temp.createConnection(); //creating connection
		
	   
	   String Statement = "Select AllergyID from Allergy where AllergyName = ?";//SQL query
	   PreparedStatement ps= null;
	 
	   try {
		   
		ps = ((java.sql.Connection)conn).prepareStatement(Statement);
	   } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	   }
	   
	   
	   
	   try {
			
			
		
		    ps.setString(1, allergyname);//fetching allergy name from jsp page
		    rs = ps.executeQuery();	//execute query
		    while(rs.next())
		    {
		     allergyID = rs.getInt("allergyID");//getting the value of allergy ID from DB
		     System.out.println(allergyID);
		    }
		    Patient_Allergy pa= new Patient_Allergy();	//object of the bean patient allergy
		   
		  //setting the values of the bean object
		    pa.setAllergyID(allergyID);
		    pa.setPatientID(patientID);
		    pa.setDose(dose);
		    pa.setDescription(description);
		    
		    PatientHandler patientAllergy=new PatientHandler();
		    
		  boolean success=patientAllergy.storePatientAllergy(pa);//method store PatientAllergy on the object insertAllergy
		 if(success)
		 {
			 request.setAttribute("success", "Insertion of allergy is successful");
			 RequestDispatcher rd = getServletContext().getRequestDispatcher("/InsertAllergy.jsp");
				
				rd.forward(request, response);
		 }
		 else{
			 request.setAttribute("error", "Insertion of allergy is unsuccessful");
			 RequestDispatcher rd = getServletContext().getRequestDispatcher("/InsertAllergy.jsp");
				
				rd.forward(request, response);
			 
		 }
	         
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	  
	   
		
	}

}
