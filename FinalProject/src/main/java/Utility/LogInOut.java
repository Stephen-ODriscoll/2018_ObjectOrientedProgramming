package Utility;

import java.util.ArrayList;

import Model.Customer;
import View.OrderMenu;

public class LogInOut {

	private static boolean logIn = false;

	public static void logIn(String username, String password) {
		
		String encrypted = Encrypt.md5Hash(password);
		ArrayList<String> result = Query.queryCustomers(username, encrypted);

		if (result != null && result.size() != 0) {

			logIn = true;
			new Customer(Integer.parseInt(result.get(0)), result.get(1), result.get(2));
			OrderMenu.refreshOrders();
		}
	}
	
	public static void logOut() {
		
		new Customer();
		logIn = false;
	}
	
	public static boolean checkExists(String username) {
		
		String result = Query.getCustomer(username);

		if (result != null)
			return true;
		
		return false;
	}

	public static boolean getLogIn() {

		return logIn;
	}
}
