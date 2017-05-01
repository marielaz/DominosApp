package karikuncheva.dominosapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import karikuncheva.dominosapp.model.User;
import karikuncheva.dominosapp.model.products.Pizza;
import karikuncheva.dominosapp.model.products.Product;

/**
 * Created by Mariela Zviskova on 14.3.2017 г..
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.PizzaViewHolder> {

    private Activity activity;
    private List<Pizza> pizzas;
    int i = 0;
    StringBuilder sb = new StringBuilder();


    public CustomAdapter(Activity activity, List<Pizza> pizzas) {
        this.activity = activity;
        this.pizzas = pizzas;
    }

    @Override
    public PizzaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(activity);
        View row = li.inflate(R.layout.single_row_pizza, parent, false);
        CustomAdapter.PizzaViewHolder vh = new PizzaViewHolder(row);
        return vh;
    }

    @Override
    public void onBindViewHolder(final PizzaViewHolder vh, final int position) {
        Pizza pizza = pizzas.get(position);
        vh.pizzaImage.setImageResource(pizza.getImageId());
        vh.pizzaName.setText(pizza.getName());

        for (i = 0; i < pizza.getIngredients().size(); i++) {
            if (i == pizza.getIngredients().size() - 1) {
                sb.append(pizza.getIngredients().get(i));
            } else {
                sb.append(pizza.getIngredients().get(i) + ", ");
            }
        }
        vh.pizzaDescr.setText(sb.toString());
        sb = new StringBuilder();
        double p = pizza.getPrice();
        vh.pizzaPrice.setText(String.format("%.2f", p));

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, ModifyPizzaActivity.class);
                intent.putExtra("pizza", pizzas.get(position));
                activity.startActivity(intent);
            }
        };

        vh.modify_pizza_bnt.setOnClickListener(listener);

        vh.pizzaImage.setOnClickListener(listener);

        vh.largeTrad.setOnClickListener(listener);

        vh.cart_pizza_bnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO add to cart
                MainActivity.loggedUser.getCart().addProduct(pizzas.get(position));
                ((PizzaFragment.ProductsCommunicator) activity).increment();
            }
        });
    }

    @Override
    public int getItemCount() {
        return pizzas.size();
    }

    class PizzaViewHolder extends RecyclerView.ViewHolder {
        ImageButton modify_pizza_bnt;
        ImageButton cart_pizza_bnt;
        ImageView pizzaImage;
        TextView pizzaName;
        TextView pizzaDescr;
        TextView pizzaPrice;
        TextView largeTrad;

        PizzaViewHolder(View row) {
            super(row);
            modify_pizza_bnt = (ImageButton) row.findViewById(R.id.modify_pizza_bnt);
            cart_pizza_bnt = (ImageButton) row.findViewById(R.id.cart_pizza_bnt);
            pizzaImage = (ImageView) row.findViewById(R.id.image_pizza);
            pizzaName = (TextView) row.findViewById(R.id.name_pizza);
            pizzaDescr = (TextView) row.findViewById(R.id.descr_pizza);
            pizzaPrice = (TextView) row.findViewById(R.id.price_pizza);
            largeTrad = (TextView) row.findViewById(R.id.largeTrad);
        }
    }
}