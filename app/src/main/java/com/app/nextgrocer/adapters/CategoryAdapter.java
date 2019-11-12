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
import com.app.nextgrocer.data.model.categories.CategoriesResponse;
import com.app.nextgrocer.local_models.LocalBean;
import com.app.nextgrocer.utils.GlideApp;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder>   {

    private final ArrayList<CategoriesResponse.DataBean> mValues;



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
        void onItemClick(CategoriesResponse.DataBean item, int position);

    }
    public void addItems(List<CategoriesResponse.DataBean> localBeanList) {
        mValues.addAll(localBeanList);
        notifyDataSetChanged();
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {


        final CategoriesResponse.DataBean mDataBean = mValues.get(position);

        GlideApp.with(mContext)
                .load(mDataBean.getImage())
                .placeholder(R.drawable.cat1)
                .into(holder.iv_cat);
        holder.tv_category_name.setText(mDataBean.getName());

        holder.tv_category_name.setTypeface(Typeface.createFromAsset(mContext.getAssets(), "fredokaOne.ttf"));

        //holder.tv_category_name.setTypeface(Typeface.createFromAsset(mContext.getAssets(), "robotoMedium.ttf"));

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

