package com.app.nextgrocer.ui.activities.splash;

import com.app.nextgrocer.ui.activities.home.MainActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.lifecycle.ViewModelProviders;

import com.app.nextgrocer.R;
import com.app.nextgrocer.base.BaseActivity;

import com.app.nextgrocer.utils.ViewModelProviderFactory;

import javax.inject.Inject;

public class SplashActivity extends BaseActivity<SplashViewModel> implements SplashNavigator {

    @Inject
    ViewModelProviderFactory factory;

    private SplashViewModel mSplashViewModel;

   /* @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }*/



    @Override
    public int getLayoutId() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return R.layout.activity_splash;
    }

    @Override
    public SplashViewModel getViewModel() {
        mSplashViewModel = ViewModelProviders.of(this,factory).get(SplashViewModel.class);
        return mSplashViewModel;
    }

    @Override
    public void openLoginActivity() {
     /*   Intent intent = LoginActivity.newIntent(SplashActivity.this);
        startActivity(intent);
        finish();*/
    }

    @Override
    public void openMainActivity() {
        Intent intent = MainActivity.newIntent(SplashActivity.this);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSplashViewModel.setNavigator(this);
        mSplashViewModel.init();
    }


}


   /* @BindView(R.id.iv_logo)
    ImageView iv_logo;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        setUnbinder(ButterKnife.bind(this));
      *//*  Animation animShake = AnimationUtils.loadAnimation(this, R.anim.shaky_animation);
        iv_logo.startAnimation(animShake);*//*
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {

                        Intent i = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(i);
                        finish();

                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 3000);




}

    @Override
    protected void setUp() {


    }*/

