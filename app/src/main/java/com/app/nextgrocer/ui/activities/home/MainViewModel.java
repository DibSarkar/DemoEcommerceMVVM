package com.app.nextgrocer.ui.activities.home;

import com.app.nextgrocer.base.BaseViewModel;
import com.app.nextgrocer.data.DataManager;

import com.app.nextgrocer.utils.rx.SchedulerProvider;

public class MainViewModel extends BaseViewModel<MainNavigator> {

    public MainViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }




}
