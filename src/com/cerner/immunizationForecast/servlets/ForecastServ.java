package com.cerner.immunizationForecast.servlets;

import java.io.IOException;
import java.util.ArrayList;

import com.cerner.immunizationForecast.beans.Forecast;
import com.cerner.immunizationForecast.operationalClasses.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class ForecastServ
 */

public class ForecastServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForecastServ() {
        super();
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
		HttpSession session=request.getSession(false);//gets the previously created session
		int userId=(int)session.getAttribute("usrId");//gets the session attribute
		int vaccineId=0;
		ArrayList<Forecast> hibForecastList= new ArrayList<Forecast>();//Creates a List of Forecast bean objects for DTap vaccine 
		ArrayList<Forecast> dTapForecastList= new ArrayList<Forecast>();
		ArrayList<Forecast> pcvForecastList= new ArrayList<Forecast>();
		ArrayList<Forecast> rotaForecastList= new ArrayList<Forecast>();
		ArrayList<Forecast> tDapForecastList= new ArrayList<Forecast>();		
		ForeCastLogic forecastOprInstance = new ForeCastLogic();//creates an instance of the operational class
		DbOperations dBOps= new DbOperations();
		if((vaccineId=dBOps.getVaccineId("DTaP"))!=0)
		{
			dTapForecastList=forecastOprInstance.createDtapForecast(userId, vaccineId);
		
			request.setAttribute("dTapForecastList", dTapForecastList);//sets the forecasted list to an object
		}
		if((vaccineId=dBOps.getVaccineId("PCV13"))!=0)
		{			
			pcvForecastList=forecastOprInstance.createPCV13Forecast(userId, vaccineId);
			request.setAttribute("pcvf", pcvForecastList);
		}
		if((vaccineId=dBOps.getVaccineId("Hib"))!=0)
		{
			hibForecastList=forecastOprInstance.createHibForecast(userId, vaccineId);//invokes the create dtap forecast
			request.setAttribute("hibForecastList", hibForecastList);
		}		
		
		if((vaccineId=dBOps.getVaccineId("TDap"))!=0)
		{			
			tDapForecastList=forecastOprInstance.createTdapForecast(userId, vaccineId);
			request.setAttribute("tdap", tDapForecastList);
		}
		
		rotaForecastList=forecastOprInstance.createRotaVirusForecast(userId);//invokes the create rotavirus forecast forecast
		request.setAttribute("rotaList", rotaForecastList);
		request.setAttribute("loaded", "true");
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/Forecast.jsp");//redirects to Forecast.jsp file
        rd.forward(request, response);//forwards the response and request(contains the forecasted list in this program) objects		
	}

}
