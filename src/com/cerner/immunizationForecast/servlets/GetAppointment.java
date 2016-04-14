package com.cerner.immunizationForecast.servlets;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;




import com.cerner.immunizationForecast.operationalClasses.PatientHandler;

/**
 * Servlet implementation class GetAppointment
 */
@WebServlet("/GetAppointment")
public class GetAppointment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAppointment() {
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
		HttpSession session=request.getSession(false);
		
	
		int dose=0;
		
		
	
		
		
		//gets the previously created session
		int userId=(int)session.getAttribute("usrId");
		//gets the session attribute for doctor
		int doctorID=10007;
        
		
		String date=request.getParameter("dateTimePicker");
		
		String vaccine=request.getParameter("Vaccine");
		dose=Integer.parseInt(request.getParameter("Dose"));
		
		//Converts the date obtained as string to java.util.date
		java.sql.Timestamp appointmentDateTime=	java.sql.Timestamp.valueOf(date);
		System.out.print(date);
		System.out.println(appointmentDateTime);
		
		PatientHandler addToAppointments=new PatientHandler();
		boolean success=addToAppointments.addAppointments(userId,doctorID,vaccine,dose,appointmentDateTime);
		
		
		if(success)
		{
			System.out.println("Done");
			request.setAttribute("status", "Success");
		}
		else 
		{
			request.setAttribute("error", "Error");
		}
		

        request.setAttribute("loaded", "load complete");
        request.setAttribute("addedSuccess", "added");
        
        
		
		
		
		
		
		RequestDispatcher rd=null;
		
		
			 
			 rd = getServletContext().getRequestDispatcher("/Forecast.jsp");
		
		
		
		
		//Redirects it into the same page so as to add as many administered vaccines as required to the history.
		rd.forward(request, response);
	}

	

}
