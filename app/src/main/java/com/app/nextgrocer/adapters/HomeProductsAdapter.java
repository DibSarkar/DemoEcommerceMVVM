package com.app.nextgrocer.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.app.nextgrocer.R;
import com.app.nextgrocer.local_models.LocalBean;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeProductsAdapter extends RecyclerView.Adapter<HomeProductsAdapter.ViewHolder>   {

    private final ArrayList<LocalBean> mValues;



    Context mContext;


    public HomeProductListener mListener;

    public HomeProductsAdapter() {
        this.mValues = new ArrayList<>();


    }
    public void setAdapterListener(HomeProductListener mListener) {
        this.mListener = mListener;
    }

    public interface HomeProductListener {
        void onItemClick(LocalBean item, int position);

    }


    public void addItems(List<LocalBean> localBeanList) {
        mValues.addAll(localBeanList);
        notifyDataSetChanged();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_home_product, null);
        mContext=parent.getContext();
        return new ViewHolder(layoutView);
    }



    @Override
    public void onBindViewHolder(final HomeProductsAdapter.ViewHolder holder, final int position) {


      final LocalBean mDataBean = mValues.get(position);
      if(mDataBean.getId()==1)
      {
          holder.iv_pro.setImageResource(R.drawable.featured_pro1);
      }
      else  if(mDataBean.getId()==2)
      {
          holder.iv_pro.setImageResource(R.drawable.featured_pro2);
      }
      else  if(mDataBean.getId()==3)
      {
          holder.iv_pro.setImageResource(R.drawable.featured_pro3);
      }
      else if(mDataBean.getId()==4)
        {
            holder.iv_pro.setImageResource(R.drawable.featured_pro1);
        }
        else  if(mDataBean.getId()==5)
        {
            holder.iv_pro.setImageResource(R.drawable.featured_pro2);
        }
        else  if(mDataBean.getId()==6)
        {
            holder.iv_pro.setImageResource(R.drawable.featured_pro3);
        }

        holder.tv_product_name.setTypeface(Typeface.createFromAsset(mContext.getAssets(), "roboto_regular.ttf"));
        holder.tv_sell_price.setTypeface(Typeface.createFromAsset(mContext.getAssets(), "robotoMedium.ttf"));
        holder.tv_mrp_price.setTypeface(Typeface.createFromAsset(mContext.getAssets(), "roboto_regular.ttf"));
        holder.tv_mrp_price.setPaintFlags( holder.tv_mrp_price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);


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


       @BindView(R.id.iv_pro)
       ImageView iv_pro;

       @BindView(R.id.tv_product_name)
       TextView tv_product_name;

        @BindView(R.id.tv_sell_price)
        TextView tv_sell_price;

        @BindView(R.id.tv_mrp_price)
        TextView tv_mrp_price;

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

