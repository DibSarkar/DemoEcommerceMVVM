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
import com.travijuu.numberpicker.library.NumberPicker;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyOrdersAdapter extends RecyclerView.Adapter<MyOrdersAdapter.ViewHolder>   {

    private final ArrayList<LocalBean> mValues;



    Context mContext;


    public MyOrdersAdapter(ArrayList<LocalBean> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_orders, null);
        mContext=parent.getContext();
        return new ViewHolder(layoutView);
    }



    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {


        final LocalBean mDataBean = mValues.get(position);



        holder.tv_orderId.setTypeface(Typeface.createFromAsset(mContext.getAssets(), "fredokaOne.ttf"));
        holder.tv_orderId_text.setTypeface(Typeface.createFromAsset(mContext.getAssets(), "fredokaOne.ttf"));
        holder.tv_date.setTypeface(Typeface.createFromAsset(mContext.getAssets(), "fredokaOne.ttf"));
        holder.tv_date_text.setTypeface(Typeface.createFromAsset(mContext.getAssets(), "fredokaOne.ttf"));
        holder.tv_order_status_text.setTypeface(Typeface.createFromAsset(mContext.getAssets(), "fredokaOne.ttf"));
        holder.tv_view_details.setTypeface(Typeface.createFromAsset(mContext.getAssets(), "fredokaOne.ttf"));
        holder.tv_delivery_label.setTypeface(Typeface.createFromAsset(mContext.getAssets(), "fredokaOne.ttf"));
        holder.tv_bill_name.setTypeface(Typeface.createFromAsset(mContext.getAssets(), "fredokaOne.ttf"));
        holder.tv_bill_address.setTypeface(Typeface.createFromAsset(mContext.getAssets(), "fredokaOne.ttf"));
        holder.tv_ordered.setTypeface(Typeface.createFromAsset(mContext.getAssets(), "fredokaOne.ttf"));
        holder.tv_shipped.setTypeface(Typeface.createFromAsset(mContext.getAssets(), "fredokaOne.ttf"));
        holder.tv_out_for_del.setTypeface(Typeface.createFromAsset(mContext.getAssets(), "fredokaOne.ttf"));
        holder.tv_arrival.setTypeface(Typeface.createFromAsset(mContext.getAssets(), "fredokaOne.ttf"));


     /*   holder.tv_orderId.setTypeface(Typeface.createFromAsset(mContext.getAssets(), "robotoMedium.ttf"));
        holder.tv_orderId_text.setTypeface(Typeface.createFromAsset(mContext.getAssets(), "roboto_regular.ttf"));
        holder.tv_date.setTypeface(Typeface.createFromAsset(mContext.getAssets(), "robotoMedium.ttf"));
        holder.tv_date_text.setTypeface(Typeface.createFromAsset(mContext.getAssets(), "roboto_regular.ttf"));
        holder.tv_order_status_text.setTypeface(Typeface.createFromAsset(mContext.getAssets(), "josesans.ttf"));
        holder.tv_view_details.setTypeface(Typeface.createFromAsset(mContext.getAssets(), "roboto_regular.ttf"));
        holder.tv_delivery_label.setTypeface(Typeface.createFromAsset(mContext.getAssets(), "robotoMedium.ttf"));
        holder.tv_bill_name.setTypeface(Typeface.createFromAsset(mContext.getAssets(), "robotoMedium.ttf"));
        holder.tv_bill_address.setTypeface(Typeface.createFromAsset(mContext.getAssets(), "roboto_regular.ttf"));
        holder.tv_ordered.setTypeface(Typeface.createFromAsset(mContext.getAssets(), "roboto_regular.ttf"));
        holder.tv_shipped.setTypeface(Typeface.createFromAsset(mContext.getAssets(), "roboto_regular.ttf"));
        holder.tv_out_for_del.setTypeface(Typeface.createFromAsset(mContext.getAssets(), "roboto_regular.ttf"));
        holder.tv_arrival.setTypeface(Typeface.createFromAsset(mContext.getAssets(), "roboto_regular.ttf"));
*/

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

        @BindView(R.id.tv_orderId)
        TextView tv_orderId;

        @BindView(R.id.tv_orderId_text)
        TextView tv_orderId_text;

        @BindView(R.id.tv_date)
        TextView tv_date;

        @BindView(R.id.tv_date_text)
        TextView tv_date_text;

        @BindView(R.id.tv_view_details)
        TextView tv_view_details;

        @BindView(R.id.tv_delivery_label)
        TextView tv_delivery_label;

        @BindView(R.id.tv_bill_address)
        TextView tv_bill_address;

        @BindView(R.id.tv_bill_name)
        TextView tv_bill_name;

        @BindView(R.id.tv_order_status_text)
        TextView tv_order_status_text;

        @BindView(R.id.tv_ordered)
        TextView tv_ordered;

        @BindView(R.id.tv_out_for_del)
        TextView tv_out_for_del;

        @BindView(R.id.tv_arrival)
        TextView tv_arrival;

        @BindView(R.id.tv_shipped)
        TextView tv_shipped;





        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            /*view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onItemClick(mValues.get(getLayoutPosition()), getLayoutPosition());
                }
            });*/

        }
    }
}




