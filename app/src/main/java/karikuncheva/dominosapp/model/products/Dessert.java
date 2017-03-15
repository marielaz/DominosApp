package karikuncheva.dominosapp.model.products;

import karikuncheva.dominosapp.model.products.Product;

public class Dessert extends Product {

	private String description;
	private int imageId;
	
	public Dessert(String name, double price, String description, int imageId) {
		super(ProductType.DESSERT, name, price);
		this.description = description;
		this.imageId = imageId;
	}

	public String getDescription() {
		return description;
	}

	public int getImageId() {
		return imageId;
	}

	@Override
	public String toString() {
		return "name = " + getName() + ", price = " + getPrice() + ", quantity = " + getQuantity() + ", sum = " + getPrice()*getQuantity();
	}
	
	

	
}
