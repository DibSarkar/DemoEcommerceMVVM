package com.app.nextgrocer.ui.activities.address.addressList;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.app.nextgrocer.data.DataManager;
import com.app.nextgrocer.data.model.address.AddressListResponse;
import com.app.nextgrocer.data.model.address.CountriesResponse;
import com.app.nextgrocer.data.model.address.DeleteAddressRequest;
import com.app.nextgrocer.data.model.address.StatesResponse;
import com.app.nextgrocer.data.model.product_list.CategoriesProductResponse;
import com.app.nextgrocer.data.model.product_list.CategoryProductRequest;
import com.app.nextgrocer.data.model.profile.ProfileRequest;
import com.app.nextgrocer.data.rest.ApiResponse;
import com.app.nextgrocer.shared.BaseViewModel;
import com.app.nextgrocer.ui.activities.address.AddressNavigator;
import com.app.nextgrocer.utils.ConnectionDetector;
import com.app.nextgrocer.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.observers.DisposableCompletableObserver;

public class AddressListViewModel extends BaseViewModel<AddressNavigator> {

    private final Application application;
    private ConnectionDetector cd;
    private final MutableLiveData<ApiResponse> responseLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<AddressListResponse.ResponseDataBean>> addressList = new MutableLiveData<>();
    private static final String TAG = "AddressListViewModel";

    public AddressListViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, Context context, Application application) {
        super(dataManager, schedulerProvider, context, application);
        this.application = application;
        cd = new ConnectionDetector(this.application.getApplicationContext());

    }


    public MutableLiveData<ApiResponse> getResponseLiveData() {
        return responseLiveData;
    }


    public void getAddress()
    {
        if(cd.isConnectedToInternet()) {
            responseLiveData.setValue(ApiResponse.loading());
            getCompositeDisposable().add(getDataManager().getAddress(new ProfileRequest(getDataManager().getCurrentUserId()))
                    .subscribeOn(getSchedulerProvider().io())
                    .observeOn(getSchedulerProvider().ui())
                    .subscribe(addressListResponse -> {
                        if(addressListResponse.getResponseCode()!=0) {

                            if (addressListResponse.getResponseData().size() > 0) {
                                responseLiveData.setValue(ApiResponse.success());
                                addressList.setValue(addressListResponse.getResponseData());
                            } else {
                                responseLiveData.setValue(ApiResponse.error_status());
                                addressList.setValue(new ArrayList<AddressListResponse.ResponseDataBean>());
                            }
                        }
                        else {
                            responseLiveData.setValue(ApiResponse.error_status());
                          //  Toast.makeText(application.getApplicationContext(),addressListResponse.getResponseText(),Toast.LENGTH_SHORT).show();
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



    public void deleteAddress(String address_id){
        if(cd.isConnectedToInternet()) {
            responseLiveData.setValue(ApiResponse.loading());
            getCompositeDisposable().add(getDataManager().deleteAddress(new DeleteAddressRequest(getDataManager().getCurrentUserId(),address_id))
                    .subscribeOn(getSchedulerProvider().io())
                    .observeOn(getSchedulerProvider().ui())
                    .subscribeWith(new DisposableCompletableObserver() {
                                       @Override
                                       public void onComplete() {
                                           responseLiveData.setValue(ApiResponse.success());
                                           getAddress();
                                       }

                                       @Override
                                       public void onError(Throwable e) {
                                           responseLiveData.setValue(ApiResponse.error(e));
                                       }
                                   }
                    ));
                   /* .subscribe(addressListResponse -> {
                        if(addressListResponse.getResponseCode()!=0) {
                            responseLiveData.setValue(ApiResponse.success());
                            if (addressListResponse.getResponseData().size() > 0) {
                                addressList.setValue(addressListResponse.getResponseData());
                            } else {
                                addressList.setValue(new ArrayList<AddressListResponse.ResponseDataBean>());
                            }
                        }
                        else {
                            responseLiveData.setValue(ApiResponse.error_status());
                            Toast.makeText(application.getApplicationContext(),addressListResponse.getResponseText(),Toast.LENGTH_SHORT).show();
                        }
                    }, throwable -> {
                        responseLiveData.setValue(ApiResponse.error(throwable));
                        Log.d(TAG, "loadCategories: " + throwable);
                    }));*/
        }
        else {
            Toast.makeText(application.getApplicationContext(),"No Internet connection",Toast.LENGTH_SHORT).show();
        }
    }

    public MutableLiveData<List<AddressListResponse.ResponseDataBean>> getAddressList() {
        return addressList;
    }
}
