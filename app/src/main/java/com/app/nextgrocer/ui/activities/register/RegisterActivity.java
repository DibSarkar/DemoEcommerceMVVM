package com.app.nextgrocer.ui.activities.register;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.app.nextgrocer.R;
import com.app.nextgrocer.base.BaseActivity;
import com.app.nextgrocer.data.rest.ApiResponse;
import com.app.nextgrocer.ui.activities.home.MainActivity;
import com.app.nextgrocer.ui.activities.login.LoginActivity;
import com.app.nextgrocer.ui.activities.login.LoginViewModel;
import com.app.nextgrocer.utils.CommonUtils;
import com.app.nextgrocer.utils.ViewModelProviderFactory;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity<RegisterViewModel> implements RegistrationDetailsNavigator
{

    @BindView(R.id.et_email)
    EditText et_email;

    @BindView(R.id.et_telephone)
    EditText et_telephone;

    @BindView(R.id.et_pass)
    EditText et_pass;

    @BindView(R.id.et_confirmpass)
    EditText et_confirmpass;

    @BindView(R.id.et_fname)
    EditText et_fname;

    @BindView(R.id.et_lname)
    EditText et_lname;

    @BindView(R.id.btn_register)
    Button btn_register;

    @BindView(R.id.tv_news)
    TextView tv_news;

    @BindView(R.id.tv_terms)
    TextView tv_terms;

    @BindView(R.id.tv_terms1)
    TextView tv_terms1;

    @BindView(R.id.tv_already)
    TextView tv_already;

    @BindView(R.id.tv_login)
    TextView tv_login;

    @BindView(R.id.rg_news)
    RadioGroup rg_news;

    @BindView(R.id.cb_terms)
    CheckBox cb_terms;

    String news_letter = "1";

    @Inject
    ViewModelProviderFactory factory;

    ProgressDialog progressDialog;

    private RegisterViewModel registerViewModel;

    @Override
    public int getLayoutId() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return R.layout.activity_register;
    }

    @Override
    public RegisterViewModel getViewModel() {
        registerViewModel = ViewModelProviders.of(this,factory).get(RegisterViewModel.class);
        return registerViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUp();
    }

    protected void setUp() {
        setUnBinder(ButterKnife.bind(this));
        updateUI();

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


    }

    void updateUI()
    {

        et_email.setTypeface(Typeface.createFromAsset(getAssets(), "fredokaOne.ttf"));
        et_pass.setTypeface(Typeface.createFromAsset(getAssets(), "fredokaOne.ttf"));
        et_fname.setTypeface(Typeface.createFromAsset(getAssets(), "fredokaOne.ttf"));
        et_lname.setTypeface(Typeface.createFromAsset(getAssets(), "fredokaOne.ttf"));
        et_confirmpass.setTypeface(Typeface.createFromAsset(getAssets(), "fredokaOne.ttf"));
        et_telephone.setTypeface(Typeface.createFromAsset(getAssets(), "fredokaOne.ttf"));
        tv_terms.setTypeface(Typeface.createFromAsset(getAssets(), "fredokaOne.ttf"));
        tv_already.setTypeface(Typeface.createFromAsset(getAssets(), "fredokaOne.ttf"));
        btn_register.setTypeface(Typeface.createFromAsset(getAssets(), "fredokaOne.ttf"));
        tv_news.setTypeface(Typeface.createFromAsset(getAssets(), "fredokaOne.ttf"));
        tv_login.setTypeface(Typeface.createFromAsset(getAssets(), "fredokaOne.ttf"));
        tv_terms1.setTypeface(Typeface.createFromAsset(getAssets(), "fredokaOne.ttf"));

     /*   et_email.setTypeface(Typeface.createFromAsset(getAssets(), "roboto_regular.ttf"));
        et_pass.setTypeface(Typeface.createFromAsset(getAssets(), "roboto_regular.ttf"));
        et_fname.setTypeface(Typeface.createFromAsset(getAssets(), "roboto_regular.ttf"));
        et_lname.setTypeface(Typeface.createFromAsset(getAssets(), "roboto_regular.ttf"));
        et_confirmpass.setTypeface(Typeface.createFromAsset(getAssets(), "roboto_regular.ttf"));
        et_telephone.setTypeface(Typeface.createFromAsset(getAssets(), "roboto_regular.ttf"));
        tv_terms.setTypeface(Typeface.createFromAsset(getAssets(), "roboto_regular.ttf"));
        tv_already.setTypeface(Typeface.createFromAsset(getAssets(), "roboto_regular.ttf"));
        btn_register.setTypeface(Typeface.createFromAsset(getAssets(), "roboto_regular.ttf"));
        tv_news.setTypeface(Typeface.createFromAsset(getAssets(), "robotoMedium.ttf"));
        tv_login.setTypeface(Typeface.createFromAsset(getAssets(), "robotoMedium.ttf"));
        tv_terms1.setTypeface(Typeface.createFromAsset(getAssets(), "robotoMedium.ttf"));*/
        tv_terms1.setText(Html.fromHtml(getResources().getString(R.string.terms_conditions)+"<a href=http://192.168.5.44/dheman/index.php?route=information/information&information_id=3>"+"</a>"));
        tv_terms1.setClickable(true);
        tv_terms1.setMovementMethod (LinkMovementMethod.getInstance());
        progressDialog = CommonUtils.showLoadingDialog(this);
        registerViewModel.setNavigator(this);
        subscribeToLiveDataResponse();
    }


    @OnClick({R.id.tv_already,R.id.tv_login,R.id.btn_register})
    void onClickEvent(View view) {
        switch (view.getId()) {

            case R.id.tv_already:
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;

            case R.id.tv_login:
                Intent intent1 = new Intent(this, LoginActivity.class);
                startActivity(intent1);
                finish();
                break;

            case R.id.btn_register :

                if (registerViewModel.validateRegistration(et_fname.getText().toString(), et_lname.getText().toString(), et_email.getText().toString(), et_telephone.getText().toString(), et_pass.getText().toString(), et_confirmpass.getText().toString())) {
                    if(cb_terms.isChecked()) {
                        registerViewModel.register(et_fname.getText().toString(), et_lname.getText().toString(), et_email.getText().toString(), et_telephone.getText().toString(), et_pass.getText().toString(), news_letter, "A", "");
                    }
                    else {
                        Toast.makeText(getApplicationContext(),getResources().getString(R.string.accept_privacy),Toast.LENGTH_SHORT).show();
                    }
                }

                break;
        }
    }

    @Override
    public void showErrorMessage(String message) {

        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();

    }

    private void subscribeToLiveDataResponse()
    {

        registerViewModel.getResponseLiveData().observe(this, new Observer<ApiResponse>() {
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

