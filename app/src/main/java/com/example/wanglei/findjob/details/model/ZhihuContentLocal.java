package com.example.wanglei.findjob.details.model;

import com.example.wanglei.findjob.date.ZhihuContent;
import com.example.wanglei.findjob.interfeze.OnLoadContentCallback;
import com.example.wanglei.findjob.utils.DBManager;

public class ZhihuContentLocal {

    public static ZhihuContentLocal mZhihuContentLocal;
    private ZhihuContentLocal(){

    }
    public static ZhihuContentLocal getmZhihuContentLocal(){

        if (mZhihuContentLocal==null){

            synchronized (ZhihuContent.class){

                if (mZhihuContentLocal==null){
                    mZhihuContentLocal = new ZhihuContentLocal();

                }

            }

        }
        return mZhihuContentLocal;
    }

    public String getZhihuContent(int id, OnLoadContentCallback<ZhihuContent>callback){

       return DBManager.getInstance().queryIdGetContent(id);

    }

    public void saveZhihuContent(int id,ZhihuContent list) {

        DBManager.getInstance().insertZhihuContent(id,list);
    }
}
