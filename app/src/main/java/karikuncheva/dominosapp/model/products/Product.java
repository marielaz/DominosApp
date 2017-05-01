package karikuncheva.dominosapp.model.products;

import java.io.Serializable;

public abstract class Product implements Serializable {


    public String pType;
    private String name;
    private double price;
    private int quantity;
    private double discPrice;
    private int imageId;
    private int id;

    public Product(String pType, String name, double price, int imageId) {
        this.pType = pType;
        this.name = name;
        this.price = price;
        this.imageId = imageId;
        this.quantity = 1;
        if (pType.equals("Pizza")) {
            this.discPrice = price - (price * 0.05);
        } else {
            this.discPrice = 0;
        }
    }

    public Product(String pType, String name, double price) {
        this.pType = pType;
        this.name = name;
        this.price = price;
        this.quantity = 1;
        if (pType.equals("Pizza")) {
            this.discPrice = price - (price * 0.05);
        } else {
            this.discPrice = 0;
        }
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

    public int getImageId() {
        return imageId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getpType() {
        return pType;
    }

    public void setpType(String pType) {
        this.pType = pType;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        long temp;
        temp = Double.doubleToLongBits(price);
        result = prime * result + (int) (temp ^ (temp >>> 32));
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
        if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
            return false;
        return true;

    }
}
