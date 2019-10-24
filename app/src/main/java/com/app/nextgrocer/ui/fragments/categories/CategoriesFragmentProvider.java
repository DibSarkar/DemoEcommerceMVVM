package com.app.nextgrocer.ui.fragments.categories;

import com.app.nextgrocer.ui.fragments.home.HomeFragment;
import com.app.nextgrocer.ui.fragments.home.HomeFragmentModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class CategoriesFragmentProvider {

    @ContributesAndroidInjector(modules = CategoriesFragmentModule.class)
    abstract CategoriesFragment provideCategoriesFragmentFactory();
}
