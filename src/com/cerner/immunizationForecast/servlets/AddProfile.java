/* Author:Srividya Shastry*/
/* Owner : Cerner		  */
/* Team: Vikings		  */

package com.cerner.immunizationForecast.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cerner.immunizationForecast.beans.Patient;
import com.cerner.immunizationForecast.operationalClasses.CreateBeans;
import com.cerner.immunizationForecast.operationalClasses.DateCalculations;
import com.cerner.immunizationForecast.operationalClasses.PatientHandler;

/**
 * Servlet implementation class AddProfile
 */
@WebServlet("/AddProfile")
public class AddProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProfile() {
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
		
		response.setContentType("text/html");
			
		PatientHandler patientHandlerObj=new PatientHandler();
		DateCalculations objOfDateCalculations = new DateCalculations();
		
		//creating an object of the model class
		Patient patientBean=new Patient();
				       
        //Getting the session object  
      	HttpSession htsession=request.getSession(false);
      	int userID;
      	userID=(int) htsession.getAttribute("usrId");
		
		String fname=request.getParameter("fname");
		String mname=request.getParameter("mname");
		String lname=request.getParameter("lname");
		String dob=request.getParameter("dob");
		char gender=request.getParameter("gender").charAt(0);
		String email=request.getParameter("email");
		String contactNum = request.getParameter("contactnum");
		//String numstart = request.getParameter("numstart");
		//String numend = request.getParameter("numend");
		float weight=Float.parseFloat(request.getParameter("weight"));
		String address=request.getParameter("address");		
		    
		//concatenating 3 parts of contactnumber so that it can be stored in database
		//contactNum = contactNum.concat(numstart);
		//contactNum = contactNum.concat(numend);
		
		
		//Convert util date to sql date so that it can be stored in the database
		java.sql.Date dateOfBirth = objOfDateCalculations.StringtoSql(dob);
		
		CreateBeans objOfCreateBeans = new CreateBeans();
		patientBean = objOfCreateBeans.createPatientBean(userID,fname,mname,lname,dateOfBirth,gender,address,contactNum,email,weight);
	
		/* if the person's details are successfully inserted,then redirect the user to*/
		/* the previous page with a message telling that the insertion was successful */
		
			
			if(patientHandlerObj.storePatientDetails(patientBean))					
			{  
			request.setAttribute("profileSuccess", "successfully added details");
			RequestDispatcher rd=request.getRequestDispatcher("/AddProfile.jsp");
			rd.forward(request, response);
			}
			else
			{
			request.setAttribute("error", "Could not add details succesfully");
			RequestDispatcher rd=request.getRequestDispatcher("/AddProfile.jsp");
			rd.forward(request, response);
			}
			
		
	}	
}
