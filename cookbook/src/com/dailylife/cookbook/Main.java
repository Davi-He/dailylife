package com.dailylife.cookbook;

import java.sql.*;

import com.dailylife.cookbook.*;

public class Main {
	/**
	 * @param args
	 */
	/**
	 * @param args
	 */
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection conn = null;
		try {
			conn = Cookbook.connect();
			System.out.println("Connected");
			// tryQuery(conn);
			// tryUpdate(conn);
			executeQuery(conn);
		} catch (Exception e) {
			Cookbook.printErrorMessage(e);
			System.exit(1);
		} finally {
			if (conn != null) {
				try {
					conn.close();
					System.out.println("Disconnected");
				} catch (SQLException e) {
					String err = Cookbook.getErrorMessage(e);
					System.out.println(err);
				}
			}
		}

	}

	public static void tryUpdate(Connection conn) {
		try {
			Statement s = conn.createStatement();
			String sql = "UPDATE limbs SET legs = legs+1 WHERE thing = 'insect'";
			int count = s.executeUpdate(sql);
			s.close();
			System.out.println("Number of rows updated: " + count);
		} catch (Exception e) {

		}
	}

	public static void executeQuery(Connection conn) {
		try {
			
			
			//String sql = "SELECT thing,legs,arms from limbs";
			String sql = "INSERT INTO limbs (thing,legs,arms) VALUES(?,?,?)";
			//Statement s = conn.createStatement();
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1,"man");
			s.setInt(2,2);
			s.setInt(3,2);
//			s.executeQuery();
			s.execute();
		ResultSet rs = s.getResultSet();
//			int count = 0;
//			while (rs.next()) {
//				//String thing = rs.getString(1);
//				String thing = rs.getString("thing");
//				//int legs = rs.getInt(2);
//				int legs = rs.getInt("legs");
//				//int arms = rs.getInt(3);
//				int arms = rs.getInt("arms");
//				System.out.println("thing: " + thing + ",legs: " + legs
//						+ ",arms: " + arms);
//				++count;
//			}
			rs.close();
			s.close();
			//System.out.println("Number of rows returned: " + count);
		} catch (Exception e) {
			Cookbook.printErrorMessage(e);
		}
	}

	public static void tryQuery(Connection conn) {
		try {
			Statement s = conn.createStatement();
			s.execute("USE cookbook");
			s.close();
			SQLWarning w = conn.getWarnings();
			while (w != null) {
				System.err.println("SQLException: " + w.getMessage());
				System.err.println("SQLState: " + w.getSQLState());
				System.err.println("VendorCode: " + w.getErrorCode());
				w = w.getNextWarning();
			}

		} catch (SQLException e) {
			System.err.println("SQLException: " + e.getMessage());
			System.err.println("SQLState: " + e.getSQLState());
			System.err.println("VendorCode: " + e.getErrorCode());
		}
	}
}
