package Model;

public class OrderDetails {
	
	private Product product;
	private int quantity;
	
	public OrderDetails(Product product, int quantity) {
		
		this.product = product;
		this.quantity = quantity;
	}
	
	public String toString() {
			
			return "\tx" + quantity + " " + product.toString() + "\n";
	}
	
}
