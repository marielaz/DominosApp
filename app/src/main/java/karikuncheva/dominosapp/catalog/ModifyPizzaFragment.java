package karikuncheva.dominosapp.catalog;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import karikuncheva.dominosapp.R;
import karikuncheva.dominosapp.model.products.Pizza;

/**
 * A simple {@link Fragment} subclass.
 */
public class ModifyPizzaFragment extends Fragment {

    interface ModifyCommunicator {
        void modifyPrice(double sum);
    }

    List<RadioButton> pizza_type_bnts = new ArrayList<RadioButton>();
    List<RadioButton> sauces_dips_bnts = new ArrayList<RadioButton>();
    RadioButton check_cream, check_tomato, check_bbq;
    TextView tomato_sauce, cream_sauce, bbq_sauce;
    Button small_bnt, med_bnt, large_bnt;
    RadioButton trad_bnt, ital_bnt, thin_bnt;
    static Pizza pizza;
    RecyclerView recyclerView;
    private double tempSizeChecked;
    static double sum;
    private double small;
    private double midd;
    private ModifyCommunicator mc;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_modify, container, false);

        if (getActivity().getIntent().getExtras() != null) {
            Bundle b = getActivity().getIntent().getExtras();
            if (b.getSerializable("pizza") != null) {
                pizza = (Pizza) b.getSerializable("pizza");
                pizza.setSize(pizza.getSize());
                pizza.setType(pizza.getType());
            }
        }

        mc = (ModifyCommunicator) getActivity();
        tempSizeChecked = pizza.getPrice();
        sum = tempSizeChecked;
        small = pizza.getPrice() - 1.50;
        midd = pizza.getPrice() - 1.00;

        recyclerView = (RecyclerView) v.findViewById(R.id.ingredients_recycler_view);
        ModifyAdapter adapter = new ModifyAdapter(getActivity(), pizza, mc);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        recyclerView.setNestedScrollingEnabled(false);

        tomato_sauce = (TextView) v.findViewById(R.id.tomato_sauce_tv);
        cream_sauce = (TextView) v.findViewById(R.id.cream_sauce_tv);
        bbq_sauce = (TextView) v.findViewById(R.id.bbq_sauce_tv);

        trad_bnt = (RadioButton) v.findViewById(R.id.trad_bnt);
        ital_bnt = (RadioButton) v.findViewById(R.id.ital_bnt);
        thin_bnt = (RadioButton) v.findViewById(R.id.thin_bnt);

        pizza_type_bnts.add(trad_bnt);
        pizza_type_bnts.add(ital_bnt);
        pizza_type_bnts.add(thin_bnt);

        check_tomato = (RadioButton) v.findViewById(R.id.check_tomato);
        check_cream = (RadioButton) v.findViewById(R.id.check_cream);
        check_bbq = (RadioButton) v.findViewById(R.id.check_bbq);

        sauces_dips_bnts.add(check_tomato);
        sauces_dips_bnts.add(check_cream);
        sauces_dips_bnts.add(check_bbq);

        if (pizza.getIngredients().contains("Tomato sauce")) {
            check_tomato.setChecked(true);
        } else if (pizza.getIngredients().contains("BBQ sauce")) {
            check_bbq.setChecked(true);
        } else {
            check_cream.setChecked(true);
        }
        small_bnt = (Button) v.findViewById(R.id.small);
        med_bnt = (Button) v.findViewById(R.id.med);
        large_bnt = (Button) v.findViewById(R.id.large);

        trad_bnt.setChecked(true);
        large_bnt.setPressed(true);

        small_bnt.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                small_bnt.setPressed(true);
                pizza.setSize("Small");
                med_bnt.setPressed(false);
                large_bnt.setPressed(false);

                sum -= tempSizeChecked;
                sum += small;
                tempSizeChecked = small;
                mc.modifyPrice(sum);
                return true;
            }

        });
        med_bnt.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                med_bnt.setPressed(true);
                pizza.setSize("Medium");
                small_bnt.setPressed(false);
                large_bnt.setPressed(false);

                sum -= tempSizeChecked;
                sum += midd;
                tempSizeChecked = midd;
                mc.modifyPrice(sum);
                return true;
            }
        });
        large_bnt.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                large_bnt.setPressed(true);
                if (!pizza.getSize().equals("Large")) {
                    pizza.setSize("Large");
                }
                small_bnt.setPressed(false);
                med_bnt.setPressed(false);

                sum -= tempSizeChecked;
                sum += pizza.getPrice();
                tempSizeChecked = pizza.getPrice();
                mc.modifyPrice(sum);
                return true;
            }
        });
        for (RadioButton radioButton : sauces_dips_bnts) {
            radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) processRadioButtonClickSauces(buttonView);
                }

            });
        }

        for (RadioButton button : pizza_type_bnts) {
            button.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) processRadioButtonClickCrust(buttonView);
                }

            });
        }
        ital_bnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pizza.setType("Italian style");

            }
        });
        trad_bnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pizza.setType("Traditional");

            }
        });
        thin_bnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pizza.setType("Thin and crispy");

            }
        });

        return v;
    }

    private void processRadioButtonClickCrust(CompoundButton buttonView) {
        for (RadioButton button : pizza_type_bnts) {
            if (button != buttonView) button.setChecked(false);
        }
    }

    private void processRadioButtonClickSauces(CompoundButton buttonView) {
        for (RadioButton button : sauces_dips_bnts) {
            if (button != buttonView) button.setChecked(false);
        }
    }
}