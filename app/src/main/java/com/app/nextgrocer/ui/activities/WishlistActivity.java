package com.app.nextgrocer.ui.activities;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.nextgrocer.R;
import com.app.nextgrocer.adapters.WishlistAdapter;
import com.app.nextgrocer.local_models.LocalBean;
import com.app.nextgrocer.shared.BaseActivity;
import com.app.nextgrocer.ui.activities.productDetails.ProductDetailsActivity;
import com.app.nextgrocer.utils.Constants;
import com.app.nextgrocer.utils.SpacesItemDecoration;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WishlistActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tv_header)
    TextView tv_header;

    @BindView(R.id.rv_wish_list)
    RecyclerView rv_wish_list;
    WishlistAdapter productListAdapter;
    ArrayList<LocalBean> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_wishlist);
        setUp();
    }

    @Override
    protected void setUp() {
        setUnbinder(ButterKnife.bind(this));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Drawable upArrow = ContextCompat.getDrawable(this, R.drawable.tool_arrow);
        upArrow.setColorFilter(ContextCompat.getColor(this, R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        setTitle("");
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
        rv_wish_list.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rv_wish_list.setItemAnimator(new DefaultItemAnimator());
        rv_wish_list.setAdapter(productListAdapter);
        tv_header.setTypeface(Typeface.createFromAsset(getAssets(), "bebasNeue.otf"));

        productListAdapter.setAdapterListener(new WishlistAdapter.WishlistAdapterListener() {
            @Override
            public void onItemClick(LocalBean item, int position) {
                Intent intent = new Intent(getApplicationContext(), ProductDetailsActivity.class);
                startActivity(intent);

    }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);

    }

}
