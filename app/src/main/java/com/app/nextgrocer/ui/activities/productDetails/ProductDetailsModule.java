package com.app.nextgrocer.ui.activities.productDetails;


import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.app.nextgrocer.adapters.HomeCategoryAdapter;
import com.app.nextgrocer.adapters.HomeProductsAdapter;
import com.app.nextgrocer.adapters.NewArrivalsProductAdapter;
import com.app.nextgrocer.base.BaseViewModel;
import com.app.nextgrocer.data.DataManager;
import com.app.nextgrocer.ui.fragments.home.HomeFragment;
import com.app.nextgrocer.utils.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;

@Module
public class ProductDetailsModule {

    @Provides
    LinearLayoutManager provideLinearLayoutManager(ProductDetailsActivity productDetailsActivity) {
        return new LinearLayoutManager(productDetailsActivity);
    }


    @Provides
    NewArrivalsProductAdapter provideNewArrivalsProductAdapter() {
        return new NewArrivalsProductAdapter();
    }
}
