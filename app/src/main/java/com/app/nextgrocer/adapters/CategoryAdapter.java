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

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder>   {

    private final ArrayList<LocalBean> mValues;



    Context mContext;
    Activity activity;
    int status;

    public CategoryListener mListener;

    public CategoryAdapter() {
        this.mValues = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_categories, null);
        mContext=parent.getContext();
        return new ViewHolder(layoutView);
    }

    public void setAdapterListener(CategoryListener mListener) {
        this.mListener = mListener;
    }

    public interface CategoryListener {
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
            holder.iv_cat.setBackgroundResource(R.drawable.cat1);
        }

        else if(mDataBean.getId()==2)
        {
            holder.iv_cat.setBackgroundResource(R.drawable.cat2);
        }
        else if(mDataBean.getId()==3)
        {
            holder.iv_cat.setBackgroundResource(R.drawable.cat3);
        }
        else if(mDataBean.getId()==4)
        {
            holder.iv_cat.setBackgroundResource(R.drawable.cat4);
        }
        else if(mDataBean.getId()==5)
        {
            holder.iv_cat.setBackgroundResource(R.drawable.cat5);
        }
        holder.tv_category_name.setTypeface(Typeface.createFromAsset(mContext.getAssets(), "robotoMedium.ttf"));

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

        @BindView(R.id.tv_category_name)
        TextView tv_category_name;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onItemClick(mValues.get(getLayoutPosition()), getLayoutPosition());
                }
            });


        }
    }
}

