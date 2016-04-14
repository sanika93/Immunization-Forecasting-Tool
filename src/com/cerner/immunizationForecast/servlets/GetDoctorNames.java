package com.cerner.immunizationForecast.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cerner.immunizationForecast.beans.Doctor;
import com.cerner.immunizationForecast.operationalClasses.DoctorOperations;

/**
 * Servlet implementation class BeforeRegister
 */

public class GetDoctorNames extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetDoctorNames() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		System.out.println("entered docs");
		ArrayList<Doctor> DoctorNames=new ArrayList<>();
		
		DoctorOperations newDocOp=new DoctorOperations();
		
		DoctorNames=newDocOp.getDoctorNames();
		
		HttpSession session=request.getSession();
		session.setAttribute("DoctorNames", DoctorNames);
		
		
		response.getWriter().write("success");
		
		
	}

}
