package com.app.nextgrocer.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.app.nextgrocer.R;
import com.app.nextgrocer.data.model.address.AddressListResponse;
import com.app.nextgrocer.data.model.product_list.ProductListResponse;
import com.app.nextgrocer.local_models.LocalBean;
import com.travijuu.numberpicker.library.NumberPicker;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddressListAdapter extends RecyclerView.Adapter<AddressListAdapter.ViewHolder>   {

    private final ArrayList<AddressListResponse.ResponseDataBean> mValues;
    Context mContext;
    private RadioButton lastCheckedRB = null;

    public AddressListListener mListener;
    public AddressListAdapter() {
        mValues = new ArrayList<>();
    }
    int selectedPosition = -1;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_address, null);
        mContext=parent.getContext();
        return new ViewHolder(layoutView);
    }

    public void setAdapterListener(AddressListListener mListener) {
        this.mListener = mListener;
    }

    public interface AddressListListener
    {
        void onItemDeleteAddress(AddressListResponse.ResponseDataBean responseDataBean,int pos);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final AddressListResponse.ResponseDataBean mDataBean = mValues.get(position);

        holder.rb_address.setChecked(selectedPosition == position);
        if(mDataBean.getDefault_address()!=0)
        {
            holder.tv_default_add.setVisibility(View.VISIBLE);
            holder.view_line.setVisibility(View.GONE);
            holder.iv_delete.setVisibility(View.GONE);

        //    holder.rb_address.setChecked(true);
        }
        else {
            holder.view_line.setVisibility(View.VISIBLE);
            holder.iv_delete.setVisibility(View.VISIBLE);
            holder.tv_default_add.setVisibility(View.GONE);
        }
        holder.tv_name.setText(mDataBean.getFirstname()+" "+mDataBean.getLastname());
        if(!mDataBean.getAddress_2().isEmpty()) {
            holder.tv_address.setText(mDataBean.getAddress_1() + ", " + mDataBean.getAddress_2());
        }
        else {
            holder.tv_address.setText(mDataBean.getAddress_1());
        }
        holder.tv_city.setText(mDataBean.getCity()+" - "+mDataBean.getPostcode());
        holder.tv_state.setText(mDataBean.getZone());
        holder.tv_country.setText(mDataBean.getCountry());

        holder.iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onItemDeleteAddress(mValues.get(position),position);
            }
        });



    }

    public void addItems(List<AddressListResponse.ResponseDataBean> responseDataBeans) {
        mValues.clear();
        mValues.addAll(responseDataBeans);
        notifyDataSetChanged();
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

        @BindView(R.id.tv_name)
        TextView tv_name;

        @BindView(R.id.tv_default_add)
        TextView tv_default_add;

        @BindView(R.id.tv_address)
        TextView tv_address;

        @BindView(R.id.tv_city)
        TextView tv_city;

        @BindView(R.id.tv_state)
        TextView tv_state;

        @BindView(R.id.tv_country)
        TextView tv_country;

        @BindView(R.id.iv_delete)
        ImageView iv_delete;

        @BindView(R.id.view_line)
        View view_line;

     /*   @BindView(R.id.rg_select_address)
        RadioGroup rg_select_address;
*/
        @BindView(R.id.rb_address)
        RadioButton rb_address;


        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

            rb_address.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectedPosition = getAdapterPosition();
                    notifyDataSetChanged();
                }
            });

        }
    }
}
