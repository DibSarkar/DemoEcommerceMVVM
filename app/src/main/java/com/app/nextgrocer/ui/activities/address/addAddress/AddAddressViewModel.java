package com.app.nextgrocer.ui.activities.address.addAddress;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.app.nextgrocer.data.DataManager;
import com.app.nextgrocer.data.model.address.AddAddressRequest;
import com.app.nextgrocer.data.model.address.CountriesResponse;
import com.app.nextgrocer.data.model.address.StatesRequest;
import com.app.nextgrocer.data.model.address.StatesResponse;
import com.app.nextgrocer.data.model.product_list.ProductListResponse;
import com.app.nextgrocer.data.model.profile.ProfileRequest;
import com.app.nextgrocer.data.model.register_login.RegisterLoginResponse;
import com.app.nextgrocer.data.rest.ApiResponse;
import com.app.nextgrocer.shared.BaseViewModel;
import com.app.nextgrocer.ui.activities.address.AddressNavigator;
import com.app.nextgrocer.ui.activities.editProfile.EditProfileNavigator;
import com.app.nextgrocer.utils.ConnectionDetector;
import com.app.nextgrocer.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;

public class AddAddressViewModel extends BaseViewModel<AddressNavigator> {

    private final Application application;
    private ConnectionDetector cd;
    private final MutableLiveData<ApiResponse> responseLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<StatesResponse.DataBean>> allstatesBeanMutableLiveData = new MutableLiveData<>();
    private final MutableLiveData<CountriesResponse> allcountryBeanMutableLiveData = new MutableLiveData<>();
    private static final String TAG = "AddAddressViewModel";

    public AddAddressViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, Context context, Application application) {
        super(dataManager, schedulerProvider, context, application);
        this.application = application;
        cd = new ConnectionDetector(this.application.getApplicationContext());
        getCountries();
    }

    private void getCountries()
    {
        if(cd.isConnectedToInternet()) {
            responseLiveData.setValue(ApiResponse.loading());
            getCompositeDisposable().add(getDataManager().getCountries(new ProfileRequest(getDataManager().getCurrentUserId()))
                    .subscribeOn(getSchedulerProvider().io())
                    .observeOn(getSchedulerProvider().ui())
                    .subscribe(countriesResponse -> {
                        allcountryBeanMutableLiveData.setValue(countriesResponse);
                        if (countriesResponse.getResponseCode() != 0) {
                            responseLiveData.setValue(ApiResponse.success());
                        } else {
                            Toast.makeText(application.getApplicationContext(), countriesResponse.getResponseText(), Toast.LENGTH_SHORT).show();
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



    public void getStates(String country_id)
    {
        if(cd.isConnectedToInternet()) {
            responseLiveData.setValue(ApiResponse.loading());
            getCompositeDisposable().add(getDataManager().getStates(new StatesRequest(country_id))
                    .subscribeOn(getSchedulerProvider().io())
                    .observeOn(getSchedulerProvider().ui())
                    .subscribe(statesResponse -> {
                        if (statesResponse.getResponseCode() != 0) {
                            responseLiveData.setValue(ApiResponse.success());
                            if(statesResponse.getData().size()>0)
                            {
                                allstatesBeanMutableLiveData.setValue(statesResponse.getData());
                            }
                            else {
                                allstatesBeanMutableLiveData.setValue(new ArrayList<StatesResponse.DataBean>());
                            }

                        } else {
                            Toast.makeText(application.getApplicationContext(), statesResponse.getResponseText(), Toast.LENGTH_SHORT).show();
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


    public boolean isValidAddress(String fname, String lname,String address1, String city, String country_id, String pincode, String state_id)
    {
        if(!fname.isEmpty())
        {
            if(!lname.isEmpty())
            {
                if(!address1.isEmpty())
                {

                    if(!city.isEmpty())
                    {
                        if(!country_id.equals(""))
                        {
                            if(!pincode.isEmpty())
                            {
                                if(!state_id.equals(""))
                                {
                                    return true;
                                }
                                else {
                                    getNavigator().showErrorMessage("Please select state");
                                    return false;
                                }
                            }
                            else {
                                getNavigator().showErrorMessage("Please enter pin code");
                                return false;
                            }
                        }
                        else {
                            getNavigator().showErrorMessage("Please select country");
                            return false;
                        }
                    }
                    else {
                        getNavigator().showErrorMessage("Please enter city");
                        return false;
                    }
                }
                else {
                    getNavigator().showErrorMessage("Please enter address1");
                    return false;
                }

            }
            else {
                getNavigator().showErrorMessage("Please enter last name");
                return false;
            }
        }
        else {
            getNavigator().showErrorMessage("Please enter first name");
            return false;
        }


    }

    public void addAddress(String firstname, String lastname, String address_1, String address_2, String postcode, String state_id, String country_id, String default_address, String company, String city)
    {
        if(cd.isConnectedToInternet()) {
            responseLiveData.setValue(ApiResponse.loading());
            getCompositeDisposable().add(getDataManager().addAddress(new AddAddressRequest(getDataManager().getCurrentUserId(),firstname,lastname,address_1, address_2,postcode,state_id, country_id, default_address, company,city))
                    .subscribeOn(getSchedulerProvider().io())
                    .observeOn(getSchedulerProvider().ui())
                    .subscribe(statesResponse -> {
                        if (statesResponse.getResponseCode() != 0) {
                            responseLiveData.setValue(ApiResponse.success());
                            Toast.makeText(application.getApplicationContext(), statesResponse.getResponseText(), Toast.LENGTH_SHORT).show();
                            getNavigator().finishActivity();
                        } else {
                            Toast.makeText(application.getApplicationContext(), statesResponse.getResponseText(), Toast.LENGTH_SHORT).show();
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

    public MutableLiveData<CountriesResponse> getAllcountryBeanMutableLiveData() {
        return allcountryBeanMutableLiveData;
    }

    public MutableLiveData<List<StatesResponse.DataBean>> getAllstatesBeanMutableLiveData() {
        return allstatesBeanMutableLiveData;
    }
}
