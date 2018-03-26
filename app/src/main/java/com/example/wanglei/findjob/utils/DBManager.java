package com.example.wanglei.findjob.utils;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.wanglei.findjob.database.DataBaseHelper;
import com.example.wanglei.findjob.date.ZhihuDialyNews;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wanglei on 18-3-24.
 */

public class DBManager  {
private static DBManager manager;
private DataBaseHelper dataBaseHelper;
private SQLiteDatabase sqLiteOpenHelper;
private DBManager(){

    dataBaseHelper = new DataBaseHelper(BaseApplication.mContext,"news.db",null,1);
       sqLiteOpenHelper = dataBaseHelper.getWritableDatabase();
}
public static DBManager getInstance(){
    if (manager==null){
        synchronized (DBManager.class){
            if (manager==null){
                manager = new DBManager();
            }


        }

    }

    return manager;

    }

    public void insert(String tableName,ArrayList <ZhihuDialyNews.StoriesBean>arrayList){

    //sqLiteOpenHelper.insert("insert")
        ZhihuDialyNews.StoriesBean storiesBean;
        ContentValues contentValues = new ContentValues();
        for (int i = 0; i < arrayList.size(); i++) {
            storiesBean = arrayList.get(i);
            contentValues.put("zhihu_id",storiesBean.getId());
            contentValues.put("zhihu_title",storiesBean.getTitle());
            contentValues.put("zhihu_content","");
            contentValues.put("zhihu_pic",storiesBean.getImages().get(0));
            //contentValues.put();
            sqLiteOpenHelper.insert("zhihu",null,contentValues);
            contentValues.clear();

        }



}
    public boolean queryId(int id){

        Cursor cursor = sqLiteOpenHelper.query("zhihu",null,null,null,null,null,null);
        if (cursor.moveToFirst()){

            do {

                int index= cursor.getInt(cursor.getColumnIndex("zhihu_id"));
             if (index==id){
    return true;
}
            }while (cursor.moveToNext());
        }
    cursor.close();
    return false;
    }
    public ArrayList<ZhihuDialyNews.StoriesBean> queryAll(){

        ArrayList<ZhihuDialyNews.StoriesBean>mArrayList = new ArrayList<>();
        Cursor cursor = sqLiteOpenHelper.query("zhihu",null,null,null,null,null,null);
        if (cursor==null||cursor.getColumnCount()!=0){
            return null;

        }
        cursor.moveToFirst();
        ZhihuDialyNews.StoriesBean storiesBean;
         if (cursor.moveToFirst()){
           storiesBean = new ZhihuDialyNews.StoriesBean() ;
           do {
                 Log.d("wanglei ", "queryAll: zhihu_title= "+cursor.getString(cursor.getColumnIndex("zhihu_title")));

                 storiesBean.setId(cursor.getInt(cursor.getColumnIndex("zhihu_id")));
                 storiesBean.setTitle(cursor.getString(cursor.getColumnIndex("zhihu_title")));
                  String picUrl = cursor.getString(cursor.getColumnIndex("zhihu_pic"));
                   if (picUrl!=null) {
                       storiesBean.getImages().add(picUrl);
                   }
                   mArrayList.add(storiesBean);
             }while (cursor.moveToNext());


         }
      cursor.close();
  return mArrayList;
    }
}
