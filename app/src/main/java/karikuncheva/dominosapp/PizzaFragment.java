

package karikuncheva.dominosapp;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import karikuncheva.dominosapp.model.Shop;
import karikuncheva.dominosapp.model.User;

/**
 * Created by Mariela Zviskova on 10.3.2017 г..
 */

public class PizzaFragment extends Fragment {

    RecyclerView recyclerView;

    public interface ProductsCommunicator{
        void increment() ;
        void decrement ();
        void clear();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pizza, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view_pizza);


        CustomAdapter adapter = new CustomAdapter(getActivity(), Shop.getInstance().getPizzas());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        return v;

    }

}