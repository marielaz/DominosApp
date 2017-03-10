package karikuncheva.dominosapp.model.products;

import karikuncheva.dominosapp.model.products.Product;

public class Drink extends Product {


	public Drink(String name, double price) {
		super(ProductType.DRINK, name, price);
		
	}

	@Override
	public String toString() {
		return "name = " + getName() + ", price = " + getPrice() +  ", quantity = " + getQuantity() + ", sum = " + getPrice()*getQuantity();
	}

	
}

