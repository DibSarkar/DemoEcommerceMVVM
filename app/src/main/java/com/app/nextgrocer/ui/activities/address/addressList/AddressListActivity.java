package com.app.nextgrocer.ui.activities.address.addressList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.nextgrocer.R;

import com.app.nextgrocer.adapters.AddressListAdapter;
import com.app.nextgrocer.base.BaseActivity;
import com.app.nextgrocer.data.model.address.AddressListResponse;
import com.app.nextgrocer.data.rest.ApiResponse;
import com.app.nextgrocer.ui.activities.address.AddressNavigator;
import com.app.nextgrocer.ui.activities.address.addAddress.AddAddressActivity;
import com.app.nextgrocer.ui.activities.editProfile.EditProfileViewModel;
import com.app.nextgrocer.utils.CommonUtils;
import com.app.nextgrocer.utils.Constants;
import com.app.nextgrocer.utils.DividerItemDecoration;
import com.app.nextgrocer.utils.SpacesItemDecoration;
import com.app.nextgrocer.utils.ViewModelProviderFactory;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddressListActivity extends BaseActivity<AddressListViewModel> implements AddressNavigator {


    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.rv_address_list)
    RecyclerView rv_address_list;

    @BindView(R.id.tv_no_address)
    TextView tv_no_address;

    @Inject
    ViewModelProviderFactory factory;

    @Inject
    AddressListAdapter addressListAdapter;

    @Inject
    LinearLayoutManager linearLayoutManager;

    AddressListViewModel addressListViewModel;
    ProgressDialog progressDialog;

    public  static final int ADD_CODE = 400;


    protected void setUp() {
        setUnBinder(ButterKnife.bind(this));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Drawable upArrow = ContextCompat.getDrawable(this, R.drawable.tool_arrow);
        upArrow.setColorFilter(ContextCompat.getColor(this, R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        setTitle("");
        setUpAdapter();
    }

    @Override
    public int getLayoutId() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return R.layout.activity_address_list;
    }

    @Override
    public AddressListViewModel getViewModel() {
        progressDialog = CommonUtils.showLoadingDialog(this);
        addressListViewModel = ViewModelProviders.of(this,factory).get(AddressListViewModel.class);
        return addressListViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUp();
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void finishActivity() {
    }


    public void setUpAdapter() {

        int spacingInPixels1 = getResources().getDimensionPixelSize(R.dimen.spacing1);
        rv_address_list.setHasFixedSize(true);
        Drawable dividerDrawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.divider);
        rv_address_list.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST,dividerDrawable));
       // rv_address_list.addItemDecoration(new SpacesItemDecoration(spacingInPixels1, Constants.SPACE_PRODUCT_LIST));
        rv_address_list.setNestedScrollingEnabled(false);
        rv_address_list.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rv_address_list.setItemAnimator(new DefaultItemAnimator());
        subscribeToLiveDataResponse();
    }

    private void subscribeToLiveDataAddress()
    {
        addressListViewModel.getAddressList().observe(this, new Observer<List<AddressListResponse.ResponseDataBean>>() {
            @Override
            public void onChanged(List<AddressListResponse.ResponseDataBean> responseDataBeans) {
                if(responseDataBeans.size()>0)
                {
                    addressListAdapter.addItems(responseDataBeans);
                    rv_address_list.setAdapter(addressListAdapter);
                    addressListAdapter.setAdapterListener(new AddressListAdapter.AddressListListener() {
                        @Override
                        public void onItemDeleteAddress(AddressListResponse.ResponseDataBean responseDataBean, int pos) {
                            addressListViewModel.deleteAddress(responseDataBean.getAddress_id());
                        }
                    });
                }
            }
        });
    }

    private void subscribeToLiveDataResponse()
    {
        addressListViewModel.getResponseLiveData().observe(this, new Observer<ApiResponse>() {
            @Override
            public void onChanged(ApiResponse apiResponse) {
                System.out.println("api statys"+" "+apiResponse.status);
                switch (apiResponse.status) {
                    case LOADING:
                        System.out.println("called loading");
                        rv_address_list.setVisibility(View.VISIBLE);
                        tv_no_address.setVisibility(View.GONE);
                        progressDialog.show();
                        break;

                    case SUCCESS:
                        System.out.println("called success");
                        progressDialog.dismiss();
                        subscribeToLiveDataAddress();
                        break;

                    case ERROR_STATUS:
                        progressDialog.dismiss();
                        rv_address_list.setVisibility(View.GONE);
                        tv_no_address.setVisibility(View.VISIBLE);

                        break;
                    case ERROR:
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), apiResponse.error.getMessage(), Toast.LENGTH_SHORT).show();
                        break;

                    default:
                        break;
                }
            }
        });

        addressListViewModel.getAddress();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);

    }

    @OnClick({R.id.tv_add})
    void onClickEvent(View view) {
        switch (view.getId()) {

            case R.id.tv_add:
               startActivityForResult(new Intent(getApplicationContext(),AddAddressActivity.class),ADD_CODE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                addressListViewModel.getAddress();
            }
        }


    }

}
