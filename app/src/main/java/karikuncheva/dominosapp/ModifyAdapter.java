package karikuncheva.dominosapp;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
    private ArrayList<Integer> tempArr = new ArrayList<Integer>();
    ModifyPizzaFragment.ModifyCommunicator mc;
    private int i = 0;

    public ModifyAdapter(Activity activity, Pizza pizza, ModifyPizzaFragment.ModifyCommunicator mc) {
        this.activity = activity;
        this.pizza = pizza;
        ingredients = Shop.getInstance().getIngr();
        this.mc = mc;
        for (i = 0; i < Shop.getInstance().getIngr().size(); i++) {
            tempArr.add(0);
        }
    }

    @Override
    public ModifyAdapter.ModifyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(activity);
        View row = li.inflate(R.layout.single_row_ingredients, parent, false);
        ModifyAdapter.ModifyViewHolder vh = new ModifyAdapter.ModifyViewHolder(row);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ModifyViewHolder vh, final int position) {

        final String ingr = ingredients.get(position);
        vh.ingredient_name.setText(ingr);
        if (pizza.getIngredients().contains(ingr)) {
            vh.check_ingr.setChecked(true);
        }

        vh.check_ingr.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!pizza.getIngredients().contains(ingr) && isChecked == true) {
                    ModifyPizzaFragment.sum += 0.60;
                    mc.modifyPrice(ModifyPizzaFragment.sum);
                    tempArr.set(position, 1);
                }

                if (!pizza.getIngredients().contains(ingr) && isChecked == false) {
                    if (tempArr.get(position) == 1) {
                        ModifyPizzaFragment.sum -= 0.60;
                        mc.modifyPrice(ModifyPizzaFragment.sum);
                        tempArr.set(position, 0);
                    }
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    class ModifyViewHolder extends RecyclerView.ViewHolder {
        TextView ingredient_name;
        CheckBox check_ingr;

        ModifyViewHolder(View row) {
            super(row);
            ingredient_name = (TextView) row.findViewById(R.id.ingredient_name);
            check_ingr = (CheckBox) row.findViewById(R.id.check_ingredient);
        }
    }
}
