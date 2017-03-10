package karikuncheva.dominosapp.model.products;

import karikuncheva.dominosapp.model.products.Product;

public class Dessert extends Product {

	
	public Dessert(String name, double price) {
		super(ProductType.DESSERT, name, price);
	}

	@Override
	public String toString() {
		return "name = " + getName() + ", price = " + getPrice() + ", quantity = " + getQuantity() + ", sum = " + getPrice()*getQuantity();
	}
	
	

	
}
