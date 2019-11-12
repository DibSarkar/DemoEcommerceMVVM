package com.app.nextgrocer.ui.fragments.myaccount.changePass;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ChangePassDialogProvider {

    @ContributesAndroidInjector
    abstract ChangePassDialog provideChangePassDialogFactory();
}
