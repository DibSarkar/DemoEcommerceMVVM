package com.app.nextgrocer.ui.fragments.myaccount;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.app.nextgrocer.R;
import com.app.nextgrocer.data.model.local_models.ProfileDataResponse;
import com.app.nextgrocer.shared.BaseFragment;
import com.app.nextgrocer.ui.activities.address.addressList.AddressListActivity;
import com.app.nextgrocer.ui.activities.editProfile.EditProfileActivity;
import com.app.nextgrocer.ui.activities.home.MainActivity;
import com.app.nextgrocer.ui.activities.MyOrdersActivity;
import com.app.nextgrocer.ui.fragments.myaccount.changePass.ChangePassDialog;
import com.app.nextgrocer.utils.ViewModelProviderFactory;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class MyAccountFragment extends BaseFragment<MyAccountViewModel> implements MyAccountNavigator {


    @BindView(R.id.tv_header)
    TextView tv_header;

    @BindView(R.id.tv_name)
    TextView tv_name;

    @BindView(R.id.tv_mob)
    TextView tv_mob;

    @BindView(R.id.tv_email)
    TextView tv_email;

    @BindView(R.id.tv_myorders_label)
    TextView tv_myorders_label;

    @BindView(R.id.tv_reward_points)
    TextView tv_reward_points;

    @BindView(R.id.tv_address_label)
    TextView tv_address_label;

    @BindView(R.id.tv_return_label)
    TextView tv_return_label;

    @BindView(R.id.tv_change_label)
    TextView tv_change_label;

    @BindView(R.id.tv_logout_label)
    TextView tv_logout_label;

    @Inject
    ViewModelProviderFactory factory;



    MyAccountViewModel myAccountViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        myAccountViewModel.setNavigator(this);
    }

    public static MyAccountFragment newInstance() {
        System.out.println("called fragment");
        MyAccountFragment fragment = new MyAccountFragment();
        //Bundle args = new Bundle();
        //args.putSerializable(PEOPLE_LIST, peopleBeansList);
        //fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_account;
    }

    @Override
    public MyAccountViewModel getViewModel() {
        myAccountViewModel = ViewModelProviders.of(this,factory).get(MyAccountViewModel.class);
        return myAccountViewModel;
    }

    public void setUp()
    {
        updateUI();

    }

    void updateUI()
    {
        tv_header.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "fredokaOne.ttf"));
        tv_email.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "fredokaOne.ttf"));
        tv_name.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "fredokaOne.ttf"));
        tv_mob.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "fredokaOne.ttf"));
        tv_myorders_label.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "fredokaOne.ttf"));
        tv_reward_points.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "fredokaOne.ttf"));
        tv_address_label.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "fredokaOne.ttf"));
        tv_return_label.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "fredokaOne.ttf"));
        tv_change_label.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "fredokaOne.ttf"));
        tv_logout_label.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "fredokaOne.ttf"));
        /*
        tv_header.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "bebasNeue.otf"));
        tv_email.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "roboto_regular.ttf"));
        tv_name.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "robotoMedium.ttf"));
        tv_mob.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "robotoMedium.ttf"));
        tv_myorders_label.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "robotoMedium.ttf"));
        tv_reward_points.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "robotoMedium.ttf"));
        tv_address_label.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "robotoMedium.ttf"));
        tv_return_label.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "robotoMedium.ttf"));
        tv_change_label.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "robotoMedium.ttf"));
        tv_logout_label.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "robotoMedium.ttf"));
        tv_my_orders_text.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "robotoMedium.ttf"));
        tv_reward_text.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "robotoMedium.ttf"));
        tv_address_text.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "robotoMedium.ttf"));
        tv_return_label_text.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "robotoMedium.ttf"));
        tv_change_label_text.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "robotoMedium.ttf"));*/
    }



    private void subscribeToLiveDataProfile()
    {
        myAccountViewModel.getProfileDataResponseMutableLiveData().observe(this, new Observer<ProfileDataResponse>() {
            @Override
            public void onChanged(ProfileDataResponse profileDataResponse) {

                tv_name.setText(profileDataResponse.getName());
                tv_email.setText(profileDataResponse.getEmaild());
                tv_mob.setText(profileDataResponse.getMobile());

            }
        });
    }

    @OnClick({R.id.tv_myorders_label,R.id.tv_logout_label,R.id.iv_logout,R.id.iv_edit,R.id.ll_change_pass,R.id.ll_address})
    void onClickEvent(View view) {
        switch (view.getId()) {

            case R.id.tv_myorders_label :
                Intent intent = new Intent(getActivity(), MyOrdersActivity.class);
                startActivity(intent);
                 break;

            case R.id.tv_logout_label :
                myAccountViewModel.logout();
                break;

            case R.id.iv_logout :
                myAccountViewModel.logout();
                break;

            case R.id.iv_edit :
                startActivity(new Intent(getActivity(), EditProfileActivity.class));
                break;

            case R.id.ll_change_pass :
                if(getActivity()!=null) {
                    ChangePassDialog.newInstance().show(getActivity().getSupportFragmentManager());
                }
                break;

            case R.id.ll_address :
                startActivity(new Intent(getActivity(), AddressListActivity.class));
                break;

        }
        }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void openMainActivity() {
        Toast.makeText(getActivity(),"Loggedout successfully",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getActivity(), MainActivity.class));
    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("gets called");
        myAccountViewModel.loadProfileData();
        subscribeToLiveDataProfile();
    }
}
