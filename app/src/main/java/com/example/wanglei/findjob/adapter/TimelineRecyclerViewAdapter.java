package com.example.wanglei.findjob.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.wanglei.findjob.R;
import com.example.wanglei.findjob.date.ZhihuDialyNews;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wanglei on 18-3-23.
 */

public class TimelineRecyclerViewAdapter extends RecyclerView.Adapter<TimelineRecyclerViewAdapter.ItemViewHolder>{
    OnRecyclerItemClick onRecyclerItemClick;
    Context mContext;
    ArrayList<ZhihuDialyNews.StoriesBean> arrayList;

    public TimelineRecyclerViewAdapter(Context c, ArrayList<ZhihuDialyNews.StoriesBean> arrayList1){

        mContext = c;
        arrayList = arrayList1;

    }
    public void setOnRecyclerItemClick(OnRecyclerItemClick onRecyclerItemClick){
        this.onRecyclerItemClick = onRecyclerItemClick;

    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.recyclerview_item,parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, final int position) {
        if (onRecyclerItemClick!=null){

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onRecyclerItemClick.onItemClick(view,position);
                }
            });
        }
        ZhihuDialyNews.StoriesBean storiesBean = arrayList.get(position);
        Log.d("wanglei", "onBindViewHolder: title= "+storiesBean.getTitle());
        holder.textView.setText(storiesBean.getTitle());
        String picUrl = storiesBean.getImages().get(0);
        if (picUrl!=null){

            Glide.with(mContext)
                    .load(picUrl)
                    .asBitmap()
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(holder.imageView);
        }




    }
    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void update(List<ZhihuDialyNews.StoriesBean> zhihuDialyNews) {

        arrayList.clear();
        arrayList.addAll(zhihuDialyNews);
        notifyDataSetChanged();
        notifyItemInserted(zhihuDialyNews.size());

    }

    static class ItemViewHolder extends RecyclerView.ViewHolder{
    private ImageView imageView;
    private TextView textView;
        public ItemViewHolder(View itemView) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.recycler_image);
        textView = (TextView) itemView.findViewById(R.id.recycler_tv);
    }
}

    public interface OnRecyclerItemClick{

        void onItemClick(View v,int position);

    }
}
