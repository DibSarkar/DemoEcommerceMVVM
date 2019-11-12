package com.app.nextgrocer.ui.activities.login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.app.nextgrocer.R;
import com.app.nextgrocer.base.BaseActivity;
import com.app.nextgrocer.data.rest.ApiResponse;
import com.app.nextgrocer.ui.activities.register.RegisterActivity;
import com.app.nextgrocer.ui.activities.home.MainActivity;
import com.app.nextgrocer.utils.CommonUtils;
import com.app.nextgrocer.utils.ViewModelProviderFactory;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<LoginViewModel> implements LoginDetailsNavigator {

    @BindView(R.id.et_email)
    EditText et_email;

    @BindView(R.id.et_pass)
    EditText et_pass;

    @BindView(R.id.btn_login)
    Button btn_login;

    @BindView(R.id.forgot_password)
    TextView tv_forgot;

    @BindView(R.id.tv_new_cus)
    TextView tv_new_cus;

  /*  @BindView(R.id.tv_send_otp)
    TextView tv_send_otp;*/

    @BindView(R.id.tv_signup)
    TextView tv_signup;

    @Inject
    ViewModelProviderFactory factory;

    ProgressDialog progressDialog;

    private LoginViewModel loginViewModel;

    @Override
    public int getLayoutId() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return R.layout.activity_login;
    }

    @Override
    public LoginViewModel getViewModel() {
        loginViewModel = ViewModelProviders.of(this,factory).get(LoginViewModel.class);
        return loginViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setUp();
    }


    protected void setUp() {
        setUnBinder(ButterKnife.bind(this));
        progressDialog = CommonUtils.showLoadingDialog(this);
        loginViewModel.setNavigator(this);
        updateUI();

    }


    void updateUI()
    {

        et_email.setTypeface(Typeface.createFromAsset(getAssets(), "fredokaOne.ttf"));
        et_pass.setTypeface(Typeface.createFromAsset(getAssets(), "fredokaOne.ttf"));
        tv_forgot.setTypeface(Typeface.createFromAsset(getAssets(), "fredokaOne.ttf"));
        btn_login.setTypeface(Typeface.createFromAsset(getAssets(), "fredokaOne.ttf"));
        tv_signup.setTypeface(Typeface.createFromAsset(getAssets(), "fredokaOne.ttf"));
        tv_new_cus.setTypeface(Typeface.createFromAsset(getAssets(), "fredokaOne.ttf"));

     /*   et_email.setTypeface(Typeface.createFromAsset(getAssets(), "roboto_regular.ttf"));
        et_pass.setTypeface(Typeface.createFromAsset(getAssets(), "roboto_regular.ttf"));
        tv_forgot.setTypeface(Typeface.createFromAsset(getAssets(), "roboto_light.ttf"));
        btn_login.setTypeface(Typeface.createFromAsset(getAssets(), "roboto_regular.ttf"));
        tv_signup.setTypeface(Typeface.createFromAsset(getAssets(), "robotoMedium.ttf"));
        tv_new_cus.setTypeface(Typeface.createFromAsset(getAssets(), "robotoMedium.ttf"));*/
       // tv_send_otp.setTypeface(Typeface.createFromAsset(getAssets(), "robotoMedium.ttf"));
        subscribeToLiveDataResponse();
    }
    @OnClick({R.id.tv_new_cus,R.id.tv_signup,R.id.btn_login})
    void onClickEvent(View view) {
        switch (view.getId()) {

            case R.id.tv_new_cus:
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                finish();
                break;

            case R.id.tv_signup:
                Intent intent1 = new Intent(this, RegisterActivity.class);
                startActivity(intent1);
                finish();
                break;


            case R.id.btn_login :

                if(loginViewModel.isEmailAndPasswordValid(et_email.getText().toString(),et_pass.getText().toString()))
                {
                     hideKeyboard();
                     loginViewModel.login(et_email.getText().toString(),et_pass.getText().toString(),"A","");
                }
                else {
                    Toast.makeText(this, getString(R.string.invalid_email_password), Toast.LENGTH_SHORT).show();
                }
                break;



        }
    }

    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    @Override
    public void openMainActivity() {

        startActivity(new Intent(this, MainActivity.class));

    }

    private void subscribeToLiveDataResponse()
    {

        loginViewModel.getResponseLiveData().observe(this, new Observer<ApiResponse>() {
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
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
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
}
