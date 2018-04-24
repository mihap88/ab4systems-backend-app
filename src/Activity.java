
public class Activity {
	public Activity(String name, double price) {
		this.name = name;
		this.price = price;
	}
	
	private String name;
	private double price;
	
	public String getName() {
		return this.name;
	}
	
	public double getDailyPrice() {
		return this.price;
	}
}
