package com.app.nextgrocer.ui.activities.home;

import com.app.nextgrocer.ui.fragments.categories.CategoriesFragment;
import com.app.nextgrocer.ui.fragments.categories.CategoriesFragmentModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainActivityProvider {

    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity provideMainActivity();

}
