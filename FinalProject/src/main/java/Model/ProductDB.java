package Model;

import java.util.ArrayList;

public class ProductDB {
	
	private static ArrayList<Product> productList = new ArrayList<Product>();
	private static int longestName = 0;
	private static int longestPrice = 0;
	private static int longestProductID = 0;
	private static int longestProduct = 0;
	
	public static void add(Product product) {
		
		productList.add(product);
		
		if(product.getName().length() > longestName)
			longestName = product.getName().length();
		
		if(("" + product.getProductID()).length() > longestProductID)
			longestProductID = ("" + product.getProductID()).length();
		
		if(("" + product.getPrice()).length() > longestPrice)
			longestPrice = ("" + product.getPrice()).length();
		
		if(longestName + longestPrice + longestProductID + 
				product.getDesc().length() > longestProduct)
			longestProduct = longestName + longestPrice + 
			longestProductID + product.getDesc().length();
	}
	
	public static boolean remove(int id) {
		
		for(int i = 0; i < productList.size(); i++) {
			
			if (productList.get(i).getProductID() == id) {
				
				productList.remove(i);
				return true;
			}
		}
		
		return false;
	}
	
	public static Product search(String text) {
		
		Product product = null;
		
		try {
			
			product = searchByID(Integer.parseInt(text));
			
		} catch(Exception e) {
			
			text = text.toLowerCase();
			
			for (int i = 0; i < productList.size(); i++) {
				
				if (productList.get(i).getName().toLowerCase().contains(text))
					return productList.get(i);
			}
		}
		
		return product;
	}
	
	public static Product searchByName(String name) {

		for (int i = 0; i < productList.size(); i++) {
			
			if (productList.get(i).getName().equals(name))
				return productList.get(i);
		}
		
		return null;
	}
	
	public static Product searchByID(int id) {
		
		for (int i = 0; i < productList.size(); i++)
			if (productList.get(i).getProductID() == id)
				return productList.get(i);
		
		return null;
	}
	
	public static int nextID() {
		
		for(int i = 0; i < Integer.MAX_VALUE && i <= productList.size(); i++)
			if(searchByID(i+1) == null)
				return i+1;
		
		return 0;
	}
	
	public static int getLongestProduct() {
		
		return longestProduct + 12;
	}
	
	public static Product getProduct(int position) {
		
		return productList.get(position);
	}
	
	public static int getLongestName() {
		
		return longestName;
	}
	
	public static int getLongestPrice() {
		
		return longestPrice;
	}
	
	public static int getLongestID() {
		
		return longestProductID;
	}
	
	
	public static int length() {
		
		return productList.size();
	}
	
	public static String display() {
		
		String list = "";
		
		for (int i = 0; i < productList.size(); i++)
			list += "\t" + productList.get(i).getProductID()  + ". " + productList.get(i).toString() + "\n";
		
		return list;
	}
}
