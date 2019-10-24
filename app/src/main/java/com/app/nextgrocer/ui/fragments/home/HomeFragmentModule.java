package com.app.nextgrocer.ui.fragments.home;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.app.nextgrocer.adapters.HomeCategoryAdapter;
import com.app.nextgrocer.adapters.HomeProductsAdapter;
import com.app.nextgrocer.adapters.NewArrivalsProductAdapter;

import dagger.Module;
import dagger.Provides;

@Module
public class HomeFragmentModule {

    @Provides
    LinearLayoutManager provideLinearLayoutManager(HomeFragment fragment) {
        return new LinearLayoutManager(fragment.getActivity());
    }

    @Provides
    GridLayoutManager provideGridLayoutManager(HomeFragment fragment) {
        return new GridLayoutManager(fragment.getActivity(),3);
    }

    @Provides
    HomeCategoryAdapter provideHomeCategoryAdapter() {
        return new HomeCategoryAdapter();
    }

    @Provides
    HomeProductsAdapter provideHomeProductsAdapter() {
        return new HomeProductsAdapter();
    }

    @Provides
    NewArrivalsProductAdapter provideNewArrivalsProductAdapter() {
        return new NewArrivalsProductAdapter();
    }


}
