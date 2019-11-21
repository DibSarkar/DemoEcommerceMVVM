package com.app.nextgrocer.ui.activities.address.addAddress;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.app.nextgrocer.R;
import com.app.nextgrocer.adapters.CountriesAdapter;
import com.app.nextgrocer.adapters.StatesAdapter;
import com.app.nextgrocer.base.BaseActivity;
import com.app.nextgrocer.data.model.address.CountriesResponse;
import com.app.nextgrocer.data.model.address.StatesResponse;
import com.app.nextgrocer.data.rest.ApiResponse;
import com.app.nextgrocer.ui.activities.address.AddressNavigator;
import com.app.nextgrocer.utils.CommonUtils;
import com.app.nextgrocer.utils.ViewModelProviderFactory;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddAddressActivity extends BaseActivity<AddAddressViewModel> implements AddressNavigator {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.btn_add)
    Button btn_add;

    @BindView(R.id.tv_header)
    TextView tv_header;

    @Inject
    ViewModelProviderFactory viewModelProviderFactory;

    @Inject
    CountriesAdapter countryAdapter;

    @Inject
    StatesAdapter stateAdapter;

    @BindView(R.id.sp_country)
    Spinner sp_country;

    @BindView(R.id.sp_state)
    Spinner sp_state;

    @BindView(R.id.rg_default)
    RadioGroup rg_default;

    @BindView(R.id.et_fname)
    EditText et_fname;

    @BindView(R.id.et_lname)
    EditText et_lname;

    @BindView(R.id.et_comp)
    EditText et_comp;

    @BindView(R.id.et_address1)
    EditText et_address1;

    @BindView(R.id.et_address2)
    EditText et_address2;

    @BindView(R.id.et_city)
    EditText et_city;

    @BindView(R.id.et_pin)
    EditText et_pin;

    @BindView(R.id.ll_default)
    LinearLayout ll_default;




    String country_id = "";
    String state_id = "";
    String default_address = "1";
    ProgressDialog progressDialog;
    AddAddressViewModel addAddressViewModel;

    protected void setUp() {
        setUnBinder(ButterKnife.bind(this));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Drawable upArrow = ContextCompat.getDrawable(this, R.drawable.tool_arrow);
        upArrow.setColorFilter(ContextCompat.getColor(this, R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        setTitle("");
        updateUI();
        addAddressViewModel.setNavigator(this);

        rg_default.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {

                    case R.id.rb_yes:
                        default_address = "1";

                        break;
                    case R.id.rb_no:
                        default_address = "0";
                        break;
                }
            }
        });

    }

    @Override
    public int getLayoutId() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return R.layout.activity_add_address;
    }

    @Override
    public AddAddressViewModel getViewModel() {
        progressDialog = CommonUtils.showLoadingDialog(this);
        addAddressViewModel = ViewModelProviders.of(this,viewModelProviderFactory).get(AddAddressViewModel.class);
        subscribeToLiveDataResponse();

        return addAddressViewModel;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUp();
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

    void updateUI()
    {
        tv_header.setTypeface(Typeface.createFromAsset(getAssets(), "fredokaOne.ttf"));
        btn_add.setTypeface(Typeface.createFromAsset(getAssets(), "fredokaOne.ttf"));
    }


    @Override
    public void showErrorMessage(String message) {

        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void finishActivity() {
        Intent returnIntent = new Intent();
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }

    @OnClick({R.id.btn_add})
    void onClickEvent(View view) {
        switch (view.getId()) {

            case R.id.btn_add :

                if(addAddressViewModel.isValidAddress(et_fname.getText().toString(),et_lname.getText().toString(),et_address1.getText().toString(),et_city.getText().toString(),country_id,et_pin.getText().toString(),state_id))
                {
                    addAddressViewModel.addAddress(et_fname.getText().toString(),et_lname.getText().toString(),et_address1.getText().toString(),et_address2.getText().toString(),et_pin.getText().toString(),state_id,country_id,default_address,et_comp.getText().toString(),et_city.getText().toString());
                }

                break;
        }
    }

    private void subscribeToLiveDataCountries()
    {
        addAddressViewModel.getAllcountryBeanMutableLiveData().observe(this, new Observer<CountriesResponse>() {
            @Override
            public void onChanged(CountriesResponse allcountryBeans) {
                if(allcountryBeans!=null) {
                    if (allcountryBeans.getAllcountry().size() > 0) {
                        countryAdapter.loadAllCountries(allcountryBeans.getAllcountry());
                        sp_country.setAdapter(countryAdapter);
                        sp_country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                country_id = allcountryBeans.getAllcountry().get(position).getId();
                                addAddressViewModel.getStates(allcountryBeans.getAllcountry().get(position).getId());
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }
                    if(allcountryBeans.getHas_address().equals("1"))
                    {
                        ll_default.setVisibility(View.VISIBLE);

                    }
                    else {
                        ll_default.setVisibility(View.GONE);
                        default_address = "1";
                    }
                }
            }
        });

        subscribeToLiveDataStates();
    }

        private void subscribeToLiveDataStates()

        {

        addAddressViewModel.getAllstatesBeanMutableLiveData().observe(this, new Observer<List<StatesResponse.DataBean>>() {
            @Override
            public void onChanged(List<StatesResponse.DataBean> dataBeans) {

                if(dataBeans.size()>0)
                {
                    stateAdapter.loadAllStates(dataBeans);
                    sp_state.setAdapter(stateAdapter);
                    sp_state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            state_id=dataBeans.get(position).getId();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }

            }
        });
    }
    private void subscribeToLiveDataResponse()
    {

        addAddressViewModel.getResponseLiveData().observe(this, new Observer<ApiResponse>() {
            @Override
            public void onChanged(ApiResponse apiResponse) {
                System.out.println("api statys"+" "+apiResponse.status);
                switch (apiResponse.status) {
                    case LOADING:
                        System.out.println("called loading");
                        progressDialog.show();
                        break;

                    case SUCCESS:
                        System.out.println("called success");
                        progressDialog.dismiss();

                        break;

                    case ERROR_STATUS:
                        progressDialog.dismiss();
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
        subscribeToLiveDataCountries();
    }


}
