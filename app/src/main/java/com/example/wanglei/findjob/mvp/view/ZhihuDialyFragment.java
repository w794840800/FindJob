package com.example.wanglei.findjob.mvp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.wanglei.findjob.R;
import com.example.wanglei.findjob.adapter.TImelineFragmentViewPagerAdapter;
import com.example.wanglei.findjob.adapter.TimelineRecyclerViewAdapter;
import com.example.wanglei.findjob.date.ZhihuDialyNews;
import com.example.wanglei.findjob.mvp.Presenter.ZhihuDialyPresenter;
import com.example.wanglei.findjob.mvp.ZhihuDailyContract;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by wanglei on 18-3-21.
 */

public class ZhihuDialyFragment extends Fragment implements ZhihuDailyContract.View {
   int year;
   int month;
   int day;
   int size;
   boolean isLoadingMore = false;
   FloatingActionButton floatingActionButton;
   boolean isFirstAdd = true;
   SwipeRefreshLayout swipeRefreshLayout;
    List<ZhihuDialyNews> zhihuDialyNewsList;
   RecyclerView mRecyclerView;
   LinearLayout mEmptyView;
   LinearLayoutManager linearLayoutManager;
   TimelineRecyclerViewAdapter timelineRecyclerViewAdapter;
    ZhihuDailyContract.Presenter mZhihuPresenter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.timeline_page,container,false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        new ZhihuDialyPresenter(this);
    mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler);
    mEmptyView = (LinearLayout) view.findViewById(R.id.empty);
    linearLayoutManager = new LinearLayoutManager(getActivity());
    floatingActionButton = (FloatingActionButton) getActivity().findViewById(R.id.fab);
    swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe);
    swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            Calendar calendar = Calendar.getInstance();
            mZhihuPresenter.loadMore(calendar.getTimeInMillis(),false);

        }
    });
   mRecyclerView.setLayoutManager(linearLayoutManager);
    mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if(dy>0){
            floatingActionButton.hide();

            if (linearLayoutManager.findLastCompletelyVisibleItemPosition()==(size-1)){
                loadMore();

            }
        }else{
            floatingActionButton.show();

        }
    }
});
    }

    private void loadMore() {
    Calendar calendar = Calendar.getInstance();
    calendar.set(year,month,--day);
    isLoadingMore = true;
    mZhihuPresenter.loadMore(calendar.getTimeInMillis(),false);
    }

    public static ZhihuDialyFragment getInstance(){

        return new ZhihuDialyFragment();

    }
    @Override
    public void setPresenter(ZhihuDailyContract.Presenter presenter) {

        mZhihuPresenter = presenter;
    }

    @Override
    public void setLoadingIndicator(final boolean isLoading) {


          swipeRefreshLayout.post(new Runnable() {
              @Override
              public void run() {

                  if (isLoading){
                      swipeRefreshLayout.setRefreshing(true);
                  }else {
                      swipeRefreshLayout.setRefreshing(false);

                  }

              }
          });


    }


    @Override
    public void showResult(List<ZhihuDialyNews.StoriesBean> zhihuDialyNews) {
        Log.d(TAG, "showResult: size= "+zhihuDialyNews.size());
        size = zhihuDialyNews.size();
        mEmptyView.setVisibility((zhihuDialyNews.size()!=0)?View.INVISIBLE:View.VISIBLE);
        if (timelineRecyclerViewAdapter==null){

            timelineRecyclerViewAdapter = new
                    TimelineRecyclerViewAdapter(getActivity(),
                    (ArrayList<ZhihuDialyNews.StoriesBean>) zhihuDialyNews);

            mRecyclerView.setAdapter(timelineRecyclerViewAdapter);
        }else {
            timelineRecyclerViewAdapter.update(zhihuDialyNews);

        }



    }

    @Override
    public void onResume() {
        super.onResume();
        Calendar calendar = Calendar.getInstance();

      year = calendar.get(Calendar.YEAR);
      month = calendar.get(Calendar.MONTH);
      day = calendar.get(Calendar.DAY_OF_MONTH);
        Log.d(TAG, "onResume: year = "+year+" month= "+month+" day= "+day
        +" "+calendar.getTimeInMillis());
      setLoadingIndicator(isLoadingMore);
        if (isFirstAdd){
            mZhihuPresenter.loadMore(calendar.getTimeInMillis(),true);
            isFirstAdd = false;
        }else {


        }

    }

    @Override
    public void showError() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void stopLoading() {

    }
}
