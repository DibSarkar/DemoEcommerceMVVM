package com.app.nextgrocer.ui.activities.home;

import com.app.nextgrocer.adapters.NewArrivalsProductAdapter;
import com.app.nextgrocer.ui.fragments.MyAccountFragment;
import com.app.nextgrocer.ui.fragments.categories.CategoriesFragment;
import com.app.nextgrocer.ui.fragments.home.HomeFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {


    @Provides
    HomeFragment provideHomeFragment() {
        return new HomeFragment();
    }

    @Provides
    CategoriesFragment provideCategoriesFragment() {
        return new CategoriesFragment();
    }

    @Provides
    MyAccountFragment provideMyAccountFragment() {
        return new MyAccountFragment();
    }


}
