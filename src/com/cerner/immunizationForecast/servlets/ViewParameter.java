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

import com.cerner.immunizationForecast.beans.ParameterandValue;
import com.cerner.immunizationForecast.beans.Patient_Parameter;
import com.cerner.immunizationForecast.operationalClasses.DbOperations;
import com.cerner.immunizationForecast.operationalClasses.ParameterOperations;
import com.cerner.immunizationForecast.operationalClasses.PatientHandler;

/**
 * Servlet implementation class ViewParameter
 */
@WebServlet("/ViewParameter")
public class ViewParameter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewParameter() {
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
		
		PatientHandler objOfPatientHandler = new PatientHandler();	
		DbOperations objOfDbOperations = new DbOperations();
		ParameterOperations objOfParameterOperations = new ParameterOperations();
		
		char gender ='\0';
			
		ArrayList <Patient_Parameter> patientParamList = new ArrayList<Patient_Parameter>();		
		ArrayList<String> allParameters = new ArrayList<String>();												//List that holds all the health conditions to be checked with the patient
		ArrayList<ParameterandValue> parametersAndValueforUserDisplay= new ArrayList<ParameterandValue>();		//List that holds the health condition along with the value(Yes or No) as selected by the particular patient
		
		patientParamList = objOfPatientHandler.getPatientParameter(userID);
		allParameters = objOfDbOperations.getAllParameters();
		gender = objOfDbOperations.getGender(userID);
		parametersAndValueforUserDisplay = objOfParameterOperations.getListBasedOnGenderForView(gender,allParameters,patientParamList);
                
   		request.setAttribute("list",parametersAndValueforUserDisplay);	
   		request.setAttribute("fetchedParam", "Done Fetching");
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/ViewParameter.jsp");
        rd.forward(request, response);        
		
	}

}
