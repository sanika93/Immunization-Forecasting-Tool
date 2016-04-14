package com.cerner.immunizationForecast.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cerner.immunizationForecast.operationalClasses.DbOperations;

/**
 * Servlet implementation class CheckDemographics
 */

public class CheckDemographics extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckDemographics() {
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
		System.out.println("Entered demographics servlet");
		HttpSession htsession=request.getSession(false);
      	int userID;
      	userID=(int) htsession.getAttribute("usrId");
      	
      	DbOperations dpOps=new DbOperations();
      	boolean checkIfPatientIDExists=dpOps.checkIfPatientIDExists(userID);
      	
      	if(checkIfPatientIDExists==true)
      	{
      		response.getWriter().write("success");
      	}
      	else
      	{
      		response.getWriter().write("failure");
      	}
      	
      	response.setContentType("text/plain");
	}

}
