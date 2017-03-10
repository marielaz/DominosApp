package karikuncheva.dominosapp.model.products;

public class Pizza extends Product {

	public enum Type {
		TRADITIONAL, THIN_AND_CRISPY, FLUFFY
	};

	public enum Size {
		SMALL, MEDIUM, LARGE
	};

	public Type type;
	public Size size;

	public Pizza(String name, double price) {
		super(ProductType.PIZZA, name, price);
		this.type = Type.TRADITIONAL;
		this.size = Size.LARGE;

	}


	// change the size of the pizza and the crust and modify the price
	// NE RABOTI!
	public Pizza changePizza(Pizza pizza, Size size, Type type) {
	
		if (size == Size.MEDIUM) {
			Pizza p = new Pizza(pizza.getName(), pizza.getPrice() - 1.00);
			p.size= Size.MEDIUM;
			p.type= type;
			return p;
		} else if (size == size.SMALL) {
			Pizza p = new Pizza(pizza.getName(), pizza.getPrice() - 1.50);
			p.size= Size.SMALL;
			p.type = type;
			return p;
		}
		else if (size == size.LARGE && type != type.TRADITIONAL){
			Pizza p = new Pizza(pizza.getName(), pizza.getPrice());
			p.type = type;
		}
		
		return pizza;
	}

	@Override
	public String toString() {
		return "name = " + getName() + ", type = " + type + ", size = " + size + ", price = " + getPrice()
				+ ", quantity = " + getQuantity() + ", after discount 5% = "
				+ String.format("%.2f", getDiscPrice() * getQuantity());
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((size == null) ? 0 : size.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pizza other = (Pizza) obj;
		if (size != other.size)
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	
}
