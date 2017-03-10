package karikuncheva.dominosapp.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import karikuncheva.dominosapp.model.products.Product.ProductType;

public class Shop {

	private static Shop instance;

	private HashSet<Client> clients;
	private HashMap<ProductType, HashSet<Cart.Product>> catalog;

	public Shop() {

		ArrayList<Cart.Product> products = new ArrayList<Cart.Product>();
		this.clients = new HashSet<Client>();
		this.catalog = new HashMap<ProductType, HashSet<Cart.Product>>();

		products.add(new Client.Pizza("Margarita", 12.00));
		products.add(new Client.Pizza("Beast", 16.50));
		products.add(new Client.Pizza("Mediterraneo", 14.50));
		products.add(new Client.Pizza("Carbonara", 14.50));
		products.add(new Client.Pizza("Alfredo", 15.50));
		products.add(new Client.Pizza("Vita", 14.50));
		products.add(new Client.Pizza("Chickenita", 18.50));
		products.add(new Client.Pizza("American Hot", 15.50));
		products.add(new Client.Pizza("New York", 16.50));
		products.add(new Client.Pizza("Bulgarian", 15.50));

		products.add(new Admin.Dessert("Choko Pie", 6.50));
		products.add(new Admin.Dessert("Souflle", 6.50));
		products.add(new Admin.Dessert("Nirvana", 2.90));
		products.add(new Admin.Dessert("Mini Pancakes", 3.50));

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
	private void addToCatalog(ArrayList<Cart.Product> products) {

		for (int i = 0; i < products.size(); i++) {
			Cart.Product p = products.get(i);

			if (!this.catalog.containsKey(p.pType)) {
				catalog.put(p.pType, new HashSet<Cart.Product>());
			}

			if (!this.catalog.get(p.pType).contains(p)) {
				catalog.get(p.pType).add(p);
			}
		}
	}
	// only admin can add products from the shop
	public void addNewProduct(ProductType type, String name, double price) {
		Cart.Product p;
		if (type == ProductType.PIZZA) {
			p = new Client.Pizza(name, price);
		} else if (type == ProductType.DESSERT) {
			p = new Admin.Dessert(name, price);
		} else {
			p = new Cart.Drink(name, price);
		}

		if (!this.catalog.containsKey(p.pType)) {
			this.catalog.put(p.pType, new HashSet<>());
			
		}
		if (!this.catalog.get(p.pType).contains(p)) {
			this.catalog.get(p.pType).add(p);
		}
	}

	// only admin can remove products from the shop
	public void removeProduct(Cart.Product p) {
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
		for (Entry<ProductType, HashSet<Cart.Product>> productType : catalog.entrySet()) {
			System.out.println(productType.getKey());
			System.out.println();
			for (Cart.Product p1 : productType.getValue()) {
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
