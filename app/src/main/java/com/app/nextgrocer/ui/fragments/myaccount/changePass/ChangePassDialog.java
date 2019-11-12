package com.app.nextgrocer.ui.fragments.myaccount.changePass;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.app.nextgrocer.R;
import com.app.nextgrocer.data.rest.ApiResponse;
import com.app.nextgrocer.shared.BaseDialog;
import com.app.nextgrocer.shared.BaseViewModel;
import com.app.nextgrocer.ui.fragments.myaccount.MyAccountViewModel;
import com.app.nextgrocer.utils.CommonUtils;
import com.app.nextgrocer.utils.ViewModelProviderFactory;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import dagger.android.support.AndroidSupportInjection;

public class ChangePassDialog extends BaseDialog implements ChangePassNavigator {

    private static final String TAG = ChangePassDialog.class.getSimpleName();

    @BindView(R.id.edt_newpass)
    EditText edt_newpass;

    @BindView(R.id.edt_conpass)
    EditText edt_conpass;

    @BindView(R.id.btn_change)
    Button btn_change;

    @BindView(R.id.tv_change_pass)
    TextView tv_change_pass;

    ProgressDialog progressDialog;


    @Inject
    ViewModelProviderFactory factory;

    private ChangePassViewModel changePassViewModel;
    public static ChangePassDialog newInstance() {
        ChangePassDialog fragment = new ChangePassDialog();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public int getLayoutId() {
        return R.layout.layout_dialog_change_pass;
    }

    @Override
    public ChangePassViewModel getViewModel() {
        //AndroidSupportInjection.inject(this);
        changePassViewModel = ViewModelProviders.of(this,factory).get(ChangePassViewModel.class);
        return  changePassViewModel;
    }

    @Override
    public void setUp() {
        progressDialog = CommonUtils.showLoadingDialog(getActivity());
        changePassViewModel.setNavigator(this);
        edt_newpass.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "fredokaOne.ttf"));
        edt_conpass.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "fredokaOne.ttf"));
        btn_change.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "fredokaOne.ttf"));
        tv_change_pass.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "fredokaOne.ttf"));

        subscribeToLiveDataResponse();
    }

    @OnClick({R.id.btn_change})
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.btn_change:

                if(!edt_newpass.getText().toString().isEmpty())
                {
                   if(!edt_conpass.getText().toString().isEmpty())
                   {
                       if(edt_newpass.getText().toString().equals(edt_conpass.getText().toString()))
                       {
                           changePassViewModel.changePass(edt_newpass.getText().toString());
                       }
                       else {
                           showErrorMessage(getActivity().getResources().getString(R.string.password_mismatches));
                       }
                   }
                   else {
                       showErrorMessage(getActivity().getResources().getString(R.string.enter_confirm_password));

                   }
                }
                else {
                    showErrorMessage(getActivity().getResources().getString(R.string.enter_password));

                }

                break;
        }

    }

    public void show(FragmentManager fragmentManager) {
        super.show(fragmentManager, TAG);
    }
    @Override
    public void dismissDialog() {
        dismissDialog(TAG);
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();

    }

    private void subscribeToLiveDataResponse()
    {

        changePassViewModel.getResponseLiveData().observe(this, new Observer<ApiResponse>() {
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
                        if(getActivity()!=null) {
                            hideKeyboard(getActivity());
                        }
                        break;

                    case ERROR_STATUS:
                        progressDialog.dismiss();
                        break;
                    case ERROR:
                        progressDialog.dismiss();
                        Toast.makeText(getActivity(), apiResponse.error.getMessage(), Toast.LENGTH_SHORT).show();
                        break;

                    default:
                        break;
                }
            }
        });
    }
    private static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
