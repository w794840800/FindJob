package com.example.wanglei.findjob.mvp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wanglei.findjob.R;

/**
 * Created by wanglei on 18-3-22.
 */

public class GuokeFragment extends Fragment {

    public static GuokeFragment getInstance(){
        return new GuokeFragment();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.test,container,false);
        return view;
    }
}
