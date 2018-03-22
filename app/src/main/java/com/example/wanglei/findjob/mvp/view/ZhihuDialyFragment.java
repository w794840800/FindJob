package com.example.wanglei.findjob.mvp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wanglei.findjob.R;
import com.example.wanglei.findjob.date.ZhihuDialyNews;
import com.example.wanglei.findjob.mvp.Presenter.ZhihuDialyPresenter;
import com.example.wanglei.findjob.mvp.ZhihuDailyContract;

import java.util.Calendar;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by wanglei on 18-3-21.
 */

public class ZhihuDialyFragment extends Fragment implements ZhihuDailyContract.View {
   int year;
   int month;
   int day;
    ZhihuDailyContract.Presenter mZhihuPresenter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //View view = inflater.inflate(R.layout.fragment_timeline,container,false);

        return null;
    }

    public static ZhihuDialyFragment getInstance(){

        return new ZhihuDialyFragment();

    }
    @Override
    public void setPresenter(ZhihuDailyContract.Presenter presenter) {

        mZhihuPresenter = presenter;
    }

    @Override
    public void setLoadingIndicator(boolean isLoading) {


    }


    @Override
    public void showResult(List<ZhihuDialyNews> zhihuDialyNews) {



    }

    @Override
    public void onResume() {
        super.onResume();
        Calendar calendar = Calendar.getInstance();

      year = calendar.get(Calendar.YEAR);
      month = calendar.get(Calendar.MONTH);
      day = calendar.get(Calendar.DAY_OF_MONTH);
        Log.d(TAG, "onResume: year = "+year+" month= "+month+" day= "+day
        +" "+calendar.getTimeInMillis());
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
