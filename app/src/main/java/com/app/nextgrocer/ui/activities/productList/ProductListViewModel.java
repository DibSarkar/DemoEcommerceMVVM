package com.app.nextgrocer.ui.activities.productList;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.app.nextgrocer.data.DataManager;
import com.app.nextgrocer.data.model.product_details.ProductDetailsRequest;
import com.app.nextgrocer.data.model.product_list.CategoriesProductResponse;
import com.app.nextgrocer.data.model.product_list.CategoryProductRequest;
import com.app.nextgrocer.data.model.product_list.ProductListResponse;
import com.app.nextgrocer.data.rest.ApiResponse;
import com.app.nextgrocer.shared.BaseViewModel;
import com.app.nextgrocer.utils.ConnectionDetector;
import com.app.nextgrocer.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;


public class ProductListViewModel extends BaseViewModel<ProductListNavigator> {

    private final MutableLiveData<List<ProductListResponse.FeatureBean>> featured_list = new MutableLiveData<>();

    private final MutableLiveData<List<ProductListResponse.NewarrivalBean>> newarrival_list = new MutableLiveData<>();

    private final MutableLiveData<List<CategoriesProductResponse.ProductBean>> product_list = new MutableLiveData<>();

    private final MutableLiveData<ApiResponse> responseLiveData = new MutableLiveData<>();


    private static final String TAG = "ProductListViewModel";
    private  final Application application;
    private ConnectionDetector cd;
    public ProductListViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, Context context, Application application) {
        super(dataManager, schedulerProvider, context, application);
        this.application = application;
        cd = new ConnectionDetector(this.application.getApplicationContext());
    }


    public MutableLiveData<ApiResponse> getResponseLiveData() {
        return responseLiveData;
    }

    public void fetchFeaturedProducts(String sort, String order)
    {

        if(cd.isConnectedToInternet()) {
            responseLiveData.setValue(ApiResponse.loading());
            getCompositeDisposable().add(getDataManager().getProductList(sort, order)
                    .subscribeOn(getSchedulerProvider().io())
                    .observeOn(getSchedulerProvider().ui())
                    .subscribe(productListResponse -> {
                        if (productListResponse.getResponseCode() != 0) {
                            responseLiveData.setValue(ApiResponse.success());
                            if (productListResponse.getFeature().size() > 0) {
                                featured_list.setValue(productListResponse.getFeature());
                            } else {
                                featured_list.setValue(new ArrayList<ProductListResponse.FeatureBean>());
                            }
                            getNavigator().getProductsCount(productListResponse.getFeature().size());
                        } else {
                            responseLiveData.setValue(ApiResponse.success());
                            Toast.makeText(application.getApplicationContext(), productListResponse.getResponseText(), Toast.LENGTH_SHORT).show();

                        }
                    }, throwable -> {
                        responseLiveData.setValue(ApiResponse.error(throwable));
                        Log.d(TAG, "loadCategories: " + throwable);
                    }));
        }
        else {
            Toast.makeText(application.getApplicationContext(),"No Internet connection",Toast.LENGTH_SHORT).show();

        }

        //loadSliders();
    }

    public void fetchNewArrivals(String sort, String order)
    {
        if(cd.isConnectedToInternet()) {
        responseLiveData.setValue(ApiResponse.loading());

        getCompositeDisposable().add(getDataManager().getProductList(sort, order)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(productListResponse -> {
                    if(productListResponse.getResponseCode()!=0) {
                        responseLiveData.setValue(ApiResponse.success());
                        if (productListResponse.getFeature().size() > 0) {
                            newarrival_list.setValue(productListResponse.getNewarrival());
                        } else {
                            newarrival_list.setValue(new ArrayList<ProductListResponse.NewarrivalBean>());
                        }
                        getNavigator().getProductsCount(productListResponse.getFeature().size());
                    }
                    else {

                        responseLiveData.setValue(ApiResponse.success());
                        Toast.makeText(application.getApplicationContext(),productListResponse.getResponseText(),Toast.LENGTH_SHORT).show();
                    }
                }, throwable -> {
                    responseLiveData.setValue(ApiResponse.error(throwable));
                    Log.d(TAG, "loadCategories: " + throwable);
                }));
        }
        else {
            Toast.makeText(application.getApplicationContext(),"No Internet connection",Toast.LENGTH_SHORT).show();

        }

        //loadSliders();
    }

    public void fetchProductsByCategory(String cat_id, String sort, String order)
    {
        if(cd.isConnectedToInternet()) {
        responseLiveData.setValue(ApiResponse.loading());
        getCompositeDisposable().add(getDataManager().getCategoryProductList(new CategoryProductRequest(cat_id),sort, order)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(productListResponse -> {
                    if(productListResponse.getResponseCode()!=0) {
                        responseLiveData.setValue(ApiResponse.success());
                        if (productListResponse.getProduct().size() > 0) {
                            product_list.setValue(productListResponse.getProduct());
                        } else {
                            product_list.setValue(new ArrayList<CategoriesProductResponse.ProductBean>());
                        }
                        getNavigator().getProductsCount(productListResponse.getProduct().size());
                    }
                    else {
                        responseLiveData.setValue(ApiResponse.success());
                        Toast.makeText(application.getApplicationContext(),productListResponse.getResponseText(),Toast.LENGTH_SHORT).show();
                    }
                }, throwable -> {
                    responseLiveData.setValue(ApiResponse.error(throwable));
                    Log.d(TAG, "loadCategories: " + throwable);
                }));
        }
        else {
            Toast.makeText(application.getApplicationContext(),"No Internet connection",Toast.LENGTH_SHORT).show();

        }

        //loadSliders();
    }

    public MutableLiveData<List<ProductListResponse.FeatureBean>> getFeature_list()
    {
        return featured_list;
    }

    public MutableLiveData<List<ProductListResponse.NewarrivalBean>> getNewArrival_List()
    {
        return newarrival_list;
    }

    public MutableLiveData<List<CategoriesProductResponse.ProductBean>> getProduct_list() {
        return product_list;
    }
}
