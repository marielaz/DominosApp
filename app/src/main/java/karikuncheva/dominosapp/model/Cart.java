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
        if (!this.products.containsKey(p.pType)) {
            products.put(p.getpType(), new HashSet<Product>());
        }
        if (!this.products.get(p.pType).contains(p)) {
            products.get(p.pType).add(p);
            p.setQuantity(1);
        } else {
            int currentQuantity = p.getQuantity();
            p.setQuantity(++currentQuantity);
        }

        // get Discount
        if (p.pType.equals("pizza")) {
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
        if (p.pType.equals("pizza")) {
            this.totalSum -= p.getDiscPrice();
        } else {
            this.totalSum -= p.getPrice();
        }
    }
}

