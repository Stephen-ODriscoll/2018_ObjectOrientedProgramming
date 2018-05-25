package Model;

public class Product {

	private String name;
	private String desc;
	private double price;
	private int productID;

	public Product(int productID, String name, double price, String desc) {

		this.name = name;
		this.price = price;
		this.productID = productID;
		this.desc = desc;
		
	}
	
	public Product get() {
		
		return this;
	}

	public String getName() {

		return this.name;
	}

	public String getDesc() {

		return this.desc;
	}

	public double getPrice() {

		return this.price;
	}

	public int getProductID() {

		return this.productID;
	}

	public void setName(String name) {

		this.name = name;
	}

	public void setDesc(String desc) {

		this.desc = desc;
	}

	public void setPrice(double price) {

		this.price = price;
	}

	public void setProductID(int productID) {

		this.productID = productID;
	}

	public String toString() {

		return (productID + getFill(ProductDB.getLongestID(), "" + productID) + name
				+ getFill(ProductDB.getLongestName(), name) + "€" + price
				+ getFill(ProductDB.getLongestPrice(), "" + price));
	}

	public String getFill(int amount, String value) {

		String spaces = "";

		int i = -4;

		i += value.length();

		for (; i < amount; i++)
			spaces += " ";

		return spaces;
	}

}
