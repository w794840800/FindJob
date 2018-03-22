package com.example.wanglei.findjob.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by wanglei on 18-3-22.
 */

public class TImelineFragmentViewPagerAdapter extends FragmentPagerAdapter {

    ArrayList<Fragment>fragmentArrayList;
    ArrayList<String>stringArrayList;
    public TImelineFragmentViewPagerAdapter(FragmentManager fm,
                                            ArrayList<Fragment>fragmentArrayList1
    ,ArrayList<String>stringArrayList1) {
        super(fm);
    fragmentArrayList = fragmentArrayList1;
    stringArrayList  =stringArrayList1;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentArrayList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentArrayList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return stringArrayList.get(position);
    }
}

