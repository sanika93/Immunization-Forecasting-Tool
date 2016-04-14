package com.cerner.immunizationForecast.operationalClasses;

import java.sql.*;

import com.cerner.immunizationForecast.beans.Login;

public class ValidateCredentials 
{
	private int uid;
	public boolean validateCred(Login usr)
	{
		SingletonDB sdb= SingletonDB.getInstance();//create instance of the singleton class
		Connection con=sdb.createConnection();//invoke method of singleton class to establish connection with the db
		PreparedStatement stmt=null;		
		try
		{			
			String getQuery="Select UserId,Password from user where Username=? and Password=? and active=?";//sql select string
			stmt=con.prepareStatement(getQuery);//initialize stmt (PreparedStatement)
			stmt.setString(1, usr.getUsername());//set the value for the first ? as the username of the bean object in sql string
			stmt.setString(2, usr.getPassword());//set the value for the second ? as the password of the bean object in sql string
			stmt.setString(3,String.valueOf('t'));//set the value for the third ? as the active status of the bean object in sql string so that only active users can login
			ResultSet rs=stmt.executeQuery();
			if(rs.next())
			{
				if(usr.getPassword().equals(rs.getString("Password")))
	        	 { 
					setUid(rs.getInt("UserID"));//set userid using the setter					
					return true;//returns true if username and password match
	        	 }
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
		return false;
	}
	public int getUid() //getter 
	{
		return uid;
	}
	public void setUid(int uid) //setter
	{
		this.uid = uid;
	}
		
}
