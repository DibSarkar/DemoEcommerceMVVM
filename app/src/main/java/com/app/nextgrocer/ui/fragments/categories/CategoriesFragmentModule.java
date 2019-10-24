package com.app.nextgrocer.ui.fragments.categories;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.app.nextgrocer.adapters.CategoryAdapter;
import com.app.nextgrocer.adapters.HomeCategoryAdapter;
import com.app.nextgrocer.ui.fragments.home.HomeFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class CategoriesFragmentModule {

    @Provides
    LinearLayoutManager provideLinearLayoutManager(CategoriesFragment fragment) {
        return new LinearLayoutManager(fragment.getActivity());
    }

    @Provides
    CategoryAdapter provideCategoryAdapter() {
        return new CategoryAdapter();
    }

}
