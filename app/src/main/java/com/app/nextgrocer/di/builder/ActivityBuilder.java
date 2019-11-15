/*
 *  Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      https://mindorks.com/license/apache-v2
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License
 */

package com.app.nextgrocer.di.builder;


import com.app.nextgrocer.ui.activities.editProfile.EditProfileActivity;
import com.app.nextgrocer.ui.activities.login.LoginActivity;
import com.app.nextgrocer.ui.activities.productDetails.ProductDetailsActivity;
import com.app.nextgrocer.ui.activities.productDetails.ProductDetailsModule;
import com.app.nextgrocer.ui.activities.productList.ProductListActivity;
import com.app.nextgrocer.ui.activities.productList.ProductListModule;
import com.app.nextgrocer.ui.activities.register.RegisterActivity;
import com.app.nextgrocer.ui.activities.splash.SplashActivity;
import com.app.nextgrocer.ui.activities.home.MainActivity;
import com.app.nextgrocer.ui.fragments.categories.CategoriesFragmentProvider;
import com.app.nextgrocer.ui.fragments.home.HomeFragmentProvider;
import com.app.nextgrocer.ui.fragments.myaccount.MyAccountFragmentProvider;
import com.app.nextgrocer.ui.fragments.myaccount.changePass.ChangePassDialogProvider;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by amitshekhar on 14/09/17.
 */

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract LoginActivity bindLoginActivity();

    @ContributesAndroidInjector
    abstract RegisterActivity bindRegisterActivity();

    @ContributesAndroidInjector(modules = {HomeFragmentProvider.class,
            CategoriesFragmentProvider.class, MyAccountFragmentProvider.class, ChangePassDialogProvider.class})
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector
    abstract SplashActivity bindSplashActivity();

    @ContributesAndroidInjector
    abstract EditProfileActivity bindEditProfileActivity();

    @ContributesAndroidInjector(modules = ProductDetailsModule.class)
    abstract ProductDetailsActivity bindProductDetailsActivity();

    @ContributesAndroidInjector(modules = ProductListModule.class)
    abstract ProductListActivity bindProductListActivity();



}
