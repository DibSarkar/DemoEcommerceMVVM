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

    public static final String PRODUCT_DETAILS_URL = Constants.BASE_URL+"product/productDetails";


    private ApiEndPoint() {
        // This class is not publicly instantiable
    }
}
