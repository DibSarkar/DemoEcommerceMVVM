package com.app.nextgrocer.ui.fragments.categories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.app.nextgrocer.base.BaseViewModel;
import com.app.nextgrocer.data.DataManager;
import com.app.nextgrocer.local_models.LocalBean;
import com.app.nextgrocer.ui.fragments.home.HomeFragmentNavigator;
import com.app.nextgrocer.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class CategoriesViewModel extends BaseViewModel<CategoriesFragmentNavigator> {


    private final MutableLiveData<List<LocalBean>> categories = new MutableLiveData<>();

    private final ArrayList<LocalBean> categoriesList = new ArrayList<>();
    private static final String TAG = "CategoriesViewModel";

    public CategoriesViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        fetchCategories();

    }

    private List<LocalBean> loadCategories()
    {
        for (int i = 0; i <= 4 ; i++) {
            categoriesList.add(new LocalBean(i));
        }
        return categoriesList;
    }

    private void fetchCategories() {

        getCompositeDisposable().add(Observable.fromArray(loadCategories())
                .doOnNext(list -> Log.d(TAG, "loadCategories: " + list.size()))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(cat -> {
                    if (cat != null) {
                        Log.d(TAG, "loadCategories: " + cat.size());

                        categories.setValue(cat);
                    }
                }, throwable -> {
                    Log.d(TAG, "loadCategories: " + throwable);
                }));
    }

    public MutableLiveData<List<LocalBean>> getCategories()
    {
        return  categories;
    }


}
