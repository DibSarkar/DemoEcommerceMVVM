package com.app.nextgrocer.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.app.nextgrocer.R;

import java.util.ArrayList;
import java.util.List;

public class SortByAdapter extends BaseAdapter {


    ArrayList<String> mList;
    Context context;

    int pos;

    public SortByAdapter() {

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

    public void loadAllItems(List<String> dataBeanList)
    {
        mList.clear();
        mList.addAll(dataBeanList);
        notifyDataSetChanged();
    }

    public void loadStates(List<String> dataBeanList)
    {
        mList.clear();
        mList.addAll(dataBeanList);
        notifyDataSetChanged();
    }


    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        context = parent.getContext();
        return initView(position, convertView);
    }

    private View initView(int position, View convertView) {
        if(convertView == null)
            convertView = View.inflate(context,
                    android.R.layout.simple_spinner_dropdown_item,
                    null);
        TextView tvText1 = convertView.findViewById(android.R.id.text1);
        tvText1.setText(mList.get(position));
        return convertView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder viewHolder;
        pos = position;

        if(convertView==null){
            convertView = View.inflate(parent.getContext(),
                    R.layout.layout_item_sort,
                    null);
            viewHolder = new ViewHolder();
            viewHolder.text1 = (TextView) convertView.findViewById(R.id.tv_sort_title);
            convertView.setTag(viewHolder);
        }else{
            // just re-use the viewHolder
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.text1.setText(mList.get(position));

        return convertView;
    }
}

