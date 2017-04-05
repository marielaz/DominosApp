package karikuncheva.dominosapp.model.products;

public class Pizza extends Product {

	public Type type;
	public Size size;
	private String description;

	public Pizza(String name, double price, String description, int imageId) {
		super(ProductType.PIZZA, name, price, description, imageId);
		this.type = Type.TRADITIONAL;
		this.size = Size.LARGE;
		this.description = description;
	}

	public Pizza(String name, String description){
		super(name, description);
	}

	public String getDescription() {
		return description;
	}


	public Pizza modifyPizza(Pizza pizza, Size size, Type type) {

		Pizza p = pizza;
		if (size == Size.MEDIUM) {
			p = new Pizza(pizza.getName(), pizza.getPrice() - 1.00, pizza.getDescription(), getImageId());
			p.size= Size.MEDIUM;
			p.type= type;
			return p;
		} else if (size == size.SMALL) {
			p = new Pizza(pizza.getName(), pizza.getPrice() - 1.50, pizza.getDescription(), getImageId());
			p.size= Size.SMALL;
			p.type = type;
			return p;
		}
		else if (size == size.LARGE && type != type.TRADITIONAL){
			p = new Pizza(pizza.getName(), pizza.getPrice(), pizza.getDescription(), getImageId());
			p.type = type;
			return p;
		}
		
		return p;
	}

//	public Pizza modifyPizza(Pizza pizza, Size size, Type type) {
//		return pizza.changePizza(pizza, size, type);
//	}


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

	public Type getType() {
		return type;
	}

	public Size getSize() {
		return size;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public void setSize(Size size) {
		this.size = size;
	}
}
