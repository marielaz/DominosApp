package karikuncheva.dominosapp;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import karikuncheva.dominosapp.model.User;
import karikuncheva.dominosapp.model.products.Pizza;

/**
 * Created by Mariela Zviskova on 14.3.2017 г..
 */

class CustomAdapter extends ArrayAdapter<String> {

    private Activity activity;
    private List<Pizza> pizzas;
    private User user;


    class PizzaViewHolder {
        View row;
        ImageButton modify_pizza_bnt;
        ImageButton cart_pizza_bnt;
        ImageView pizzaImage;
        TextView pizzaName;
        TextView pizzaDescr;
        TextView pizzaPrice;

        PizzaViewHolder(View row){

             modify_pizza_bnt = (ImageButton) row.findViewById(R.id.modify_pizza_bnt);
             cart_pizza_bnt = (ImageButton) row.findViewById(R.id.cart_pizza_bnt);
             pizzaImage = (ImageView) row.findViewById(R.id.image_pizza);
             pizzaName = (TextView) row.findViewById(R.id.name_pizza);
             pizzaDescr = (TextView) row.findViewById(R.id.descr_pizza);
             pizzaPrice = (TextView) row.findViewById(R.id.price_pizza);
        }

    }


    public CustomAdapter(Activity activity, List<Pizza> pizzas, User user) {
        super(activity, R.layout.single_row_pizza);
        this.activity = activity;
        this.pizzas = pizzas;
        this.user = user;
    }

    @Override
    public int getCount() {
        return pizzas.size();
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //convert xml to java with inflater
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row;
        PizzaViewHolder vh;

        if (convertView == null) {
            row = inflater.inflate(R.layout.single_row_pizza, parent, false);
            vh = new  PizzaViewHolder(row);
            row.setTag(vh);
        }else{
            row = convertView;
            vh = (PizzaViewHolder) row.getTag();
        }

        ImageButton modify_pizza_bnt = vh.modify_pizza_bnt;
        ImageButton cart_pizza_bnt = vh.cart_pizza_bnt;
        ImageView pizzaImage = vh.pizzaImage;
        final TextView pizzaName = vh.pizzaName;
        TextView pizzaDescr = vh.pizzaDescr;
        TextView pizzaPrice = vh.pizzaPrice;
        pizzaImage.setImageResource(pizzas.get(position).getImageId());
        pizzaName.setText(pizzas.get(position).getName());
        pizzaDescr.setText(pizzas.get(position).getDescription());
        double p = pizzas.get(position).getPrice();
        pizzaPrice.setText(String.format("%.2f", p));

        modify_pizza_bnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, ModifyPizzaActivity.class);
                intent.putExtra("pizza", pizzas.get(position));
                activity.startActivityForResult(intent, 1);
            }
        });

        cart_pizza_bnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO add to cart
                user.getCart().addProduct(pizzas.get(position));
                String chosenPizza = pizzaName.getText().toString() + " is added to your cart!";
                Toast.makeText(v.getContext(), chosenPizza, Toast.LENGTH_SHORT).show();
            }
        });

        return row;
    }



}