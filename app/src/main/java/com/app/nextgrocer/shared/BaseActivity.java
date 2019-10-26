package com.app.nextgrocer.shared;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.app.nextgrocer.utils.AppUtils;
import com.app.nextgrocer.utils.ConnectionDetector;
import com.google.android.material.snackbar.Snackbar;

import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity {

    private Context context;
    private ProgressDialog mProgressDialog;
    private ConnectionDetector cd;
    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = this;
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setCancelable(false);
        cd = new ConnectionDetector(this);

    }

    public void setUnbinder(Unbinder unbinder) {
        this.unbinder = unbinder;
    }

    @Override
    protected void onDestroy() {
        if(unbinder != null)
            unbinder.unbind();
        super.onDestroy();
    }

    public void showLoading(String msg){
        hideLoading();

        mProgressDialog.setMessage(msg);
        mProgressDialog.show();
    }

    public void hideLoading(){
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

    public void showSnackBar(String message) {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                message, Snackbar.LENGTH_SHORT);
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView
                .findViewById(com.google.android.material.R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(this, android.R.color.white));
        snackbar.show();
    }

    public void showToast(String message) {
        Toast.makeText(this, message , Toast.LENGTH_SHORT).show();
    }

    public boolean isNetAvailable(){
        return cd.isConnectedToInternet();
    }

    protected abstract void setUp();


   /* public class SpaceDecoration extends RecyclerView.ItemDecoration {
        int px;

        public SpaceDecoration(int px) {
            this.px = px;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);

            outRect.bottom = Math.round(AppUtils.getDeviceDensityScale(context) * px);
        }
    }*/

}
