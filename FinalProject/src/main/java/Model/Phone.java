package Model;

public class Phone extends Product {

	private String make;
	private String model;
	private int storage;
	
	public Phone(int productID, String make, String model, int storage, double price) {
		
		super (productID, make + " " + model, price, " " + storage + "GB");
		
		this.make = make;
		this.model = model;
		this.storage = storage;
	}
	
	public Product getProduct() {
		
		return super.get();
	}
	
	
	public String getMake() {
		
		return this.make;
	}
	
	
	public String getModel() {
		
		return this.model;
	}
	
	
	public int getStorage() {
		
		return this.storage;
	}
	
	
	public void setMake(String make) {
		
		this.make = make;
	}
	
	
	public void setModel(String model) {
		
		this.model = model;
	}
	
	
	public void setStorage(int storage) {
		
		this.storage = storage;
	}
	
	
	public String toString() {
		
		String text = (" " + this.storage + "GB");
		return super.toString() + text;
	}
}
