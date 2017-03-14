package karikuncheva.dominosapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import karikuncheva.dominosapp.model.Shop;
import karikuncheva.dominosapp.model.products.Pizza;

/**
 * Created by Mariela Zviskova on 14.3.2017 г..
 */

class CustomAdapter extends ArrayAdapter<String> {

    Context fragment1;
    List<Pizza> pizzas;
    Shop shop = Shop.getInstance();

    public CustomAdapter(Context fragment1, List<Pizza> pizzas){
        super(fragment1, R.layout.single_row_pizza);
        this.fragment1 = fragment1;
        this.pizzas = pizzas;
    }

    @Override
    public int getCount() {
        return pizzas.size();
    }

    @NonNull
    @Override
    //viewGroup parent = list
    public View getView(int position, View convertView, ViewGroup parent) {
        //convert xml to java with inflater
        LayoutInflater inflater = (LayoutInflater) fragment1.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.single_row_pizza, parent, false);

//        if (convertView == null) {
//            convertView = LayoutInflater.from(getContext()).inflate(R.layout.single_row_pizza, parent, false);
//        }
        TextView pizzaName = (TextView) row.findViewById(R.id.name);
        TextView pizzaDescr = (TextView) row.findViewById(R.id.description);
        TextView pizzaPrice = (TextView) row.findViewById(R.id.price);
        pizzaName.setText(pizzas.get(position).getName());
        pizzaDescr.setText(pizzas.get(position).getDescription());
        String price = Double.toString(pizzas.get(position).getPrice());
        pizzaPrice.setText(price);
        return row;
    }
}