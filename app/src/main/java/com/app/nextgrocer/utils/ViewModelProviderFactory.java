package com.app.nextgrocer.utils;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.app.nextgrocer.data.DataManager;
import com.app.nextgrocer.ui.activities.productDetails.ProductDetailsViewModel;
import com.app.nextgrocer.ui.fragments.categories.CategoriesViewModel;
import com.app.nextgrocer.ui.fragments.home.HomeFragmentViewModel;
import com.app.nextgrocer.ui.activities.home.MainViewModel;
import com.app.nextgrocer.ui.activities.splash.SplashViewModel;
import com.app.nextgrocer.utils.rx.SchedulerProvider;


import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by jyotidubey on 22/02/19.
 */
@Singleton
public class ViewModelProviderFactory extends ViewModelProvider.NewInstanceFactory {

  private final DataManager dataManager;
  private final SchedulerProvider schedulerProvider;

  @Inject
  public ViewModelProviderFactory(DataManager dataManager,
                                  SchedulerProvider schedulerProvider) {
    this.dataManager = dataManager;
    this.schedulerProvider = schedulerProvider;
  }


  @Override
  public <T extends ViewModel> T create(Class<T> modelClass) {
    if (modelClass.isAssignableFrom(SplashViewModel.class)) {
      //noinspection unchecked
      return (T) new SplashViewModel(dataManager,schedulerProvider);
    }
    else if (modelClass.isAssignableFrom(MainViewModel.class)) {
      //noinspection unchecked
      return (T) new MainViewModel(dataManager,schedulerProvider);
    }
    else if (modelClass.isAssignableFrom(HomeFragmentViewModel.class)) {
      //noinspection unchecked
      return (T) new HomeFragmentViewModel(dataManager,schedulerProvider);
    }
    else if (modelClass.isAssignableFrom(ProductDetailsViewModel.class)) {
      //noinspection unchecked
      return (T) new ProductDetailsViewModel(dataManager,schedulerProvider);
    }

    else if (modelClass.isAssignableFrom(CategoriesViewModel.class)) {
      //noinspection unchecked
      return (T) new CategoriesViewModel(dataManager,schedulerProvider);
    }
    throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
  }
}