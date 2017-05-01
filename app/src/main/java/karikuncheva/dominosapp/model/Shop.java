package karikuncheva.dominosapp.model;

import java.io.Serializable;
import java.util.ArrayList;


public class Shop implements Serializable {


    private static Shop instance;
    private ArrayList<String> ingr = new ArrayList<>();


    public Shop() {
        addIngredients(ingr);
    }

    public static Shop getInstance() {
        if (instance == null) {
            instance = new Shop();
        }
        return instance;
    }

    public ArrayList<String> getIngr() {
        return ingr;
    }

    private void addIngredients(ArrayList<String> ingredients) {
        ingredients.add("mozzarella");
        ingredients.add("feta cheese");
        ingredients.add("emmental");
        ingredients.add("cheddar");
        ingredients.add("bacon");
        ingredients.add("ham");
        ingredients.add("chickeh");
        ingredients.add("spicy beef");
        ingredients.add("beef");
        ingredients.add("pepperoni");
        ingredients.add("tomatos");
        ingredients.add("green peppers");
        ingredients.add("spicy peppers");
        ingredients.add("baby spinach");
        ingredients.add("onion");
        ingredients.add("mushrooms");
        ingredients.add("olives");
    }
}
