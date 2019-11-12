package com.app.nextgrocer.base;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;


import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.app.nextgrocer.shared.BaseFragment;
import com.app.nextgrocer.shared.BaseViewModel;
import com.app.nextgrocer.utils.CommonUtils;
import com.app.nextgrocer.utils.NetworkUtils;

import butterknife.Unbinder;
import dagger.android.AndroidInjection;


public abstract class BaseActivity <V extends BaseViewModel> extends AppCompatActivity  implements BaseFragment.Callback
       {

    // TODO
    // this can probably depend on isLoading variable of BaseViewModel,
    // since its going to be common for all the activities
    private ProgressDialog mProgressDialog;

    private V mViewModel;

    /**
     * Override for set binding variable
     *
     * @return variable id
     */
   // public abstract int getBindingVariable();

    /**
     * @return layout resource id
     */
    @LayoutRes
    public abstract int getLayoutId();

    /**
     * Override for set view model
     *
     * @return view model instance
     */
    public abstract V getViewModel();



    /*@Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }*/
    private Unbinder mUnBinder;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        performDependencyInjection();
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        this.mViewModel = mViewModel == null ? getViewModel() : mViewModel;
        //performDataBinding();
    }



   public void setUnBinder(Unbinder unBinder) {
       mUnBinder = unBinder;
   }

   @Override
   protected void onDestroy() {

       if (mUnBinder != null) {
           mUnBinder.unbind();
       }
       super.onDestroy();
   }

    @TargetApi(Build.VERSION_CODES.M)
    public boolean hasPermission(String permission) {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
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

    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

           public  Context getContext()
           {
               final Context applicationContext = getApplicationContext();
               return applicationContext;
           }

           public boolean isNetworkConnected() {
        return NetworkUtils.isNetworkConnected(getApplicationContext());
    }

//    public void openActivityOnTokenExpire() {
//        startActivity(LoginActivity.newIntent(this));
//        finish();
//    }

    public void performDependencyInjection() {
        AndroidInjection.inject(this);
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void requestPermissionsSafely(String[] permissions, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode);
        }
    }

    public void showLoading() {
        hideLoading();
        mProgressDialog = CommonUtils.showLoadingDialog(this);
    }

   /* private void performDataBinding() {
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId());
        this.mViewModel = mViewModel == null ? getViewModel() : mViewModel;
        mViewDataBinding.setVariable(getBindingVariable(), mViewModel);
        mViewDataBinding.executePendingBindings();
    }*/

           @Override
           public void onFragmentAttached() {

           }

           @Override
           public void onFragmentDetached(String tag) {

           }

       /*    @Override
           protected void attachBaseContext(Context newBase) {
               super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
           }*/

}