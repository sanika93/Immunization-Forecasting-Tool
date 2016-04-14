/* Author:Srividya Shastry*/
/* Owner : Cerner		  */
/* Team: Vikings		  */

package com.cerner.immunizationForecast.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cerner.immunizationForecast.operationalClasses.DbOperations;
import com.cerner.immunizationForecast.operationalClasses.ParameterOperations;

/**
 * Servlet implementation class AddParameter1
 */

public class BeforeAddParameter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BeforeAddParameter() {
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
		
		//Getting the session object  
		
      	HttpSession htsession=request.getSession(false);
      	int userID;
      	userID=(int) htsession.getAttribute("usrId");
		
		ArrayList<String> allParameters = new ArrayList<String>();		//Arrraylist that contains all the health conditions(including Pregnancy) that need to be known about the patient
		ArrayList<String> listToBeDisplayedBasedOnGender  = new ArrayList<String>();	//List that does not contain the parameter "Pregnancy" so that it can be displayed to a male user
		DbOperations dbOperationsobj = new DbOperations();
		ParameterOperations objOfParameterOperations = new ParameterOperations();

	
		char gender = '\0';
				
					
		
		gender = dbOperationsobj.getGender(userID);
		if(gender=='\0')
		{
			
			 response.getWriter().write("failure");
			
			 

		}
		
		
		
		else
		{
			
			
			allParameters = dbOperationsobj.getAllParameters();	
		    listToBeDisplayedBasedOnGender = objOfParameterOperations.getListBasedOnGenderForAdd(gender,allParameters);
		    htsession.setAttribute("listparameter",listToBeDisplayedBasedOnGender);
			
			response.getWriter().write("success");
			
		}
		
		response.setContentType("text/plain");
	}	
}
