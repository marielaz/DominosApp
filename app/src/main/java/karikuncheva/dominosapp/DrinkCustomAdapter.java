package karikuncheva.dominosapp;
import android.app.Activity;
import android.content.Context;
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
import karikuncheva.dominosapp.model.Shop;
import karikuncheva.dominosapp.model.User;
import karikuncheva.dominosapp.model.products.Dessert;
import karikuncheva.dominosapp.model.products.Drink;

import static karikuncheva.dominosapp.R.id.cart_bnt;

/**
 * Created by Mariela Zviskova on 14.3.2017 г..
 */

public class DrinkCustomAdapter extends ArrayAdapter<String> {

    private Activity activity;
    private List<Drink> drinks;
    private User user;


    class DrinkViewHolder {
        View row;
        ImageButton cart_bnt;
        ImageView drinkImage;
        TextView drinkName;
        TextView drinkDescr;
        TextView drinkPrice;

        DrinkViewHolder(View row){

             cart_bnt = (ImageButton) row.findViewById(R.id.cart_bnt);
             drinkImage = (ImageView) row.findViewById(R.id.image);
             drinkName = (TextView) row.findViewById(R.id.name);
             drinkDescr = (TextView) row.findViewById(R.id.description);
             drinkPrice = (TextView) row.findViewById(R.id.price);
        }

    }


    public DrinkCustomAdapter(Activity activity, List<Drink> drinks, User user){
        super(activity, R.layout.single_row_des_dr);
        this.activity = activity;
        this.drinks = drinks;
        this.user = user;
    }

    @Override
    public int getCount() {
        return drinks.size();
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row;
        DrinkViewHolder vh;

        if (convertView == null) {
            row = inflater.inflate(R.layout.single_row_des_dr, parent, false);
            vh = new DrinkViewHolder(row);
            row.setTag(vh);
        }else{
            row = convertView;
            vh = (DrinkViewHolder) row.getTag();
        }

        Drink dr = drinks.get(position);

        ImageButton cart_bnt = vh.cart_bnt;
        ImageView drinkImage = vh.drinkImage;
        final TextView drinkName = vh.drinkName;
        TextView drinkDescr = vh.drinkDescr;
        TextView drinkPrice = vh.drinkPrice;
        drinkImage.setImageResource(dr.getImageId());
        drinkName.setText(dr.getName());
        drinkDescr.setText(dr.getDescription());
        double p = dr.getPrice();
        drinkPrice.setText(String.format("%.2f", p));

        cart_bnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO add to cart
                user.getCart().addProduct(drinks.get(position));
                String chosenDrink = drinkName.getText().toString() + " is added to your cart!" ;
                Toast.makeText(v.getContext(), chosenDrink, Toast.LENGTH_SHORT).show();


            }
        });

        return row;
    }
}
