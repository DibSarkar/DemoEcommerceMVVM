package com.app.nextgrocer.ui.fragments.myaccount;

import com.app.nextgrocer.ui.fragments.myaccount.changePass.ChangePassDialogProvider;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MyAccountFragmentProvider {

    @ContributesAndroidInjector
    abstract MyAccountFragment provideMyAccountFragmentFactory();
}
