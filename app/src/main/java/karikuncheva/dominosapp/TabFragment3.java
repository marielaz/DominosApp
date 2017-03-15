package karikuncheva.dominosapp;

/**
 * Created by Mariela Zviskova on 10.3.2017 г..
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import karikuncheva.dominosapp.R;
import karikuncheva.dominosapp.model.Shop;

public class TabFragment3 extends Fragment {

    ListView list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_fragment_3, container, false);
        list = (ListView) v.findViewById(R.id.listDrink);
        DrinkCustomAdapter adapter = new DrinkCustomAdapter(this.getContext(), Shop.getInstance().getDrinks());
        list.setAdapter(adapter);
        return v;
    }
}