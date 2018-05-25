package Model;

import java.util.ArrayList;

public class Customer {

	private static int customerID;
	private static String name;
	private static String address;
	private static ArrayList<Order> orderList = new ArrayList<Order>();
	
	public Customer() {
		
		Customer.customerID = 0;
		Customer.name = null;
		Customer.address = null;
	}
	
	public Customer(int customerID, String name, String address) {
		
		Customer.customerID = customerID;
		Customer.name = name;
		Customer.address = address;
	}
	
	public static void addOrder(Order order) {
		
		orderList.add(order);
	}
	
	
	public static void removeOrder(int id) {
		
		orderList.remove(searchOrder(id));
	}
	
	public static Order searchOrder(int id) {
		
		for (int i = 0; i < orderList.size(); i++)
			if (orderList.get(i).getOrderID() == id)
				return orderList.get(i);
		
		return null;
	}
	
	public static int nextID() {
		
		for(int i = 0; i < Integer.MAX_VALUE && i <= orderList.size(); i++)
			if(searchOrder(i+1) == null)
				return i+1;
		
		return 0;
	}
	
	public static String getOrderDetails(int position) {
		
		String orders = "";
		
		if(orderList.size() == 0) {
			
			orders = "\tNo Orders to Display";
			return orders;
		}
		
		orders += orderList.get(position).getOrderID() + ". Order Details:\n" + orderList.get(position).toString();
		
		return orders;
	}
	
	public static int getHeight(int position) {
		
		String orders = getOrderDetails(position);
		String[] result = orders.split("\n");
		return result.length * 22;
	}
	
	public static Order getOrder(int position) {
		
		return orderList.get(position);
	}
	
	
	public static int numOrders() {
		
		return orderList.size();
	}
	
	public static int getCustomerID() {
		
		return Customer.customerID;
	}

	public static String getName() {
		
		return Customer.name;
	}
	
	
	public static String getAddress() {
		
		return Customer.address;
	}
}
