package com.example.wanglei.findjob.utils;

import com.example.wanglei.findjob.date.ZhihuContent;
import com.example.wanglei.findjob.date.ZhihuDialyNews;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by wanglei on 18-3-21.
 */

public interface ApiService {
    String ZHIHU_DAILY_BASE = "https://news-at.zhihu.com/api/4/news/";

    String DOUBAN_MOMENT_BASE = "https://moment.douban.com/api/";

    String GUOKR_HANDPICK_BASE = "http://apis.guokr.com/minisite/";
    @GET("before/{date}")
    Observable<ZhihuDialyNews>getNews(@Path("date")String date);
    @GET("news/{id}")
    Observable<ZhihuContent>getContent(@Path("id")int id);
}



