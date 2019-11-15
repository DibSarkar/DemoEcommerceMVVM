package com.app.nextgrocer.ui.fragments.home;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.app.nextgrocer.data.model.home.HomeApiResponse;
import com.app.nextgrocer.data.rest.ApiResponse;
import com.app.nextgrocer.shared.BaseViewModel;
import com.app.nextgrocer.data.DataManager;
import com.app.nextgrocer.local_models.LocalBean;
import com.app.nextgrocer.utils.ConnectionDetector;
import com.app.nextgrocer.utils.rx.SchedulerProvider;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.List;

public class HomeFragmentViewModel extends BaseViewModel<HomeFragmentNavigator> {

    private final MutableLiveData<List<HomeApiResponse.CategoryBean>> categoryList = new MutableLiveData<>();
    private final MutableLiveData<List<HomeApiResponse.FeatureBean>> featuredProductsList = new MutableLiveData<>();
    private final MutableLiveData<List<HomeApiResponse.NewarrivalBean>> new_arrival_productsList = new MutableLiveData<>();
    private final MutableLiveData<List<HomeApiResponse.BannerBean.TopbannerImageBean>> slider_list = new MutableLiveData<>();
    private final MutableLiveData<ApiResponse> responseLiveData = new MutableLiveData<>();
    private final ArrayList<LocalBean> new_arrival_products = new ArrayList<>();


    private static final String TAG = "HomeFragmentViewModel";



    private final Application application;

    private ConnectionDetector cd;
    public HomeFragmentViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, Context context, Application application) {
        super(dataManager, schedulerProvider,context, application);

        context = getContext();
        this.application = application;
        System.out.println("context home"+" "+context);
        cd = new ConnectionDetector(this.application.getApplicationContext());
        if(cd.isConnectedToInternet()) {
            fetchHomeData();
        }
        else {
            Toast.makeText(application.getApplicationContext(),"No Internet connection",Toast.LENGTH_SHORT).show();
        }
    }

    public MutableLiveData<ApiResponse> getHomeResponse() {
        return responseLiveData;
    }

    private List<LocalBean> loadNewArrivalProducts()
    {
        for (int i = 0; i <= 2 ; i++) {
            new_arrival_products.add(new LocalBean(i));
        }
        return new_arrival_products;

    }



    private void fetchHomeData()
    {

        responseLiveData.setValue(ApiResponse.loading());
        getCompositeDisposable().add(
                getDataManager()
                .getHomeData()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(homeApiResponse -> {
                            if (homeApiResponse != null && homeApiResponse.getResponseCode() != 0) {
                                responseLiveData.setValue(ApiResponse.success());
                                if(homeApiResponse.getCategory().size()>0)
                                {

                                    categoryList.setValue(homeApiResponse.getCategory());
                                }
                                else {
                                    categoryList.setValue(new ArrayList<HomeApiResponse.CategoryBean>());
                                }

                                if((homeApiResponse.getBanner().size()>0)&&(homeApiResponse.getBanner().get(0).getTopbannerImage().size()>0))
                                {

                                    slider_list.setValue(homeApiResponse.getBanner().get(0).getTopbannerImage());
                                }
                                else {
                                    slider_list.setValue(new ArrayList<HomeApiResponse.BannerBean.TopbannerImageBean>());
                                }


                                if(homeApiResponse.getFeature().size()>0)
                                {
                                    featuredProductsList.setValue(homeApiResponse.getFeature());
                                }
                                else {
                                    featuredProductsList.setValue(new ArrayList<HomeApiResponse.FeatureBean>());

                                }

                                if(homeApiResponse.getNewarrival().size()>0)
                                {
                                    new_arrival_productsList.setValue(homeApiResponse.getNewarrival());
                                }
                                else {
                                    new_arrival_productsList.setValue(new ArrayList<HomeApiResponse.NewarrivalBean>());

                                }
                            }
                            else {
                                Toast.makeText(application.getApplicationContext(),homeApiResponse.getResponseText(),Toast.LENGTH_SHORT).show();
                                responseLiveData.setValue(ApiResponse.success());
                            }



                }, throwable -> {
                    responseLiveData.setValue(ApiResponse.error(throwable));
                    Log.d(TAG, "loadHomeProducts: " + throwable);
                }));


    }

    public LiveData<List<HomeApiResponse.CategoryBean>> getCategoriesList() {
        return categoryList;
    }


    public LiveData<List<HomeApiResponse.FeatureBean>> getFeaturedProducts() {
        return featuredProductsList;
    }

    public LiveData<List<HomeApiResponse.NewarrivalBean>> getNewArrivalProducts() {
        return new_arrival_productsList;
    }

    public LiveData<List<HomeApiResponse.BannerBean.TopbannerImageBean>> getSliders()
    {
        return slider_list;
    }

}

