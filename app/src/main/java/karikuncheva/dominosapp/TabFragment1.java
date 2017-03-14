

package karikuncheva.dominosapp;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import karikuncheva.dominosapp.R;
import karikuncheva.dominosapp.model.Shop;
import karikuncheva.dominosapp.model.products.Pizza;

/**
 * Created by Mariela Zviskova on 10.3.2017 г..
 */

public class TabFragment1 extends Fragment {

    private Shop shop;
    ListView list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.tab_fragment_1, container, false);
        list = (ListView) v.findViewById(R.id.listView);
        CustomAdapter adapter = new CustomAdapter(this.getContext(), Shop.getInstance().getPizzas());
        list.setAdapter(adapter);
        return v;

    }

}