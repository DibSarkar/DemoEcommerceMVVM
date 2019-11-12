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

package com.app.nextgrocer.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;


import com.app.nextgrocer.data.DataManager;
import com.app.nextgrocer.di.PreferenceInfo;
import com.app.nextgrocer.utils.Constants;

import javax.inject.Inject;

/**
 * Created by amitshekhar on 07/07/17.
 */

public class AppPreferencesHelper implements PreferencesHelper {

    private static final String PREF_KEY_ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN";

    private static final String PREF_KEY_CURRENT_USER_EMAIL = "PREF_KEY_CURRENT_USER_EMAIL";

    private static final String PREF_KEY_CURRENT_USER_ID = "PREF_KEY_CURRENT_USER_ID";

    private static final String PREF_KEY_CURRENT_USER_FIRST_NAME = "PREF_KEY_CURRENT_USER_FIRST_NAME";

    private static final String PREF_KEY_CURRENT_USER_LAST_NAME = "PREF_KEY_CURRENT_USER_LAST_NAME";

    private static final String PREF_KEY_CURRENT_USER_MOBILE =  "PREF_KEY_CURRENT_USER_MOBILE";

    private static final String PREF_KEY_CURRENT_USER_SUBSCRIBE =  "PREF_KEY_CURRENT_USER_SUBSCRIBE";



    private static final String PREF_KEY_USER_LOGGED_IN_MODE = "PREF_KEY_USER_LOGGED_IN_MODE";

    private final SharedPreferences mPrefs;

    @Inject
    public AppPreferencesHelper(Context context, @PreferenceInfo String prefFileName) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }


    @Override
    public String getCurrentUserEmail() {
        return mPrefs.getString(PREF_KEY_CURRENT_USER_EMAIL, null);
    }

    @Override
    public void setCurrentUserEmail(String email) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_USER_EMAIL, email).apply();
    }

    @Override
    public String getFirstName() {
        return mPrefs.getString(PREF_KEY_CURRENT_USER_FIRST_NAME,null);
    }

    @Override
    public void setFirstName(String firstName) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_USER_FIRST_NAME, firstName).apply();
    }

    @Override
    public String getLastName() {
        return mPrefs.getString(PREF_KEY_CURRENT_USER_LAST_NAME,null);
    }

    @Override
    public void setLastName(String lastName) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_USER_LAST_NAME, lastName).apply();

    }

    @Override
    public String getMobileNo() {
        return mPrefs.getString(PREF_KEY_CURRENT_USER_MOBILE,null);
    }

    @Override
    public void setMobileNo(String mobileNo) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_USER_MOBILE, mobileNo).apply();
    }

    @Override
    public String getCurrentUserId() {
        return mPrefs.getString(PREF_KEY_CURRENT_USER_ID,null);
    }

    @Override
    public void setCurrentUserId(String userId) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_USER_ID, userId).apply();
    }

    @Override
    public boolean isSubscribe() {
        return mPrefs.getBoolean(PREF_KEY_CURRENT_USER_SUBSCRIBE,false);
    }

    @Override
    public void setSubscribe(boolean subscribe) {
        mPrefs.edit().putBoolean(PREF_KEY_CURRENT_USER_SUBSCRIBE, subscribe).apply();
    }


    @Override
    public int getCurrentUserLoggedInMode() {
        return mPrefs.getInt(PREF_KEY_USER_LOGGED_IN_MODE,
                DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT.getType());
    }

    @Override
    public void setCurrentUserLoggedInMode(DataManager.LoggedInMode mode) {
        mPrefs.edit().putInt(PREF_KEY_USER_LOGGED_IN_MODE, mode.getType()).apply();
    }


}
