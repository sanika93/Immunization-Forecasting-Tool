package com.cerner.immunizationForecast.beans;


public class NewUser {

	private int UserID;
	private String Username;
	private String Password;
	private char active;
	private int Type;
	
	public int getUserID()
	{
		return UserID;
	}
	
	public void setUserID(int userID)
	{
		UserID = userID;
	}

	public String getUsername() 
	{
		return Username;
	}

	public void setUsername(String username)
	{
		Username = username;
	}

	public String getPassword()
	{
		return Password;
	}

	public void setPassword(String password)
	{
		Password = password;
	}

	public int isType()
	{
		return Type;
	}

	public void setType(int type) 
	{
		Type = type;
	}

	public char isActive() 
	{
		return active;
	}

	public void setActive(char active) {
		this.active = active;
	}
	
}
