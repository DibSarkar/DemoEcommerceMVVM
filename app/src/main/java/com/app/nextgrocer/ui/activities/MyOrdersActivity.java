package com.app.nextgrocer.ui.activities;

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
import com.app.nextgrocer.adapters.MyOrdersAdapter;
import com.app.nextgrocer.local_models.LocalBean;
import com.app.nextgrocer.shared.BaseActivity;
import com.app.nextgrocer.utils.Constants;
import com.app.nextgrocer.utils.SpacesItemDecoration;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyOrdersActivity extends BaseActivity {

    @BindView(R.id.rv_myorders)
    RecyclerView rv_myorders;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tv_header)
    TextView tv_header;

    MyOrdersAdapter myOrdersAdapter;
    ArrayList<LocalBean> orderList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_my_orders);
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
        loadOrders();
    }

    public void loadOrders()
    {
        orderList = new ArrayList<>();
        orderList.add(new LocalBean(1));
        orderList.add(new LocalBean(2));
        orderList.add(new LocalBean(3));
        int spacingInPixels1 = getResources().getDimensionPixelSize(R.dimen.spacing1);
        myOrdersAdapter = new MyOrdersAdapter(orderList);
        rv_myorders.setHasFixedSize(true);
        rv_myorders.addItemDecoration(new SpacesItemDecoration(spacingInPixels1, Constants.SPACE_CHECKOUT_LIST));
        rv_myorders.setNestedScrollingEnabled(false);
        rv_myorders.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rv_myorders.setItemAnimator(new DefaultItemAnimator());
        rv_myorders.setAdapter(myOrdersAdapter);
        tv_header.setTypeface(Typeface.createFromAsset(getAssets(), "fredokaOne.ttf"));
        //tv_header.setTypeface(Typeface.createFromAsset(getAssets(), "bebasNeue.otf"));
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
