	package Utility;

import java.sql.*;

public class Connect {
	
	private static Connection connection;
	private static Connect connect;

	private Connect() {

		try {

			connection = DriverManager.getConnection("jdbc:mysql://157.190.43.7/r00146853_programming?useSSL=false",
					"R00146853", "test");
			System.out.println("Database connection established");

		} catch (Exception ex) {
			System.out.println("SQLException: " + ex.getMessage());
		}
	}
	
	public static Connect getInstance() {
		
		if(connect == null)
			connect = new Connect();
		
		return connect;
	}
	
	public static Connection getConnection() {
		
		return connection;
	}

	public static boolean insertDelete(String statement) {

		try {

			Statement insertStmt = connection.createStatement();
			int result = insertStmt.executeUpdate(statement);
			System.out.println("The Number or records inserted/deleted is: " + result);
			insertStmt.close();
			return true;

		} catch (Exception io) {
			System.out.println("error" + io);
			return false;
		}
	}

}
