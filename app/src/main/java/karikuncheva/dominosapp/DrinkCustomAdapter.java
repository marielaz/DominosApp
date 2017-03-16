package karikuncheva.dominosapp;
import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import karikuncheva.dominosapp.model.Shop;
import karikuncheva.dominosapp.model.products.Dessert;
import karikuncheva.dominosapp.model.products.Drink;

/**
 * Created by Mariela Zviskova on 14.3.2017 г..
 */

public class DrinkCustomAdapter extends ArrayAdapter<String> {

    Activity activity;
    List<Drink> drinks;
    Shop shop = Shop.getInstance();

    public DrinkCustomAdapter(Activity activity, List<Drink> drinks){
        super(activity, R.layout.single_row_des_dr);
        this.activity = activity;
        this.drinks = drinks;
    }

    @Override
    public int getCount() {
        return drinks.size();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.single_row_des_dr, parent, false);

        ImageView drinkImage = (ImageView) row.findViewById(R.id.image);
        TextView drinkName = (TextView) row.findViewById(R.id.name);
        TextView drinkDescr = (TextView) row.findViewById(R.id.description);
        TextView drinkPrice = (TextView) row.findViewById(R.id.price);
        drinkImage.setImageResource(drinks.get(position).getImageId());
        drinkName.setText(drinks.get(position).getName());
        drinkDescr.setText(drinks.get(position).getDescription());
        double p = drinks.get(position).getPrice();
        drinkPrice.setText(String.format("%.2f", p));
        return row;
    }
}
