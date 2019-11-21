package com.app.nextgrocer.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.app.nextgrocer.R;
import com.app.nextgrocer.data.model.address.CountriesResponse;
import com.app.nextgrocer.data.model.address.StatesResponse;

import java.util.ArrayList;
import java.util.List;

public class StatesAdapter extends BaseAdapter
{
    ArrayList<StatesResponse.DataBean> mList;
    Context context;

    int pos;
    Typeface font;

    public StatesAdapter() {
        mList = new ArrayList<>();

    }


    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    static final class ViewHolder {
        TextView text1;
    }

    public void loadAllStates(List<StatesResponse.DataBean> dataBeanList)
    {
        mList.clear();
        mList.addAll(dataBeanList);
        notifyDataSetChanged();
    }



    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        context = parent.getContext();
        font = Typeface.createFromAsset(context.getAssets(),
                "fredokaOne.ttf");
        return initView(position, convertView);
    }

    private View initView(int position, View convertView) {
        if(convertView == null)
            convertView = View.inflate(context,
                    android.R.layout.simple_list_item_1,
                    null);
        TextView tvText1 = convertView.findViewById(android.R.id.text1);
        tvText1.setText(mList.get(position).getName());
        tvText1.setTypeface(font);
        return convertView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder viewHolder;
        pos = position;

        if(convertView==null){
            convertView = View.inflate(parent.getContext(),
                    R.layout.layout_item_state_country,
                    null);
            viewHolder = new ViewHolder();
            viewHolder.text1 = (TextView) convertView.findViewById(R.id.tv_state_country);
            convertView.setTag(viewHolder);


        }else{
            // just re-use the viewHolder
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.text1.setTypeface(Typeface.createFromAsset(parent.getContext().getAssets(), "fredokaOne.ttf"));

        viewHolder.text1.setText(mList.get(position).getName());
        //  viewHolder.text1.setTypeface(font);
        return convertView;
    }
}
