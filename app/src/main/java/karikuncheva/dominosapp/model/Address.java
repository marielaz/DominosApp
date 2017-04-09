package karikuncheva.dominosapp.model;

/**
 * Created by Karina Kuncheva on 4/7/2017.
 */

public class Address {

    private String town;
    private String naighborhood;
    private String block;
    private String phoneNumber;

    public Address(String town, String naighborhood, String block, String phoneNumber) {
        this.town = town;
        this.naighborhood = naighborhood;
        this.block = block;
        this.phoneNumber = phoneNumber;
    }

    public String getTown() {
        return town;
    }

    public String getNaighborhood() {
        return naighborhood;
    }

    public String getBlock() {
        return block;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
