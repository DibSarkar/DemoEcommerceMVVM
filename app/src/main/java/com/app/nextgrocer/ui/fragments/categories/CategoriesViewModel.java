package com.app.nextgrocer.ui.fragments.categories;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.app.nextgrocer.data.model.categories.CategoriesResponse;
import com.app.nextgrocer.data.model.product_list.ProductListResponse;
import com.app.nextgrocer.data.rest.ApiResponse;
import com.app.nextgrocer.shared.BaseViewModel;
import com.app.nextgrocer.data.DataManager;
import com.app.nextgrocer.local_models.LocalBean;
import com.app.nextgrocer.utils.ConnectionDetector;
import com.app.nextgrocer.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class CategoriesViewModel extends BaseViewModel<CategoriesFragmentNavigator> {


    private final MutableLiveData<List<CategoriesResponse.DataBean>> categories = new MutableLiveData<>();

    private final MutableLiveData<ApiResponse> responseLiveData = new MutableLiveData<>();

    private static final String TAG = "CategoriesViewModel";
    private final Application application;
    private ConnectionDetector cd;
    public CategoriesViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, Context context, Application application) {
        super(dataManager, schedulerProvider, context, application);
        this.application = application;
        cd = new ConnectionDetector(this.application.getApplicationContext());
        if(cd.isConnectedToInternet()) {
        fetchCategories();
        }
        else {
            Toast.makeText(application.getApplicationContext(),"No Internet connection",Toast.LENGTH_SHORT).show();
        }


    }

    private void fetchCategories() {

        responseLiveData.setValue(ApiResponse.loading());
        getCompositeDisposable().add(getDataManager().getCategoryList()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(categoriesResponse -> {
                    if(categoriesResponse.getResponseCode()!=0) {
                        responseLiveData.setValue(ApiResponse.success());
                        if (categoriesResponse.getData().size() > 0) {
                            categories.setValue(categoriesResponse.getData());
                        } else {
                            categories.setValue(new ArrayList<CategoriesResponse.DataBean>());
                        }

                    }
                    else {
                        responseLiveData.setValue(ApiResponse.error_status());
                        Toast.makeText(application.getApplicationContext(),categoriesResponse.getResponseText(),Toast.LENGTH_SHORT).show();

                    }
                }, throwable -> {
                    responseLiveData.setValue(ApiResponse.error(throwable));
                    Log.d(TAG, "loadCategories: " + throwable);
                }));
    }

    public MutableLiveData<ApiResponse> getResponseLiveData() {
        return responseLiveData;
    }

    public MutableLiveData<List<CategoriesResponse.DataBean>> getCategories()
    {
        return  categories;
    }


}
