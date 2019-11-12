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

package com.app.nextgrocer.data;

import android.content.Context;

import com.app.nextgrocer.data.model.CommonResponse;
import com.app.nextgrocer.data.model.categories.CategoriesResponse;
import com.app.nextgrocer.data.model.changePass.ChangePassRequest;
import com.app.nextgrocer.data.model.home.HomeApiResponse;
import com.app.nextgrocer.data.model.product_details.ProductDetailsRequest;
import com.app.nextgrocer.data.model.product_details.ProductDetailsResponse;
import com.app.nextgrocer.data.model.product_list.CategoriesProductResponse;
import com.app.nextgrocer.data.model.product_list.CategoryProductRequest;
import com.app.nextgrocer.data.model.product_list.ProductListResponse;
import com.app.nextgrocer.data.model.profile.EditProfileRequest;
import com.app.nextgrocer.data.model.profile.ProfileRequest;
import com.app.nextgrocer.data.model.register_login.LoginRequest;
import com.app.nextgrocer.data.model.register_login.RegisterLoginResponse;
import com.app.nextgrocer.data.model.register_login.RegisterRequest;
import com.app.nextgrocer.data.prefs.PreferencesHelper;
import com.app.nextgrocer.data.rest.ApiHeader;
import com.app.nextgrocer.data.rest.ApiHelper;
import com.google.gson.Gson;


import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by amitshekhar on 07/07/17.
 */
@Singleton
public class AppDataManager implements DataManager {

    private static final String TAG = "AppDataManager";

    private final ApiHelper mApiHelper;

    private final Context mContext;

  //  private final DbHelper mDbHelper;

    private final Gson mGson;

    private final PreferencesHelper mPreferencesHelper;

    @Inject
    public AppDataManager(Context context, /*DbHelper dbHelper*/  PreferencesHelper preferencesHelper,ApiHelper apiHelper, Gson gson) {
        mContext = context;
//     mDbHelper = dbHelper;
        mPreferencesHelper = preferencesHelper;
        mApiHelper = apiHelper;
        mGson = gson;
    }


    @Override
    public Observable<Boolean> seedDatabaseOptions() {
        return null;
    }

    @Override
    public Observable<Boolean> seedDatabaseQuestions() {
        return null;
    }

    @Override
    public void setUserAsLoggedOut() {
        updateUserInfo(null,
                null,
                null,
              null,
                null,
                false);

    }

    @Override
    public void updateApiHeader(String api_token) {

        mApiHelper.getApiHeader().getProtectedApiHeader().setApi_token(api_token);
    }

    @Override
    public void updateUserInfo(String user_id, String fname, String lname, String email, String mobile, boolean subscribe) {

        setCurrentUserId(user_id);
        setFirstName(fname);
        setLastName(lname);
        setCurrentUserEmail(email);
        setMobileNo(mobile);
        setSubscribe(subscribe);

    }


    @Override
    public String getCurrentUserEmail() {
        return mPreferencesHelper.getCurrentUserEmail();
    }

    @Override
    public void setCurrentUserEmail(String email) {
        mPreferencesHelper.setCurrentUserEmail(email);

    }

    @Override
    public String getFirstName() {
        return mPreferencesHelper.getFirstName();
    }

    @Override
    public void setFirstName(String firstName) {

        mPreferencesHelper.setFirstName(firstName);

    }

    @Override
    public String getLastName() {
        return mPreferencesHelper.getLastName();
    }

    @Override
    public void setLastName(String lastName) {
        mPreferencesHelper.setLastName(lastName);

    }

    @Override
    public String getMobileNo() {
        return mPreferencesHelper.getMobileNo();
    }

    @Override
    public void setMobileNo(String mobileNo) {
        mPreferencesHelper.setMobileNo(mobileNo);

    }

    @Override
    public String getCurrentUserId() {
        return mPreferencesHelper.getCurrentUserId();
    }

    @Override
    public void setCurrentUserId(String userId) {
        mPreferencesHelper.setCurrentUserId(userId);

    }

    @Override
    public boolean isSubscribe() {
        return mPreferencesHelper.isSubscribe();
    }

    @Override
    public void setSubscribe(boolean subscribe) {
        mPreferencesHelper.setSubscribe(subscribe);

    }

    @Override
    public int getCurrentUserLoggedInMode() {
        return 0;
    }

    @Override
    public void setCurrentUserLoggedInMode(LoggedInMode mode) {

    }


    @Override
    public Observable<HomeApiResponse> getHomeData() {
        return mApiHelper.getHomeData();
    }

    @Override
    public Single<ProductDetailsResponse> getProductDetails(ProductDetailsRequest productDetailsRequest) {
        return mApiHelper.getProductDetails(productDetailsRequest);
    }

    @Override
    public Observable<ProductListResponse> getProductList(String sort, String order) {
        return mApiHelper.getProductList(sort, order);
    }

    @Override
    public Observable<CategoriesResponse> getCategoryList() {
        return mApiHelper.getCategoryList();
    }

    @Override
    public Observable<CategoriesProductResponse> getCategoryProductList(CategoryProductRequest categoryProductRequest,String sort, String order) {
        return mApiHelper.getCategoryProductList(categoryProductRequest,sort,order);
    }

    @Override
    public Single<RegisterLoginResponse> register(RegisterRequest registerRequest) {
        return mApiHelper.register(registerRequest);
    }

    @Override
    public Single<RegisterLoginResponse> login(LoginRequest loginRequest) {
        return mApiHelper.login(loginRequest);
    }

    @Override
    public Single<RegisterLoginResponse> getProfile(ProfileRequest profileRequest) {
        return mApiHelper.getProfile(profileRequest);
    }

    @Override
    public Single<RegisterLoginResponse> editProfile(EditProfileRequest editProfileRequest) {
        return mApiHelper.editProfile(editProfileRequest);
    }

    @Override
    public Single<CommonResponse> changePass(ChangePassRequest changePassRequest) {
        return mApiHelper.changePass(changePassRequest);
    }


    @Override
    public ApiHeader getApiHeader() {
        return mApiHelper.getApiHeader();
    }
}
