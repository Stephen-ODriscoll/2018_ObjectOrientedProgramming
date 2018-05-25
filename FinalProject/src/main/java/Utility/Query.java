package Utility;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Model.Customer;

public class Query {
	
	public static ArrayList<ArrayList<String>> query(String statement, int columns) {

		try {

			Statement stmt = Connect.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(statement);
			ArrayList<ArrayList<String>> result = new ArrayList<>();

			while (rs.next()) {

				result.add(new ArrayList<>());

				for (int i = 0; i < columns; i++)
					result.get(result.size() - 1).add(rs.getString(i + 1));
			}

			rs.close();
			stmt.close();
			return result;

		} catch (Exception io) {
			System.out.println("error" + io);
			return new ArrayList<>();
		}
	}

	public static ArrayList<String> queryCustomers(String username, String password) {

		final int columns = 4;
		
		try {

			String query = "Select * From Customers where username = ? and password = ?";
			PreparedStatement pstmt = Connect.getConnection().prepareStatement(query);
			ArrayList<String> result = new ArrayList<>();
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next())
				for (int i = 0; i < columns; i++)
					result.add(rs.getString(i + 1));

			rs.close();
			pstmt.close();
			return result;

		} catch (Exception io) {
			System.out.println("error" + io);
			return new ArrayList<>();
		}
	}

	public static String getCustomer(String username) {
		
		try {

			String query = "Select username From Customers where username = ?";
			PreparedStatement pstmt = Connect.getConnection().prepareStatement(query);
			String result = "";
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next())
					result = rs.getString(1);

			rs.close();
			pstmt.close();
			return result;

		} catch (Exception io) {
			System.out.println("error" + io);
			return "";
		}
	}
	
	public static ArrayList<ArrayList<String>> getOrders(int customerID) {
		
		final int columns = 6;
		
		try {

			String query = "Select * From OrderDetails o1 Join Orders o2 on o1.orderID = o2.orderID and customerID = ?;";
			PreparedStatement pstmt = Connect.getConnection().prepareStatement(query);
			ArrayList<ArrayList<String>> result = new ArrayList<>();
			pstmt.setString(1, "" + Customer.getCustomerID());
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				result.add(new ArrayList<>());

				for (int i = 0; i < columns; i++)
					result.get(result.size() - 1).add(rs.getString(i + 1));
			}

			rs.close();
			pstmt.close();
			return result;

		} catch (Exception io) {
			System.out.println("error" + io);
			return new ArrayList<>();
		}
	}
}
