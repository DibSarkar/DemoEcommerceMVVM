package com.app.nextgrocer.ui.activities.productDetails;


import androidx.recyclerview.widget.LinearLayoutManager;

import com.app.nextgrocer.adapters.NewArrivalsProductAdapter;
import com.app.nextgrocer.adapters.SimilarProductsAdapter;

import dagger.Module;
import dagger.Provides;

@Module
public class ProductDetailsModule {

    @Provides
    LinearLayoutManager provideLinearLayoutManager(ProductDetailsActivity productDetailsActivity) {
        return new LinearLayoutManager(productDetailsActivity);
    }


    @Provides
    SimilarProductsAdapter provideSimilarProductsAdapter() {
        return new SimilarProductsAdapter();
    }
}
