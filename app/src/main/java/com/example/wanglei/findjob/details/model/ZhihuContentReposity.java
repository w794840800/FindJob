package com.example.wanglei.findjob.details.model;

import com.example.wanglei.findjob.date.ZhihuContent;
import com.example.wanglei.findjob.interfeze.OnLoadContentCallback;

public class ZhihuContentReposity {

    private ZhihuContent content;
    public static ZhihuContentReposity mZhihuContentReposity;

    private ZhihuContentReposity(){


    }

    public static ZhihuContentReposity getZhihuContentReposity(){
        if (mZhihuContentReposity==null){

            synchronized (ZhihuContentReposity.class){

                if (mZhihuContentReposity==null){
                    mZhihuContentReposity = new ZhihuContentReposity();

                }

            }

        }
        return mZhihuContentReposity;




    }

    public void getZhihuContent(final int id, final OnLoadContentCallback<ZhihuContent>callback){
        if (content!=null){
            callback.onNewsLoad(content);
            return;
        }

          ZhihuContentRemote.getmZhihuContent()
                  .getZhihuContent(id, new OnLoadContentCallback<ZhihuContent>() {
                      @Override
                      public void onNewsLoad(ZhihuContent list) {

                          content = list;
                          saveContent(id,list);
                          callback.onNewsLoad(list);
                      }

                      @Override
                      public void onNewsNotLoad(ZhihuContent list) {

                          ZhihuContentLocal.getmZhihuContentLocal().getZhihuContent(id,
                          new OnLoadContentCallback<ZhihuContent>() {

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

                      @Override
                      public void onLoadStart() {

                      }
                  });


    }

    private void saveContent(int id, ZhihuContent list) {
        ZhihuContentLocal.getmZhihuContentLocal().saveZhihuContent(id,list);

    }


}
