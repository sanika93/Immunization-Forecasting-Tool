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

import com.cerner.immunizationForecast.beans.Vaccination_History;
import com.cerner.immunizationForecast.operationalClasses.ViewVaccinationHistory;

/**
 * Servlet implementation class Demo
 */
@WebServlet("/Demo")
public class Demo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Demo() {
        super();
        // TODO Auto-generated constructor stub
    }

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		
		HttpSession session=request.getSession(false);//gets the previously created session
		int userId=(int)session.getAttribute("usrId");
		int patientID=userId;
		
		
		
		
		//Get the id of the person so that history of that particular person can be displayed.
		ViewVaccinationHistory viewHistory=new ViewVaccinationHistory();
		//Create an array list of the type vaccination history
		ArrayList<Vaccination_History> vaccineHistory =new ArrayList<Vaccination_History>();
		
		
		vaccineHistory=viewHistory.viewVaccinesAdministered(patientID);

        request.setAttribute("DTap", vaccineHistory);
        request.setAttribute("loaded", "load complete");

        

    //Forwarding the result acquired from the vaccination_history table 

         RequestDispatcher rd;
         rd= getServletContext().getRequestDispatcher("/View.jsp");

        //View.jsp Shows the complete set of vaccinations that have been administered to the person

         	rd.forward(request, response);


		
		
		
		
		
		/*vaccineHistoryDTap=viewHistory.viewVaccinesAdministered(patientID,100);
		vaccineHistoryTDap=viewHistory.viewVaccinesAdministered(patientID,101);
		request.setAttribute("DTap", vaccineHistoryDTap);
		request.setAttribute("TDap", vaccineHistoryTDap);
		request.setAttribute("loaded", "load complete");
		
	    //Forwarding the result acquired from the vaccination_history table 
		 RequestDispatcher rd;
       if (statusOfView==1)
    	   rd= getServletContext().getRequestDispatcher("/View.jsp");
       else
    	   rd=getServletContext().getRequestDispatcher("/ViewImmunization.jsp");
        
       //View.jsp Shows the complete set of vaccinations that have been administered to the person
        rd.forward(request, response);*/
	
 
	}

}
