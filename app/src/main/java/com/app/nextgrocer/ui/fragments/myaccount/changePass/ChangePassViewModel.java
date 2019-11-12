package com.app.nextgrocer.ui.fragments.myaccount.changePass;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.app.nextgrocer.R;
import com.app.nextgrocer.data.DataManager;
import com.app.nextgrocer.data.model.changePass.ChangePassRequest;
import com.app.nextgrocer.data.model.profile.EditProfileRequest;
import com.app.nextgrocer.data.rest.ApiResponse;
import com.app.nextgrocer.shared.BaseViewModel;
import com.app.nextgrocer.utils.CommonUtils;
import com.app.nextgrocer.utils.ConnectionDetector;
import com.app.nextgrocer.utils.rx.SchedulerProvider;

public class ChangePassViewModel extends BaseViewModel<ChangePassNavigator> {
    private final Application application;
    private ConnectionDetector cd;

    private final MutableLiveData<ApiResponse> responseLiveData = new MutableLiveData<>();
    private static final String TAG = "ChangePassViewModel";

    public ChangePassViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, Context context, Application application) {
        super(dataManager, schedulerProvider,context, application);
        this.application = application;
        cd = new ConnectionDetector(this.application.getApplicationContext());
    }


    public void changePass(String password)
    {

        if(cd.isConnectedToInternet()) {
            responseLiveData.setValue(ApiResponse.loading());
            getCompositeDisposable().add(getDataManager().changePass(new ChangePassRequest(getDataManager().getCurrentUserId(),password))
                    .subscribeOn(getSchedulerProvider().io())
                    .observeOn(getSchedulerProvider().ui())
                    .subscribe(commonResponse -> {
                        if (commonResponse.getResponseCode() != 0) {
                            responseLiveData.setValue(ApiResponse.success());
                            Toast.makeText(application.getApplicationContext(),commonResponse.getResponseText(),Toast.LENGTH_SHORT).show();
                            getNavigator().dismissDialog();

                        } else {
                            Toast.makeText(application.getApplicationContext(), commonResponse.getResponseText(), Toast.LENGTH_SHORT).show();
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
