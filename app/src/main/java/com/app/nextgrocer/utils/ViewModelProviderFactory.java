package com.app.nextgrocer.utils;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.app.nextgrocer.data.DataManager;
import com.app.nextgrocer.ui.activities.editProfile.EditProfileViewModel;
import com.app.nextgrocer.ui.activities.login.LoginViewModel;
import com.app.nextgrocer.ui.activities.productDetails.ProductDetailsViewModel;
import com.app.nextgrocer.ui.activities.productList.ProductListViewModel;
import com.app.nextgrocer.ui.activities.register.RegisterViewModel;
import com.app.nextgrocer.ui.fragments.categories.CategoriesViewModel;
import com.app.nextgrocer.ui.fragments.home.HomeFragmentViewModel;
import com.app.nextgrocer.ui.activities.home.MainViewModel;
import com.app.nextgrocer.ui.activities.splash.SplashViewModel;
import com.app.nextgrocer.ui.fragments.myaccount.MyAccountViewModel;
import com.app.nextgrocer.ui.fragments.myaccount.changePass.ChangePassViewModel;
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
  private final Context context;
  private final Application application;

  @Inject
  public ViewModelProviderFactory(DataManager dataManager,
                                  SchedulerProvider schedulerProvider, Context context, Application application) {
    this.dataManager = dataManager;
    this.schedulerProvider = schedulerProvider;
    this.context = context;
    this.application = application;
  }



  @Override
  public <T extends ViewModel> T create(Class<T> modelClass) {
    if (modelClass.isAssignableFrom(SplashViewModel.class)) {
      //noinspection unchecked
      return (T) new SplashViewModel(dataManager,schedulerProvider,context,application);
    }
    else if (modelClass.isAssignableFrom(MainViewModel.class)) {
      //noinspection unchecked
      return (T) new MainViewModel(dataManager,schedulerProvider,context,application);
    }
    else if (modelClass.isAssignableFrom(HomeFragmentViewModel.class)) {
      //noinspection unchecked
      return (T) new HomeFragmentViewModel(dataManager,schedulerProvider,context,application);
    }
    else if (modelClass.isAssignableFrom(ProductDetailsViewModel.class)) {
      //noinspection unchecked
      return (T) new ProductDetailsViewModel(dataManager,schedulerProvider,context,application);
    }
    else if (modelClass.isAssignableFrom(CategoriesViewModel.class)) {
      //noinspection unchecked
      return (T) new CategoriesViewModel(dataManager,schedulerProvider,context,application);
    }
    else if (modelClass.isAssignableFrom(ProductListViewModel.class)) {
      //noinspection unchecked
      return (T) new ProductListViewModel(dataManager,schedulerProvider,context,application);
    }
    else if (modelClass.isAssignableFrom(LoginViewModel.class)) {
      //noinspection unchecked
      return (T) new LoginViewModel(dataManager,schedulerProvider,context,application);
    }
    else if (modelClass.isAssignableFrom(RegisterViewModel.class)) {
      //noinspection unchecked
      return (T) new RegisterViewModel(dataManager,schedulerProvider,context,application);
    }
    else if (modelClass.isAssignableFrom(MyAccountViewModel.class)) {
      //noinspection unchecked
      return (T) new MyAccountViewModel(dataManager,schedulerProvider,context,application);
    }
    else if (modelClass.isAssignableFrom(EditProfileViewModel.class)) {
      //noinspection unchecked
      return (T) new EditProfileViewModel(dataManager,schedulerProvider,context,application);
    }
    else if (modelClass.isAssignableFrom(ChangePassViewModel.class)) {
      //noinspection unchecked
      return (T) new ChangePassViewModel(dataManager,schedulerProvider,context,application);
    }

    throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
  }
}