package karikuncheva.dominosapp.model;

import java.util.HashMap;
import java.util.Random;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import products.Dessert;
import products.Drink;
import products.Pizza;
import products.Pizza.Size;
import products.Pizza.Type;
import products.Product;
import products.Product.ProductType;

public class Client {

	private String username;
	private String name;
	private String address;
	private String password;
	private String phoneNumber;
	private Cart cart;
	private String email;
	private double money;
	private Shop shop;
	private static final String PASS_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,16}$";
	private Pattern regexPattern;
	private Matcher regMatcher;

	public Client(String username, String name, String address, String password, String phoneNumber, String email) {
		if (username != null && !username.isEmpty()) {
			this.username = username;
		}
		if (name != null && !name.isEmpty()) {
			this.name = name;
		}
		if (address != null && !address.isEmpty()) {
			this.address = address;
		}
		validatePassword(password);
		validateMobileNumber(phoneNumber);
		validateEmailAddress(email);
		this.cart = new Cart();
		this.shop = Shop.getInstance();
		this.shop.addClient(this);
		this.money = 100;

	}

	public void validatePassword(String pass) {
		if (!pass.matches(PASS_REGEX)) {
			System.out.println("Invalid password. Please try again!");
		} else {
			this.password = pass;
		}
	}

	public Cart getCart() {
		return cart;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public double getMoney() {
		return money;
	}

	public void validateEmailAddress(String emailAddress) {

		regexPattern = Pattern.compile("^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$");
		regMatcher = regexPattern.matcher(emailAddress);
		if (regMatcher.matches()) {
			this.email = emailAddress;
		} else {
			System.out.println("Invalid Email Address");
		}
	}

	public void validateMobileNumber(String mobileNumber) {
		regexPattern = Pattern.compile("^((088)|(089)|(087))[0-9]{7}$");
		regMatcher = regexPattern.matcher(mobileNumber);
		if (regMatcher.matches()) {
			this.phoneNumber = mobileNumber;
		} else {
			System.out.println("Invalid Mobile Number");
		}
	}

	public void changePassword(String password) {
		if (password.matches(PASS_REGEX)) {
			this.password = password;
			System.out.println("Password is changed.");
		}
	}

	public void changeAddress(String newAddress) {
		if (newAddress != null && !newAddress.isEmpty()) {
			this.address = newAddress;
			System.out.println("Address is changed.");
		}
	}

	public void changeName(String newName) {
		if (newName != null && !newName.isEmpty()) {
			this.name = newName;
			System.out.println("The name is changed.");
		}
	}

	// the client make order and put the product into the cart
	public void chooseProduct(Product p) {
		if (this.cart != null) {
			this.cart.addProduct(p);
		}
	}

	public void removeProductFromCart(Product p) {
		this.cart.removeProduct(p);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		Client other = (Client) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	public void setGiftCard(String code) {
		if (code != null && code.length() == 5 && !code.isEmpty()) {
			String n = ".*[0-9]*.";
			String a = ".*[A-Z]*.";
			if (code.matches(n) && code.matches(a)) {
				int countDigits = 0;

				for (int i = 0; i < code.length(); i++) {
					if (Character.isDigit(code.charAt(i))) {
						countDigits++;
						continue;
					}
				}
				if (countDigits > code.length() - countDigits) {
					switch (new Random().nextInt(3)) {
					case 0:
						this.cart.addProduct(new Drink("Coca Cola", 0));
						break;
					case 1:
						this.cart.addProduct(new Drink("Fanta", 0));
						break;
					case 2:
						this.cart.addProduct(new Drink("Sprite", 0));
						break;

					default:
						break;
					}
				} else {
					switch (new Random().nextInt(3)) {
					case 0:
						this.cart.addProduct(new Dessert("Choco Pie", 0));
						break;
					case 1:
						this.cart.addProduct(new Dessert("Nirvana", 0));
						break;
					case 2:
						this.cart.addProduct(new Dessert("Mini pancakes", 0));
						break;

					default:
						break;
					}
				}
			}
		} else {
			System.out.println("Wrong code!");
		}
	}

	// the client modify the pizza;
	public Pizza modifyPizza(Pizza pizza, Size size, Type type) {
		return pizza.changePizza(pizza, size, type);
	}

	// print the bill
	public void printBill() {

		System.out.print("Total sum: ");
		System.out.println(String.format("%.2f", this.cart.getTotalSum()) + " lv");
	}

	public void makeOrder() {

		payCheck();
		// empty cart
		this.cart = new Cart();
	}

	// the client pay the bill
	private void payCheck() {
		if (this.cart.getTotalSum() < this.money) {
			this.setMoney(this.money - this.cart.getTotalSum());
			System.out.println("Bill paid");
			System.out.println("Money left: " + String.format("%.2f", this.money));
		} else {
			System.out.println("You don't have enough money to pay the bill!");
			// return;
		}
	}

	@Override
	public String toString() {
		return "Client [username = " + username + ", name= " + name + ", address = " + address + ", password = "
				+ password + ", phoneNumber = " + phoneNumber + ", email = " + email;
	}

}
