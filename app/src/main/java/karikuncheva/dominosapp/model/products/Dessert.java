package karikuncheva.dominosapp.model.products;


public class Dessert extends Product {

	private  String description;

	public Dessert(String name, double price, int imageId, String description) {
		super(ProductType.DESSERT, name, price, imageId);
		this.description = description;
	}
	public Dessert(String name, double price, String description) {
		super(ProductType.DESSERT, name, price);
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		return "name = " + getName() + ", price = " + getPrice() + ", quantity = " + getQuantity() + ", sum = " + getPrice()*getQuantity();
	}

}
