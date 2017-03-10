package karikuncheva.dominosapp.model;

public abstract class Product {

	public enum ProductType {PIZZA, DESSERT, DRINK};
	
	public ProductType pType;
	private String name;
	private double price;
	private int quantity ;
	private double discPrice;
	
	public Product(ProductType pType, String name, double price) {
		this.pType = pType;
		this.name = name;
		this.price = price;
		this.quantity= 1;
		this.discPrice = 0;
	}
	
	public double getDiscPrice() {
		return discPrice;
	}
	
	public void setDiscPrice(double discPrice) {
		this.discPrice = discPrice;
	}
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public String getName() {
		return name;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity( int quantity) {
		this.quantity = quantity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	
	
	
}
