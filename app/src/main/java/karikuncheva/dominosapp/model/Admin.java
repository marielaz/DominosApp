package karikuncheva.dominosapp.model;

import products.Dessert;
import products.Drink;
import products.Pizza;
import products.Product;
import products.Product.ProductType;

public final class Admin {

	private String username;
	private String password;
	private Shop shop;

	public Admin(String username, String password) {
		this.username = username;
		this.password = password;
		this.shop = Shop.getInstance();
	}

	public Shop getShop() {
		return shop;
	}

	public void addProduct(ProductType type, String name, double price) {
		shop.addNewProduct(type, name, price);
	}

	// only admin can remove products from the cart!
	public void removeProduct(Product p) {
		shop.removeProduct(p);
		}

	}

