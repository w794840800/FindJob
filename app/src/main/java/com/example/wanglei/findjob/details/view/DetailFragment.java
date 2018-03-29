package com.example.wanglei.findjob.details.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wanglei.findjob.R;
import com.example.wanglei.findjob.details.DetailActivity;
import com.example.wanglei.findjob.details.DetailsConstract;

public class DetailFragment extends Fragment implements DetailsConstract.View{
    int id;
    String title;
    private DetailsConstract.Presenter mPresenter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      id = getActivity().getIntent().getIntExtra(DetailActivity.KEY_ID,0);
      title = getActivity().getIntent().getStringExtra(DetailActivity.TITLE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_detail,container,false);
        initView(view);
        return view;
    }

    private void initView(View view) {



    }


    @Override
    public void onResume() {
        super.onResume();
        mPresenter.loadContent(id);

        }

    @Override
    public void setPresenter(DetailsConstract.Presenter presenter) {
        mPresenter = presenter;

    }
}
