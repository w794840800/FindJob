package com.example.wanglei.findjob.details;

import com.example.wanglei.findjob.BasePresenter;
import com.example.wanglei.findjob.BaseView;

/**
 * Created by wanglei on 18-3-26.
 */

public interface DetailsConstract {

    interface View extends BaseView<Presenter>{
    //void Show
    }
    interface Presenter extends BasePresenter{
    void loadContent(int id);
    }
}
