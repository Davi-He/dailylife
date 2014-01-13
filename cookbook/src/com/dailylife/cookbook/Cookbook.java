package com.dailylife.cookbook;

import java.sql.*;

public class Cookbook {
	public static Connection connect() throws Exception
	{
		String url = "jdbc:mysql://q978304.xicp.net:12296/cookbook";
		String userName = "cbuser";
		String password = "cbpass";		
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		return( DriverManager.getConnection(url, userName, password));
	}
	public static String getErrorMessage(Exception e)
	{
		StringBuffer s= new StringBuffer();
		if (e instanceof SQLException)		{
			s.append("Error message: "+e.getMessage()+"\n");
			s.append("Error code: "+ ((SQLException) e).getErrorCode() + "\n");
		}
		else{
			s.append(e +"\n");
		}
		return (s.toString());
	}
	public static void printErrorMessage(Exception e)
	{
		System.err.println(Cookbook.getErrorMessage(e));
	}
}
