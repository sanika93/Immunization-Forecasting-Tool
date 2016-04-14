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

import com.cerner.immunizationForecast.operationalClasses.VaccineHandler;

/**
 * Servlet implementation class ViewAllergyHistory
 */
@WebServlet("/ViewAllergyHistory")
public class ViewAllergyHistory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewAllergyHistory() {
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
		int userId=(int)session.getAttribute("usrId");//gets the session attribute
		VaccineHandler viewAllergy = new VaccineHandler();		
		ArrayList<com.cerner.immunizationForecast.beans.View_Patient_Allergy>  patientallergyList = new ArrayList <com.cerner.immunizationForecast.beans.View_Patient_Allergy>();
		
		patientallergyList = viewAllergy.getPatientAllergy(userId);
						
		request.setAttribute("listData", patientallergyList);
		request.setAttribute("fetchedAllergy", "fetched allergy");
        //Forwarding the result acquired from the vaccination_history table 

    RequestDispatcher rd = getServletContext().getRequestDispatcher("/ViewAllergy.jsp");

   //View.jsp Shows the complete set of vaccinations that have been administered to the person

    rd.forward(request, response);


		
	}

}
