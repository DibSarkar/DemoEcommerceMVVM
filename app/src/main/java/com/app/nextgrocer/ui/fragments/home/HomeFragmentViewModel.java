package com.app.nextgrocer.ui.fragments.home;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.app.nextgrocer.data.model.home.HomeApiResponse;
import com.app.nextgrocer.shared.BaseViewModel;
import com.app.nextgrocer.data.DataManager;
import com.app.nextgrocer.local_models.LocalBean;
import com.app.nextgrocer.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;

public class HomeFragmentViewModel extends BaseViewModel<HomeFragmentNavigator> {

    private final MutableLiveData<List<HomeApiResponse.CategoryBean>> categoryList = new MutableLiveData<>();
    private final MutableLiveData<List<HomeApiResponse.FeatureBean>> featuredProductsList = new MutableLiveData<>();
    private final MutableLiveData<List<HomeApiResponse.NewarrivalBean>> new_arrival_productsList = new MutableLiveData<>();
    private final MutableLiveData<List<HomeApiResponse.BannerBean.TopbannerImageBean>> slider_list = new MutableLiveData<>();

    private final ArrayList<HomeApiResponse.CategoryBean> categories = new ArrayList<>();
    private final ArrayList<LocalBean> featured_products = new ArrayList<>();
    private final ArrayList<LocalBean> new_arrival_products = new ArrayList<>();


    private static final String TAG = "HomeFragmentViewModel";

    public HomeFragmentViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        fetchHomeData();

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




     /*   getCompositeDisposable().add(Observable.fromArray(loadNewArrivalProducts())
                .doOnNext(list -> Log.d(TAG, "loadNewArrivalProducts: " + list.size()))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new_arrival -> {
                    if (new_arrival != null) {
                        Log.d(TAG, "v: " + new_arrival.size());

                        new_arrival_productsList.setValue(new_arrival);
                    }
                }, throwable -> {
                    Log.d(TAG, "loadNewArrivalProducts: " + throwable);
                }));
*/
        getCompositeDisposable().add(getDataManager()
                .getHomeData()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(homeApiResponse -> {
                            if (homeApiResponse != null && homeApiResponse.getResponseCode() != 0) {

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

                }, throwable -> {
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

