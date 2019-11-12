package com.app.nextgrocer.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.app.nextgrocer.R;
import com.app.nextgrocer.data.model.product_list.ProductListResponse;
import com.app.nextgrocer.utils.GlideApp;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewArrivalsProductListAdapter extends RecyclerView.Adapter<NewArrivalsProductListAdapter.ViewHolder>   {

    private final ArrayList<ProductListResponse.NewarrivalBean> mValues;

    Context mContext;
    public ProductListListener mListener;
    public NewArrivalsProductListAdapter() {
        this.mValues = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_product_list, null);
        mContext=parent.getContext();
        return new ViewHolder(layoutView);
    }

    public void setAdapterListener(ProductListListener mListener) {
        this.mListener = mListener;
    }

    public interface ProductListListener {
        void onItemClick(ProductListResponse.NewarrivalBean item, int position);

    }

    public void addItems(List<ProductListResponse.NewarrivalBean> newarrivalBeanList) {
        mValues.clear();
        mValues.addAll(newarrivalBeanList);
        notifyDataSetChanged();
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {


        final ProductListResponse.NewarrivalBean mDataBean = mValues.get(position);

        holder.tv_product_name.setText(mDataBean.getName());

        GlideApp.with(mContext)
                .load(mDataBean.getThumb())
                .placeholder(R.drawable.featured_pro1)
                .into(holder.iv_pro);

        if(!mDataBean.getSpecial().isEmpty())
        {
            holder.fl_offer.setVisibility(View.VISIBLE);
            holder.tv_mrp_price.setVisibility(View.VISIBLE);
            holder.tv_mrp_price.setText(mContext.getResources().getString(R.string.Rs)+" "+mDataBean.getPrice());
            holder.tv_sell_price.setText(mContext.getResources().getString(R.string.Rs)+" "+mDataBean.getSpecial());
            double offer = Math.round(((Double.parseDouble(mDataBean.getPrice())-Double.parseDouble(mDataBean.getSpecial()))/Double.parseDouble(mDataBean.getPrice()))*100);
            DecimalFormat df = new DecimalFormat("###.#");
            holder.tv_offer.setText("("+df.format(offer)+"%"+" "+"OFF"+")");
            holder.tv_offer.setTypeface(Typeface.createFromAsset(mContext.getAssets(), "fredokaOne.ttf"));
         //   holder.tv_offer.setTypeface(Typeface.createFromAsset(mContext.getAssets(), "bebasNeue.otf"));

        }
        else {
             holder.fl_offer.setVisibility(View.GONE);
            holder.tv_mrp_price.setVisibility(View.GONE);
            holder.tv_sell_price.setText(mContext.getResources().getString(R.string.Rs)+" "+mDataBean.getPrice());
        }
        holder.tv_add.setTypeface(Typeface.createFromAsset(mContext.getAssets(), "fredokaOne.ttf"));
        holder.tv_product_name.setTypeface(Typeface.createFromAsset(mContext.getAssets(), "fredokaOne.ttf"));
        holder.tv_sell_price.setTypeface(Typeface.createFromAsset(mContext.getAssets(), "fredokaOne.ttf"));
        holder.tv_mrp_price.setTypeface(Typeface.createFromAsset(mContext.getAssets(), "fredokaOne.ttf"));
        holder.tv_select.setTypeface(Typeface.createFromAsset(mContext.getAssets(), "fredokaOne.ttf"));

/*        holder.tv_add.setTypeface(Typeface.createFromAsset(mContext.getAssets(), "robotoMedium.ttf"));
        holder.tv_product_name.setTypeface(Typeface.createFromAsset(mContext.getAssets(), "roboto_regular.ttf"));
        holder.tv_sell_price.setTypeface(Typeface.createFromAsset(mContext.getAssets(), "robotoMedium.ttf"));
        holder.tv_mrp_price.setTypeface(Typeface.createFromAsset(mContext.getAssets(), "roboto_regular.ttf"));*/
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

        @BindView(R.id.tv_offer)
        TextView tv_offer;

        @BindView(R.id.tv_add)
        TextView tv_add;

        @BindView(R.id.fl_offer)
        FrameLayout fl_offer;

        @BindView(R.id.tv_select)
        TextView tv_select;

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



