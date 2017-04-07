package karikuncheva.dominosapp.model.products;


public class Dessert extends Product {

	
	public Dessert(String name, double price, String description, int imageId) {
		super(ProductType.DESSERT, name, price, description, imageId);

	}
	public Dessert(String name, double price, String description) {
		super(ProductType.DESSERT, name, price, description);

	}

	@Override
	public String toString() {
		return "name = " + getName() + ", price = " + getPrice() + ", quantity = " + getQuantity() + ", sum = " + getPrice()*getQuantity();
	}

}
