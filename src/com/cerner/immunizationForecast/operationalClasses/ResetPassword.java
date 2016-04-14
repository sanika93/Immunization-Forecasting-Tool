package com.cerner.immunizationForecast.operationalClasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.cerner.immunizationForecast.beans.Login;
import com.cerner.immunizationForecast.beans.Passwd;

public class ResetPassword 
{
	public boolean resetPassword(Passwd pwdBean)
	{
		Login logInBean= new Login();//create a login bean so that we can set up the bean a reuse a previously written "ValidateCredentials"
		logInBean.setUsername(pwdBean.getUserName());
		logInBean.setPassword(pwdBean.getOriginalPw());
		ValidateCredentials vCred= new ValidateCredentials();
		boolean flag=vCred.validateCred(logInBean);//check validity
		if(flag)
		{
			SingletonDB sdb= SingletonDB.getInstance();//create instance of the singleton class
			Connection con=sdb.createConnection();//invoke method of singleton class to establish connection with the db
			PreparedStatement stmt=null;		
			try
			{			
				String updateQuery="UPDATE user SET Password=? where UserID=?";//sql select string
				stmt=con.prepareStatement(updateQuery);//initialize stmt (PreparedStatement)
				stmt.setString(1,pwdBean.getNewPw());//set the value for the first ? as the new password of the bean object in sql string
				stmt.setInt(2,pwdBean.getuId());//set the value for the second ? as the userid of the bean object in sql string
				int rows=stmt.executeUpdate();
				if(rows>0)
				{
					return true;//return true if the update query affects a row
				}			
			}
			catch(SQLException exc)
			{
				return false;
			}
			finally
			{
				try 
				{
					stmt.close();
				} 
				catch (SQLException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				sdb.closeConnection(con);
			}
		}
		return false;
	}
}
