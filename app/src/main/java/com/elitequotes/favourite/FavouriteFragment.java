package com.elitequotes.favourite;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elitequotes.R;

/**
 * Created by veinhorn on 20.3.14.
 */
public class FavouriteFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = layoutInflater.inflate(R.layout.favourite_fragment, container, false);

        return rootView;
    }
}
