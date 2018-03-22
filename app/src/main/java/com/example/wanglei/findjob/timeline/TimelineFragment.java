package com.example.wanglei.findjob.timeline;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wanglei.findjob.R;
import com.example.wanglei.findjob.adapter.TImelineFragmentViewPagerAdapter;
import com.example.wanglei.findjob.favorite.FavoriteFragment;
import com.example.wanglei.findjob.mvp.view.DoubanFragment;
import com.example.wanglei.findjob.mvp.view.GuokeFragment;
import com.example.wanglei.findjob.mvp.view.ZhihuDialyFragment;
import com.example.wanglei.findjob.my.MyFragment;

import java.util.ArrayList;

/**
 * Created by wanglei on 18-3-22.
 */

public class TimelineFragment extends Fragment {
    ArrayList<Fragment> fragmentArrayList;
    ArrayList<String>stringArrayList;
    ViewPager viewPager;
    TabLayout tabLayout;
    ZhihuDialyFragment zhihuDialyFragment;
    DoubanFragment doubanFragment;
    GuokeFragment guokeFragment;
    FloatingActionButton fab;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timeline,container,false);
        initView(view);
        return view;
    }

    private void initView(View view) {

        viewPager =(ViewPager) view.findViewById(R.id.viewpager);
        tabLayout =(TabLayout) view.findViewById(R.id.tablayout);
         viewPager.setAdapter(new TImelineFragmentViewPagerAdapter(getChildFragmentManager(),fragmentArrayList,stringArrayList));
         tabLayout.setupWithViewPager(viewPager);
          fab = (FloatingActionButton) view.findViewById(R.id.fab);
        viewPager.setOffscreenPageLimit(3);
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
              if (position!=2){
                  fab.show();

              }else {
                  fab.show();

              }
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentArrayList = new ArrayList<>();
        stringArrayList = new ArrayList<>();
       if (savedInstanceState!=null){

           FragmentManager manager = getChildFragmentManager();

           zhihuDialyFragment= (ZhihuDialyFragment) manager.getFragment(savedInstanceState,ZhihuDialyFragment.class.getSimpleName());
           doubanFragment = (DoubanFragment) manager.getFragment(savedInstanceState, DoubanFragment.class.getSimpleName());
           guokeFragment = (GuokeFragment) manager.getFragment(savedInstanceState, GuokeFragment.class.getSimpleName());
       }else {
           zhihuDialyFragment = ZhihuDialyFragment.getInstance();
           doubanFragment = DoubanFragment.getInstance();
           guokeFragment = GuokeFragment.getInstance();
       }
        fragmentArrayList.add(zhihuDialyFragment);
       fragmentArrayList.add(doubanFragment);
       fragmentArrayList.add(guokeFragment);
       stringArrayList.add("知乎精选");
       stringArrayList.add("豆瓣一刻");
       stringArrayList.add("果壳精选");

    }
    public static TimelineFragment getTimelineFragment(){
        return new TimelineFragment();

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
       FragmentManager fragmentManager = getFragmentManager();
        if (zhihuDialyFragment.isAdded()) {
            fragmentManager.putFragment(outState,ZhihuDialyFragment.class.getSimpleName(),zhihuDialyFragment);
        }
        if (doubanFragment.isAdded()){
            fragmentManager.putFragment(outState,DoubanFragment.class.getSimpleName(),doubanFragment);
        }
        if (guokeFragment.isAdded()){
            fragmentManager.putFragment(outState,GuokeFragment.class.getSimpleName(),guokeFragment);
        }


    }

}
