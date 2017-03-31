

package karikuncheva.dominosapp;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import karikuncheva.dominosapp.model.User;
import karikuncheva.dominosapp.model.products.Pizza;

/**
 * Created by Mariela Zviskova on 10.3.2017 г..
 */

public class TabFragment1 extends Fragment {

    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_fragment_1, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view_pizza);

        User user = (User) getArguments().getSerializable("user");
        CustomAdapter adapter = new CustomAdapter(getActivity(), Shop.getInstance().getPizzas(), user);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        return v;

    }

}