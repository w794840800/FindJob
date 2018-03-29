package com.example.wanglei.findjob.details.presenter;

import com.example.wanglei.findjob.date.ZhihuContent;
import com.example.wanglei.findjob.details.DetailsConstract;
import com.example.wanglei.findjob.details.model.ZhihuContentRemote;
import com.example.wanglei.findjob.details.model.ZhihuContentReposity;
import com.example.wanglei.findjob.interfeze.OnLoadContentCallback;

public class DetailPresenter implements DetailsConstract.Presenter {
    @Override
    public void start() {

    }

    @Override
    public void loadContent(int id) {

        ZhihuContentReposity.getZhihuContentReposity().getZhihuContent(id, new OnLoadContentCallback<ZhihuContent>() {
            @Override
            public void onNewsLoad(ZhihuContent list) {

            }

            @Override
            public void onNewsNotLoad(ZhihuContent list) {

            }

            @Override
            public void onLoadStart() {

            }
        });


    }
}
