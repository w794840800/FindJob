package com.example.wanglei.findjob;

import android.view.View;

/**
 * Created by wanglei on 18-3-21.
 */

public interface BaseView<T> {

    void setPresenter(T presenter);
    void  setLoadingIndicator(boolean isLoading);
}
