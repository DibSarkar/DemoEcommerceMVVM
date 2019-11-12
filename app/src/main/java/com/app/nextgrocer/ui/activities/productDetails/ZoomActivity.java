package com.app.nextgrocer.ui.activities.productDetails;

import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;


import com.app.nextgrocer.R;
import com.app.nextgrocer.utils.GlideApp;
import com.app.nextgrocer.utils.TouchImageView;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by lenevo on 14-09-2017.
 */

public class ZoomActivity extends AppCompatActivity  {

    private String zoomImage;
/*    private Matrix matrix = new Matrix();
    private float scale = 1f;*/


    @BindView(R.id.product_image)
    TouchImageView product_image;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_zoom);

        ButterKnife.bind(this);
        setUp();
    }

    private void setUp()
    {


        Bundle bundle = getIntent().getExtras();
        zoomImage = bundle.getString("ZoomImage","");
        GlideApp.with(getApplicationContext())
                .load(zoomImage)
                .transition(DrawableTransitionOptions.withCrossFade())
                .fitCenter()

                .into(product_image);
        product_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float x = product_image.getScaleX();
                float y = product_image.getScaleY();

                product_image.setScaleX((float) (x+1));
                product_image.setScaleY((float) (y+1));
            }
        });

    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }




}
