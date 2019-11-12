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
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.nextgrocer.R;
import com.app.nextgrocer.adapters.WishlistAdapter;
import com.app.nextgrocer.local_models.LocalBean;
import com.app.nextgrocer.ui.activities.productDetails.ProductDetailsActivity;
import com.app.nextgrocer.utils.Constants;
import com.app.nextgrocer.utils.SpacesItemDecoration;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WishlistFragment extends Fragment {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tv_header)
    TextView tv_header;

    @BindView(R.id.rv_wish_list)
    RecyclerView rv_wish_list;
    WishlistAdapter productListAdapter;
    ArrayList<LocalBean> productList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public static WishlistFragment newInstance() {
        System.out.println("called fragment");
        WishlistFragment fragment = new WishlistFragment();
        //Bundle args = new Bundle();
        //args.putSerializable(PEOPLE_LIST, peopleBeansList);
        //fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wishlist, container, false);
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


    protected void setUp() {

        loadProducts();
    }

    public void loadProducts()
    {
        productList = new ArrayList<>();
        productList.add(new LocalBean(1));
        productList.add(new LocalBean(2));
        productList.add(new LocalBean(3));
        productList.add(new LocalBean(4));
        productList.add(new LocalBean(5));
        int spacingInPixels1 = getResources().getDimensionPixelSize(R.dimen.spacing1);
        productListAdapter = new WishlistAdapter(productList);
        rv_wish_list.setHasFixedSize(true);
        rv_wish_list.addItemDecoration(new SpacesItemDecoration(spacingInPixels1, Constants.SPACE_PRODUCT_LIST));
        rv_wish_list.setNestedScrollingEnabled(false);
        rv_wish_list.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        rv_wish_list.setItemAnimator(new DefaultItemAnimator());
        rv_wish_list.setAdapter(productListAdapter);

        tv_header.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "fredokaOne.ttf"));

        // tv_header.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "bebasNeue.otf"));

        productListAdapter.setAdapterListener(new WishlistAdapter.WishlistAdapterListener() {
            @Override
            public void onItemClick(LocalBean item, int position) {
                Intent intent = new Intent(getActivity(), ProductDetailsActivity.class);
                startActivity(intent);

    }
        });

    }



}
