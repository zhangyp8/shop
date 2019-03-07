package domain;

import java.util.HashMap;
import java.util.Map;

public class Car {
	private Map<String, CarItem> carItems = new HashMap<String, CarItem>(); 
	private double totalPrice;
	public Map<String, CarItem> getCarItems() {
		return carItems;
	}
	public void setCarItems(Map<String, CarItem> carItems) {
		this.carItems = carItems;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
}
