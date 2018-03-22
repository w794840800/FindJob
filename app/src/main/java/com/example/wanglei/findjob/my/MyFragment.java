package com.example.wanglei.findjob.my;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wanglei.findjob.R;
import com.example.wanglei.findjob.favorite.FavoriteFragment;

/**
 * Created by wanglei on 18-3-22.
 */

public class MyFragment extends Fragment {

    public static MyFragment getInstance(){

        return new MyFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.test,container,false);
        return view;
    }


}
