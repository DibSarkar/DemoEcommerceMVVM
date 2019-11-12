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

package com.app.nextgrocer.data.rest;


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
import com.app.nextgrocer.utils.Constants;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by amitshekhar on 07/07/17.
 */

@Singleton
public class AppApiHelper implements ApiHelper {

    private ApiHeader mApiHeader;

    @Inject
    public AppApiHelper(ApiHeader apiHeader) {
        mApiHeader = apiHeader;
    }

    @Override
    public Observable<HomeApiResponse> getHomeData() {
            return Rx2AndroidNetworking.post(ApiEndPoint.HOME_URL)
                   //.addHeaders(mApiHeader.getProtectedApiHeader())
                    .addQueryParameter("api_token", Constants.API_TOKEN)
                    .build()
                    .getObjectObservable(HomeApiResponse.class);
        }

    @Override
    public Single<ProductDetailsResponse> getProductDetails(ProductDetailsRequest productDetailsRequest) {
        return Rx2AndroidNetworking.post(ApiEndPoint.PRODUCT_DETAILS_URL)
                .addBodyParameter(productDetailsRequest)
                // .addHeaders(mApiHeader.getProtectedApiHeader())
                .addQueryParameter("api_token", Constants.API_TOKEN)
                .build()
                .getObjectSingle(ProductDetailsResponse.class);
    }

    @Override
    public Observable<ProductListResponse> getProductList(String sort, String order) {
         return Rx2AndroidNetworking.post(ApiEndPoint.PRODUCT_LIST_URL)
                // .addHeaders(mApiHeader.getProtectedApiHeader())
                 .addQueryParameter("api_token", Constants.API_TOKEN)
                 .addQueryParameter("sort",sort)
                 .addQueryParameter("order",order)
                 .build()
                 .getObjectObservable(ProductListResponse.class);
    }

    @Override
    public Observable<CategoriesResponse> getCategoryList() {
        return Rx2AndroidNetworking.post(ApiEndPoint.CATEGORIES_URL)
                // .addHeaders(mApiHeader.getProtectedApiHeader())
                .addQueryParameter("api_token", Constants.API_TOKEN)
                .build()
                .getObjectObservable(CategoriesResponse.class);
    }

    @Override
    public Observable<CategoriesProductResponse> getCategoryProductList(CategoryProductRequest categoryRequest,String sort, String order) {
        return Rx2AndroidNetworking.post(ApiEndPoint.CAT_PRODUCT_URL)
                .addBodyParameter(categoryRequest)
                .addQueryParameter("sort",sort)
                .addQueryParameter("order",order)
                // .addHeaders(mApiHeader.getProtectedApiHeader())
                .addQueryParameter("api_token", Constants.API_TOKEN)
                .build()
                .getObjectObservable(CategoriesProductResponse.class);
    }

    @Override
    public Single<RegisterLoginResponse> register(RegisterRequest registerRequest) {
        return Rx2AndroidNetworking.post(ApiEndPoint.REGISTER_URL)
                .addBodyParameter(registerRequest)
                // .addHeaders(mApiHeader.getProtectedApiHeader())
                .addQueryParameter("api_token", Constants.API_TOKEN)
                .build()
                .getObjectSingle(RegisterLoginResponse.class);
    }

    @Override
    public Single<RegisterLoginResponse> login(LoginRequest registerRequest) {
        return Rx2AndroidNetworking.post(ApiEndPoint.LOGIN_URL)
                .addBodyParameter(registerRequest)
                // .addHeaders(mApiHeader.getProtectedApiHeader())
                .addQueryParameter("api_token", Constants.API_TOKEN)
                .build()
                .getObjectSingle(RegisterLoginResponse.class);
    }

    @Override
    public Single<RegisterLoginResponse> getProfile(ProfileRequest profileRequest) {
        return Rx2AndroidNetworking.post(ApiEndPoint.MYACCOUNT_URL)
                .addBodyParameter(profileRequest)
                // .addHeaders(mApiHeader.getProtectedApiHeader())
                .addQueryParameter("api_token", Constants.API_TOKEN)
                .build()
                .getObjectSingle(RegisterLoginResponse.class);
    }

    @Override
    public Single<RegisterLoginResponse> editProfile(EditProfileRequest editProfileRequest) {
        return Rx2AndroidNetworking.post(ApiEndPoint.EDIT_PROFILE_URL)
                .addBodyParameter(editProfileRequest)
                // .addHeaders(mApiHeader.getProtectedApiHeader())
                .addQueryParameter("api_token", Constants.API_TOKEN)
                .build()
                .getObjectSingle(RegisterLoginResponse.class);
    }

    @Override
    public Single<CommonResponse> changePass(ChangePassRequest changePassRequest) {
        return Rx2AndroidNetworking.post(ApiEndPoint.CHANGE_PASS_URL)
                .addBodyParameter(changePassRequest)
                // .addHeaders(mApiHeader.getProtectedApiHeader())
                .addQueryParameter("api_token", Constants.API_TOKEN)
                .build()
                .getObjectSingle(CommonResponse.class);
    }

    @Override
    public ApiHeader getApiHeader() {
        return mApiHeader;
    }

}

 /*   @Override
    public Single<LoginResponse> doFacebookLoginApiCall(LoginRequest.FacebookLoginRequest request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_FACEBOOK_LOGIN)
                .addHeaders(mApiHeader.getPublicApiHeader())
                .addBodyParameter(request)
                .build()
                .getObjectSingle(LoginResponse.class);
    }

    @Override
    public Single<LoginResponse> doGoogleLoginApiCall(LoginRequest.GoogleLoginRequest request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_GOOGLE_LOGIN)
                .addHeaders(mApiHeader.getPublicApiHeader())
                .addBodyParameter(request)
                .build()
                .getObjectSingle(LoginResponse.class);
    }

    @Override
    public Single<LogoutResponse> doLogoutApiCall() {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_LOGOUT)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectSingle(LogoutResponse.class);
    }

    @Override
    public Single<LoginResponse> doServerLoginApiCall(LoginRequest.ServerLoginRequest request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_SERVER_LOGIN)
                .addHeaders(mApiHeader.getPublicApiHeader())
                .addBodyParameter(request)
                .build()
                .getObjectSingle(LoginResponse.class);
    }

    @Override
    public ApiHeader getApiHeader() {
        return mApiHeader;
    }

    @Override
    public Single<BlogResponse> getBlogApiCall() {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_BLOG)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectSingle(BlogResponse.class);
    }

    @Override
    public Single<OpenSourceResponse> getOpenSourceApiCall() {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_OPEN_SOURCE)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectSingle(OpenSourceResponse.class);
    }*/

