package com.cerner.immunizationForecast.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cerner.immunizationForecast.beans.Passwd;
import com.cerner.immunizationForecast.operationalClasses.ResetPassword;

/**
 * Servlet implementation class ResetPw
 */
@WebServlet("/ResetPw")
public class ResetPwServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResetPwServ() {
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
		HttpSession session=request.getSession(false);//gets the previously created session
		String userName = (String) session.getAttribute("un");//gets the session attribute
		int uID= (int)session.getAttribute("usrId");//get the userId from the session object
		Passwd pwdBean = new Passwd();//create a password bean
		pwdBean.setuId(uID);
		pwdBean.setUserName(userName);
		pwdBean.setOriginalPw(request.getParameter("pw"));
		pwdBean.setNewPw(request.getParameter("newPw"));
		pwdBean.setConfNewPw(request.getParameter("reTypPw"));
		if(!(pwdBean.getNewPw().equals(pwdBean.getConfNewPw())))//check whether retyped password and new password are same
		{
			request.setAttribute("pwdMismatch", "passwords dont match");//set error message if they are not same and redirect the user to the reset password page
			request.getRequestDispatcher("resetUserPw.jsp").forward(request, response);
		}
		ResetPassword resetPwd= new ResetPassword();//create an instance of the operational class
		boolean flag=resetPwd.resetPassword(pwdBean);//send the pwdBean to the method that resets the password
		if(flag)
		{
			request.setAttribute("pwdChanged", "pwd changed successfully");//set success message and redirect to login page so that he can login again with new password
			request.getRequestDispatcher("welcome.jsp").forward(request, response);			
		}
		else
		{
			request.setAttribute("pwdInvalid", "passwords dont match");//if the original password is wrong then set error message and redirect to reset password page again
			request.getRequestDispatcher("resetUserPw.jsp").forward(request, response);
		}
	}

}
