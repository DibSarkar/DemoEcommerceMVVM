package com.app.nextgrocer.utils;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    private int space;
    private int spaceFragment;


    public SpacesItemDecoration(int space, int spaceFragment) {
        this.space = space;
        this.spaceFragment = spaceFragment;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view,
                               RecyclerView parent, RecyclerView.State state) {
        if(spaceFragment==1) {
            outRect.left = space;
            outRect.right = space;
            outRect.bottom = space;
            if (parent.getChildLayoutPosition(view) == 0) {
                outRect.left = space;
            } else {
                outRect.left = 0;
            }
        }

        else if(spaceFragment==2)
        {
            outRect.left = space;
            outRect.right = space;
            outRect.bottom = space;
        }

        else if(spaceFragment==3)
        {
            outRect.left = space;
            outRect.right = space;
            //outRect.top =space;
        }

        else if(spaceFragment==4)
        {
            outRect.bottom = space;
        }

        else if(spaceFragment == 5)
        {
            outRect.bottom = space;
            outRect.right = space;
            outRect.left = space;
            //outRect.top = space;
        }
    }
}

