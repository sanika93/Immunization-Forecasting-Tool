package com.cerner.immunizationForecast.servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.lang.String;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import com.cerner.immunizationForecast.beans.ViewAppointment;
import com.cerner.immunizationForecast.operationalClasses.DoctorOperations;
import com.cerner.immunizationForecast.operationalClasses.ForeCastLogic;

/**
 * Servlet implementation class GetSchedule
 */

public class GetSchedule extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetSchedule() {
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
		
		
		HttpSession htsession=request.getSession(false);
      	int userID;
      	userID=(int) htsession.getAttribute("usrId");
      	
      	String checkIfNow=request.getParameter("date");
      	String date=null;
      	
        
      	ArrayList<ViewAppointment> appointmentList=new ArrayList<>();
      	ArrayList<ViewAppointment> finalAppointmentList=new ArrayList<>();
      	ArrayList<ViewAppointment> upcomingAppointmentList=new ArrayList<>();
      	
      	DoctorOperations doctorOps=new DoctorOperations();
  		appointmentList=doctorOps.getDoctorAppointments(userID);
      	
  		/*Checks the appointments for the day*/
      	if(checkIfNow!=null)
      	
      	{
      		ForeCastLogic flogic=new ForeCastLogic();
      		Date getCurrentDate=new Date();
      		getCurrentDate=flogic.now();
      		date=new SimpleDateFormat("yyyy-MM-dd").format(getCurrentDate);
      		
      		
      		
      	
      		for(int i=0;i<appointmentList.size();i++)
      		{
      			Date appointmentDate = appointmentList.get(i).getAppointmentDate();
      			String toBeComparedDate=new SimpleDateFormat("yyyy-MM-dd").format(appointmentDate);
      			if(date.equals(toBeComparedDate))
      			{
      				ViewAppointment finalAppointment=new ViewAppointment();
      				finalAppointment.setPatientName(appointmentList.get(i).getPatientName());
      				finalAppointment.setVaccineName(appointmentList.get(i).getVaccineName());
      				finalAppointment.setDose(appointmentList.get(i).getDose());
      				finalAppointment.setAppointmentDate(appointmentList.get(i).getAppointmentDate());
      				finalAppointment.setAppointmentTime(appointmentList.get(i).getAppointmentTime());
      				finalAppointmentList.add(finalAppointment);
      			}
      		
      		}
      		
      		String getDay=date.substring(8, 10);
      		int replacedDate=Integer.parseInt(getDay);
      		replacedDate+=1;
      		String convertDate=Integer.toString(replacedDate);
      		String newdate=date.replaceAll(getDay, convertDate);
      	
      		
      		for(int i=0;i<appointmentList.size();i++)
      		{
      			Date appointmentDate = appointmentList.get(i).getAppointmentDate();
      			String toBeComparedDate=new SimpleDateFormat("yyyy-MM-dd").format(appointmentDate);
      			if(newdate.equals(toBeComparedDate))
      			{
      				ViewAppointment finalUpcomingAppointment=new ViewAppointment();
      				finalUpcomingAppointment.setPatientName(appointmentList.get(i).getPatientName());
      				finalUpcomingAppointment.setVaccineName(appointmentList.get(i).getVaccineName());
      				finalUpcomingAppointment.setDose(appointmentList.get(i).getDose());
      				finalUpcomingAppointment.setAppointmentDate(appointmentList.get(i).getAppointmentDate());
      				finalUpcomingAppointment.setAppointmentTime(appointmentList.get(i).getAppointmentTime());
      				upcomingAppointmentList.add(finalUpcomingAppointment);
      			}
      		
      		}
      		
      		
      		
      		
      	
      		htsession.setAttribute("getdetailsapp", "success");
      		htsession.setAttribute("AppointmentList", finalAppointmentList);
      		htsession.setAttribute("UpcomingAppointmentList", upcomingAppointmentList);
      
      		response.getWriter().write("success");
      	}
      	
	}
	
	

}
