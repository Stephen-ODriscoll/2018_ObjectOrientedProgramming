package Model;

import java.util.ArrayList;

public class Order {
	
	private int orderID;
	private ArrayList<OrderDetails> orderDetailsList = new ArrayList<OrderDetails>();
	
	public Order(int orderID) {
		
		this.orderID = orderID;
	}
	
	public void add(Product product, int quantity) {
		
		OrderDetails orderDetails = new OrderDetails(product, quantity);
		orderDetailsList.add(orderDetails);
	}
	
	
	public boolean remove(int position) {
		
		if (position < orderDetailsList.size()) {
			
			orderDetailsList.remove(position);
			return true;
		}
		
		return false;
	}
	
	
	public int size() {
		
		return orderDetailsList.size();
	}
	
	public int getOrderID() {
		
		return this.orderID;
	}
	
	
	public String toString() {
		
		String list = "";
		
		for (int i = 0; i < orderDetailsList.size(); i++)
			list += orderDetailsList.get(i).toString();
		
		return list;
	}
	
}
