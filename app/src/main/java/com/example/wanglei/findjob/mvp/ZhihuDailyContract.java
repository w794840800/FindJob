package com.example.wanglei.findjob.mvp;

import com.example.wanglei.findjob.BasePresenter;
import com.example.wanglei.findjob.BaseView;
import com.example.wanglei.findjob.date.ZhihuDialyNews;

import java.util.List;

/**
 * Created by wanglei on 18-3-21.
 */

public interface ZhihuDailyContract {
    interface Presenter extends BasePresenter{
        //请求数据
        void loadDate();

        void refresh();
        void loadMore(long date);
        //void showDetail(long date);

    }
    interface View extends BaseView<Presenter>{

        //
        void showResult(List<ZhihuDialyNews>zhihuDialyNews);
        void showError();
        void showLoading();
        void stopLoading();

    }

}
