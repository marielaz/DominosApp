package karikuncheva.dominosapp.model;

import java.io.Serializable;

/**
 * Created by Karina Kuncheva on 4/7/2017.
 */

public class Address implements Serializable {

    private String town;
    private String neighborhood;
    private String street;
    private String number;
    private String block;
    private String postCode;
    private String apartament;
    private String floor;

    public Address(String town, String neighborhood, String street, String number, String block, String postCode, String apartament, String floor) {
        this.town = town;
        this.neighborhood = neighborhood;
        this.street = street;
        this.number = number;
        this.block = block;
        this.postCode = postCode;
        this.apartament = apartament;
        this.floor = floor;
    }

    public String getTown() {
        return town;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public String getStreet() {
        return street;
    }

    public String getNumber() {
        return number;
    }

    public String getBlock() {
        return block;
    }

    public String getPostCode() {
        return postCode;
    }

    public String getApartament() {
        return apartament;
    }

    public String getFloor() {
        return floor;
    }
}
