package com.app.nextgrocer.adapters;

import android.content.Context;
import android.graphics.Paint;
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

public class HomeProductsAdapter extends RecyclerView.Adapter<HomeProductsAdapter.ViewHolder>   {

    private final ArrayList<HomeApiResponse.FeatureBean> mValues;



    Context mContext;


    public HomeProductListener mListener;

    public HomeProductsAdapter() {
        this.mValues = new ArrayList<>();


    }
    public void setAdapterListener(HomeProductListener mListener) {
        this.mListener = mListener;
    }

    public interface HomeProductListener {
        void onItemClick(HomeApiResponse.FeatureBean item, int position);

    }


    public void addItems(List<HomeApiResponse.FeatureBean> featureBeanList) {
        mValues.addAll(featureBeanList);
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


      final HomeApiResponse.FeatureBean featureBean = mValues.get(position);

      holder.tv_product_name.setText(featureBean.getName());
      if(!featureBean.getSpecial().isEmpty())
      {
          holder.tv_mrp_price.setVisibility(View.VISIBLE);
          holder.tv_mrp_price.setText(mContext.getResources().getString(R.string.Rs)+" "+featureBean.getPrice());
          holder.tv_sell_price.setText(mContext.getResources().getString(R.string.Rs)+" "+featureBean.getSpecial());

      }
      else {
          holder.tv_mrp_price.setVisibility(View.GONE);
          holder.tv_sell_price.setText(mContext.getResources().getString(R.string.Rs)+" "+featureBean.getPrice());
      }

        GlideApp.with(mContext)
                .load(featureBean.getThumb())
                .placeholder(R.drawable.featured_pro1)
                .into(holder.iv_pro);

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

