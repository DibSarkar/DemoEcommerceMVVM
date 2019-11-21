package com.app.nextgrocer.ui.activities.address.addAddress;

import com.app.nextgrocer.adapters.CountriesAdapter;
import com.app.nextgrocer.adapters.StatesAdapter;

import dagger.Module;
import dagger.Provides;

@Module
public class CountriesModule {

    @Provides
    CountriesAdapter provideCountriesAdapter() {
        return new CountriesAdapter();
    }


    @Provides
    StatesAdapter provideStatesAdapter() {
        return new StatesAdapter();
    }
}
