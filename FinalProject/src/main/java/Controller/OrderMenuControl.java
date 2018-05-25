package Controller;

import java.util.ArrayList;

import Model.Customer;
import Model.Order;
import Model.Product;
import Model.ProductDB;
import Utility.Connect;
import Utility.Query;
import View.OrderMenu;
import javafx.scene.control.TextArea;

public class OrderMenuControl {

	private ArrayList<TextArea> selectedOrders = new ArrayList<>();
	
	public void updateOrdersList() {
		
		ArrayList<ArrayList<String>> result = Query.getOrders(Customer.getCustomerID());
		
		if(result.size() == 0)
			return;
		
		int orderID = Integer.parseInt(result.get(0).get(1));
		Order order = new Order(orderID);
		
		for (int i = 0; i < result.size(); i++) {
			
			if(orderID != Integer.parseInt(result.get(i).get(1))) {
				
				Customer.addOrder(order);
				order = new Order(Integer.parseInt(result.get(i).get(1)));
				orderID = Integer.parseInt(result.get(i).get(1));
			}
			
			Product product = ProductDB.searchByID(Integer.parseInt(result.get(i).get(2)));
			order.add(product, Integer.parseInt(result.get(i).get(3)));
		}
		
		Customer.addOrder(order);
	}
	
	public void removeOrders() {
		
		while(selectedOrders.size() > 0) {
			
			String id = "";
			
			for(int x = 0; true; x++) {
				
				if(selectedOrders.get(0).getText().charAt(x) == '.')
					break;
				
				else
					id += selectedOrders.get(0).getText().charAt(x);
			}
			
			Order order = Customer.searchOrder(Integer.parseInt(id));
			
			Connect.insertDelete("Delete from Orders Where orderID = '" + order.getOrderID()
				+ "'" + "and customerID = " + Customer.getCustomerID() + ";");
			Customer.removeOrder(Integer.parseInt(id));
			selectedOrders.remove(0);
		}
		OrderMenu.refreshOrders();
	}
	
	public void select(TextArea order) {
		
		selectedOrders.add(order);
	}
	
	public boolean deselect(TextArea orderInfo) {
		
		if(selectedOrders.contains(orderInfo)) {
			
			selectedOrders.remove(orderInfo);
			return true;
		}
		
		return false;
	}
	
	public void deselectAll() {
		
		selectedOrders = new ArrayList<>();
	}
	
	public ArrayList<String> getOrders() {

		ArrayList<String> orders = new ArrayList<String>();

		for(int i = 0; i < Customer.numOrders(); i++)
			orders.add(Customer.getOrderDetails(i));

		return orders;
	}
}
