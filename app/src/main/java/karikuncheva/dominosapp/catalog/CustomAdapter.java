package karikuncheva.dominosapp.catalog;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import karikuncheva.dominosapp.LoginActivity;
import karikuncheva.dominosapp.R;
import karikuncheva.dominosapp.model.products.Pizza;

/**
 * Created by Mariela Zviskova on 14.3.2017 г..
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.PizzaViewHolder> {

    private Activity activity;
    private List<Pizza> pizzas;
    int i = 0;


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
        vh.pizzaDescr.setText(pizza.getIngredients());

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
                LoginActivity.loggedUser.getCart().addProduct(pizzas.get(position));
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