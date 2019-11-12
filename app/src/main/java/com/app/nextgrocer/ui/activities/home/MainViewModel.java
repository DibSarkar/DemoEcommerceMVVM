package com.app.nextgrocer.ui.activities.home;

import android.app.Application;
import android.content.Context;

import com.app.nextgrocer.shared.BaseViewModel;
import com.app.nextgrocer.data.DataManager;

import com.app.nextgrocer.utils.rx.SchedulerProvider;

public class MainViewModel extends BaseViewModel<MainNavigator> {

    public MainViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, Context context, Application application) {
        super(dataManager, schedulerProvider, context,application);

    }

    public boolean isLoggedIn()
    {
        if(getDataManager().getCurrentUserId()!=null)
        {
            return true;
        }
        else {
            return false;
        }

    }

}
