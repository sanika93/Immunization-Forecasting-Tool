package com.cerner.immunizationForecast.servlets;



import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cerner.immunizationForecast.beans.Vaccination_History;
import com.cerner.immunizationForecast.operationalClasses.AddVaccinationHistory;
import com.cerner.immunizationForecast.operationalClasses.ViewVaccinationHistory;

/**
 * Servlet implementation class AddImmune
 */
@WebServlet("/AddImmune")
public class AddImmune extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddImmune() {
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
	   // int patientID=Integer.parseInt(request.getParameter("10001"));//Need to send session ID
		HttpSession session=request.getSession(false);
		String vaccineName=null;
		int dose=0;
		
		vaccineName=request.getParameter("Vaccine");
		dose=Integer.parseInt(request.getParameter("Dose"));
	
		
		
		//gets the previously created session
		int userId=(int)session.getAttribute("usrId");//gets the session attribute
		
		String admin_date=request.getParameter("DateOfAdministration");
		/*int addOrEdit=Integer.parseInt(request.getParameter("status"));*/
		String checkAddorEditorDeleteButton=request.getParameter("addImmunizations");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		//Converts the date obtained as string to java.util.date
		java.util.Date date=null;
		try {
			date= formatter.parse(admin_date);
		
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//format the administration date 
		AddVaccinationHistory addToHistory=new AddVaccinationHistory();
		boolean success=addToHistory.addVaccinesAdministered(userId,vaccineName,dose,date,checkAddorEditorDeleteButton);
		
		
		if(success)
		{
			request.setAttribute("status", "Success");
		}
		else 
		{
			request.setAttribute("error", "Error");
		}
		

        request.setAttribute("loaded", "load complete");
        request.setAttribute("addedSuccess", "added");
        
        
		
		
		
		
		
		RequestDispatcher rd=null;
		
		
			 
			 rd = getServletContext().getRequestDispatcher("/View.jsp");
		
		
		
		
		//Redirects it into the same page so as to add as many administered vaccines as required to the history.
		rd.forward(request, response);
	}

}
