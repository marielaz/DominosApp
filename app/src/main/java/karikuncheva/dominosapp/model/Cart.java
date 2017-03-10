package karikuncheva.dominosapp.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

import karikuncheva.dominosapp.model.products.Pizza;
import karikuncheva.dominosapp.model.products.Product;
import karikuncheva.dominosapp.model.products.Product.ProductType;


public class Cart {

	private double totalSum;
	private HashMap<ProductType, HashSet<Product>> products;

	public Cart() {
		this.totalSum = 0;
		this.products = new HashMap<ProductType, HashSet<Product>>();
	}

	public double getTotalSum() {
		return totalSum;
	}
	
	// add the product into the cart and if the product is Pizza - get discount
	public void addProduct(Product p) {
		if (!this.products.containsKey(p.pType)) {
			products.put(p.pType, new HashSet<Product>());
		}

		if (!this.products.get(p.pType).contains(p)) {
			products.get(p.pType).add(p);
			p.setQuantity(1);

		} else {
			int currentQuantity = p.getQuantity();
			p.setQuantity(++currentQuantity);
		}
		
		// get Discount
		if (p.pType == ProductType.PIZZA) {
			p.setDiscPrice(p.getPrice() - p.getPrice() * 0.05);
			this.totalSum += p.getDiscPrice();
		} else {
			this.totalSum += p.getPrice();
		}

	}

	public void removeProduct(Product p) {

		if (this.products.containsKey(p.pType)) {
			if (this.products.get(p.pType).contains(p) && p.getQuantity() == 1) {
				modifyTotalSum(p);

				products.get(p.pType).remove(p);

				if (this.products.get(p.pType).isEmpty()) {
					this.products.remove(p.pType);
				}
			} else if (this.products.get(p.pType).contains(p)) {

				modifyTotalSum(p);

				p.setQuantity(p.getQuantity() - 1);
			} else {
				return;
			}
		} else {
			return;
		}
	}

	// modify the total sum if the client remove product from the cart
	private void modifyTotalSum(Product p) {
		if (p.pType == ProductType.PIZZA) {
			this.totalSum -= p.getDiscPrice();
		} else {
			this.totalSum -= p.getPrice();
		}
	}

	// info cart
	public void printCart() {
		for (Entry<ProductType, HashSet<Product>> product : products.entrySet()) {
			System.out.println(product.getKey());
			System.out.println();
			for (Product p1 : product.getValue()) {
				System.out.println(p1);
			}
			System.out.println("-------------------");
		}
}

	public static class Drink extends Product {


        public Drink(String name, double price) {
            super(ProductType.DRINK, name, price);

        }

        @Override
        public String toString() {
            return "name = " + getName() + ", price = " + getPrice() +  ", quantity = " + getQuantity() + ", sum = " + getPrice()*getQuantity();
        }


    }

	public abstract static class Product {

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
}
