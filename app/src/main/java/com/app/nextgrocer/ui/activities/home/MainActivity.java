package com.app.nextgrocer.ui.activities.home;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.app.nextgrocer.R;
import com.app.nextgrocer.base.BaseActivity;

import com.app.nextgrocer.ui.activities.login.LoginActivity;
import com.app.nextgrocer.ui.fragments.WishlistFragment;
import com.app.nextgrocer.ui.fragments.home.HomeFragment;
import com.app.nextgrocer.ui.fragments.categories.CategoriesFragment;
import com.app.nextgrocer.ui.fragments.myaccount.MyAccountFragment;


import com.app.nextgrocer.utils.ViewModelProviderFactory;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class MainActivity extends BaseActivity<MainViewModel> implements MainNavigator, HasSupportFragmentInjector {

    @BindView(R.id.navView)
    BottomNavigationViewEx navView;

    @Inject
    ViewModelProviderFactory factory;

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

    private MainViewModel mainViewModel;

    @Override
    public int getLayoutId() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return R.layout.activity_main;
    }

    @Override
    public MainViewModel getViewModel() {
        mainViewModel = ViewModelProviders.of(this,factory).get(MainViewModel.class);
        return mainViewModel;
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.nav_home:
                    System.out.println("caledd 1");
                    HomeFragment homeFragment = HomeFragment.newInstance();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fl_Body, homeFragment).commit();

                    return  true;

                case R.id.nav_categories:
                    CategoriesFragment categoriesFragment = CategoriesFragment.newInstance();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fl_Body, categoriesFragment).commit();

                    return  true;
                case R.id.nav_wishlist:
                    WishlistFragment wishlistFragment = WishlistFragment.newInstance();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fl_Body, wishlistFragment).commit();

                    return  true;
                case R.id.nav_account:
                    if(mainViewModel.isLoggedIn())
                    {
                        MyAccountFragment myAccountFragment = MyAccountFragment.newInstance();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fl_Body, myAccountFragment).commit();

                    }
                    else {
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    }

                    return  true;
                case R.id.nav_notif:

                    Toast.makeText(getApplicationContext(),"Under Development",Toast.LENGTH_SHORT).show();
                    return  true;

            }

            return false;
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUp();
    }


    protected void setUp() {
        setUnBinder(ButterKnife.bind(this));
        navView.setCurrentItem(0);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        navView.enableAnimation(false);
        navView.enableShiftingMode(false);
        navView.enableItemShiftingMode(false);

        HomeFragment homeFragment = HomeFragment.newInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_Body, homeFragment).commit();
        hideSoftKeyboard();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

    }

    public void hideSoftKeyboard() {
        if(getCurrentFocus()!=null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}
