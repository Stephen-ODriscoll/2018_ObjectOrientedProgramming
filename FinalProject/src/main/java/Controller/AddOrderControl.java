package Controller;

import Model.Customer;
import Model.Order;
import Model.Product;
import Model.ProductDB;
import Utility.Connect;
import View.OrderMenu;

public class AddOrderControl {
	
	private Order order;
	private Product product;
	private int id = 0;

	public String search(String condition) {

		product = ProductDB.search(condition);

		if (product != null) {

			String text = product.getProductID() + "    " + product.getPrice() + "    " + product.getName() + "    "
					+ product.getDesc();

			if(text.length() > 28)
				return text.substring(0, 28);
			
			return text;
		}
		
		return "";
	}

	public void add(int quantity) {

			if (order == null) {
				
				id = nextID();
				order = new Order(id);
				Customer.addOrder(order);
				Connect.insertDelete("Insert Into Orders (orderID, customerID) Values (" + id + ", "
						+ Customer.getCustomerID() + ")");
			}

			order.add(product, quantity);

			Connect.insertDelete("Insert Into OrderDetails (orderID, productID, quantity) Values ("
					+ id + ", " + product.getProductID() + ", " + quantity + ")");
			OrderMenu.refreshOrders();
	}

	public int nextID() {
		
		boolean result = false;

		for (int id = 1; id < Integer.MAX_VALUE && result == false; id++) {
			
			result = true;
			
			for(int i = 0; i < Customer.numOrders(); i++)
				if (Customer.getOrder(i).getOrderID() == id) {
					
					result = false;
				}
			
			if(result == true)
				return id;
		}

		return 0;
	}
}
