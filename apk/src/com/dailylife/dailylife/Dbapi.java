package com.dailylife.dailylife;

import java.sql.*;

//import com.dailylife.cookbook.Cookbook;

public class Dbapi {

	static String url = "jdbc:mysql://q978304.xicp.net:12296/cookbook";
	// String url = "jdbc:mysql://172.20.191.171:3306/cookbook";
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

	public static Connection connect() throws Exception {
		return (DriverManager.getConnection(url, userName, password));
	}

	public static void sendMessage(Connection conn, String msg) {
		try {
			// String sql = "SELECT thing,legs,arms from limbs";
			String sql = "INSERT INTO chat (userid,message) VALUES(?,?)";
			// Statement s = conn.createStatement();
			System.out.println("->>>>>" + conn);
			PreparedStatement s = conn.prepareStatement(sql);
			s.setInt(1, 0);
			// s.setInt(2,msg);
			s.setString(2, msg);
			// s.setInt(3,2);
			// s.executeQuery();
			s.execute();
			ResultSet rs = s.getResultSet();
			// int count = 0;
			// while (rs.next()) {
			// //String thing = rs.getString(1);
			// String thing = rs.getString("thing");
			// //int legs = rs.getInt(2);
			// int legs = rs.getInt("legs");
			// //int arms = rs.getInt(3);
			// int arms = rs.getInt("arms");
			// System.out.println("thing: " + thing + ",legs: " + legs
			// + ",arms: " + arms);
			// ++count;
			// }
			rs.close();
			s.close();
			// System.out.println("Number of rows returned: " + count);
		} catch (Exception e) {
			Dbapi.printErrorMessage(e);
		}
	}

	public static void getMessage() {
		try {
			// String sql = "SELECT thing,legs,arms from limbs";
			String sql = "SELECT * from class";
			// Statement s = conn.createStatement();
			// System.out.println("-->" + connect());
			PreparedStatement s = connect().prepareStatement(sql);
			// s.setInt(1, 0);
			// s.setInt(2,msg);
			// s.setString(2, msg);
			// s.setInt(3,2);
			s.executeQuery();
			// s.execute();
			ResultSet rs = s.getResultSet();
			int count = 0;
			while (rs.next()) {
				// String thing = rs.getString(1);
				String mail = rs.getString("name");
				// int legs = rs.getInt(2);
				int id = rs.getInt("id");
				// int arms = rs.getInt(3);
				// int admin = rs.getInt("admin");
				System.out.println("mail: " + mail + ",id: " + id);
				++count;
			}
			rs.close();
			s.close();
			System.out.println("Number of rows returned: " + count);
		} catch (Exception e) {
			// Dbapi.printErrorMessage(e);
			e.printStackTrace();
		}
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
				// String thing = rs.getString(1);
				String mail = rs.getString("mail");
				// int legs = rs.getInt(2);
				int id = rs.getInt("id");
				// int arms = rs.getInt(3);
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
}
