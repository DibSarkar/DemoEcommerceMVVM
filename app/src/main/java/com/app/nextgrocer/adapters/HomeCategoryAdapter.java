package com.app.nextgrocer.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.app.nextgrocer.R;
import com.app.nextgrocer.data.model.home.HomeApiResponse;
import com.app.nextgrocer.utils.GlideApp;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeCategoryAdapter extends RecyclerView.Adapter<HomeCategoryAdapter.ViewHolder>   {

    private final ArrayList<HomeApiResponse.CategoryBean> mValues;

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
        void onItemClick(HomeApiResponse.CategoryBean item, int position);

    }

    public void addItems(List<HomeApiResponse.CategoryBean> localBeanList) {
        mValues.addAll(localBeanList);
        notifyDataSetChanged();
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {


        final HomeApiResponse.CategoryBean categoryBean = mValues.get(position);
        holder.tv_category.setText(categoryBean.getName());
        GlideApp.with(mContext)
                .load(categoryBean.getImage())
                .placeholder(R.drawable.category1)
                .centerCrop()
                .into(holder.iv_cat);

        holder.tv_category.setTypeface(Typeface.createFromAsset(mContext.getAssets(), "fredokaOne.ttf"));
        //  holder.tv_category.setTypeface(Typeface.createFromAsset(mContext.getAssets(), "robotoMedium.ttf"));

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
