package karikuncheva.dominosapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import karikuncheva.dominosapp.model.User;

/**
 * Created by Mariela Zviskova on 7.4.2017 г..
 */

public class ProfilePagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public ProfilePagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {


        switch (position) {
            case 0:
                ProfileFragment tab1 = new ProfileFragment();
                return tab1;
            case 1:
                AddressFragment tab2 = new AddressFragment();
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {

        return mNumOfTabs;
    }
}