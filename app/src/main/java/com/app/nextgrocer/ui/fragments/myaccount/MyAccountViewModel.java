package com.app.nextgrocer.ui.fragments.myaccount;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.app.nextgrocer.data.DataManager;
import com.app.nextgrocer.data.model.home.HomeApiResponse;
import com.app.nextgrocer.data.model.local_models.ProfileDataResponse;
import com.app.nextgrocer.shared.BaseViewModel;
import com.app.nextgrocer.ui.fragments.home.HomeFragmentNavigator;
import com.app.nextgrocer.utils.ConnectionDetector;
import com.app.nextgrocer.utils.rx.SchedulerProvider;

import java.util.List;

public class MyAccountViewModel extends BaseViewModel<MyAccountNavigator> {

    private final Application application;
    private final MutableLiveData<ProfileDataResponse> profileDataResponseMutableLiveData = new MutableLiveData<>();


    public MyAccountViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, Context context, Application application) {

        super(dataManager, schedulerProvider,context, application);
        this.application = application;
       // loadProfileData();
    }


    public void loadProfileData()
    {
        getDataManager().getFirstName();
        getDataManager().getLastName();
        getDataManager().getMobileNo();
        getDataManager().getCurrentUserEmail();
        profileDataResponseMutableLiveData.setValue(new ProfileDataResponse(getDataManager().getFirstName()+" "+getDataManager().getLastName(),getDataManager().getCurrentUserEmail(),getDataManager().getMobileNo()));

    }

    public void logout()
    {
        getDataManager().setUserAsLoggedOut();
        getNavigator().openMainActivity();
    }

    public MutableLiveData<ProfileDataResponse> getProfileDataResponseMutableLiveData() {
        return profileDataResponseMutableLiveData;
    }
}
