package com.example.wanglei.findjob.mvp.Presenter;

import android.view.Gravity;

import com.example.wanglei.findjob.date.ZhihuDialyNews;
import com.example.wanglei.findjob.interfeze.OnLoadContentCallback;
import com.example.wanglei.findjob.mvp.ZhihuDailyContract;
import com.example.wanglei.findjob.mvp.ZhihuModel;
import com.example.wanglei.findjob.utils.DBManager;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by wanglei on 18-3-21.
 */

public class ZhihuDialyPresenter implements ZhihuDailyContract.Presenter {
    ZhihuModel zhihuModel;
    ZhihuDailyContract.View mView;
    Disposable mDispose;
    public ZhihuDialyPresenter( ZhihuDailyContract.View view){

        mView = view;
        mView.setPresenter(this);
    }
    @Override
    public void start() {

    }

    @Override
    public void loadDate() {

    }

    @Override
    public void refresh() {

    }

    @Override
    public void loadMore(long date, final boolean isLoadingMore) {
    zhihuModel = ZhihuModel.getZhihuModel();

        zhihuModel.loadZhihuMore(new OnLoadContentCallback<List<ZhihuDialyNews.StoriesBean>>() {
            @Override
            public void onNewsLoad(List<ZhihuDialyNews.StoriesBean> list) {

                mView.showResult(list);
                mView.setLoadingIndicator(false);
            }

            @Override
            public void onNewsNotLoad(List<ZhihuDialyNews.StoriesBean> list) {
                //DBManager.getInstance().queryAll();
                if (list==null||list.size()==0){

                    mView.setLoadingIndicator(false);
                    return;
                }
                mView.showResult(list);

                    mView.setLoadingIndicator(false);




            }


            @Override
            public void onLoadStart() {
         if (isLoadingMore) {
             mView.setLoadingIndicator(true);
         }
            }
        },date);
    /*  zhihuModel.loadZhihuMore(new Observer<ZhihuDialyNews>() {
        @Override
        public void onSubscribe(Disposable d) {
            mDispose = d;
            mView.setLoadingIndicator(true);
        }

        @Override
        public void onNext(ZhihuDialyNews zhihuDialyNews) {


        }

        @Override
        public void onError(Throwable e) {
            mView.setLoadingIndicator(false);
        }

        @Override
        public void onComplete() {
            mView.setLoadingIndicator(false);
        }
    },date);*/

    }
}
