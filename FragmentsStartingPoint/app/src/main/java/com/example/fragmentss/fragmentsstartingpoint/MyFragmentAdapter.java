package com.example.fragmentss.fragmentsstartingpoint;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MyFragmentAdapter extends FragmentStatePagerAdapter {

    // Save here all the fragments
    private final List<Fragment> mFragmentList = new ArrayList<>();

    // Save here the fragment's names
    private final List<String> mFragmentTitleList= new ArrayList<>();

    public MyFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    /**
     * Add a fragment to the list
     * @param fragment
     * @param title
     */
    public void addFragment(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

}
