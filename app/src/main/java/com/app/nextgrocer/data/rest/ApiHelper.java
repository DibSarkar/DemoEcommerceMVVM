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

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by amitshekhar on 07/07/17.
 */

public interface ApiHelper {

    ApiHeader getApiHeader();

    Observable<HomeApiResponse> getHomeData();

    Single<ProductDetailsResponse> getProductDetails(ProductDetailsRequest productDetailsRequest);

    Observable<ProductListResponse> getProductList(String sort, String order);

    Observable<CategoriesResponse> getCategoryList();

    Observable<CategoriesProductResponse> getCategoryProductList(CategoryProductRequest categoryProductRequest,String sort, String order);

    Single<RegisterLoginResponse> register(RegisterRequest registerRequest);

    Single<RegisterLoginResponse> login(LoginRequest loginRequest);

    Single<RegisterLoginResponse> getProfile(ProfileRequest profileRequest);

    Single<RegisterLoginResponse> editProfile(EditProfileRequest profileRequest);

    Single<CommonResponse> changePass(ChangePassRequest changePassRequest);
  /*  Single<LoginResponse> doGoogleLoginApiCall(LoginRequest.GoogleLoginRequest request);

    Single<LogoutResponse> doLogoutApiCall();

    Single<LoginResponse> doServerLoginApiCall(LoginRequest.ServerLoginRequest request);

    ApiHeader getApiHeader();

    Single<BlogResponse> getBlogApiCall();

    Single<OpenSourceResponse> getOpenSourceApiCall();*/
}
