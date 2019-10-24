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

public class RegisterActivity extends BaseActivity

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



        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);
        setUp();
    }
        @Override
        protected void setUp() {
        setUnbinder(ButterKnife.bind(this));
        et_email.setTypeface(Typeface.createFromAsset(getAssets(), "roboto_regular.ttf"));
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
        tv_terms1.setTypeface(Typeface.createFromAsset(getAssets(), "robotoMedium.ttf"));
    }

        @OnClick({R.id.tv_already,R.id.tv_login})
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


            }
        }
    }

