package karikuncheva.dominosapp.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

import karikuncheva.dominosapp.model.products.Product;


public class Cart implements Serializable {

    private double totalSum;
    private HashMap<String, HashSet<Product>> products;

    public Cart() {
        this.totalSum = 0;
        this.products = new HashMap<String, HashSet<Product>>();
    }

    public double getTotalSum() {
        return totalSum;
    }

    public HashMap<String, HashSet<Product>> getProducts() {
        return products;
    }

    // add the product into the cart and if the product is Pizza - get discount
    public void addProduct(Product p) {
        if (!this.products.containsKey(p.getpType())) {
            products.put(p.getpType(), new HashSet<Product>());
        }
        if (!this.products.get(p.getpType()).contains(p)) {
            products.get(p.getpType()).add(p);
            p.setQuantity(1);
        } else {
            int currentQuantity = p.getQuantity();
            p.setQuantity(++currentQuantity);
        }

        // get Discount
        if (p.getpType().equals("pizza")) {
            p.setDiscPrice(p.getPrice() - p.getPrice() * 0.05);
            this.totalSum += p.getDiscPrice();
        } else {
            this.totalSum += p.getPrice();
        }
    }

    public void removeProduct(Product p) {

        if (this.products.containsKey(p.getpType())) {
            if (this.products.get(p.getpType()).contains(p) && p.getQuantity() == 1) {
                modifyTotalSum(p);

                products.get(p.getpType()).remove(p);

                if (this.products.get(p.getpType()).isEmpty()) {
                    this.products.remove(p.getpType());
                }
            } else if (this.products.get(p.getpType()).contains(p)) {

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
        if (p.getpType().equals("pizza")) {
            this.totalSum -= p.getDiscPrice();
        } else {
            this.totalSum -= p.getPrice();
        }
    }
}

