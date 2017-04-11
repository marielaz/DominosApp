package karikuncheva.dominosapp;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;
import java.util.ArrayList;
import karikuncheva.dominosapp.model.Shop;
import karikuncheva.dominosapp.model.products.Pizza;


/**
 * Created by Karina Kuncheva on 4/9/2017.
 */

public class ModifyAdapter extends RecyclerView.Adapter<ModifyAdapter.ModifyViewHolder> {

    private Activity activity;
    private Pizza pizza;
    private ArrayList<String> ingredients;
    public ModifyAdapter(Activity activity, Pizza pizza) {
        this.activity = activity;
        this.pizza = pizza;
        ingredients = Shop.getInstance().getIngr();
    }

    @Override
    public ModifyAdapter.ModifyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(activity);
        View row = li.inflate(R.layout.single_row_ingredients, parent, false);
        ModifyAdapter.ModifyViewHolder vh = new ModifyAdapter.ModifyViewHolder(row);
        return vh;
    }

    @Override
    public void onBindViewHolder(ModifyViewHolder vh, int position) {

        String ingr = ingredients.get(position);
        vh.ingredient_name.setText(ingr);
        if (pizza.getIngredients().contains(ingr)){
            //vh.small_ingred_rb.setChecked(true);
            vh.check_ingr.setChecked(true);
        }
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    class ModifyViewHolder extends RecyclerView.ViewHolder {
        TextView ingredient_name;
//        RadioButton small_ingred_rb;
//        RadioButton large_ingred_rb;
        CheckBox check_ingr;

        ModifyViewHolder(View row) {
            super(row);
            ingredient_name = (TextView) row.findViewById(R.id.ingredient_name);
//            small_ingred_rb = (RadioButton) row.findViewById(R.id.small_ingred_rb);
//            large_ingred_rb = (RadioButton) row.findViewById(R.id.large_ingred_rb);
            check_ingr = (CheckBox) row.findViewById(R.id.check_ingredient);
        }
    }
}
