package com.app.nextgrocer.ui.activities.editProfile;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.app.nextgrocer.R;
import com.app.nextgrocer.data.DataManager;
import com.app.nextgrocer.data.model.profile.EditProfileRequest;
import com.app.nextgrocer.data.model.profile.ProfileRequest;
import com.app.nextgrocer.data.model.register_login.LoginRequest;
import com.app.nextgrocer.data.model.register_login.RegisterLoginResponse;
import com.app.nextgrocer.data.rest.ApiResponse;
import com.app.nextgrocer.shared.BaseViewModel;
import com.app.nextgrocer.ui.activities.login.LoginDetailsNavigator;
import com.app.nextgrocer.utils.CommonUtils;
import com.app.nextgrocer.utils.ConnectionDetector;
import com.app.nextgrocer.utils.rx.SchedulerProvider;

public class EditProfileViewModel extends BaseViewModel<EditProfileNavigator> {

    private final Application application;
    private ConnectionDetector cd;
    private final MutableLiveData<ApiResponse> responseLiveData = new MutableLiveData<>();

    private final MutableLiveData<RegisterLoginResponse> profileResponseData = new MutableLiveData<>();
    private static final String TAG = "EditProfileViewModel";

    public EditProfileViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, Context context, Application application) {
        super(dataManager,schedulerProvider,context,application);
        this.application = application;
        cd = new ConnectionDetector(this.application.getApplicationContext());
        getProfileDetails();
    }


    public boolean validateUpdate(String fname, String lname, String emaild, String mobile)
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
                                return true;
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


    private void getProfileDetails()
    {
        if(cd.isConnectedToInternet()) {
            responseLiveData.setValue(ApiResponse.loading());
            getCompositeDisposable().add(getDataManager().getProfile(new ProfileRequest(getDataManager().getCurrentUserId()))
                    .subscribeOn(getSchedulerProvider().io())
                    .observeOn(getSchedulerProvider().ui())
                    .subscribe(profileResponse -> {
                        if (profileResponse.getResponseCode() != 0) {
                            responseLiveData.setValue(ApiResponse.success());
                            profileResponseData.setValue(profileResponse);

                        } else {
                            Toast.makeText(application.getApplicationContext(), profileResponse.getResponseText(), Toast.LENGTH_SHORT).show();
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

    public void update(String fname, String lname, String emaild, String mobile,String subscribe)
    {
        if(cd.isConnectedToInternet()) {
            responseLiveData.setValue(ApiResponse.loading());
            getCompositeDisposable().add(getDataManager().editProfile(new EditProfileRequest(getDataManager().getCurrentUserId(),fname,lname,emaild,mobile,subscribe))
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
                                    getNavigator().finishActivity();
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

    public MutableLiveData<RegisterLoginResponse> getProfileResponseData() {
        return profileResponseData;
    }
}
