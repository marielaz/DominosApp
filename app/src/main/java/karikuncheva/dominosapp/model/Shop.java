package karikuncheva.dominosapp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.List;

import karikuncheva.dominosapp.R;
import karikuncheva.dominosapp.model.products.Product.ProductType;
import karikuncheva.dominosapp.model.products.Dessert;
import karikuncheva.dominosapp.model.products.Drink;
import karikuncheva.dominosapp.model.products.Pizza;
import karikuncheva.dominosapp.model.products.Product;


public class Shop implements Serializable {


    private static Shop instance;

    private HashSet<User> users;
    private HashMap<ProductType, HashSet<Product>> catalog;
    private ArrayList<Pizza> pizzas = new ArrayList<>();
    private ArrayList<Dessert> desserts = new ArrayList<>();
    private ArrayList<Drink> drinks = new ArrayList<>();
    private ArrayList<String> ingr = new ArrayList<>();


    public Shop() {

        ArrayList<Product> products = new ArrayList<Product>();
        this.users = new HashSet<User>();
        this.catalog = new HashMap<ProductType, HashSet<Product>>();

        pizzas.add(new Pizza("Margarita", 12.00, R.drawable.margarita));
        pizzas.get(0).getIngredients().add("mozzarella");
        pizzas.get(0).getIngredients().add("tomato sause");

        pizzas.add(new Pizza("Beast", 16.50, R.drawable.beast));
        pizzas.get(1).getIngredients().add("tomato sauce");
        pizzas.get(1).getIngredients().add("mozzarella");
        pizzas.get(1).getIngredients().add("ham");
        pizzas.get(1).getIngredients().add("bacon");
        pizzas.get(1).getIngredients().add("spicy beef");

        pizzas.add(new Pizza("Mediterraneo", 14.50, R.drawable.mediterraneo));
        pizzas.get(2).getIngredients().add("mozzarella");
        pizzas.get(2).getIngredients().add("tomato sauce");
        pizzas.get(2).getIngredients().add("green pappers");
        pizzas.get(2).getIngredients().add("feta cheese");
        pizzas.get(2).getIngredients().add("olives");


        pizzas.add(new Pizza("Carbonara", 14.50, R.drawable.carbonara));
        pizzas.get(3).getIngredients().add("cream");
        pizzas.get(3).getIngredients().add("mozzarella");
        pizzas.get(3).getIngredients().add("bacon");
        pizzas.get(3).getIngredients().add("mushrooms");

        pizzas.add(new Pizza("Alfredo", 15.50, R.drawable.alfredo));
        pizzas.get(4).getIngredients().add("cream");
        pizzas.get(4).getIngredients().add("mozzarella");
        pizzas.get(4).getIngredients().add("baby spinach");
        pizzas.get(4).getIngredients().add("chicken");

        pizzas.add(new Pizza("Vita", 14.50, R.drawable.vita));
        pizzas.get(5).getIngredients().add("tomato sauce");
        pizzas.get(5).getIngredients().add("mozzarella");
        pizzas.get(5).getIngredients().add("baby spinach");
        pizzas.get(5).getIngredients().add("feta cheese");
        pizzas.get(5).getIngredients().add("tomatos");

        pizzas.add(new Pizza("Chickenita", 18.50, R.drawable.chickenita));
        pizzas.get(6).getIngredients().add("BBQ sauce");
        pizzas.get(6).getIngredients().add("mozzarella");
        pizzas.get(6).getIngredients().add("chicken");
        pizzas.get(6).getIngredients().add("pepperoni");
        pizzas.get(6).getIngredients().add("tomatos");
        pizzas.get(6).getIngredients().add("emmental");

        pizzas.add(new Pizza("American Hot", 15.50, R.drawable.americanhot));
        pizzas.get(7).getIngredients().add("tomato sauce");
        pizzas.get(7).getIngredients().add("mozzarella");
        pizzas.get(7).getIngredients().add("pepperoni");
        pizzas.get(7).getIngredients().add("spicy pappers");
        pizzas.get(7).getIngredients().add("onion");

        pizzas.add(new Pizza("New York", 16.50,  R.drawable.newyork));
        pizzas.get(8).getIngredients().add("tomato sauce");
        pizzas.get(8).getIngredients().add("mozzarella");
        pizzas.get(8).getIngredients().add("bacon");
        pizzas.get(8).getIngredients().add("cheddar");
        pizzas.get(8).getIngredients().add("mushrooms");

        pizzas.add(new Pizza("Bulgarian", 15.50,  R.drawable.bulgaria));
        pizzas.get(9).getIngredients().add("tomato sauce");
        pizzas.get(9).getIngredients().add("mozzarella");
        pizzas.get(9).getIngredients().add("onion");
        pizzas.get(9).getIngredients().add("olives");
        pizzas.get(9).getIngredients().add("green pappers");
        pizzas.get(9).getIngredients().add("feta cheese");

        desserts.add(new Dessert("Choko Pie", 6.50,  R.drawable.chocopie, "Freshly oven baked puff pastry filled with Nutella"));
        desserts.add(new Dessert("Souflle", 6.50, R.drawable.souffle, "Chocolate lava cake filled with melted warm chocolate"));
        desserts.add(new Dessert("Nirvana", 2.90,  R.drawable.nirvana, "Nirvana Pralines & Cream"));
        desserts.add(new Dessert("Mini Pancakes", 3.50,  R.drawable.minipancakes, "12 puffy mini pancakes with banana tast"));

        drinks.add(new Drink("Coca-Cola", 2.80, R.drawable.cola, "1,25l"));
        drinks.add(new Drink("Fanta", 2.80, R.drawable.fanta, "1,25l"));
        drinks.add(new Drink("Sprite", 2.80, R.drawable.sprite, "1,25l"));
        drinks.add(new Drink("Nestea", 2.00, R.drawable.nestea, "1,25l"));

        //addToCatalog(products);

        addIngredients(ingr);
    }

    public static Shop getInstance() {
        if (instance == null) {
            instance = new Shop();
        }
        return instance;
    }

    public List getPizzas() {
        return Collections.unmodifiableList(pizzas);
    }

    public List getDesserts() {
        return Collections.unmodifiableList(desserts);
    }

    public List getDrinks() {
        return Collections.unmodifiableList(drinks);
    }


    public Set getUsers() {
        return Collections.unmodifiableSet(users);

    }

    public ArrayList<String> getIngr() {
        return ingr;
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
//	public void addNewProduct(ProductType type, String name, double price, String description) {
//		Product p;
//		if (type == ProductType.PIZZA) {
//			p = new Pizza(name, price, description );
//		} else if (type == ProductType.DESSERT) {
//			p = new Dessert(name, price);
//		} else {
//			p = new Drink(name, price);
//		}
//
//		if (!this.catalog.containsKey(p.pType)) {
//			this.catalog.put(p.pType, new HashSet<Product>());
//
//		}
//		if (!this.catalog.get(p.pType).contains(p)) {
//			this.catalog.get(p.pType).add(p);
//		}
//	}

    // only admin can remove products from the shop
    public void removeProduct(Product p) {
        if (this.catalog.containsKey(p.pType)) {

        }
        if (this.catalog.get(p.pType).contains(p)) {
            this.catalog.get(p.pType).remove(p);
        }

    }


    public void printClients() {
        for (User user : users) {
            System.out.println(user);
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

    public void addClient(User user) {
        if (!this.users.contains(user) && user != null) {
            this.users.add(user);
        }
    }

    private void addIngredients(ArrayList<String> ingredients){
        ingredients.add("mozzarella");
        ingredients.add("feta cheese");
        ingredients.add("emmental");
        ingredients.add("cheddar");
        ingredients.add("bacon");
        ingredients.add("ham");
        ingredients.add("chickeh");
        ingredients.add("spice beef");
        ingredients.add("beef");
        ingredients.add("pepperoni");
        ingredients.add("tomatos");
        ingredients.add("green pappers");
        ingredients.add("spicy peppers");
        ingredients.add("baby spinach");
        ingredients.add("onion");
        ingredients.add("mushrooms");
        ingredients.add("olives");
    }
}
