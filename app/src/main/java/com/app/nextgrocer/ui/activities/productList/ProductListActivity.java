package com.app.nextgrocer.ui.activities.productList;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.nextgrocer.R;
import com.app.nextgrocer.adapters.CategoriesProductAdapter;
import com.app.nextgrocer.adapters.FeaturedProductListAdapter;
import com.app.nextgrocer.adapters.NewArrivalsProductListAdapter;
import com.app.nextgrocer.adapters.SortByAdapter;
import com.app.nextgrocer.base.BaseActivity;
import com.app.nextgrocer.data.model.product_list.CategoriesProductResponse;
import com.app.nextgrocer.data.model.product_list.ProductListResponse;
import com.app.nextgrocer.data.rest.ApiResponse;
import com.app.nextgrocer.local_models.LocalBean;
import com.app.nextgrocer.ui.activities.productDetails.ProductDetailsActivity;
import com.app.nextgrocer.ui.activities.productDetails.ProductDetailsViewModel;
import com.app.nextgrocer.utils.CommonUtils;
import com.app.nextgrocer.utils.Constants;
import com.app.nextgrocer.utils.SpacesItemDecoration;
import com.app.nextgrocer.utils.ViewModelProviderFactory;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductListActivity extends BaseActivity<ProductListViewModel> implements ProductListNavigator {


    @BindView(R.id.rv_product_list)
    RecyclerView rv_product_list;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tv_cat_name)
    TextView tv_cat_name;

    @BindView(R.id.tv_pro_count)
    TextView tv_pro_count;

    @BindView(R.id.tv_sort_by)
    TextView tv_sort_by;

    @BindView(R.id.spinner_sort)
    Spinner spinner_sort;

    @BindView(R.id.rl_sort)
    RelativeLayout rl_sort;

    @Inject
    ViewModelProviderFactory factory;

    @Inject
    LinearLayoutManager linearLayoutManager;

    private ProductListViewModel productListViewModel;

    @Inject
    SortByAdapter sortByAdapter;

    @Inject
    FeaturedProductListAdapter productListAdapter;

    @Inject
    NewArrivalsProductListAdapter newArrivalsProductListAdapter;

    @Inject
    CategoriesProductAdapter categoriesProductAdapter;


    ArrayList<String> sort_by;

    ProgressDialog progressDialog;

    @Override
    public int getLayoutId() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return R.layout.activity_productlist;
    }

    @Override
    public ProductListViewModel getViewModel() {
        productListViewModel = ViewModelProviders.of(this,factory).get(ProductListViewModel.class);
        return productListViewModel;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_productlist);
        setUp();

    }

    protected void setUp() {
        productListViewModel.setNavigator(this);
        setUnBinder(ButterKnife.bind(this));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        progressDialog = CommonUtils.showLoadingDialog(this);
        Drawable upArrow = ContextCompat.getDrawable(this, R.drawable.tool_arrow);
        upArrow.setColorFilter(ContextCompat.getColor(this, R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        setTitle("");



        if(getIntent().getExtras()!=null) {

            tv_cat_name.setText(getIntent().getExtras().getString("name"));
        }
        updateUI();


        spinner_sort.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                System.out.println("selected position"+sort_by.get(i));

                if(i==0)
                {
                    requestProducts("","");
                }
                else if(i==1)
                {
                    requestProducts(Constants.SORT_NAME,Constants.SORT_ASC);
                }
                else if(i==2)
                {
                    requestProducts(Constants.SORT_NAME,Constants.SORT_DESC);
                }

                else if(i==3)
                {
                    requestProducts(Constants.SORT_PRICE,Constants.SORT_ASC);
                }

                else if(i==4)
                {
                    requestProducts(Constants.SORT_PRICE,Constants.SORT_DESC);
                }

                else if(i==5)
                {
                    requestProducts(Constants.SORT_RATING,Constants.SORT_DESC);
                }

                else if(i==6)
                {
                    requestProducts(Constants.SORT_RATING,Constants.SORT_ASC);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });




    }

    private void updateUI()
    {
        tv_cat_name.setTypeface(Typeface.createFromAsset(getAssets(), "fredokaOne.ttf"));
        tv_pro_count.setTypeface(Typeface.createFromAsset(getAssets(), "fredokaOne.ttf"));
        tv_sort_by.setTypeface(Typeface.createFromAsset(getAssets(), "fredokaOne.ttf"));

       /* tv_cat_name.setTypeface(Typeface.createFromAsset(getAssets(), "bebasNeue.otf"));
        tv_pro_count.setTypeface(Typeface.createFromAsset(getAssets(), "roboto_regular.ttf"));
        tv_sort_by.setTypeface(Typeface.createFromAsset(getAssets(), "roboto_regular.ttf"));*/

        loadProducts();
    }

    public void loadProducts() {

        int spacingInPixels1 = getResources().getDimensionPixelSize(R.dimen.spacing1);
        rv_product_list.setHasFixedSize(true);
        rv_product_list.addItemDecoration(new SpacesItemDecoration(spacingInPixels1, Constants.SPACE_PRODUCT_LIST));
        rv_product_list.setNestedScrollingEnabled(false);
        rv_product_list.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rv_product_list.setItemAnimator(new DefaultItemAnimator());
        sort_by = new ArrayList<>();
        sort_by.add("Default");
        sort_by.add("Name (A-Z)");
        sort_by.add("Name (Z-A)");
        sort_by.add("Price (Low to High)");
        sort_by.add("Price (High to Low)");
        sort_by.add("Rating (Highest)");
        sort_by.add("Rating (Lowest)");
        sortByAdapter.loadAllItems(sort_by);
        spinner_sort.setAdapter(sortByAdapter);
        spinner_sort.setSelection(0,false);

        subscribeToLiveDataResponse();
        requestProducts("","");

    }


    private void requestProducts(String sort,String order)
    {
        if (getIntent().getExtras()!= null) {
            if(getIntent().hasExtra("name")) {
                if (getIntent().getExtras().getString("name").equals(Constants.FEATURED_PRODUCTS)) {

                    rl_sort.setVisibility(View.GONE);
                    productListViewModel.fetchFeaturedProducts("", "");

                } else if (getIntent().getExtras().getString("name").equals(Constants.NEWARRIVAL_PRODUCTS)) {
                    rl_sort.setVisibility(View.VISIBLE);
                    productListViewModel.fetchNewArrivals(sort, order);

                }
                else {
                    if(getIntent().hasExtra("cat_id")) {
                        if (getIntent().getExtras().getString("cat_id") != null) {
                            if (!getIntent().getExtras().getString("cat_id").equals("")) {
                                rl_sort.setVisibility(View.VISIBLE);
                                productListViewModel.fetchProductsByCategory(getIntent().getExtras().getString("cat_id"), sort, order);
                            }
                        }
                    }
                }
            }

            else {
                if(getIntent().hasExtra("cat_id")) {
                    if (getIntent().getExtras().getString("cat_id") != null) {
                        if (!getIntent().getExtras().getString("cat_id").equals("")) {
                            rl_sort.setVisibility(View.VISIBLE);
                            productListViewModel.fetchProductsByCategory(getIntent().getExtras().getString("cat_id"), sort, order);
                        }
                    }
                }
            }

        }
    }

    private void subscribeToLiveDataCategoryProducts()
    {
        productListViewModel.getProduct_list().observe(this, new Observer<List<CategoriesProductResponse.ProductBean>>() {
            @Override
            public void onChanged(List<CategoriesProductResponse.ProductBean> productBeans) {
                categoriesProductAdapter.addItems(productBeans);
                rv_product_list.setAdapter(categoriesProductAdapter);
            }
        });

        categoriesProductAdapter.setAdapterListener(new CategoriesProductAdapter.ProductListListener() {
            @Override
            public void onItemClick(CategoriesProductResponse.ProductBean item, int position) {
                Intent intent = new Intent(ProductListActivity.this, ProductDetailsActivity.class);
                intent.putExtra("pro_id",item.getProduct_id());
                startActivity(intent);
            }
        });
    }


     private void subscribeToLiveDataNewProducts()
     {
         productListViewModel.getNewArrival_List().observe(this, new Observer<List<ProductListResponse.NewarrivalBean>>() {
             @Override
             public void onChanged(List<ProductListResponse.NewarrivalBean> newarrivalBeanList) {
                 newArrivalsProductListAdapter.addItems(newarrivalBeanList);
                 rv_product_list.setAdapter(newArrivalsProductListAdapter);
             }
         });

         newArrivalsProductListAdapter.setAdapterListener(new NewArrivalsProductListAdapter.ProductListListener() {
             @Override
             public void onItemClick(ProductListResponse.NewarrivalBean item, int position) {
                 Intent intent = new Intent(ProductListActivity.this, ProductDetailsActivity.class);
                 intent.putExtra("pro_id",item.getProduct_id());
                 startActivity(intent);
             }
         });
     }


    private void subscribeToLiveDataProducts()
    {
        productListViewModel.getFeature_list().observe(this, new Observer<List<ProductListResponse.FeatureBean>>() {
            @Override
            public void onChanged(List<ProductListResponse.FeatureBean> featureBeans) {

                productListAdapter.addItems(featureBeans);
                rv_product_list.setAdapter(productListAdapter);

            }
        });

        productListAdapter.setAdapterListener(new FeaturedProductListAdapter.ProductListListener() {
            @Override
            public void onItemClick(ProductListResponse.FeatureBean item, int position) {
                Intent intent = new Intent(ProductListActivity.this, ProductDetailsActivity.class);
                intent.putExtra("pro_id",item.getProduct_id());
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

    @Override
    public void getProductsCount(int count) {

        tv_pro_count.setText(String.valueOf(count)+" "+"Items");
    }

    private void subscribeToLiveDataResponse()
    {

        productListViewModel.getResponseLiveData().observe(this, new Observer<ApiResponse>() {
            @Override
            public void onChanged(ApiResponse apiResponse) {
                System.out.println("api statys"+" "+apiResponse.status);
                switch (apiResponse.status) {
                    case LOADING:
                        System.out.println("called loading");
                        progressDialog.show();
                        break;

                    case SUCCESS:
                        System.out.println("called success");
                        progressDialog.dismiss();
                        if(getIntent().hasExtra("name")) {
                            if (getIntent().getExtras().getString("name").equals(Constants.FEATURED_PRODUCTS)) {
                                subscribeToLiveDataProducts();
                            } else if (getIntent().getExtras().getString("name").equals(Constants.NEWARRIVAL_PRODUCTS)) {
                                subscribeToLiveDataNewProducts();
                            } else {
                                if (getIntent().hasExtra("cat_id")) {
                                    if (getIntent().getExtras().getString("cat_id") != null) {
                                        if (!getIntent().getExtras().getString("cat_id").equals("")) {
                                            subscribeToLiveDataCategoryProducts();                                        }
                                    }
                                }
                            }
                        }
                        else {
                            if(getIntent().hasExtra("cat_id")) {
                                if (getIntent().getExtras().getString("cat_id") != null) {
                                    if (!getIntent().getExtras().getString("cat_id").equals("")) {
                                        subscribeToLiveDataCategoryProducts();
                                    }
                                }
                            }

                        }
                        break;

                    case ERROR:
                        progressDialog.dismiss();

                        Toast.makeText(getApplicationContext(), apiResponse.error.getMessage(), Toast.LENGTH_SHORT).show();
                        break;

                    default:
                        break;
                }

            }
        });

    }



}
