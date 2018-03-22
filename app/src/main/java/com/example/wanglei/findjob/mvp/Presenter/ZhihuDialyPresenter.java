package com.example.wanglei.findjob.mvp.Presenter;

import com.example.wanglei.findjob.date.ZhihuDialyNews;
import com.example.wanglei.findjob.mvp.ZhihuDailyContract;
import com.example.wanglei.findjob.mvp.ZhihuModel;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by wanglei on 18-3-21.
 */

public class ZhihuDialyPresenter implements ZhihuDailyContract.Presenter {
    ZhihuModel zhihuModel;
    ZhihuDailyContract.View mView;
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
    public void loadMore(long date) {
    zhihuModel = ZhihuModel.getZhihuModel();
    zhihuModel.loadZhihuMore(new Observer<ZhihuDialyNews>() {
        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(ZhihuDialyNews zhihuDialyNews) {


        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    },date);

    }
}
