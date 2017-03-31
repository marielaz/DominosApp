package karikuncheva.dominosapp;

/**
 * Created by Mariela Zviskova on 10.3.2017 г..
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import karikuncheva.dominosapp.model.User;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    private User user;

    public PagerAdapter(FragmentManager fm, int NumOfTabs, User user) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        this.user =user;
    }

    @Override
    public Fragment getItem(int position) {

        Bundle b = new Bundle();
        b.putSerializable("user", user);

        switch (position) {
            case 0:
                PizzaFragment tab1 = new PizzaFragment();
                tab1.setArguments(b);
                return tab1;
            case 1:
                DessertFragment tab2 = new DessertFragment();
                tab2.setArguments(b);
                return tab2;
            case 2:
                DrinkFragment tab3 = new DrinkFragment();
                tab3.setArguments(b);
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {

        return mNumOfTabs;
    }
}