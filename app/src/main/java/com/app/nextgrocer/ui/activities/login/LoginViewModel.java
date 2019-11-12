package com.app.nextgrocer.ui.activities.login;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.app.nextgrocer.data.DataManager;
import com.app.nextgrocer.data.model.product_details.ProductDetailsRequest;
import com.app.nextgrocer.data.model.product_details.ProductDetailsResponse;
import com.app.nextgrocer.data.model.register_login.LoginRequest;
import com.app.nextgrocer.data.rest.ApiResponse;
import com.app.nextgrocer.shared.BaseViewModel;
import com.app.nextgrocer.ui.activities.productDetails.ProductDetailsNavigator;
import com.app.nextgrocer.utils.CommonUtils;
import com.app.nextgrocer.utils.ConnectionDetector;
import com.app.nextgrocer.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.Collections;

public class LoginViewModel extends BaseViewModel<LoginDetailsNavigator> {

    private final Application application;
    private ConnectionDetector cd;
    private final MutableLiveData<ApiResponse> responseLiveData = new MutableLiveData<>();
    private static final String TAG = "LoginViewModel";

    public LoginViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, Context context, Application application) {
        super(dataManager,schedulerProvider,context,application);
        this.application = application;
        cd = new ConnectionDetector(this.application.getApplicationContext());

    }


    public boolean isEmailAndPasswordValid(String email, String password) {
        // validate email and password
        if (TextUtils.isEmpty(email)) {
            return false;
        }
        if (!CommonUtils.isEmailValid(email)) {
            return false;
        }
        if (TextUtils.isEmpty(password)) {
            return false;
        }
        return true;
    }

    public void login(String email,String pass,String device_type, String device_token)
    {
        if(cd.isConnectedToInternet()) {
            responseLiveData.setValue(ApiResponse.loading());
            getCompositeDisposable().add(getDataManager().login(new LoginRequest(email,pass,device_type,device_token))
                    .subscribeOn(getSchedulerProvider().io())
                    .observeOn(getSchedulerProvider().ui())
                    .subscribe(loginResponse -> {
                        if (loginResponse.getResponseCode() != 0) {
                            responseLiveData.setValue(ApiResponse.success());
                            Toast.makeText(application.getApplicationContext(),loginResponse.getResponseText(),Toast.LENGTH_SHORT).show();
                            getDataManager().updateUserInfo(loginResponse.getResponseData().getId(),loginResponse.getResponseData().getFirstname(),
                                    loginResponse.getResponseData().getLastname(),
                                    loginResponse.getResponseData().getEmail(),
                                    loginResponse.getResponseData().getTelephone(),
                                    loginResponse.getResponseData().isNewsletter());
                          //  getNavigator().openMainActivity();


                        } else {
                            Toast.makeText(application.getApplicationContext(), loginResponse.getResponseText(), Toast.LENGTH_SHORT).show();
                            responseLiveData.setValue(ApiResponse.error_status());

                        }
                    }, throwable -> {
                        responseLiveData.setValue(ApiResponse.error(throwable));
                        Log.d(TAG, "loadCategories: " + throwable);
                    }));
        }
        else {
            Toast.makeText(application.getApplicationContext(),"No Internet connection",Toast.LENGTH_SHORT).show();

        }
    }

    public MutableLiveData<ApiResponse> getResponseLiveData() {
        return responseLiveData;
    }
}
