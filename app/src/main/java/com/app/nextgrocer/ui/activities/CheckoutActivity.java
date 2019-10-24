package com.app.nextgrocer.ui.activities;

import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.nextgrocer.R;
import com.app.nextgrocer.adapters.CheckoutAdapter;
import com.app.nextgrocer.local_models.LocalBean;
import com.app.nextgrocer.shared.BaseActivity;
import com.app.nextgrocer.utils.Constants;
import com.app.nextgrocer.utils.SpacesItemDecoration;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CheckoutActivity extends BaseActivity {


    @BindView(R.id.rv_checkout)
    RecyclerView rv_checkout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tv_header)
    TextView tv_header;

    @BindView(R.id.tv_delivery_label)
    TextView tv_delivery_label;

    @BindView(R.id.tv_billing_label)
    TextView tv_billing_label;

    @BindView(R.id.tv_shipping_label)
    TextView tv_shipping_label;

    @BindView(R.id.tv_special_label)
    TextView tv_special_label;

    @BindView(R.id.tv_payment_label)
    TextView tv_payment_label;

    @BindView(R.id.tv_change_del)
    TextView tv_change_del;

    @BindView(R.id.tv_change_bill)
    TextView tv_change_bill;

    @BindView(R.id.tv_change_ship)
    TextView tv_change_ship;

    @BindView(R.id.btn_place)
    Button btn_place;

    @BindView(R.id.tv_date)
    TextView tv_date;

    @BindView(R.id.tv_fixed_time)
    TextView tv_fixed_time;

    @BindView(R.id.tv_time)
    TextView tv_time;

    @BindView(R.id.tv_bill_name)
    TextView tv_bill_name;

    @BindView(R.id.tv_bill_address)
    TextView tv_bill_address;

    @BindView(R.id.tv_bill_mobile_label)
    TextView tv_bill_mobile_label;

    @BindView(R.id.tv_bill_mobile_text)
    TextView tv_bill_mobile_text;

    @BindView(R.id.tv_shipp_name)
    TextView tv_shipp_name;

    @BindView(R.id.tv_shipp_address)
    TextView tv_shipp_address;

    @BindView(R.id.tv_shipp_mobile_label)
    TextView tv_shipp_mobile_label;

    @BindView(R.id.tv_shipp_mobile_text)
    TextView tv_shipp_mobile_text;

    @BindView(R.id.edt_special)
    EditText edt_special;

    @BindView(R.id.rb_cod)
    RadioButton rb_cod;

    @BindView(R.id.rb_online)
    RadioButton rb_online;


    CheckoutAdapter checkoutAdapter;
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
        setContentView(R.layout.activity_checkout);
        setUp();

    }

    public void loadProducts()
    {
        productList = new ArrayList<>();
        productList.add(new LocalBean(1));
        productList.add(new LocalBean(2));
        int spacingInPixels1 = getResources().getDimensionPixelSize(R.dimen.spacing1);
        checkoutAdapter = new CheckoutAdapter(productList);
        rv_checkout.setHasFixedSize(true);
        rv_checkout.addItemDecoration(new SpacesItemDecoration(spacingInPixels1, Constants.SPACE_CHECKOUT_LIST));
        rv_checkout.setNestedScrollingEnabled(false);
        rv_checkout.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rv_checkout.setItemAnimator(new DefaultItemAnimator());
        rv_checkout.setAdapter(checkoutAdapter);
        tv_header.setTypeface(Typeface.createFromAsset(getAssets(), "bebasNeue.otf"));
        tv_delivery_label.setTypeface(Typeface.createFromAsset(getAssets(), "robotoMedium.ttf"));
        tv_billing_label.setTypeface(Typeface.createFromAsset(getAssets(), "robotoMedium.ttf"));
        tv_shipping_label.setTypeface(Typeface.createFromAsset(getAssets(), "robotoMedium.ttf"));
        tv_special_label.setTypeface(Typeface.createFromAsset(getAssets(), "robotoMedium.ttf"));
        tv_payment_label.setTypeface(Typeface.createFromAsset(getAssets(), "robotoMedium.ttf"));
        tv_change_bill.setTypeface(Typeface.createFromAsset(getAssets(), "robotoMedium.ttf"));
        tv_change_del.setTypeface(Typeface.createFromAsset(getAssets(), "robotoMedium.ttf"));
        tv_change_ship.setTypeface(Typeface.createFromAsset(getAssets(), "robotoMedium.ttf"));
        btn_place.setTypeface(Typeface.createFromAsset(getAssets(), "roboto_regular.ttf"));
        tv_date.setTypeface(Typeface.createFromAsset(getAssets(), "robotoMedium.ttf"));
        tv_fixed_time.setTypeface(Typeface.createFromAsset(getAssets(), "roboto_regular.ttf"));
        tv_time.setTypeface(Typeface.createFromAsset(getAssets(), "roboto_regular.ttf"));
        tv_bill_name.setTypeface(Typeface.createFromAsset(getAssets(), "robotoMedium.ttf"));
        tv_bill_address.setTypeface(Typeface.createFromAsset(getAssets(), "roboto_regular.ttf"));
        tv_bill_mobile_label.setTypeface(Typeface.createFromAsset(getAssets(), "robotoMedium.ttf"));
        tv_bill_mobile_text.setTypeface(Typeface.createFromAsset(getAssets(), "roboto_regular.ttf"));
        tv_shipp_name.setTypeface(Typeface.createFromAsset(getAssets(), "robotoMedium.ttf"));
        tv_shipp_address.setTypeface(Typeface.createFromAsset(getAssets(), "roboto_regular.ttf"));
        tv_shipp_mobile_label.setTypeface(Typeface.createFromAsset(getAssets(), "robotoMedium.ttf"));
        tv_shipp_mobile_text.setTypeface(Typeface.createFromAsset(getAssets(), "roboto_regular.ttf"));
        edt_special.setTypeface(Typeface.createFromAsset(getAssets(), "roboto_regular.ttf"));
        rb_cod.setTypeface(Typeface.createFromAsset(getAssets(), "roboto_regular.ttf"));
        rb_online.setTypeface(Typeface.createFromAsset(getAssets(), "roboto_regular.ttf"));


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
