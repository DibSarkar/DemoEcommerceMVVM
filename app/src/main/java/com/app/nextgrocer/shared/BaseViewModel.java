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

package com.app.nextgrocer.shared;


import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.app.nextgrocer.R;
import com.app.nextgrocer.data.DataManager;
import com.app.nextgrocer.utils.CommonUtils;
import com.app.nextgrocer.utils.rx.SchedulerProvider;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by amitshekhar on 07/07/17.
 */

public abstract class BaseViewModel<N> extends AndroidViewModel {

    private final DataManager mDataManager;

    private com.app.nextgrocer.base.BaseActivity mActivity;

    private final SchedulerProvider mSchedulerProvider;

    private CompositeDisposable mCompositeDisposable;
    private WeakReference<N> mNavigator;
    private ProgressDialog mProgressDialog;
    private Context context;


    public BaseViewModel(DataManager dataManager,
                         SchedulerProvider schedulerProvider, Context context, Application application) {
        super(application);
        this.mDataManager = dataManager;
        this.mSchedulerProvider = schedulerProvider;
        this.mCompositeDisposable = new CompositeDisposable();
        this.context = context;
       // mProgressDialog = CommonUtils.showLoadingDialog(context);

      //  mProgressDialog.setCancelable(false);

    }

    public Context getContext() {

        return context;
    }

    public void setContext() {

        this.context =  mActivity.getApplicationContext();

    }

    @Override
    protected void onCleared() {
        mCompositeDisposable.dispose();
        super.onCleared();
    }

    public CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable;
    }

    public void showLoading(String msg,Context context){

        mProgressDialog = new ProgressDialog(context, R.style.MyAlertDialogTheme);
        mProgressDialog.setMessage(msg);
        mProgressDialog.show();
    }

    public void hideLoading(){
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

    public DataManager getDataManager() {
        return mDataManager;
    }


    public N getNavigator() {
        return mNavigator.get();
    }

    public void setNavigator(N navigator) {
        this.mNavigator = new WeakReference<>(navigator);
    }

    public SchedulerProvider getSchedulerProvider() {
        return mSchedulerProvider;
    }
}
