package com.example.wanglei.findjob.mvp;

import com.example.wanglei.findjob.date.ZhihuDialyNews;
import com.example.wanglei.findjob.utils.HttpClients;

import io.reactivex.Observer;

/**
 * Created by wanglei on 18-3-22.
 */

public class ZhihuModel {

    private ZhihuModel(){}
    public static ZhihuModel getZhihuModel(){

        return new ZhihuModel();
    }

    public void loadZhihuMore(Observer<ZhihuDialyNews>observer,long date){

        HttpClients.getHttpClient().getZhiDialyNews(observer,date);

    }


}
