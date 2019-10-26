package com.app.nextgrocer.ui.activities.productDetails;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.app.nextgrocer.R;
import com.app.nextgrocer.data.model.product_details.ProductDetailsRequest;
import com.app.nextgrocer.data.model.product_details.ProductDetailsResponse;
import com.app.nextgrocer.shared.BaseViewModel;
import com.app.nextgrocer.data.DataManager;
import com.app.nextgrocer.local_models.LocalBean;
import com.app.nextgrocer.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;

public class ProductDetailsViewModel extends BaseViewModel<ProductDetailsNavigator> {

    private final MutableLiveData<List<LocalBean>> similar_products = new MutableLiveData<>();
    private final MutableLiveData<List<ProductDetailsResponse.ProductBean.ImagesBean.ImageBean>> sliders_list = new MutableLiveData<>();
    private final MutableLiveData<List<ProductDetailsResponse.RelatedProductBean>> related_list = new MutableLiveData<>();

    private static final String TAG = "ProductDetailsViewModel";
    private final ArrayList<LocalBean> similar_productsList = new ArrayList<>();


    public ProductDetailsViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);

    }

    public void fetchSimilarProducts(String product_id)
    {


        getCompositeDisposable().add(getDataManager().getProductDetails(new ProductDetailsRequest(product_id))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(productDetailsResponse -> {
                    if(!productDetailsResponse.getProduct().getImages().getImage().isEmpty())
                    {
                        sliders_list.setValue(productDetailsResponse.getProduct().getImages().getImage());
                    }
                    else {
                        sliders_list.setValue(Collections.singletonList(new ProductDetailsResponse.ProductBean.ImagesBean.ImageBean(productDetailsResponse.getProduct().getThumb(), productDetailsResponse.getProduct().getThumb())));
                    }

                    if(productDetailsResponse.getRelatedProduct().size()>0)
                    {
                        related_list.setValue(productDetailsResponse.getRelatedProduct());
                    }
                    getNavigator().getProductData(productDetailsResponse.getProduct().getName(),productDetailsResponse.getProduct().getDescription(),productDetailsResponse.getProduct().getRating(),productDetailsResponse.getProduct().getReviews(),productDetailsResponse.getProduct().getPrice(),productDetailsResponse.getProduct().getSpecial());
                }, throwable -> {
                    Log.d(TAG, "loadCategories: " + throwable);
                }));

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
