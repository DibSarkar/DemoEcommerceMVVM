package com.app.nextgrocer.ui.activities.productList;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.app.nextgrocer.adapters.CategoriesProductAdapter;
import com.app.nextgrocer.adapters.FeaturedProductListAdapter;
import com.app.nextgrocer.adapters.NewArrivalsProductListAdapter;
import com.app.nextgrocer.adapters.SortByAdapter;

import dagger.Module;
import dagger.Provides;

@Module
public class ProductListModule {

    @Provides
    LinearLayoutManager provideLinearLayoutManager(ProductListActivity productListActivity) {
        return new LinearLayoutManager(productListActivity);
    }

    @Provides
    FeaturedProductListAdapter provideProductListAdapter() {
        return new FeaturedProductListAdapter();
    }

    @Provides
    NewArrivalsProductListAdapter provideNewArrivalsProductListAdapter() {
        return new NewArrivalsProductListAdapter();
    }

    @Provides
    SortByAdapter provideSortByAdapter() {
        return new SortByAdapter();
    }

    @Provides
    CategoriesProductAdapter provideCategoriesProductAdapter() {
        return new CategoriesProductAdapter();
    }
}
