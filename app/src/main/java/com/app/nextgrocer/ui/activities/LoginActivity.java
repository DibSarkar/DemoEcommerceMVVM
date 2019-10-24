package com.app.nextgrocer.ui.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.app.nextgrocer.R;
import com.app.nextgrocer.shared.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

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

    @BindView(R.id.tv_send_otp)
    TextView tv_send_otp;

    @BindView(R.id.tv_signup)
    TextView tv_signup;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        setUp();
    }

    @Override
    protected void setUp() {
        setUnbinder(ButterKnife.bind(this));
        et_email.setTypeface(Typeface.createFromAsset(getAssets(), "roboto_regular.ttf"));
        et_pass.setTypeface(Typeface.createFromAsset(getAssets(), "roboto_regular.ttf"));
        tv_forgot.setTypeface(Typeface.createFromAsset(getAssets(), "roboto_light.ttf"));
        btn_login.setTypeface(Typeface.createFromAsset(getAssets(), "roboto_regular.ttf"));
        tv_signup.setTypeface(Typeface.createFromAsset(getAssets(), "robotoMedium.ttf"));
        tv_new_cus.setTypeface(Typeface.createFromAsset(getAssets(), "robotoMedium.ttf"));
        tv_send_otp.setTypeface(Typeface.createFromAsset(getAssets(), "robotoMedium.ttf"));

    }

    @OnClick({R.id.tv_new_cus,R.id.tv_signup})
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


        }
    }
}
