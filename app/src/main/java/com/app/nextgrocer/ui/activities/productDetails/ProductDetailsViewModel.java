package com.app.nextgrocer.ui.activities.productDetails;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.app.nextgrocer.R;
import com.app.nextgrocer.base.BaseViewModel;
import com.app.nextgrocer.data.DataManager;
import com.app.nextgrocer.local_models.LocalBean;
import com.app.nextgrocer.ui.activities.splash.SplashNavigator;
import com.app.nextgrocer.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;

public class ProductDetailsViewModel extends BaseViewModel<ProductDetailsNavigator> {

    private final MutableLiveData<List<LocalBean>> similar_products = new MutableLiveData<>();
    private static final String TAG = "ProductDetailsViewModel";
    private final ArrayList<LocalBean> similar_productsList = new ArrayList<>();
    private final HashMap<String,Integer> file_maps = new HashMap<String, Integer>();

    public ProductDetailsViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        fetchSimilarProducts();
    }

    private void loadSliders()
    {

        file_maps.put("Banner1", R.drawable.banner);
        file_maps.put("Banner2",R.drawable.banner);
       /* Observable<Map.Entry<String,Integer>> entries =
                Observable.fromIterable(file_maps.entrySet());*/


    }
    private List<LocalBean> loadSimilarProducts()
    {
        for (int i = 0; i <= 2 ; i++) {
            similar_productsList.add(new LocalBean(i));
        }
        return similar_productsList;

    }


    private void fetchSimilarProducts()
    {
        getCompositeDisposable().add(Observable.fromArray(loadSimilarProducts())
                .doOnNext(list -> Log.d(TAG, "loadCategories: " + list.size()))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(similar -> {
                    if (similar != null) {
                        Log.d(TAG, "loadCategories: " + similar.size());

                        similar_products.setValue(similar);
                    }
                }, throwable -> {
                    Log.d(TAG, "loadCategories: " + throwable);
                }));
        loadSliders();
    }


    public MutableLiveData<List<LocalBean>> getSimilar_ProductsList()
    {
        return similar_products;
    }

    public HashMap<String,Integer> getSliders()
    {
        return file_maps;
    }
}
