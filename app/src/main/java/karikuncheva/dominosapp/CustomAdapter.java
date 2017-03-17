package karikuncheva.dominosapp;

import android.app.Activity;
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

import java.util.List;

import karikuncheva.dominosapp.model.Shop;
import karikuncheva.dominosapp.model.User;
import karikuncheva.dominosapp.model.products.Pizza;

/**
 * Created by Mariela Zviskova on 14.3.2017 г..
 */

class CustomAdapter extends ArrayAdapter<String> {


      Activity activity;
//    Bundle bundle = activity.getIntent().getExtras();
//     User user = (User)bundle.getSerializable("user");
    List<Pizza> pizzas;
    Shop shop = Shop.getInstance();

    public CustomAdapter(Activity activity, List<Pizza> pizzas){
        super(activity, R.layout.single_row_pizza);
        this.activity = activity;
        this.pizzas = pizzas;
    }


    @Override
    public int getCount() {
        return pizzas.size();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //convert xml to java with inflater
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.single_row_pizza, parent, false);

//        if (convertView == null) {
//            convertView = LayoutInflater.from(getContext()).inflate(R.layout.single_row_pizza, parent, false);
//        }

        ImageButton modify_pizza_bnt = (ImageButton) row.findViewById(R.id.modify_pizza_bnt);
        ImageButton cart_pizza_bnt = (ImageButton) row.findViewById(R.id.cart_pizza_bnt);
        ImageView pizzaImage = (ImageView) row.findViewById(R.id.image_pizza);
        TextView pizzaName = (TextView) row.findViewById(R.id.name_pizza);
        TextView pizzaDescr = (TextView) row.findViewById(R.id.descr_pizza);
        TextView pizzaPrice = (TextView) row.findViewById(R.id.price_pizza);
        pizzaImage.setImageResource(pizzas.get(position).getImageId());
        pizzaName.setText(pizzas.get(position).getName());
        pizzaDescr.setText(pizzas.get(position).getDescription());
        double p = pizzas.get(position).getPrice();
        pizzaPrice.setText(String.format("%.2f", p));

        modify_pizza_bnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, ModifyPizzaActivity.class);
                activity.startActivity(intent);
            }
        });

        cart_pizza_bnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO add to cart


            }
        });

        return row;
    }
}