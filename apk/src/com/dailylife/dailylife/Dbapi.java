package com.dailylife.dailylife;

import java.sql.*;
import java.text.SimpleDateFormat;

import android.R.integer;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;

public class Dbapi {
	static int LocationType = 0;

	static String url = "jdbc:mysql://q978304.xicp.net:12296/dailylife_0";
	static String userName = "dailylife.0";
	static String password = "dailylife.pass";
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

	public static Connection connect() throws Exception {
		return (DriverManager.getConnection(url, userName, password));
	}

	public static void sendMessage(String msg, Context context) {
		Location mLocation = getLocation(context);
		try {
			String sql = "INSERT INTO chat (userid,sendtime,message,where_lon,where_lat,where_type) VALUES(?,?,?,?,?,?)";
			System.out.println("sendMessage:" + msg);
			java.util.Date date = new java.util.Date();
			Timestamp tt = new Timestamp(date.getTime());

			// Statement s = conn.createStatement();
			PreparedStatement s = connect().prepareStatement(sql);
			s.setInt(1, 0);
			s.setTimestamp(2, tt);
			s.setString(3, msg);
			s.setDouble(4, mLocation.getLongitude());
			s.setDouble(5, mLocation.getLatitude());
			s.setInt(6, LocationType);
			s.execute();
			//ResultSet rs = s.getResultSet();
			//rs.close();
			s.close();
		} catch (Exception e) {
			// Dbapi.printErrorMessage(e);
			e.printStackTrace();
		}
	}

	public static String getMessage() {
		try {
			String sql = "SELECT * from chat";
			PreparedStatement s = connect().prepareStatement(sql);
			s.executeQuery();
			// s.execute();
			ResultSet rs = s.getResultSet();
			int count = 0;
			while (rs.next()) {
				String message = rs.getString("message");
				int userid = rs.getInt("userid");
				String sendtime = rs.getString("sendtime");
				System.out.println("message: " + message + ",userid: " + userid
						+ ",sendtime" + sendtime);
				++count;
			}
			rs.close();
			s.close();
			System.out.println("Number of rows returned: " + count);
		} catch (Exception e) {
			// Dbapi.printErrorMessage(e);
			e.printStackTrace();
		}
		return null;
	}

	public static ResultSet getres(Connection conn) throws SQLException {
		Statement stmt;
		ResultSet rs;
		stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
				ResultSet.CONCUR_UPDATABLE);
		try {
			rs = stmt.executeQuery("select * from users");
			int count = 0;
			while (rs.next()) {
				String mail = rs.getString("mail");
				int id = rs.getInt("id");
				int admin = rs.getInt("admin");
				System.out.println("mail: " + mail + ",id: " + id + ",admin: "
						+ admin);
				++count;
			}
			rs.close();
			stmt.close();
			return rs;
		} catch (Exception e) {
			rs = null;
			System.out.println("Êý¾Ý¿â²éÑ¯Ê§°Ü");
		}
		return rs;
	}

	public static String getErrorMessage(Exception e) {
		StringBuffer s = new StringBuffer();
		if (e instanceof SQLException) {
			s.append("Error message: " + e.getMessage() + "\n");
			s.append("Error code: " + ((SQLException) e).getErrorCode() + "\n");
		} else {
			s.append(e + "\n");
		}
		return (s.toString());
	}

	public static void printErrorMessage(Exception e) {
		System.err.println(Dbapi.getErrorMessage(e));
	}

	// Get the Location by GPS or WIFI
	public static Location getLocation(Context context) {
		LocationManager locMan = (LocationManager) context
				.getSystemService(Context.LOCATION_SERVICE);
		Location location = locMan
				.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		LocationType =0;
		if (location == null) {
			LocationType = 1;
			location = locMan
					.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		}
		return location;
	}

}
