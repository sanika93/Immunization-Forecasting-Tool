package com.cerner.immunizationForecast.operationalClasses;

import java.sql.*;

public class SingletonDB {
	
	private static SingletonDB singleton = new SingletonDB( );
	
	private SingletonDB(){ }
	   
	   /* Static 'instance' method */
	   public static SingletonDB getInstance( ) {
	      return singleton;
	      
	   }
	   
	   public Connection createConnection() 
	   {
		   Connection con=null;
			try 
			{				
				String sql="jdbc:mysql://localhost/vaccineforecast";
				String path="com.mysql.jdbc.Driver";				
				Class.forName(path);				
				con = DriverManager.getConnection(sql,"sanikanew","usernew");
			} 
			catch(ClassNotFoundException e) 
			{
				System.out.println("Driver not found");	
			} 
			catch (SQLException sqlex) 
			{
				System.out.println("Connection exception" + sqlex);
			} 			
			return con;
		}
	   
	   public void closeConnection(Connection con) 
	   {
			if(con != null) 
			{
				try 
				{
					con.close();
				}
				catch (SQLException e) 
				{					
					e.printStackTrace();
				}
			}
		}
	 
		public void closeStatement(Statement ps) 
		{
			if(ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			}
		}

}
