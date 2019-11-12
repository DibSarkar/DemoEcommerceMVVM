package com.app.nextgrocer.ui.activities.register;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.app.nextgrocer.R;
import com.app.nextgrocer.data.DataManager;
import com.app.nextgrocer.data.model.profile.EditProfileRequest;
import com.app.nextgrocer.data.model.register_login.LoginRequest;
import com.app.nextgrocer.data.model.register_login.RegisterRequest;
import com.app.nextgrocer.data.rest.ApiResponse;
import com.app.nextgrocer.shared.BaseViewModel;
import com.app.nextgrocer.ui.activities.login.LoginDetailsNavigator;
import com.app.nextgrocer.utils.CommonUtils;
import com.app.nextgrocer.utils.ConnectionDetector;
import com.app.nextgrocer.utils.rx.SchedulerProvider;

public class RegisterViewModel extends BaseViewModel<RegistrationDetailsNavigator> {

    private final Application application;
    private ConnectionDetector cd;
    private final MutableLiveData<ApiResponse> responseLiveData = new MutableLiveData<>();
    private static final String TAG = "RegisterViewModel";

    public RegisterViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, Context context, Application application) {
        super(dataManager, schedulerProvider, context, application);
        this.application = application;
        cd = new ConnectionDetector(this.application.getApplicationContext());
    }


    public boolean validateRegistration(String fname, String lname, String emaild, String mobile, String password, String confpassword)
    {

        if(!fname.isEmpty()){

            if(!lname.isEmpty())
            {
                if(!emaild.isEmpty())
                {
                    if (CommonUtils.isEmailValid(emaild)) {
                        if(!mobile.isEmpty())
                        {
                            if(mobile.length()==10)
                            {
                                if(!password.isEmpty())
                                {
                                    if(!confpassword.isEmpty())
                                    {
                                        if(password.equals(confpassword))
                                        {
                                            return true;
                                        }
                                        else {
                                            getNavigator().showErrorMessage(application.getApplicationContext().getResources().getString(R.string.password_mismatches));
                                            return false;
                                        }
                                    }
                                    else {
                                        getNavigator().showErrorMessage(application.getApplicationContext().getResources().getString(R.string.enter_confirm_password));
                                        return false;
                                    }
                                }
                                else {
                                    getNavigator().showErrorMessage(application.getApplicationContext().getResources().getString(R.string.enter_password));
                                    return false;
                                }
                            }
                            else {
                                getNavigator().showErrorMessage(application.getApplicationContext().getResources().getString(R.string.valid_mobile));
                                return false;
                            }
                        }
                        else {
                            getNavigator().showErrorMessage(application.getApplicationContext().getResources().getString(R.string.enter_mobile));
                            return false;
                        }
                    }
                    else {
                        getNavigator().showErrorMessage(application.getApplicationContext().getResources().getString(R.string.valid_email));
                        return false;
                    }
                }
                else {
                    getNavigator().showErrorMessage(application.getApplicationContext().getResources().getString(R.string.enter_email));
                    return false;
                }
            }
            else {
                getNavigator().showErrorMessage(application.getApplicationContext().getResources().getString(R.string.enter_lastname));
                return false;
            }
        }
        else {
            getNavigator().showErrorMessage(application.getApplicationContext().getResources().getString(R.string.enter_firstname));
            return false;
        }
    }



    public void register(String fname, String lname, String emaild, String mobile, String password, String subscribe,String device_type, String device_token)
    {

        if(cd.isConnectedToInternet()) {
            responseLiveData.setValue(ApiResponse.loading());
            getCompositeDisposable().add(getDataManager().register(new RegisterRequest(fname,lname,emaild,mobile,password,subscribe,device_type,device_token))
                    .subscribeOn(getSchedulerProvider().io())
                    .observeOn(getSchedulerProvider().ui())
                    .subscribe(registerLoginResponse -> {
                        if (registerLoginResponse.getResponseCode() != 0) {
                            responseLiveData.setValue(ApiResponse.success());
                            Toast.makeText(application.getApplicationContext(),registerLoginResponse.getResponseText(),Toast.LENGTH_SHORT).show();
                            getDataManager().updateUserInfo(registerLoginResponse.getResponseData().getId(),
                                    registerLoginResponse.getResponseData().getFirstname(),
                                    registerLoginResponse.getResponseData().getLastname(),
                                    registerLoginResponse.getResponseData().getEmail(),
                                    registerLoginResponse.getResponseData().getTelephone(),
                                    registerLoginResponse.getResponseData().isNewsletter());
                            //  getNavigator().openMainActivity();


                        } else {
                            Toast.makeText(application.getApplicationContext(), registerLoginResponse.getResponseText(), Toast.LENGTH_SHORT).show();
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
