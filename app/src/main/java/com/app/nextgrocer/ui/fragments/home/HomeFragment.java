package com.app.nextgrocer.ui.fragments.home;

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

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.nextgrocer.R;
import com.app.nextgrocer.base.BaseFragment;
import com.app.nextgrocer.ui.activities.productDetails.ProductDetailsActivity;
import com.app.nextgrocer.ui.activities.ProductListActivity;
import com.app.nextgrocer.adapters.HomeCategoryAdapter;
import com.app.nextgrocer.adapters.HomeProductsAdapter;
import com.app.nextgrocer.adapters.NewArrivalsProductAdapter;
import com.app.nextgrocer.local_models.LocalBean;
import com.app.nextgrocer.utils.Constants;
import com.app.nextgrocer.utils.SpacesItemDecoration;
import com.app.nextgrocer.utils.ViewModelProviderFactory;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;

import java.util.HashMap;
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

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public HomeFragmentViewModel getViewModel() {
        homeFragmentViewModel = ViewModelProviders.of(this,factory).get(HomeFragmentViewModel.class);
        return homeFragmentViewModel;
    }

    @Override
    public void setUp() {

        loadSliders();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeFragmentViewModel.setNavigator(this);

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

        tv_featured.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "bebasNeue.otf"));
        tv_newarrivals.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "bebasNeue.otf"));
        btn_viewall1.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "roboto_regular.ttf"));
        btn_viewall2.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "roboto_regular.ttf"));
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



    @OnClick({R.id.btn_viewall1,R.id.btn_viewall2,R.id.iv_back_cat,R.id.iv_next_cat})
    void onClickEvent(View view) {
        switch (view.getId()) {

            case R.id.btn_viewall1 :

                Intent intent = new Intent(getActivity(), ProductListActivity.class);
                startActivity(intent);
                break;

            case R.id.btn_viewall2 :

                Intent intent2 = new Intent(getActivity(), ProductListActivity.class);
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
        }
    }

    @Override
    public void goBack() {

    }


    void setUpCategories()
    {
        /* For Home Categories */
        rv_categories.setHasFixedSize(true);
        rv_categories.setNestedScrollingEnabled(false);
        // int mNoOfColumns = AppUtils.calculateNoOfColumns(getActivity(),140F);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        rv_categories.setLayoutManager(linearLayoutManager);
        rv_categories.setItemAnimator(new DefaultItemAnimator());
        subscribeToLiveDataCategories();

        categoryAdapter.setAdapterListener(new HomeCategoryAdapter.HomeCategoryListener() {
            @Override
            public void onItemClick(LocalBean item, int position) {
                Intent intent = new Intent(getActivity(), ProductDetailsActivity.class);
                startActivity(intent);
            }
        });

        /* For Featured Products */
        rv_featured.setHasFixedSize(true);
        rv_featured.setNestedScrollingEnabled(false);
        rv_featured.setLayoutManager(gridLayoutManager);
        rv_featured.setItemAnimator(new DefaultItemAnimator());
        subscribeToLiveDataFeaturedProducts();

        homeProductsAdapter.setAdapterListener(new HomeProductsAdapter.HomeProductListener() {
            @Override
            public void onItemClick(LocalBean item, int position) {

                Intent intent = new Intent(getActivity(), ProductDetailsActivity.class);
                startActivity(intent);
            }
        });

        /* For New Arrival Products */
        rv_new_arrivals.setHasFixedSize(true);
        linearLayoutManager1.setOrientation(RecyclerView.HORIZONTAL);
        rv_new_arrivals.setNestedScrollingEnabled(false);
        rv_new_arrivals.setLayoutManager(linearLayoutManager1);
        rv_new_arrivals.setItemAnimator(new DefaultItemAnimator());
        subscribeToLiveDataNewArrivalProducts();

        newArrivalsProductAdapter.setAdapterListener(new NewArrivalsProductAdapter.NewArrivalProductListener() {
            @Override
            public void onItemClick(LocalBean item, int position) {
                Intent intent = new Intent(getActivity(), ProductDetailsActivity.class);
                startActivity(intent);
            }
        });

        /* Fetch sliders */
        observeToSliders();



        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.spacing);
        int spacingInPixels1 = getResources().getDimensionPixelSize(R.dimen.spacing1);
        int spacingInPixels2 = getResources().getDimensionPixelSize(R.dimen.spacing3);
        rv_featured.addItemDecoration(new SpacesItemDecoration(spacingInPixels, Constants.SPACE_HOME_FRAGMENT));
        rv_new_arrivals.addItemDecoration(new SpacesItemDecoration(spacingInPixels1, Constants.SPACE_HOME_FRAGMENT));
        rv_categories.addItemDecoration(new SpacesItemDecoration(spacingInPixels2, Constants.SPACE_HOME_CATYEGORY_LIST));



    }

    private void observeToSliders()
    {


        System.out.println();
        HashMap<String,Integer> file_maps = homeFragmentViewModel.getSliders();


        for(String name : file_maps.keySet()){
            DefaultSliderView defaultSliderView = new DefaultSliderView(getActivity());
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

    private void subscribeToLiveDataCategories() {
        homeFragmentViewModel.getCategoriesList().observe(this, new Observer<List<LocalBean>>() {
            @Override
            public void onChanged(List<LocalBean> localBeans) {

                categoryAdapter.addItems(localBeans);
                rv_categories.setAdapter(categoryAdapter);


            }
        });
    }

    private void subscribeToLiveDataFeaturedProducts() {
        homeFragmentViewModel.getFeaturedProducts().observe(this, new Observer<List<LocalBean>>() {
            @Override
            public void onChanged(List<LocalBean> localBeans) {

                homeProductsAdapter.addItems(localBeans);
                rv_featured.setAdapter(homeProductsAdapter);


            }
        });
    }


    private void subscribeToLiveDataNewArrivalProducts() {
        homeFragmentViewModel.getNewArrivalProducts().observe(this, new Observer<List<LocalBean>>() {
            @Override
            public void onChanged(List<LocalBean> localBeans) {

                newArrivalsProductAdapter.addItems(localBeans);
                rv_new_arrivals.setAdapter(newArrivalsProductAdapter);
            }
        });
    }

}
