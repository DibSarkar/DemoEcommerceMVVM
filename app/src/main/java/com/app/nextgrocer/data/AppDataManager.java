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

import com.app.nextgrocer.data.rest.ApiHelper;
import com.google.gson.Gson;
import com.google.gson.internal.$Gson$Types;
import com.google.gson.reflect.TypeToken;


import java.lang.reflect.Type;
import java.util.List;

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

    //private final PreferencesHelper mPreferencesHelper;

    @Inject
    public AppDataManager(Context context, /*DbHelper dbHelper, PreferencesHelper preferencesHelper,*/ ApiHelper apiHelper, Gson gson) {
        mContext = context;
 /*       mDbHelper = dbHelper;
        mPreferencesHelper = preferencesHelper;*/
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

    }

    @Override
    public void updateApiHeader(Long userId, String accessToken) {

    }

    @Override
    public void updateUserInfo(String accessToken, Long userId, LoggedInMode loggedInMode, String userName, String email, String profilePicPath) {

    }
}
