package com.app.nextgrocer.ui.activities.productDetails;

import android.content.Intent;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.nextgrocer.R;
import com.app.nextgrocer.adapters.NewArrivalsProductAdapter;
import com.app.nextgrocer.adapters.SimilarProductsAdapter;
import com.app.nextgrocer.base.BaseActivity;
import com.app.nextgrocer.data.model.product_details.ProductDetailsResponse;
import com.app.nextgrocer.local_models.LocalBean;
import com.app.nextgrocer.ui.activities.CartActivity;
import com.app.nextgrocer.ui.activities.home.MainNavigator;
import com.app.nextgrocer.ui.activities.home.MainViewModel;
import com.app.nextgrocer.utils.ChildAnimationExample;
import com.app.nextgrocer.utils.Constants;
import com.app.nextgrocer.utils.SpacesItemDecoration;
import com.app.nextgrocer.utils.ViewModelProviderFactory;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.travijuu.numberpicker.library.NumberPicker;

import java.text.DecimalFormat;
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

    @BindView(R.id.fl_offer)
    FrameLayout fl_offer;

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

    @BindView(R.id.tv_desc)
    TextView tv_desc;

    @BindView(R.id.tv_similar)
    TextView tv_similar;

    @BindView(R.id.btn_add)
    Button btn_add;

    @BindView(R.id.btn_wish)
    Button btn_wish;

    @BindView(R.id.rb_product)
    AppCompatRatingBar appCompatRatingBar;

    @Inject
    ViewModelProviderFactory factory;

    @Inject
    LinearLayoutManager linearLayoutManager;

    private ProductDetailsViewModel productDetailsViewModel;

    @Inject
    SimilarProductsAdapter similarProductsAdapter;


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
        productDetailsViewModel.setNavigator(this);

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
        tv_header.setTypeface(Typeface.createFromAsset(getAssets(), "bebasNeue.otf"));
        tv_desc_title.setTypeface(Typeface.createFromAsset(getAssets(), "bebasNeue.otf"));
        tv_offer.setTypeface(Typeface.createFromAsset(getAssets(), "bebasNeue.otf"));
        tv_similar.setTypeface(Typeface.createFromAsset(getAssets(), "bebasNeue.otf"));
        tv_mrp_price.setTypeface(Typeface.createFromAsset(getAssets(), "roboto_regular.ttf"));
        tv_reviews.setTypeface(Typeface.createFromAsset(getAssets(), "roboto_regular.ttf"));
        tv_desc.setTypeface(Typeface.createFromAsset(getAssets(), "roboto_regular.ttf"));
        tv_sell_price.setTypeface(Typeface.createFromAsset(getAssets(), "roboto_bold.ttf"));
        btn_wish.setTypeface(Typeface.createFromAsset(getAssets(), "roboto_regular.ttf"));
        btn_add.setTypeface(Typeface.createFromAsset(getAssets(), "roboto_regular.ttf"));
        tv_product_name.setTypeface(Typeface.createFromAsset(getAssets(), "roboto_regular.ttf"));
        setUpList();

    }




    public void loadSimilarProducts() {


        if(getIntent().getExtras()!=null) {

         productDetailsViewModel.fetchSimilarProducts(getIntent().getExtras().getString("pro_id"));
         subscribeToLiveDataSliders();
         subscribeToLiveDataSimilarProducts();

        }
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

    private void subscribeToLiveDataSimilarProducts()
    {
        productDetailsViewModel.getSimilar_ProductsList().observe(this, new Observer<List<ProductDetailsResponse.RelatedProductBean>>() {
             @Override
             public void onChanged(List<ProductDetailsResponse.RelatedProductBean> relatedProductBeans) {

                 similarProductsAdapter.addItems(relatedProductBeans);
                 rv_similar_products.setAdapter(similarProductsAdapter);
             }

         });

         similarProductsAdapter.setAdapterListener(new SimilarProductsAdapter.SimilarProductsListener() {
             @Override
             public void onItemClick(ProductDetailsResponse.RelatedProductBean item, int position) {
                 Intent intent = new Intent(getApplicationContext(), ProductDetailsActivity.class);
                 startActivity(intent);
             }
         });
    }
    private void subscribeToLiveDataSliders()
    {

       productDetailsViewModel.getProductSliders().observe(this, new Observer<List<ProductDetailsResponse.ProductBean.ImagesBean.ImageBean>>() {
           @Override
           public void onChanged(List<ProductDetailsResponse.ProductBean.ImagesBean.ImageBean> imageBeans) {

               if (imageBeans.size() > 0) {
                   System.out.println("length of image" + " " + imageBeans.size());

                   slider.removeAllSliders();
                   slider.stopAutoCycle();
                   for (int i = 0; i < imageBeans.size(); i++) {
                       DefaultSliderView textSliderView = new DefaultSliderView(getApplicationContext());
                       // initialize a SliderLayout
                       textSliderView
                               .description("")
                               .image(imageBeans.get(i).getThumb())
                               .setScaleType(BaseSliderView.ScaleType.FitCenterCrop);
                       textSliderView.bundle(new Bundle());
                       textSliderView.getBundle()
                               .putString("extra", imageBeans.get(i).getThumb());
                       slider.addSlider(textSliderView);
                   }
                   slider.setPresetTransformer(SliderLayout.Transformer.Accordion);
                   slider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
                   slider.setCustomAnimation(new ChildAnimationExample());
                   slider.setDuration(4000);
               }
           }
       });


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

    @Override
    public void getProductData(String pro_name, String desc, int rating, int reviews, String price, String special_price) {

        tv_product_name.setText(pro_name);
        tv_reviews.setText(String.valueOf(reviews)+" "+"Reviews");
        tv_desc.setText(Html.fromHtml(desc));
        appCompatRatingBar.setRating(rating);
        if(!special_price.equals(""))
        {
            fl_offer.setVisibility(View.VISIBLE);
            double offer = Math.round(((Double.parseDouble(price)-Double.parseDouble(special_price))/Double.parseDouble(price))*100);
            DecimalFormat df = new DecimalFormat("###.#");
            tv_offer.setText("("+df.format(offer)+"%"+" "+"OFF"+")");
            tv_mrp_price.setVisibility(View.VISIBLE);
            tv_mrp_price.setPaintFlags(tv_mrp_price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            tv_mrp_price.setText(getApplicationContext().getResources().getString(R.string.Rs)+" "+price);
            tv_sell_price.setText(getApplicationContext().getResources().getString(R.string.Rs)+" "+special_price);
        }
        else {
            fl_offer.setVisibility(View.GONE);
            tv_mrp_price.setVisibility(View.INVISIBLE);
            tv_sell_price.setText(getApplicationContext().getResources().getString(R.string.Rs)+" "+price);

        }


    }
}
