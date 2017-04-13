package karikuncheva.dominosapp.model;

import java.io.Serializable;

/**
 * Created by Karina Kuncheva on 4/7/2017.
 */

public class Address implements Serializable {

    private String town;
    private String neighbourhood;
    private String street;
    private String number;
    private String block;
    private String postCode;
    private String apartment;
    private String floor;
    private int id;
    private int idUser;

    public Address(String town, String neighbourhood, String street, String number, String block, String postCode, String apartment, String floor) {
        this.town = town;
        this.neighbourhood = neighbourhood;
        this.street = street;
        this.number = number;
        this.block = block;
        this.postCode = postCode;
        this.apartment = apartment;
        this.floor = floor;
    }

    public String getTown() {
        return town;
    }

    public String getNeighbourhood() {
        return neighbourhood;
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

    public String getApartment() {
        return apartment;
    }

    public String getFloor() {
        return floor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}
