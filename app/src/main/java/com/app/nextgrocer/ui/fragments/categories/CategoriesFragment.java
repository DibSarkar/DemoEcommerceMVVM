package com.app.nextgrocer.ui.fragments.categories;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.nextgrocer.R;
import com.app.nextgrocer.base.BaseFragment;
import com.app.nextgrocer.ui.activities.productDetails.ProductDetailsActivity;
import com.app.nextgrocer.adapters.CategoryAdapter;
import com.app.nextgrocer.local_models.LocalBean;
import com.app.nextgrocer.ui.fragments.home.HomeFragmentNavigator;
import com.app.nextgrocer.ui.fragments.home.HomeFragmentViewModel;
import com.app.nextgrocer.utils.Constants;
import com.app.nextgrocer.utils.SpacesItemDecoration;
import com.app.nextgrocer.utils.ViewModelProviderFactory;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoriesFragment extends BaseFragment<CategoriesViewModel> implements CategoriesFragmentNavigator {

    @BindView(R.id.tv_shop_by_cat)
    TextView tv_shop_by_cat;

    @BindView(R.id.rv_categories)
    RecyclerView rv_categories;

    @Inject
    CategoryAdapter categoryAdapter;

    @Inject
    LinearLayoutManager linearLayoutManager;

    @Inject
    ViewModelProviderFactory factory;

    CategoriesViewModel categoriesViewModel;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public static CategoriesFragment newInstance() {
        System.out.println("called fragment");
        CategoriesFragment fragment = new CategoriesFragment();
        return fragment;
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_categories;
    }

    @Override
    public CategoriesViewModel getViewModel() {
        categoriesViewModel = ViewModelProviders.of(this,factory).get(CategoriesViewModel.class);
        return categoriesViewModel;
    }

    @Override
    public void setUp()
    {
        tv_shop_by_cat.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "bebasNeue.otf"));

        rv_categories.setHasFixedSize(true);
        rv_categories.setNestedScrollingEnabled(false);
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.spacing2);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rv_categories.setLayoutManager(linearLayoutManager);
        rv_categories.setItemAnimator(new DefaultItemAnimator());
        rv_categories.addItemDecoration(new SpacesItemDecoration(spacingInPixels, Constants.SPACE_CATEGORIES_FRAGMENT));

        categoryAdapter.setAdapterListener(new CategoryAdapter.CategoryListener() {
            @Override
            public void onItemClick(LocalBean item, int position) {
                Intent intent = new Intent(getActivity(), ProductDetailsActivity.class);
                startActivity(intent);
            }
        });
        subscribeToLiveDataCategories();
    }

    @Override
    public void goBack() {

    }


    private void subscribeToLiveDataCategories()
    {
        categoriesViewModel.getCategories().observe(this, new Observer<List<LocalBean>>() {
            @Override
            public void onChanged(List<LocalBean> localBeans) {

                categoryAdapter.addItems(localBeans);
                rv_categories.setAdapter(categoryAdapter);
            }
        });
    }
}
