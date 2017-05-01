

package karikuncheva.dominosapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Mariela Zviskova on 10.3.2017 г..
 */

public class PizzaFragment extends Fragment {

    RecyclerView recyclerView;

    interface ProductsCommunicator {
        void changeCount(int count);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pizza, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view_pizza);


        CustomAdapter adapter = new CustomAdapter(getActivity(), DBManager.getInstance(getActivity()).pizzas);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        return v;

    }

}