package com.example.wanglei.findjob.mvp.view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wanglei.findjob.mvp.ZhihuDailyContract;

/**
 * Created by wanglei on 18-3-21.
 */

public class ZhihuDialyFragment extends Fragment implements ZhihuDailyContract.View {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public static ZhihuDialyFragment getInstance(){

        return new ZhihuDialyFragment();

    }
    @Override
    public void setPresenter(ZhihuDailyContract.Presenter presenter) {

    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void showResult() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void stopLoading() {

    }
}
