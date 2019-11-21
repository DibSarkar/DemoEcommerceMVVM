package com.app.nextgrocer.ui.activities.address.addressList;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.app.nextgrocer.adapters.AddressListAdapter;
import com.app.nextgrocer.adapters.CountriesAdapter;
import com.app.nextgrocer.ui.activities.productList.ProductListActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class AddressListModule {

    @Provides
    AddressListAdapter provideAddressListAdapter() {
        return new AddressListAdapter();
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(AddressListActivity addressListActivity) {
        return new LinearLayoutManager(addressListActivity);
    }

}
