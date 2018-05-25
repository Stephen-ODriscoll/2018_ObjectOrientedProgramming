package Controller;

import java.util.ArrayList;

import Model.Phone;
import Model.Product;
import Model.ProductDB;
import Model.TV;
import Utility.Connect;
import Utility.Query;
import View.ProductMenu;
import javafx.scene.control.TextArea;

public class ProductMenuControl {

	private ArrayList<TextArea> selectedProducts = new ArrayList<>();
	
	public ProductMenuControl() {
		
		final int columns = 5;
		ArrayList<ArrayList<String>> result = Query.query("Select * From Phones", columns);

		for (int i = 0; i < result.size(); i++) {
			Phone phone  = new Phone(Integer.parseInt(result.get(i).get(0)), result.get(i).get(1), result.get(i).get(2),
					Integer.parseInt(result.get(i).get(3)), Double.parseDouble(result.get(i).get(4)));
			ProductDB.add(phone.getProduct());
		}
		
		final int columns1 = 6;
		result = Query.query("Select * From TVs", columns1);

		for (int i = 0; i < result.size(); i++) {
			TV tv  = new TV(Integer.parseInt(result.get(i).get(0)), result.get(i).get(1),
					Integer.parseInt(result.get(i).get(2)), result.get(i).get(3), result.get(i).get(4),
					Double.parseDouble(result.get(i).get(5)));
			ProductDB.add(tv.getProduct());
		}
	}

	public void removeProducts() {
		
		while(selectedProducts.size() > 0) {
			
			String id = "";
			
			for(int x = 0; true; x++) {
				
				if(selectedProducts.get(0).getText().charAt(x) == ' ')
					break;
				
				else
					id += selectedProducts.get(0).getText().charAt(x);
			}
			
			Product product = ProductDB.searchByID(Integer.parseInt(id));
			
			Connect.insertDelete("Delete from Phones Where productID = '" + product.getProductID() + "'");
			Connect.insertDelete("Delete from TVs Where productID = '" + product.getProductID() + "'");
			Connect.insertDelete("Delete from Products Where productID = '" + product.getProductID() + "'");
			ProductDB.remove(Integer.parseInt(id));
			selectedProducts.remove(0);
		}
		ProductMenu.refreshProducts();
	}

	public void select(TextArea product) {

		selectedProducts.add(product);
	}

	public boolean deselect(TextArea productInfo) {

		if (selectedProducts.contains(productInfo)) {

			selectedProducts.remove(productInfo);
			return true;
		}

		return false;
	}
	
	public void deselectAll() {
		
		selectedProducts = new ArrayList<>();
	}

	public ArrayList<String> getProducts() {

		ArrayList<String> products = new ArrayList<String>();

		for (int i = 0; i < ProductDB.length(); i++)
			products.add(ProductDB.getProduct(i).toString());

		return products;
	}
	
}
