package karikuncheva.dominosapp.model;


import karikuncheva.dominosapp.model.products.Product;
import karikuncheva.dominosapp.model.products.Product.ProductType;


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
	public void removeProduct(Cart.Product p) {
		shop.removeProduct(p);
		}

	public static class Dessert extends Cart.Product {


        public Dessert(String name, double price) {
            super(ProductType.DESSERT, name, price);
        }

        @Override
        public String toString() {
            return "name = " + getName() + ", price = " + getPrice() + ", quantity = " + getQuantity() + ", sum = " + getPrice()*getQuantity();
        }




    }
}

