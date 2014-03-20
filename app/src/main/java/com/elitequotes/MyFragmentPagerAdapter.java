package com.elitequotes;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by veinhorn on 20.3.14.
 */
public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    private final static int QUOTE_FRAGMENT = 0;
    private final static int FAVOURITE_FRAGMENT = 1;
    private final static int TABS_NUMBER = 2;

    public MyFragmentPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int i) {
        if(i == QUOTE_FRAGMENT) {
            return new QuoteFragment();
        } else if(i == FAVOURITE_FRAGMENT) {
            return new FavouriteFragment();
        }
        return new Fragment();
    }

    @Override
    public int getCount() {
        return TABS_NUMBER;
    }
}
