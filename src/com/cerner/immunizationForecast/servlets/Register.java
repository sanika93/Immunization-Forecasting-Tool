package com.cerner.immunizationForecast.servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cerner.immunizationForecast.beans.NewUser;
import com.cerner.immunizationForecast.operationalClasses.DoctorOperations;
import com.cerner.immunizationForecast.operationalClasses.LogDetails;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
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
		
		/*This method inserts the username and password in the database */
		
		int flag=0;

		String username=request.getParameter("user");
		String password=request.getParameter("pass");
		String repassword=request.getParameter("repass");
		int type=Integer.parseInt(request.getParameter("type"));
		
		NewUser newUser=new NewUser();
		newUser.setUsername(username);
		newUser.setPassword(password);
		newUser.setType(type);
		
		ResultSet getNames=null;
		
		/*Checks if the user has retyped correct password*/
		
		if(password.equals(repassword))
		{
			LogDetails login=new LogDetails();
			
			/*Check whether the username already exists*/
			
			getNames=login.getUsernames();
			ArrayList<NewUser> usernames=new ArrayList<>();
			try {
					while(getNames.next())
					{
						NewUser newUsername=new NewUser();
						newUsername.setUsername(getNames.getString("UserName"));
						usernames.add(newUsername);
					}
				} 
			catch (SQLException e1) 
				{
					
					e1.printStackTrace();
				}
			
			
			
			 for(int i=0;i<usernames.size();i++)
			 {
					if(username.equals(usernames.get(i).getUsername()))
					{
						
						flag=1;
						break;
					}
					else
					{
						continue;
					}
			 }
			
			if(flag==1)
			{
				request.setAttribute("usernameError","Already exists. Enter a new Username");
				RequestDispatcher rd=request.getRequestDispatcher("Register.jsp");
				rd.forward(request, response);
			}
			
			else
			
			{	    /*If username doesnt exists, inserts a new record in the database*/
			
					
					boolean result=login.storeLoginDetails(newUser);
			
					if(result==true)
					{
							
							
							request.setAttribute("regSuccess", "Registration Successful");
							HttpSession session=request.getSession();
							int userID=login.getLoginDetails(username);
							if(type==0)
							
							/* Checks whether user is a patient or a doctor*/	
							{
								String doctorName=request.getParameter("doctor");
								DoctorOperations docOps=new DoctorOperations();
								int doctorID=docOps.getDoctorDetails(doctorName);
								String active="t";
								boolean insertIntoDoc=docOps.insertPatientDoctorDetails(userID,doctorID,active);
								if(insertIntoDoc==true)
								{
									session.setAttribute("usrId", userID);
									session.setAttribute("uName", username);
									RequestDispatcher rd=request.getRequestDispatcher("home.jsp");
									rd.forward(request, response);
									
								}
							}
							
								session.setAttribute("usrId", userID);
								session.setAttribute("uName", username);
								RequestDispatcher rd=request.getRequestDispatcher("doctorHome.jsp");
								rd.forward(request, response);
							
								
					}		
		
					else
					{
						request.setAttribute("InsertionError", "Unable to register. Please tryafter sometime.");
						RequestDispatcher rd=request.getRequestDispatcher("Register.jsp");
						rd.forward(request, response);	
					}
					
					
		
			}	
		
		}
		
		else
		{
			/*Redirects the user back to register page if password is correctly matched*/
			
			request.setAttribute("RenterPassword", "Password does not match");
			RequestDispatcher rd=request.getRequestDispatcher("Register.jsp");
			rd.forward(request, response);	
			
		}

	}
}

