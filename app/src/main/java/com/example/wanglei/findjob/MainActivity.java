package com.example.wanglei.findjob;

import android.annotation.SuppressLint;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.wanglei.findjob.favorite.FavoriteFragment;
import com.example.wanglei.findjob.my.MyFragment;
import com.example.wanglei.findjob.timeline.TimelineFragment;

public class MainActivity extends AppCompatActivity {
    TimelineFragment timelineFragment;
    FavoriteFragment favoriteFragment;
    MyFragment myFragment;
    BottomNavigationView menuView;
    int select_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      menuView = (BottomNavigationView)
              findViewById(R.id.bottomNavi);
        menuView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
               showFragment(item.getItemId());

                return true;
            }
        });
       if (savedInstanceState!=null){
           FragmentManager fragmentManager = getSupportFragmentManager();
           timelineFragment = (TimelineFragment) fragmentManager.getFragment(savedInstanceState,TimelineFragment.class.getSimpleName()
           );
            favoriteFragment = (FavoriteFragment) fragmentManager.getFragment(savedInstanceState,FavoriteFragment.class.getSimpleName());
           myFragment = (MyFragment) fragmentManager.getFragment(savedInstanceState,MyFragment.class.getSimpleName());
       }  else{
           timelineFragment = TimelineFragment.getTimelineFragment();
           favoriteFragment = FavoriteFragment.getInstance();
           myFragment = MyFragment.getInstance();

       }
       if (!timelineFragment.isAdded()){

           getSupportFragmentManager().beginTransaction().add(R.id.container,timelineFragment)
                   .commit();
       }
        if (!favoriteFragment.isAdded()){

            getSupportFragmentManager().beginTransaction().add(R.id.container,favoriteFragment)
                    .commit();
        }
        if (!myFragment.isAdded()){

            getSupportFragmentManager().beginTransaction().add(R.id.container,myFragment)
                    .commit();
        }



      if (savedInstanceState!=null){
           select_id = savedInstanceState.getInt("BottomNavigationMenuView_ID",R.id.timeline);
          showFragment(select_id);

      }else{
          showFragment(R.id.timeline);

      }
    }

    private void showFragment(int select_id) {
        FragmentManager fragmentManager = getSupportFragmentManager();
    switch (select_id){

        case R.id.timeline:
            fragmentManager.beginTransaction().show(timelineFragment)
                    .hide(favoriteFragment)
                    .hide(myFragment)
                    .commit();
            break;
        case R.id.favorite:
            fragmentManager.beginTransaction().show(favoriteFragment)
                    .hide(timelineFragment)
                    .hide(myFragment)
                    .commit();
            break;
        case R.id.my:
            fragmentManager.beginTransaction().hide(favoriteFragment)
                    .hide(timelineFragment)
                    .show(myFragment)
                    .commit();
            break;
    }

    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putInt("BottomNavigationMenuView_ID",menuView.getSelectedItemId());
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (timelineFragment.isAdded()) {
            fragmentManager.putFragment(outState, TimelineFragment.class.getSimpleName(), timelineFragment);
        }
        if (favoriteFragment.isAdded()) {
            fragmentManager.putFragment(outState, FavoriteFragment.class.getSimpleName(), favoriteFragment);
        }
        if (myFragment.isAdded()) {
            fragmentManager.putFragment(outState, MyFragment.class.getSimpleName(), myFragment);
        }
    }
}
