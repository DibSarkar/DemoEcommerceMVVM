package com.app.nextgrocer.ui.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.app.nextgrocer.R;
import com.app.nextgrocer.ui.activities.LoginActivity;
import com.app.nextgrocer.ui.activities.MyOrdersActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyAccountFragment extends Fragment {


    @BindView(R.id.tv_header)
    TextView tv_header;

    @BindView(R.id.tv_name)
    TextView tv_name;

    @BindView(R.id.tv_mob)
    TextView tv_mob;

    @BindView(R.id.tv_email)
    TextView tv_email;

    @BindView(R.id.tv_myorders_label)
    TextView tv_myorders_label;

    @BindView(R.id.tv_my_orders_text)
    TextView tv_my_orders_text;

    @BindView(R.id.tv_reward_points)
    TextView tv_reward_points;

    @BindView(R.id.tv_reward_text)
    TextView tv_reward_text;

    @BindView(R.id.tv_address_label)
    TextView tv_address_label;

    @BindView(R.id.tv_address_text)
    TextView tv_address_text;

    @BindView(R.id.tv_return_label)
    TextView tv_return_label;

    @BindView(R.id.tv_return_label_text)
    TextView tv_return_label_text;

    @BindView(R.id.tv_change_label)
    TextView tv_change_label;

    @BindView(R.id.tv_change_label_text)
    TextView tv_change_label_text;

    @BindView(R.id.tv_logout_label)
    TextView tv_logout_label;





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static MyAccountFragment newInstance() {
        System.out.println("called fragment");
        MyAccountFragment fragment = new MyAccountFragment();
        //Bundle args = new Bundle();
        //args.putSerializable(PEOPLE_LIST, peopleBeansList);
        //fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        ButterKnife.bind(this, view);
        setUp();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void setUp()
    {
        tv_header.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "bebasNeue.otf"));
        tv_email.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "roboto_regular.ttf"));
        tv_name.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "robotoMedium.ttf"));
        tv_mob.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "robotoMedium.ttf"));
        tv_myorders_label.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "robotoMedium.ttf"));
        tv_reward_points.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "robotoMedium.ttf"));
        tv_address_label.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "robotoMedium.ttf"));
        tv_return_label.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "robotoMedium.ttf"));
        tv_change_label.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "robotoMedium.ttf"));
        tv_logout_label.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "robotoMedium.ttf"));
        tv_my_orders_text.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "robotoMedium.ttf"));
        tv_reward_text.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "robotoMedium.ttf"));
        tv_address_text.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "robotoMedium.ttf"));
        tv_return_label_text.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "robotoMedium.ttf"));
        tv_change_label_text.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "robotoMedium.ttf"));

    }

    @OnClick({R.id.tv_my_orders_text,R.id.tv_logout_label,R.id.iv_logout})
    void onClickEvent(View view) {
        switch (view.getId()) {

            case R.id.tv_my_orders_text :
                            Intent intent = new Intent(getActivity(), MyOrdersActivity.class);
                            startActivity(intent);
                 break;

            case R.id.tv_logout_label :
                Intent intent1 = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent1);
                break;

            case R.id.iv_logout :
                Intent intent2 = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent2);
                break;

        }
        }
}
