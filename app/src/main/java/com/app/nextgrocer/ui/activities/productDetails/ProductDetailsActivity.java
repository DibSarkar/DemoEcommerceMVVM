package com.app.nextgrocer.ui.activities.productDetails;

import android.content.Intent;
import android.graphics.Paint;
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

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.nextgrocer.R;
import com.app.nextgrocer.adapters.NewArrivalsProductAdapter;
import com.app.nextgrocer.base.BaseActivity;
import com.app.nextgrocer.local_models.LocalBean;
import com.app.nextgrocer.ui.activities.CartActivity;
import com.app.nextgrocer.ui.activities.home.MainNavigator;
import com.app.nextgrocer.ui.activities.home.MainViewModel;
import com.app.nextgrocer.utils.Constants;
import com.app.nextgrocer.utils.SpacesItemDecoration;
import com.app.nextgrocer.utils.ViewModelProviderFactory;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.travijuu.numberpicker.library.NumberPicker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.support.HasSupportFragmentInjector;

public class ProductDetailsActivity extends BaseActivity<ProductDetailsViewModel> implements ProductDetailsNavigator {


    @BindView(R.id.np_count)
    NumberPicker np_count;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tv_mrp_price)
    TextView tv_mrp_price;

    @BindView(R.id.slider)
    SliderLayout slider;

    @BindView(R.id.rv_similar_products)
    RecyclerView rv_similar_products;

    @BindView(R.id.tv_header)
    TextView tv_header;

    @BindView(R.id.tv_product_name)
    TextView tv_product_name;

    @BindView(R.id.tv_reviews)
    TextView tv_reviews;

    @BindView(R.id.tv_offer)
    TextView tv_offer;

    @BindView(R.id.tv_sell_price)
    TextView tv_sell_price;

    @BindView(R.id.tv_desc_title)
    TextView tv_desc_title;

    @BindView(R.id.tv_desc1)
    TextView tv_desc1;

    @BindView(R.id.tv_desc2)
    TextView tv_desc2;

    @BindView(R.id.tv_desc3)
    TextView tv_desc3;

    @BindView(R.id.tv_desc4)
    TextView tv_desc4;

    @BindView(R.id.tv_desc5)
    TextView tv_desc5;

    @BindView(R.id.tv_similar)
    TextView tv_similar;

    @BindView(R.id.btn_add)
    Button btn_add;

    @BindView(R.id.btn_wish)
    Button btn_wish;

    @Inject
    ViewModelProviderFactory factory;

    @Inject
    LinearLayoutManager linearLayoutManager;

    private ProductDetailsViewModel productDetailsViewModel;

    @Inject
    NewArrivalsProductAdapter newArrivalsProductAdapter;


    @Override
    public int getLayoutId() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return R.layout.activity_product_details;
    }

    @Override
    public ProductDetailsViewModel getViewModel() {
        productDetailsViewModel = ViewModelProviders.of(this,factory).get(ProductDetailsViewModel.class);
        return productDetailsViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setUp();
    }


    protected void setUp() {
        setUnBinder(ButterKnife.bind(this));
        np_count.setValue(1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Drawable upArrow = ContextCompat.getDrawable(this, R.drawable.tool_arrow);
        upArrow.setColorFilter(ContextCompat.getColor(this, R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        setTitle("Product Details");
        updateUI();
    }


    public void updateUI()
    {
        tv_mrp_price.setPaintFlags(tv_mrp_price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        tv_header.setTypeface(Typeface.createFromAsset(getAssets(), "bebasNeue.otf"));
        tv_desc_title.setTypeface(Typeface.createFromAsset(getAssets(), "bebasNeue.otf"));
        tv_offer.setTypeface(Typeface.createFromAsset(getAssets(), "bebasNeue.otf"));
        tv_similar.setTypeface(Typeface.createFromAsset(getAssets(), "bebasNeue.otf"));
        tv_mrp_price.setTypeface(Typeface.createFromAsset(getAssets(), "roboto_regular.ttf"));
        tv_reviews.setTypeface(Typeface.createFromAsset(getAssets(), "roboto_regular.ttf"));
        tv_desc1.setTypeface(Typeface.createFromAsset(getAssets(), "roboto_regular.ttf"));
        tv_desc2.setTypeface(Typeface.createFromAsset(getAssets(), "roboto_regular.ttf"));
        tv_desc3.setTypeface(Typeface.createFromAsset(getAssets(), "roboto_regular.ttf"));
        tv_desc4.setTypeface(Typeface.createFromAsset(getAssets(), "roboto_regular.ttf"));
        tv_desc5.setTypeface(Typeface.createFromAsset(getAssets(), "roboto_regular.ttf"));
        tv_sell_price.setTypeface(Typeface.createFromAsset(getAssets(), "roboto_bold.ttf"));
        btn_wish.setTypeface(Typeface.createFromAsset(getAssets(), "roboto_regular.ttf"));
        btn_add.setTypeface(Typeface.createFromAsset(getAssets(), "roboto_regular.ttf"));
        tv_product_name.setTypeface(Typeface.createFromAsset(getAssets(), "roboto_regular.ttf"));
        setUpList();

    }




    public void loadSimilarProducts() {


        productDetailsViewModel.getSimilar_ProductsList().observe(this, new Observer<List<LocalBean>>() {
            @Override
            public void onChanged(List<LocalBean> localBeans) {

                newArrivalsProductAdapter.addItems(localBeans);
                rv_similar_products.setAdapter(newArrivalsProductAdapter);
            }
        });

        newArrivalsProductAdapter.setAdapterListener(new NewArrivalsProductAdapter.NewArrivalProductListener() {
            @Override
            public void onItemClick(LocalBean item, int position) {
                Intent intent = new Intent(getApplicationContext(), ProductDetailsActivity.class);
                startActivity(intent);
            }
        });

        observeToSliders();
    }

    void setUpList()
    {
        int spacingInPixels1 = getResources().getDimensionPixelSize(R.dimen.spacing1);
        // newArrivalsProductAdapter = new NewArrivalsProductAdapter(newarrivalList);
        rv_similar_products.setHasFixedSize(true);
        rv_similar_products.addItemDecoration(new SpacesItemDecoration(spacingInPixels1, Constants.SPACE_HOME_FRAGMENT));
        rv_similar_products.setNestedScrollingEnabled(false);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        rv_similar_products.setLayoutManager(linearLayoutManager);
        rv_similar_products.setItemAnimator(new DefaultItemAnimator());
        loadSimilarProducts();
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
    private void observeToSliders()
    {


        System.out.println();
        HashMap<String,Integer> file_maps = productDetailsViewModel.getSliders();


        for(String name : file_maps.keySet()){
            DefaultSliderView defaultSliderView = new DefaultSliderView(this);
            // initialize a SliderLayout
            defaultSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit);
            defaultSliderView.bundle(new Bundle());
            defaultSliderView.getBundle()
                    .putString("extra",name);

            slider.addSlider(defaultSliderView);
        }

        slider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        slider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        slider.setCustomAnimation(new DescriptionAnimation());
        slider.setDuration(4000);

    }

    @OnClick({R.id.iv_cart})
    void onClickEvent(View view) {
        switch (view.getId()) {

            case R.id.iv_cart :
                Intent intent = new Intent(this, CartActivity.class);
                startActivity(intent);
                break;


        }
    }

}
