package com.cerner.immunizationForecast.operationalClasses;




import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.ResultSet;
//import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.cerner.immunizationForecast.beans.Log;
import com.cerner.immunizationForecast.beans.NewUser;
import com.cerner.immunizationForecast.interfaces.LogManager;

//import java.util.ArrayList;





public class LogDetails implements LogManager {
	 
	public Connection conn=null;
	public PreparedStatement st;
	public Statement cst;
	int rs=0;
	SingletonDB tmp=SingletonDB.getInstance();
	
	public boolean storeLoginDetails(NewUser login)
	{
		
		conn=tmp.createConnection();
		
		
		String insert="insert into user (UserName, Password, Type, Active) values (?,?,?,?)";
		
		try {
			st =((java.sql.Connection) conn).prepareStatement(insert);
			} 
		catch (SQLException e) 
			{
		
				e.printStackTrace();
			}
		
		try {
			 st.setString(1, login.getUsername());
			 st.setString(2, login.getPassword());
			 st.setInt(3, login.isType());
			 st.setString(4, String.valueOf('t'));
			 rs=st.executeUpdate();
			 
			} 
		catch (SQLException e) 
			{
			// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		finally 
			{
				tmp.closeConnection(conn);
			}
		
		
			if(rs>0)
			{
				return true;
			}
			else
			{
				return false;
			}
		
			
	}

	@Override
	public int getLoginDetails(String username) {
		
		ResultSet name=null;
		int userid=0;
		
		conn=tmp.createConnection();
		
		
		String getUserId="Select UserID from user where UserName='"+username+"'";
		
		try {
			cst =((java.sql.Connection) conn).createStatement();
			} 
		catch (SQLException e) 
			{
		
				e.printStackTrace();
			}
		
		try {
			
			name=cst.executeQuery(getUserId);
			
			if(name.next())
			{
			 userid=name.getInt("UserID");
			}
			
			 
			} 
		catch (SQLException e) 
			{
			
				e.printStackTrace();
			}
		
			finally 
			{
				tmp.closeConnection(conn);
			}
		
			if(userid!=0)
			{
				return userid;
			
			}
		
			else
			{
				return -1;
			}
		
		
	}

	
	public boolean storeLog(Log log) {
		
		conn=tmp.createConnection();
		
		
		String insert="insert into log (Timestamp, UserID) values (?,?)";
		
		try {
			st =((java.sql.Connection) conn).prepareStatement(insert);
			}
		catch (SQLException e)
			{
		
			 e.printStackTrace();
			}
		
		try {
			 

			
			 st.setInt(2, log.getUserID());
			 rs=st.executeUpdate();
			 
			} 
		catch (SQLException e)
			{
			
			e.printStackTrace();
			}
		
		finally 
		{
			tmp.closeConnection(conn);
		}
		
		
			if(rs>0)
			{
				return true;
			}
			else
			{
				return false;
			}
		
	}

	
	public ArrayList<Log> displayLog() {
		
		ArrayList<Log> getLogs=new ArrayList<Log>();
		
		ResultSet rs;
		
		
		conn=tmp.createConnection();
		
		
		String getUserLogs="Select * from log";
		
		try {
			
			cst =((java.sql.Connection) conn).createStatement();
			} 
		catch (SQLException e) 
		{
		
		e.printStackTrace();
		}
		
		try {
			
			 rs=cst.executeQuery(getUserLogs);
			 
			 while(rs.next())
			 {
				 Log logUsers=new Log();
				 logUsers.setTimestamp(rs.getDate("Timestamp"));
				 logUsers.setUserID(rs.getInt("UserID"));
				 getLogs.add(logUsers);
				 
			 }

			 
			} 
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finally 
		{
			tmp.closeConnection(conn);
		}
		
		return getLogs;
		
		
		
}

	
	public Date GetCurrentTimestamp() {
		
		Date date=new Date(0);
		date.getTime();
		
		return date;
	}

	@Override
	public ResultSet getUsernames() {
		
		ResultSet unames=null;
		
		
		conn=tmp.createConnection();
		
		
		String getUserName="Select UserName from user";
		
		try {
			cst =((java.sql.Connection) conn).createStatement();
			} 
		catch (SQLException e) 
			{
		
			e.printStackTrace();
			}
		
		try {
			
			unames=cst.executeQuery(getUserName);
			 
			} 
		catch (SQLException e) 
		{
			
			e.printStackTrace();
		}
		
		 return unames;
		
		
	}

	
		
		
		
		


}
