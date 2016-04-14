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
import com.cerner.immunizationForecast.operationalClasses.PatientHandler;

/**
 * Servlet implementation class ViewProfile
 */
@WebServlet("/ViewProfile")
public class ViewProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewProfile()
    {
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
		
		PatientHandler patientHandlerObj=new PatientHandler();
		//String size= 0 ;
				
		//creating an object of the model class
		
		//Getting the session object  
      	HttpSession htsession=request.getSession(false);
      	int userID;
      	userID=(int) htsession.getAttribute("usrId");	
		
		Patient patientBean = patientHandlerObj.getPatientDetails(userID); //Get person's details
		
		
		request.setAttribute("loadedProfile","loaded");
		request.setAttribute("Patient", patientBean);
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/ViewProfile.jsp");		
		rd.forward(request, response);
	}
	

}
