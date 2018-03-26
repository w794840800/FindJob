package com.example.wanglei.findjob.mvp;

import com.example.wanglei.findjob.date.ZhihuDialyNews;
import com.example.wanglei.findjob.interfeze.OnLoadContentCallback;
import com.example.wanglei.findjob.utils.DBManager;
import com.example.wanglei.findjob.utils.HttpClients;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by wanglei on 18-3-22.
 */

public class ZhihuModel {

    HashMap<Integer,ZhihuDialyNews.StoriesBean>cacheMap;
    private static ZhihuModel zhihuModel;
    private ZhihuModel(){

    }

    public static ZhihuModel getZhihuModel(){

        if (zhihuModel==null){

            synchronized (ZhihuModel.class){
                if (zhihuModel==null){
                    zhihuModel = new ZhihuModel();

                }

            }

        }
        return zhihuModel;
    }



    public void loadZhihuMore(Observer<ZhihuDialyNews>observer,long date){

        //HttpClients.getHttpClient().getZhiDialyNews(observer,date);

    }
    public void loadZhihuMore(final OnLoadContentCallback<List<ZhihuDialyNews.StoriesBean>>
                                      onLoadContentCallback, long date){

        HttpClients.getHttpClient().getZhiDialyNews(new Observer<List<ZhihuDialyNews.StoriesBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                onLoadContentCallback.onLoadStart();

            }

            @Override
            public void onNext(List<ZhihuDialyNews.StoriesBean> list) {
                //save cache
                saveCache(list);
                onLoadContentCallback.onNewsLoad(new ArrayList(cacheMap.values()));
                //save sql
                saveSQL(new ArrayList(cacheMap.values()));
            }

            @Override
            public void onError(Throwable e) {
                ArrayList<ZhihuDialyNews.StoriesBean> arrayList = DBManager.getInstance().queryAll();
                if (arrayList==null||arrayList.size()==0){
                    onLoadContentCallback.onNewsNotLoad(null);
                    return;
                }
                saveCache(arrayList);
                onLoadContentCallback.onNewsNotLoad(new ArrayList(cacheMap.values()));
            }

            @Override
            public void onComplete() {

            }
        }, date);

    }

    private void saveSQL(ArrayList arrayList) {
        if (arrayList.size()!=0)
        DBManager.getInstance().insert("zhihu",arrayList);
//        DBManager.getInstance().queryAll();
    }

    private void saveCache(List<ZhihuDialyNews.StoriesBean> list) {

        if (cacheMap==null){
            cacheMap = new LinkedHashMap<>();
        }
        for (ZhihuDialyNews.StoriesBean s:
             list) {
            cacheMap.put(s.getId(),s);
        }


    }



}
