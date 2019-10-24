package com.app.nextgrocer.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.app.nextgrocer.R;
import com.app.nextgrocer.local_models.LocalBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeCategoryAdapter extends RecyclerView.Adapter<HomeCategoryAdapter.ViewHolder>   {

    private final ArrayList<LocalBean> mValues;

    Context mContext;
    public HomeCategoryListener mListener;
    public HomeCategoryAdapter() {
        this.mValues = new ArrayList<>();


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_category, null);
        mContext=parent.getContext();
        return new ViewHolder(layoutView);
    }

    public void setAdapterListener(HomeCategoryListener mListener) {
        this.mListener = mListener;
    }

    public interface HomeCategoryListener {
        void onItemClick(LocalBean item, int position);

    }

    public void addItems(List<LocalBean> localBeanList) {
        mValues.addAll(localBeanList);
        notifyDataSetChanged();
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {


        final LocalBean mDataBean = mValues.get(position);
        if(mDataBean.getId()==1)
        {
            holder.iv_cat.setImageResource(R.drawable.category1);
        }

      else if(mDataBean.getId()==2)
        {
            holder.iv_cat.setImageResource(R.drawable.category2);
        }
        else if(mDataBean.getId()==3)
        {
            holder.iv_cat.setImageResource(R.drawable.category3);
        }
        else if(mDataBean.getId()==4)
        {
            holder.iv_cat.setImageResource(R.drawable.category4);
        }
        else if(mDataBean.getId()==5)
        {
            holder.iv_cat.setImageResource(R.drawable.category5);
        }
        holder.tv_category.setTypeface(Typeface.createFromAsset(mContext.getAssets(), "robotoMedium.ttf"));

    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.iv_cat)
        ImageView iv_cat;

        @BindView(R.id.tv_category)
        TextView tv_category;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onItemClick(mValues.get(getLayoutPosition()), getLayoutPosition());
                }
            });
            /*view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onItemClick(mValues.get(getLayoutPosition()), getLayoutPosition());
                }
            });*/

        }
    }
}
