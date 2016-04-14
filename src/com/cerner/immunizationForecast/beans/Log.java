package com.cerner.immunizationForecast.beans;

import java.util.Date;

public class Log
{
	
	private int UserID;
	private Date Timestamp;
	
	public int getUserID()
	{
		return UserID;
	}
	
	public void setUserID(int userID)
	{
		UserID = userID;
	}
	
	public Date getTimestamp() 
	{
		return Timestamp;
	}
	
	public void setTimestamp(Date timestamp)
	{
		Timestamp = timestamp;
	}
	
}
