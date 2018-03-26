package com.example.wanglei.findjob.interfeze;

import com.example.wanglei.findjob.date.ZhihuDialyNews;

import java.util.List;

/**
 * Created by wanglei on 18-3-23.
 */

public interface OnLoadContentCallback<T> {

    void onNewsLoad(T list);

    void onNewsNotLoad(T list);
    void onLoadStart();
}