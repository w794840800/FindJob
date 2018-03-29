package com.example.wanglei.findjob.utils;

import android.content.Context;

import com.example.wanglei.findjob.date.ZhihuContent;
import com.example.wanglei.findjob.date.ZhihuDialyNews;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.HTTP;

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
    public HttpClients(){

       //mContext = context;
   }

   public static class SingleHolder{

        public static final HttpClients single = new HttpClients();


   }
   public static HttpClients getHttpClient(){

        return SingleHolder.single;

   }

   public void getZhiDialyNews(Observer<List<ZhihuDialyNews.StoriesBean>>observer, long date){
    mRetrofit = new Retrofit.Builder()
            .baseUrl(ZHIHU_DAILY_BASE)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();
            apiService = mRetrofit.create(ApiService.class);
           apiService.getNews(formatZhihuDailyDateLongToString(date))
                   .map(new Function<ZhihuDialyNews, List<ZhihuDialyNews.StoriesBean>>() {
                       @Override
                       public List<ZhihuDialyNews.StoriesBean> apply(ZhihuDialyNews zhihuDialyNews) throws Exception {
                           return zhihuDialyNews.getStories();
                       }
                   })
                   .doOnError(new Consumer<Throwable>() {
                       @Override
                       public void accept(Throwable throwable) throws Exception {
                           //如果请求失败去SQL获取

                       }
                   })
                   .subscribeOn(Schedulers.io())
                   .observeOn(AndroidSchedulers.mainThread())
                   .subscribe(observer);

    }




    public void getZhiHuContent(Observer<ZhihuContent>observer, int id){
        mRetrofit = new Retrofit.Builder()
                .baseUrl(ZHIHU_DAILY_BASE)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = mRetrofit.create(ApiService.class);
        apiService.getContent(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        /*apiService.getNews(formatZhihuDailyDateLongToString(date))
                .map(new Function<ZhihuDialyNews, List<ZhihuDialyNews.StoriesBean>>() {
                    @Override
                    public List<ZhihuDialyNews.StoriesBean> apply(ZhihuDialyNews zhihuDialyNews) throws Exception {
                        return zhihuDialyNews.getStories();
                    }
                })
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        //如果请求失败去SQL获取

                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())*/
                //.subscribe(observer);

    }
    public static String formatZhihuDailyDateLongToString(long date) {
        String sDate;
        Date d = new Date(date + 24*60*60*1000);
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        sDate = format.format(d);

        return sDate;
    }
    interface DataNotLoading{
        void dataNotLoading();
    }
}
