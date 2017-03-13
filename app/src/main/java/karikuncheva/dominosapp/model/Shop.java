package karikuncheva.dominosapp.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import karikuncheva.dominosapp.model.products.Product.ProductType;
import karikuncheva.dominosapp.model.products.Dessert;
import karikuncheva.dominosapp.model.products.Drink;
import karikuncheva.dominosapp.model.products.Pizza;
import karikuncheva.dominosapp.model.products.Product;



public class Shop {

	private static Shop instance;

	private HashSet<Client> clients;
	private HashMap<ProductType, HashSet<Product>> catalog;

	public Shop() {

		ArrayList<Product> products = new ArrayList<Product>();
		this.clients = new HashSet<Client>();
		this.catalog = new HashMap<ProductType, HashSet<Product>>();

		products.add(new Pizza("Margarita", 12.00));
		products.add(new Pizza("Beast", 16.50));
		products.add(new Pizza("Mediterraneo", 14.50));
		products.add(new Pizza("Carbonara", 14.50));
		products.add(new Pizza("Alfredo", 15.50));
		products.add(new Pizza("Vita", 14.50));
		products.add(new Pizza("Chickenita", 18.50));
		products.add(new Pizza("American Hot", 15.50));
		products.add(new Pizza("New York", 16.50));
		products.add(new Pizza("Bulgarian", 15.50));

		products.add(new Dessert("Choko Pie", 6.50));
		products.add(new Dessert("Souflle", 6.50));
		products.add(new Dessert("Nirvana", 2.90));
		products.add(new Dessert("Mini Pancakes", 3.50));

		products.add(new Cart.Drink("Coca-Cola", 2.80));
		products.add(new Cart.Drink("Finta", 2.80));
		products.add(new Cart.Drink("Sprite", 2.80));
		products.add(new Cart.Drink("Nestea", 2.00));
		addToCatalog(products);
	}

	public static Shop getInstance() {
		if (instance == null) {
			instance = new Shop();
		}
		return instance;
	}

	public Set getClients() {
		return Collections.unmodifiableSet(clients);
	}

	public Map getCatalog() {
		return Collections.unmodifiableMap(catalog);
		
	}
	private void addToCatalog(ArrayList<Product> products) {

		for (int i = 0; i < products.size(); i++) {
			Product p = products.get(i);

			if (!this.catalog.containsKey(p.pType)) {
				catalog.put(p.pType, new HashSet<Product>());
			}

			if (!this.catalog.get(p.pType).contains(p)) {
				catalog.get(p.pType).add(p);
			}
		}
	}
	// only admin can add products from the shop
	public void addNewProduct(ProductType type, String name, double price) {
		Product p;
		if (type == ProductType.PIZZA) {
			p = new Pizza(name, price);
		} else if (type == ProductType.DESSERT) {
			p = new Dessert(name, price);
		} else {
			p = new Drink(name, price);
		}

		if (!this.catalog.containsKey(p.pType)) {
			this.catalog.put(p.pType, new HashSet<Product>());
			
		}
		if (!this.catalog.get(p.pType).contains(p)) {
			this.catalog.get(p.pType).add(p);
		}
	}

	// only admin can remove products from the shop
	public void removeProduct(Product p) {
		if (this.catalog.containsKey(p.pType)) {
		
		}
		if (this.catalog.get(p.pType).contains(p)) {
			this.catalog.get(p.pType).remove(p);
		}

	}


	public void printClients() {
		for (Client client : clients) {
			System.out.println(client);
		}
	}

	public void printCatalog() {
		for (Entry<ProductType, HashSet<Product>> productType : catalog.entrySet()) {
			System.out.println(productType.getKey());
			System.out.println();
			for (Product p1 : productType.getValue()) {
				System.out.println(p1);
			}
			System.out.println("-------------------");
		}
	}

	public void addClient(Client client) {
		if (!this.clients.contains(client) && client != null) {
			this.clients.add(client);
		}
	}
}
