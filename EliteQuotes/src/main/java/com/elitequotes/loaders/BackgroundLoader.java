package com.elitequotes.loaders;

import android.content.Context;
import android.view.ViewGroup;

import com.elitequotes.R;

import java.util.Random;

/**
 * Created by VEINHORN on 25/02/14.
 */
public class BackgroundLoader {
    private Context context;
    private ViewGroup viewGroup;
    private int[] backgrounds = { R.drawable.background, R.drawable.background2, R.drawable.background3, R.drawable.background4, R.drawable.background5, R.drawable.background6 };
    private final static int MIN = 0;
    private final static int MAX = 5;

    public BackgroundLoader(Context context, ViewGroup viewGroup) {
        this.context = context;
        this.viewGroup = viewGroup;
    }

    public void selectBackground() {
        Random random = new Random();
        int randomBackgroundId = random.nextInt((MAX - MIN) + 1) + MIN;
        viewGroup.setBackgroundDrawable(context.getResources().getDrawable(backgrounds[randomBackgroundId]));
    }
}
