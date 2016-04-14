/* Author:Srividya Shastry*/
/* Owner : Cerner		  */
/* Team: Vikings		  */

package com.cerner.immunizationForecast.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cerner.immunizationForecast.operationalClasses.DbOperations;
import com.cerner.immunizationForecast.operationalClasses.ParameterOperations;

/**
 * Servlet implementation class AfterAddParameter
 */
@WebServlet("/AfterAddParameter")
public class AfterAddParameter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AfterAddParameter() {
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
		
		//Getting the session object  
      	HttpSession htsession=request.getSession(false);
      	int userID;
      	userID=(int) htsession.getAttribute("usrId");
	
		int result = 0;
		char gender='\0';
		RequestDispatcher rd = null;
		
		ArrayList<String> allParameters = new ArrayList<String>();		//List that holds all the health conditions to be checked with a patient
		
		ArrayList<String> listToBeDisplayedBasedOnGender = new ArrayList<String>();  

		
		DbOperations dbOperationsobj = new DbOperations();
		ParameterOperations objOfParameterOperations = new ParameterOperations();
		
		allParameters = dbOperationsobj.getAllParameters();
		gender = dbOperationsobj.getGender(userID);
			
		String selectedParameters[]=request.getParameterValues("Parameter");	//Array that holds the values of the selected checkboxes
      	
		if(selectedParameters == null)

        {

                listToBeDisplayedBasedOnGender = objOfParameterOperations.getListBasedOnGenderForAdd(gender,allParameters);

                result = objOfParameterOperations.storeAllParametersFalse(listToBeDisplayedBasedOnGender,userID);

        }

        else

        		result = objOfParameterOperations.storeResultBasedOnGender(userID, gender, allParameters, selectedParameters);


    	    	
    	if(gender == 'f' && result == allParameters.size())
        {
        	request.setAttribute("parameter", "Success");
            rd = request.getRequestDispatcher("AddParameter.jsp");
            rd.forward(request, response);	
            
        }
    	else if(gender == 'm' && ((result+1)==allParameters.size()))
    	{
    		request.setAttribute("parameter", "Success");
            rd = request.getRequestDispatcher("AddParameter.jsp");
            rd.forward(request, response);	
            
    	}
    	
 	}		

}
