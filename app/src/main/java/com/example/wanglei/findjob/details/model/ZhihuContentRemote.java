package com.example.wanglei.findjob.details.model;

import com.example.wanglei.findjob.date.ZhihuContent;
import com.example.wanglei.findjob.interfeze.OnLoadContentCallback;
import com.example.wanglei.findjob.utils.HttpClients;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.http.PUT;

public class ZhihuContentRemote {

    public static ZhihuContentRemote mZhihuContentRemote;
    private ZhihuContentRemote(){

    }
    public static ZhihuContentRemote getmZhihuContent(){

        if (mZhihuContentRemote==null){

            synchronized (ZhihuContentRemote.class){

                if (mZhihuContentRemote==null){
                    mZhihuContentRemote = new ZhihuContentRemote();

                }

            }

        }
        return mZhihuContentRemote;
    }
    public void getZhihuContent(int id, final OnLoadContentCallback<ZhihuContent>callback){

        HttpClients.getHttpClient().getZhiHuContent(new Observer<ZhihuContent>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ZhihuContent zhihuContent) {

               // saveSQL(zhihuContent);
                callback.onNewsLoad(zhihuContent);

            }

            @Override
            public void onError(Throwable e) {

                callback.onNewsNotLoad(null);
            }

            @Override
            public void onComplete() {

            }
        },id);


    }
  public void saveSQL(ZhihuContent zhihuContent){


  }
}
