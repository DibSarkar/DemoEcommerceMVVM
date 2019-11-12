package com.app.nextgrocer.ui.activities;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.nextgrocer.R;
import com.app.nextgrocer.adapters.CartAdapter;
import com.app.nextgrocer.local_models.LocalBean;
import com.app.nextgrocer.shared.BaseActivity;
import com.app.nextgrocer.utils.Constants;
import com.app.nextgrocer.utils.SpacesItemDecoration;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CartActivity extends BaseActivity {

    @BindView(R.id.rv_product_list)
    RecyclerView rv_product_list;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tv_header)
    TextView tv_header;

    @BindView(R.id.tv_price_label)
    TextView tv_price_label;

    @BindView(R.id.tv_sub_label)
    TextView tv_sub_label;

    @BindView(R.id.tv_shipping_label)
    TextView tv_shipping_label;

    @BindView(R.id.tv_sub_text)
    TextView tv_sub_text;

    @BindView(R.id.tv_shipping_text)
    TextView tv_shipping_text;

    @BindView(R.id.tv_total_label)
    TextView tv_total_label;

    @BindView(R.id.tv_total_text)
    TextView tv_total_text;

    @BindView(R.id.btn_proceed)
    Button btn_proceed;

    CartAdapter cartAdapter;
    ArrayList<LocalBean> productList;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_cart);
        setUp();

    }

    public void loadProducts()
    {
        productList = new ArrayList<>();
        productList.add(new LocalBean(1));
        productList.add(new LocalBean(2));
        productList.add(new LocalBean(3));

        int spacingInPixels1 = getResources().getDimensionPixelSize(R.dimen.spacing1);
        cartAdapter = new CartAdapter(productList);
        rv_product_list.setHasFixedSize(true);
        rv_product_list.addItemDecoration(new SpacesItemDecoration(spacingInPixels1, Constants.SPACE_CATEGORIES_FRAGMENT));
        rv_product_list.setNestedScrollingEnabled(false);
        rv_product_list.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rv_product_list.setItemAnimator(new DefaultItemAnimator());
        rv_product_list.setAdapter(cartAdapter);


        tv_header.setTypeface(Typeface.createFromAsset(getAssets(), "fredokaOne.ttf"));
        tv_sub_label.setTypeface(Typeface.createFromAsset(getAssets(), "fredokaOne.ttf"));
        tv_shipping_label.setTypeface(Typeface.createFromAsset(getAssets(), "fredokaOne.ttf"));
        tv_sub_label.setTypeface(Typeface.createFromAsset(getAssets(), "fredokaOne.ttf"));
        tv_price_label.setTypeface(Typeface.createFromAsset(getAssets(), "fredokaOne.ttf"));
        tv_sub_text.setTypeface(Typeface.createFromAsset(getAssets(), "fredokaOne.ttf"));
        tv_shipping_text.setTypeface(Typeface.createFromAsset(getAssets(), "fredokaOne.ttf"));
        btn_proceed.setTypeface(Typeface.createFromAsset(getAssets(), "fredokaOne.ttf"));
        tv_total_label.setTypeface(Typeface.createFromAsset(getAssets(), "fredokaOne.ttf"));
        tv_total_text.setTypeface(Typeface.createFromAsset(getAssets(), "fredokaOne.ttf"));

      /*  tv_header.setTypeface(Typeface.createFromAsset(getAssets(), "bebasNeue.otf"));
        tv_sub_label.setTypeface(Typeface.createFromAsset(getAssets(), "roboto_regular.ttf"));
        tv_shipping_label.setTypeface(Typeface.createFromAsset(getAssets(), "roboto_regular.ttf"));
        tv_sub_label.setTypeface(Typeface.createFromAsset(getAssets(), "roboto_regular.ttf"));
        tv_price_label.setTypeface(Typeface.createFromAsset(getAssets(), "robotoMedium.ttf"));
        tv_sub_text.setTypeface(Typeface.createFromAsset(getAssets(), "robotoMedium.ttf"));
        tv_shipping_text.setTypeface(Typeface.createFromAsset(getAssets(), "robotoMedium.ttf"));
        btn_proceed.setTypeface(Typeface.createFromAsset(getAssets(), "roboto_regular.ttf"));
        tv_total_label.setTypeface(Typeface.createFromAsset(getAssets(), "robotoMedium.ttf"));
        tv_total_text.setTypeface(Typeface.createFromAsset(getAssets(), "robotoMedium.ttf"));
*/
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

    @OnClick({R.id.btn_proceed})
    void onClickEvent(View view) {
        switch (view.getId()) {

            case R.id.btn_proceed :
                Intent intent = new Intent(this, CheckoutActivity.class);
                startActivity(intent);

        }
    }

}
