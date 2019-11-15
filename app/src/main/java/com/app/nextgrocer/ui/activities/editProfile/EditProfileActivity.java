package com.app.nextgrocer.ui.activities.editProfile;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.app.nextgrocer.R;
import com.app.nextgrocer.base.BaseActivity;
import com.app.nextgrocer.data.model.register_login.RegisterLoginResponse;
import com.app.nextgrocer.data.rest.ApiResponse;
import com.app.nextgrocer.ui.activities.home.MainActivity;
import com.app.nextgrocer.ui.activities.login.LoginViewModel;
import com.app.nextgrocer.utils.CommonUtils;
import com.app.nextgrocer.utils.ViewModelProviderFactory;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditProfileActivity extends BaseActivity<EditProfileViewModel> implements EditProfileNavigator {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tv_header)
    TextView tv_header;

    @BindView(R.id.et_email)
    EditText et_email;

    @BindView(R.id.et_telephone)
    EditText et_telephone;

    @BindView(R.id.et_fname)
    EditText et_fname;

    @BindView(R.id.et_lname)
    EditText et_lname;

    @BindView(R.id.btn_update)
    Button btn_update;

    @BindView(R.id.rg_news)
    RadioGroup rg_news;

    @BindView(R.id.rb_yes)
    RadioButton rb_yes;

    @BindView(R.id.rb_no)
    RadioButton rb_no;

    @BindView(R.id.tv_news)
    TextView tv_news;

    @Inject
    ViewModelProviderFactory factory;

    String news_letter = "1";

    ProgressDialog progressDialog;

    private EditProfileViewModel editProfileViewModel;
    protected void setUp() {
        setUnBinder(ButterKnife.bind(this));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Drawable upArrow = ContextCompat.getDrawable(this, R.drawable.tool_arrow);
        upArrow.setColorFilter(ContextCompat.getColor(this, R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        setTitle("");
        updateUI();


    }

    void updateUI()
    {
        editProfileViewModel.setNavigator(this);
        et_email.setTypeface(Typeface.createFromAsset(getAssets(), "fredokaOne.ttf"));
        et_fname.setTypeface(Typeface.createFromAsset(getAssets(), "fredokaOne.ttf"));
        et_lname.setTypeface(Typeface.createFromAsset(getAssets(), "fredokaOne.ttf"));
        et_telephone.setTypeface(Typeface.createFromAsset(getAssets(), "fredokaOne.ttf"));
        tv_header.setTypeface(Typeface.createFromAsset(getAssets(), "fredokaOne.ttf"));
        btn_update.setTypeface(Typeface.createFromAsset(getAssets(), "fredokaOne.ttf"));
        tv_news.setTypeface(Typeface.createFromAsset(getAssets(), "fredokaOne.ttf"));

   /*     et_email.setTypeface(Typeface.createFromAsset(getAssets(), "roboto_regular.ttf"));
        et_fname.setTypeface(Typeface.createFromAsset(getAssets(), "roboto_regular.ttf"));
        et_lname.setTypeface(Typeface.createFromAsset(getAssets(), "roboto_regular.ttf"));
        et_telephone.setTypeface(Typeface.createFromAsset(getAssets(), "roboto_regular.ttf"));
        tv_header.setTypeface(Typeface.createFromAsset(getAssets(), "bebasNeue.otf"));*/

        rg_news.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {

                    case R.id.rb_yes:
                        news_letter = "1";

                        break;
                    case R.id.rb_no:
                        news_letter = "0";
                        break;
                }
            }
        });

        editProfileViewModel.setNavigator(this);
        subscribeToLiveDataProfile();
    }

    @Override
    public int getLayoutId() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return R.layout.activity_edit_profile;
    }

    @Override
    public EditProfileViewModel getViewModel() {
        progressDialog = CommonUtils.showLoadingDialog(this);
        editProfileViewModel = ViewModelProviders.of(this,factory).get(EditProfileViewModel.class);
        subscribeToLiveDataResponse();
        return editProfileViewModel;
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
    private void subscribeToLiveDataResponse()
    {

        editProfileViewModel.getResponseLiveData().observe(this, new Observer<ApiResponse>() {
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
    }


    @OnClick({R.id.btn_update})
    void onClickEvent(View view) {
        switch (view.getId()) {

            case R.id.btn_update:

                if(editProfileViewModel.validateUpdate(et_fname.getText().toString(), et_lname.getText().toString(), et_email.getText().toString(), et_telephone.getText().toString()))
                {
                    editProfileViewModel.update(et_fname.getText().toString(),et_lname.getText().toString(),et_email.getText().toString(),et_telephone.getText().toString(),news_letter);
                }
                break;
        }
    }

    private void subscribeToLiveDataProfile()
    {
        editProfileViewModel.getProfileResponseData().observe(this, new Observer<RegisterLoginResponse>() {
            @Override
            public void onChanged(RegisterLoginResponse registerLoginResponse) {

                if(registerLoginResponse.getResponseData().isNewsletter())
                {
                    news_letter = "1";
                    rb_yes.setChecked(true);
                }
                else {
                    news_letter = "0";
                    rb_no.setChecked(true);
                }
                et_fname.setText(registerLoginResponse.getResponseData().getFirstname());
                et_lname.setText(registerLoginResponse.getResponseData().getLastname());
                et_email.setText(registerLoginResponse.getResponseData().getEmail());
                et_telephone.setText(registerLoginResponse.getResponseData().getTelephone());

            }
        });
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void finishActivity() {

        finish();
    }
}
