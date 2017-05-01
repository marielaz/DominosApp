package karikuncheva.dominosapp;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import karikuncheva.dominosapp.model.products.Drink;

/**
 * Created by Mariela Zviskova on 14.3.2017 г..
 */

public class DrinkCustomAdapter extends RecyclerView.Adapter<DrinkCustomAdapter.DrinkViewHolder> {

    private Activity activity;
    private List<Drink> drinks;


    public DrinkCustomAdapter(Activity activity, List<Drink> drinks) {
        this.activity = activity;
        this.drinks = drinks;
    }

    @Override
    public DrinkViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(activity);
        View row = li.inflate(R.layout.single_row_des_dr, parent, false);
        DrinkViewHolder vh = new DrinkViewHolder(row);
        return vh;
    }

    @Override
    public void onBindViewHolder(final DrinkViewHolder vh, final int position) {
        Drink dr = drinks.get(position);
        vh.drinkImage.setImageResource(dr.getImageId());
        vh.drinkName.setText(dr.getName());
        vh.drinkDescr.setText(dr.getDescription());
        double p = dr.getPrice();
        vh.drinkPrice.setText(String.format("%.2f", p));

        vh.cart_bnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO add to cart
                MainActivity.loggedUser.getCart().addProduct(drinks.get(position));
                final int idx = CatalogActivity.count + 1;
                CatalogActivity.count = idx;
                ((PizzaFragment.ProductsCommunicator) activity).changeCount(CatalogActivity.count);
            }
        });

    }

    @Override
    public int getItemCount() {
        return drinks.size();
    }

    class DrinkViewHolder extends RecyclerView.ViewHolder {
        ImageButton cart_bnt;
        ImageView drinkImage;
        TextView drinkName;
        TextView drinkDescr;
        TextView drinkPrice;

        DrinkViewHolder(View row) {
            super(row);
            cart_bnt = (ImageButton) row.findViewById(R.id.cart_bnt);
            drinkImage = (ImageView) row.findViewById(R.id.image);
            drinkName = (TextView) row.findViewById(R.id.name);
            drinkDescr = (TextView) row.findViewById(R.id.description);
            drinkPrice = (TextView) row.findViewById(R.id.price);
        }

    }
}