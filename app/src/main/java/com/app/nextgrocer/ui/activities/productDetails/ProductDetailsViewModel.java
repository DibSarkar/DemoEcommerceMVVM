package com.app.nextgrocer.ui.activities.productDetails;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.app.nextgrocer.R;
import com.app.nextgrocer.data.model.product_details.ProductDetailsRequest;
import com.app.nextgrocer.data.model.product_details.ProductDetailsResponse;
import com.app.nextgrocer.data.rest.ApiResponse;
import com.app.nextgrocer.shared.BaseViewModel;
import com.app.nextgrocer.data.DataManager;
import com.app.nextgrocer.local_models.LocalBean;
import com.app.nextgrocer.utils.ConnectionDetector;
import com.app.nextgrocer.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;

public class ProductDetailsViewModel extends BaseViewModel<ProductDetailsNavigator> {

    private final MutableLiveData<List<ProductDetailsResponse.ProductBean.ImagesBean.ImageBean>> sliders_list = new MutableLiveData<>();
    private final MutableLiveData<List<ProductDetailsResponse.RelatedProductBean>> related_list = new MutableLiveData<>();
    private final MutableLiveData<ApiResponse> responseLiveData = new MutableLiveData<>();

    private static final String TAG = "ProductDetailsViewModel";

    private final Application application;
    private ConnectionDetector cd;
    public ProductDetailsViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, Context context, Application application) {
        super(dataManager,schedulerProvider,context,application);
        this.application = application;
        cd = new ConnectionDetector(this.application.getApplicationContext());

    }

    public MutableLiveData<ApiResponse> getProductResponse() {
        return responseLiveData;
    }

    public void fetchSimilarProducts(String product_id)
    {

        if(cd.isConnectedToInternet()) {
            responseLiveData.setValue(ApiResponse.loading());
            getCompositeDisposable().add(getDataManager().getProductDetails(new ProductDetailsRequest(product_id))
                    .subscribeOn(getSchedulerProvider().io())
                    .observeOn(getSchedulerProvider().ui())
                    .subscribe(productDetailsResponse -> {
                        if (productDetailsResponse.getResponseCode() != 0) {
                            responseLiveData.setValue(ApiResponse.success());
                            if (!productDetailsResponse.getProduct().getImages().getImage().isEmpty()) {
                                sliders_list.setValue(productDetailsResponse.getProduct().getImages().getImage());
                            } else {
                                sliders_list.setValue(Collections.singletonList(new ProductDetailsResponse.ProductBean.ImagesBean.ImageBean(productDetailsResponse.getProduct().getThumb(), productDetailsResponse.getProduct().getThumb())));
                            }

                            if (productDetailsResponse.getRelatedProduct().size() > 0) {
                                related_list.setValue(productDetailsResponse.getRelatedProduct());
                            } else {
                                related_list.setValue(new ArrayList<ProductDetailsResponse.RelatedProductBean>());
                            }
                            getNavigator().getProductData(productDetailsResponse.getProduct().getName(), productDetailsResponse.getProduct().getDescription(), productDetailsResponse.getProduct().getRating(), productDetailsResponse.getProduct().getReviews(), productDetailsResponse.getProduct().getPrice(), productDetailsResponse.getProduct().getSpecial());

                        } else {
                            Toast.makeText(application.getApplicationContext(), productDetailsResponse.getResponseText(), Toast.LENGTH_SHORT).show();
                            responseLiveData.setValue(ApiResponse.success());

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

    public MutableLiveData<List<ProductDetailsResponse.ProductBean.ImagesBean.ImageBean>> getProductSliders()
    {
        return sliders_list;
    }

    public MutableLiveData<List<ProductDetailsResponse.RelatedProductBean>> getSimilar_ProductsList()
    {
        return related_list;
    }



}
