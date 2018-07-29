package com.be.mypals.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.be.mypals.Fragments.TabFragmentAdopt;
import com.be.mypals.Fragments.TabFragmentAll;
import com.be.mypals.Fragments.TabFragmentFoster;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                TabFragmentAll tab1 = new TabFragmentAll();
                return tab1;
            case 1:
                TabFragmentAdopt tab2 = new TabFragmentAdopt();
                return tab2;
            case 2:
                TabFragmentFoster tab3 = new TabFragmentFoster();
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