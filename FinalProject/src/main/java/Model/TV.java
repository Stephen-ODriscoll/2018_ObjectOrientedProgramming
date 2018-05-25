package Model;

public class TV extends Product{
	
	private String make;
	private int screen;
	private String type;
	private boolean threeD;
	
	public TV(int productID, String make, int screen, String type, String threeD, double price) {
		
		super(productID, make, price, screen + "inch " + type + " with 3D");
		
		this.make = make;
		this.screen = screen;
		this.type = type;
		set3D(threeD);
	}
	
	
	public String getMake() {
		
		return this.make;
	}
	
	public Product getProduct() {
		
		return super.get();
	}
	
	public int getScreen() {
		
		return this.screen;
	}
	
	
	public String getType() {
		
		return this.type;
	}
	
	public boolean get3D() {
		
		return this.threeD;
	}
	
	
	public void setMake(String make) {
		
		this.make = make;
	}
	
	
	public void setScreen(int screen) {
		
		this.screen = screen;
	}
	
	public void set3D(String threeD) {
		
		if(threeD.equals("Yes"))
			this.threeD = true;
		
		else 
			this.threeD = false;
	}
	
	
	public String toString() {
		
		String text = " " + this.screen + "inch " + this.type;
		
		if(threeD == true)
			text += " with 3D";
		
		return super.toString() + text;
	}

}
