package com.app.nextgrocer.ui.fragments.home;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.app.nextgrocer.R;
import com.app.nextgrocer.base.BaseViewModel;
import com.app.nextgrocer.data.DataManager;
import com.app.nextgrocer.local_models.LocalBean;
import com.app.nextgrocer.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;

public class HomeFragmentViewModel extends BaseViewModel<HomeFragmentNavigator> {

    private final MutableLiveData<List<LocalBean>> categoryList = new MutableLiveData<>();
    private final MutableLiveData<List<LocalBean>> featuredProductsList = new MutableLiveData<>();
    private final MutableLiveData<List<LocalBean>> new_arrival_productsList = new MutableLiveData<>();

    private final ArrayList<LocalBean> categories = new ArrayList<>();
    private final ArrayList<LocalBean> featured_products = new ArrayList<>();
    private final ArrayList<LocalBean> new_arrival_products = new ArrayList<>();
    private final HashMap<String,Integer> file_maps = new HashMap<String, Integer>();

    private static final String TAG = "HomeFragmentViewModel";

    public HomeFragmentViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        fetchHomeData();

    }


    private List<LocalBean> loadCategories()
    {
        for (int i = 0; i <= 4 ; i++) {
            categories.add(new LocalBean(i));
        }
        return categories;
    }

    private List<LocalBean> loadFeaturedProducts()
    {
        for (int i = 0; i <= 5 ; i++) {
            featured_products.add(new LocalBean(i));
        }

        return featured_products;

    }

    private List<LocalBean> loadNewArrivalProducts()
    {
        for (int i = 0; i <= 2 ; i++) {
            new_arrival_products.add(new LocalBean(i));
        }
        return new_arrival_products;

    }

    private void loadSliders()
    {

        file_maps.put("Banner1", R.drawable.banner);
        file_maps.put("Banner2",R.drawable.banner);
       /* Observable<Map.Entry<String,Integer>> entries =
                Observable.fromIterable(file_maps.entrySet());*/


    }

    private void fetchHomeData()
    {

        getCompositeDisposable().add(Observable.fromArray(loadCategories())
                .doOnNext(list -> Log.d(TAG, "loadCategories: " + list.size()))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(cat -> {
                    if (cat != null) {
                        Log.d(TAG, "loadCategories: " + cat.size());

                        categoryList.setValue(cat);
                    }
                }, throwable -> {
                    Log.d(TAG, "loadCategories: " + throwable);
                }));


        getCompositeDisposable().add(Observable.fromArray(loadFeaturedProducts())
                .doOnNext(list -> Log.d(TAG, "loadFeaturedProducts: " + list.size()))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(featured_products -> {
                    if (featured_products != null) {
                        Log.d(TAG, "loadFeaturedProducts: " + featured_products.size());

                        featuredProductsList.setValue(featured_products);
                    }
                }, throwable -> {
                    Log.d(TAG, "loadFeaturedProducts: " + throwable);
                }));


        getCompositeDisposable().add(Observable.fromArray(loadNewArrivalProducts())
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

        loadSliders();


    }

    public LiveData<List<LocalBean>> getCategoriesList() {
        return categoryList;
    }


    public LiveData<List<LocalBean>> getFeaturedProducts() {
        return featuredProductsList;
    }

    public LiveData<List<LocalBean>> getNewArrivalProducts() {
        return new_arrival_productsList;
    }

    public HashMap<String,Integer> getSliders()
    {
        return file_maps;
    }

}

