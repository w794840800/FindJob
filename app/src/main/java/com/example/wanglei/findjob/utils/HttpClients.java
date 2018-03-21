package com.example.wanglei.findjob.utils;

import android.content.Context;

import com.example.wanglei.findjob.date.ZhihuDialyNews;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wanglei on 18-3-21.
 */

public class HttpClients {
    Retrofit mRetrofit;
  public  String ZHIHU_DAILY_BASE = "https://news-at.zhihu.com/api/4/news/";
    String DOUBAN_MOMENT_BASE = "https://moment.douban.com/api/";

    String GUOKR_HANDPICK_BASE = "http://apis.guokr.com/minisite/";
    Context mContext;
   ApiService apiService;
    public HttpClients(Context context){

       mContext = context;


   }
public void getZhiDialyNews(Observer<ZhihuDialyNews>observer,long date){
    mRetrofit = new Retrofit.Builder()
            .baseUrl(ZHIHU_DAILY_BASE)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();
            apiService = mRetrofit.create(ApiService.class);
           apiService.getNews(String.valueOf(date))
                   .subscribeOn(Schedulers.io())
                   .observeOn(AndroidSchedulers.mainThread())
                   .subscribe(observer);


    }
}
