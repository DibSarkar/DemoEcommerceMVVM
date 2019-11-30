package com.app.nextgrocer.ui.fragments.home;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.nextgrocer.R;
import com.app.nextgrocer.data.model.home.HomeApiResponse;
import com.app.nextgrocer.data.rest.ApiResponse;
import com.app.nextgrocer.shared.BaseFragment;
import com.app.nextgrocer.ui.activities.CartActivity;
import com.app.nextgrocer.ui.activities.productDetails.ProductDetailsActivity;
import com.app.nextgrocer.ui.activities.productList.ProductListActivity;
import com.app.nextgrocer.adapters.HomeCategoryAdapter;
import com.app.nextgrocer.adapters.HomeProductsAdapter;
import com.app.nextgrocer.adapters.NewArrivalsProductAdapter;
import com.app.nextgrocer.utils.ChildAnimationExample;
import com.app.nextgrocer.utils.CommonUtils;
import com.app.nextgrocer.utils.Constants;
import com.app.nextgrocer.utils.SpacesItemDecoration;
import com.app.nextgrocer.utils.ViewModelProviderFactory;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeFragment extends BaseFragment<HomeFragmentViewModel> implements HomeFragmentNavigator {

    @BindView(R.id.slider)
    SliderLayout slider;

    @BindView(R.id.sv_view)
    SearchView sv_view;

    @BindView(R.id.rv_featured)
    RecyclerView rv_featured;

    @BindView(R.id.rv_categories)
    RecyclerView rv_categories;

    @BindView(R.id.rv_new_arrivals)
    RecyclerView rv_new_arrivals;

    @BindView(R.id.tv_featured)
    TextView tv_featured;

    @BindView(R.id.tv_newarrivals)
    TextView tv_newarrivals;

    @BindView(R.id.btn_viewall1)
    Button btn_viewall1;

    @BindView(R.id.btn_viewall2)
    Button btn_viewall2;

    @Inject
    HomeProductsAdapter homeProductsAdapter;

    @Inject
    GridLayoutManager gridLayoutManager;

    @Inject
    LinearLayoutManager linearLayoutManager;

    @Inject
    LinearLayoutManager linearLayoutManager1;

    @Inject
    NewArrivalsProductAdapter newArrivalsProductAdapter;

    @Inject
    HomeCategoryAdapter categoryAdapter;

    @Inject
    ViewModelProviderFactory factory;

    private HomeFragmentViewModel homeFragmentViewModel;


    ProgressDialog progressDialog;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public HomeFragmentViewModel getViewModel() {
        homeFragmentViewModel = ViewModelProviders.of(this,factory).get(HomeFragmentViewModel.class);
        subscribeToLiveDataResponse();
        return homeFragmentViewModel;
    }

    @Override
    public void setUp() {
        loadSliders();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        progressDialog = CommonUtils.showLoadingDialog(getActivity());
        super.onCreate(savedInstanceState);
     //   homeFragmentViewModel.setNavigator(this);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    public static HomeFragment newInstance() {
        System.out.println("called fragment");
        HomeFragment fragment = new HomeFragment();
        //Bundle args = new Bundle();
        //args.putSerializable(PEOPLE_LIST, peopleBeansList);
        //fragment.setArguments(args);
        return fragment;
    }


    private void loadSliders()
    {

        tv_featured.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "fredokaOne.ttf"));
        tv_newarrivals.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "fredokaOne.ttf"));
        btn_viewall1.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "fredokaOne.ttf"));
        btn_viewall2.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "fredokaOne.ttf"));
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        hideSoftKeyboard();
        setUpCategories();
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    private void hideSoftKeyboard() {
        if (getActivity().getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
        }

    }
    @Override
    public void onDetach() {
        super.onDetach();
    }



    @OnClick({R.id.btn_viewall1,R.id.btn_viewall2,R.id.iv_back_cat,R.id.iv_next_cat,R.id.iv_cart})
    void onClickEvent(View view) {
        switch (view.getId()) {

            case R.id.btn_viewall1 :

                Intent intent = new Intent(getActivity(), ProductListActivity.class);
                intent.putExtra("name",Constants.FEATURED_PRODUCTS);
                startActivity(intent);
                break;

            case R.id.btn_viewall2 :

                Intent intent2 = new Intent(getActivity(), ProductListActivity.class);
                intent2.putExtra("name",Constants.NEWARRIVAL_PRODUCTS);
                startActivity(intent2);
                break;


            case R.id.iv_back_cat :
                if (linearLayoutManager.findFirstVisibleItemPosition() > 0) {
                    rv_categories.smoothScrollToPosition(linearLayoutManager.findFirstVisibleItemPosition() - 1);
                } else {
                    rv_categories.smoothScrollToPosition(0);
                }
               // rv_categories.getLayoutManager().scrollToPosition(categoryLayoutManager.findLastVisibleItemPosition() + 1);
                break;

            case R.id.iv_next_cat :
                rv_categories.smoothScrollToPosition(linearLayoutManager.findLastVisibleItemPosition() + 1);

               // rv_categories.getLayoutManager().scrollToPosition(categoryLayoutManager.findFirstVisibleItemPosition() - 1);
                break;

            case R.id.iv_cart :
                startActivity(new Intent(getActivity(), CartActivity.class));
                break;
        }
    }

    @Override
    public void goBack() {

    }


   private void setUpCategories()
    {
        /* For Home Categories */
        rv_categories.setHasFixedSize(true);
        rv_categories.setNestedScrollingEnabled(false);
        // int mNoOfColumns = AppUtils.calculateNoOfColumns(getActivity(),140F);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        rv_categories.setLayoutManager(linearLayoutManager);
        rv_categories.setItemAnimator(new DefaultItemAnimator());


        /* For Featured Products */
        rv_featured.setHasFixedSize(true);
        rv_featured.setNestedScrollingEnabled(false);
        rv_featured.setLayoutManager(gridLayoutManager);
        rv_featured.setItemAnimator(new DefaultItemAnimator());


        /* For New Arrival Products */
        rv_new_arrivals.setHasFixedSize(true);
        linearLayoutManager1.setOrientation(RecyclerView.HORIZONTAL);
        rv_new_arrivals.setNestedScrollingEnabled(false);
        rv_new_arrivals.setLayoutManager(linearLayoutManager1);
        rv_new_arrivals.setItemAnimator(new DefaultItemAnimator());

        /* Fetch sliders */


        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.spacing);
        int spacingInPixels1 = getResources().getDimensionPixelSize(R.dimen.spacing1);
        int spacingInPixels2 = getResources().getDimensionPixelSize(R.dimen.spacing3);
        rv_featured.addItemDecoration(new SpacesItemDecoration(spacingInPixels, Constants.SPACE_HOME_FRAGMENT));
        rv_new_arrivals.addItemDecoration(new SpacesItemDecoration(spacingInPixels1, Constants.SPACE_HOME_FRAGMENT));
        rv_categories.addItemDecoration(new SpacesItemDecoration(spacingInPixels2, Constants.SPACE_HOME_CATYEGORY_LIST));



    }

    private void subscribeToLiveDataSliders()
    {

      homeFragmentViewModel.getSliders().observe(this, new Observer<List<HomeApiResponse.BannerBean.TopbannerImageBean>>() {
          @Override
          public void onChanged(List<HomeApiResponse.BannerBean.TopbannerImageBean> topbannerImageBeans) {
              for (int i = 0; i < topbannerImageBeans.size(); i++) {
                  DefaultSliderView textSliderView = new DefaultSliderView(getContext());
                  // initialize a SliderLayout
                  textSliderView
                          .description("")
                          .image(topbannerImageBeans.get(i).getImage());
                          //.setScaleType(BaseSliderView.ScaleType);
                  textSliderView.bundle(new Bundle());
                  textSliderView.getBundle()
                          .putString("extra", topbannerImageBeans.get(i).getImage());
                  slider.addSlider(textSliderView);
              }
              slider.setPresetTransformer(SliderLayout.Transformer.Accordion);
              slider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
              slider.setCustomAnimation(new ChildAnimationExample());
              slider.setDuration(4000);
          }
      });

    }


    private void subscribeToLiveDataResponse()
    {
        homeFragmentViewModel.getHomeResponse().observe(this, new Observer<ApiResponse>() {
            @Override
            public void onChanged(ApiResponse apiResponse) {
                switch (apiResponse.status) {

                    case LOADING:
                        System.out.println("called loading");
                        progressDialog.show();
                        break;

                    case SUCCESS:
                        System.out.println("called success");
                        progressDialog.dismiss();
                        subscribeToLiveDataCategories();

                        break;

                    case ERROR:
                        progressDialog.dismiss();

                        Toast.makeText(getActivity(),apiResponse.error.getMessage(), Toast.LENGTH_SHORT).show();
                        break;

                    default:
                        break;
                }

            }
        });
    }

    private void subscribeToLiveDataCategories() {
        homeFragmentViewModel.getCategoriesList().observe(this, new Observer<List<HomeApiResponse.CategoryBean>>() {
            @Override
            public void onChanged(List<HomeApiResponse.CategoryBean> categoryBeans) {

                categoryAdapter.addItems(categoryBeans);
                rv_categories.setAdapter(categoryAdapter);


            }
        });
        categoryAdapter.setAdapterListener(new HomeCategoryAdapter.HomeCategoryListener() {
            @Override
            public void onItemClick(HomeApiResponse.CategoryBean item, int position) {
                Intent intent = new Intent(getActivity(), ProductListActivity.class);
                intent.putExtra("name",item.getName());
                intent.putExtra("cat_id",item.getCategory_id());
                startActivity(intent);
            }
        });

        subscribeToLiveDataFeaturedProducts();

    }


    private void subscribeToLiveDataFeaturedProducts() {
        homeFragmentViewModel.getFeaturedProducts().observe(this, new Observer<List<HomeApiResponse.FeatureBean>>() {
            @Override
            public void onChanged(List<HomeApiResponse.FeatureBean> featureBeans) {

                homeProductsAdapter.addItems(featureBeans);
                rv_featured.setAdapter(homeProductsAdapter);
            }
        });
        homeProductsAdapter.setAdapterListener(new HomeProductsAdapter.HomeProductListener() {
            @Override
            public void onItemClick(HomeApiResponse.FeatureBean item, int position) {

                Intent intent = new Intent(getActivity(), ProductDetailsActivity.class);
                intent.putExtra("pro_id",item.getProduct_id());
                startActivity(intent);
            }
        });
        subscribeToLiveDataNewArrivalProducts();

    }



    private void subscribeToLiveDataNewArrivalProducts() {
        homeFragmentViewModel.getNewArrivalProducts().observe(this, new Observer<List<HomeApiResponse.NewarrivalBean>>() {
            @Override
            public void onChanged(List<HomeApiResponse.NewarrivalBean> localBeans) {

                newArrivalsProductAdapter.addItems(localBeans);
                rv_new_arrivals.setAdapter(newArrivalsProductAdapter);
            }
        });

        newArrivalsProductAdapter.setAdapterListener(new NewArrivalsProductAdapter.NewArrivalProductListener() {
            @Override
            public void onItemClick(HomeApiResponse.NewarrivalBean item, int position) {
                Intent intent = new Intent(getActivity(), ProductDetailsActivity.class);
                intent.putExtra("pro_id",item.getProduct_id());
                startActivity(intent);
            }
        });

        subscribeToLiveDataSliders();
    }





}
