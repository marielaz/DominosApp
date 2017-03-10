package karikuncheva.dominosapp.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import products.Pizza;
import products.Product;
import products.Product.ProductType;


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
	
}
