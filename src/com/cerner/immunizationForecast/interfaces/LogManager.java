package com.cerner.immunizationForecast.interfaces;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.cerner.immunizationForecast.beans.Log;
import com.cerner.immunizationForecast.beans.NewUser;




public interface LogManager {
	
	public boolean storeLoginDetails(NewUser login);
	public int getLoginDetails(String username);
	public boolean storeLog(Log log);
	public ArrayList<Log> displayLog();
	public Date GetCurrentTimestamp();
	public ResultSet getUsernames();
	

}
