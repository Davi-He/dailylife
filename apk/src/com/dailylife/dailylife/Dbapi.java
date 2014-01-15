package com.dailylife.dailylife;

import java.sql.*;

public class Dbapi {
	
	static String url = "jdbc:mysql://q978304.xicp.net:12296/cookbook";
//	String url = "jdbc:mysql://172.20.191.171:3306/cookbook";
	static String userName = "cbuser";
	static String password = "cbpass";
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static Connection connect() throws Exception
	{	
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
		System.err.println(Dbapi.getErrorMessage(e));
	}
}
