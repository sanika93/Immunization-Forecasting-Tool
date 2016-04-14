package com.cerner.immunizationForecast.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cerner.immunizationForecast.beans.Login;
import com.cerner.immunizationForecast.operationalClasses.ValidateCredentials;

/**
 * Servlet implementation class ValCredServ
 */
@WebServlet("/ValCredServ")
public class ValCredServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValCredServ() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String userName = request.getParameter("Uname");//extract username from the connection string
		String password = request.getParameter("Passwd");//extract password from the connection string
		Login loginBean= new Login();//create Login bean object
		loginBean.setUsername(userName);//set username for the bean object
		loginBean.setPassword(password); //set password for the bean object
		ValidateCredentials validateCredentials=new ValidateCredentials();//Create object for the operational class
		
		boolean valid= validateCredentials.validateCred(loginBean);//Pass the login bean object to the validateCred method of the operational class
		if(valid)
		{
			HttpSession session=request.getSession(); //Create a session object
			session.setAttribute("usrId",validateCredentials.getUid());//Set the userid for the session object. This will be used in ForecastServ class
			session.setAttribute("un", userName);
			request.setAttribute("uName", userName);
			
			request.getRequestDispatcher("doctorHome.jsp").forward(request, response);//on successful authentication redirect to user home page
			request.getSession().setAttribute("UId", loginBean.getUsername());
		}
		else
		{
			request.setAttribute("invalidCred", "InValid Credentials");
			request.getRequestDispatcher("index.jsp").forward(request, response);//on unsuccessful authentication return to the same login page
		}
	}

}
