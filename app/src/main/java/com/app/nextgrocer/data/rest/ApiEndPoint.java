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


import com.app.nextgrocer.BuildConfig;
import com.app.nextgrocer.utils.Constants;

/**
 * Created by amitshekhar on 07/07/17.
 */

public final class ApiEndPoint {

    public static final String HOME_URL = Constants.BASE_URL+"home/homePage";

    public static final String PRODUCT_LIST_URL = Constants.BASE_URL+"product/allProduct";

    public static final String PRODUCT_DETAILS_URL = Constants.BASE_URL+"product/productDetails";

    public static final String CATEGORIES_URL = Constants.BASE_URL+"category/allcategory";

    public static final String CAT_PRODUCT_URL = Constants.BASE_URL+"category/catProduct";

    public static final String REGISTER_URL = Constants.BASE_URL+"customer/add";

    public static final String LOGIN_URL = Constants.BASE_URL+"customer/login";

    public static final String MYACCOUNT_URL = Constants.BASE_URL+"customer/myAccount";

    public static final String EDIT_PROFILE_URL = Constants.BASE_URL+"customer/editAccount";

    public static final String CHANGE_PASS_URL = Constants.BASE_URL+"customer/changePassword";

    public static final String COUNTRIES_URL = Constants.BASE_URL+"address/countryList";

    public static final String STATES_URL = Constants.BASE_URL+"address/stateList";

    public static final String ADD_ADDRESS_URL = Constants.BASE_URL+"address/addressAdd";

    public static final String ADDRESS_LIST_URL = Constants.BASE_URL+"address/getAddress";

    public static final String DELETE_ADDRESS_URL = Constants.BASE_URL+"address/deleteAddress";

}
