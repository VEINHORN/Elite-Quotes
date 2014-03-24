package com.elitequotes.favourite;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.elitequotes.R;

/**
 * Created by veinhorn on 20.3.14.
 */
public class FavouriteFragment extends Fragment {
    private ListView favouriteListView;
    private FavouriteElementsAdapter favouriteElementsAdapter;
    private FavouriteElementsContainer favouriteElementsContainer;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState) {
        Activity activity = getActivity();
        View rootView = layoutInflater.inflate(R.layout.favourite_fragment, container, false);
        favouriteListView = (ListView)rootView.findViewById(R.id.favourite_listview);
        favouriteElementsContainer = new FavouriteElementsContainer();
        favouriteElementsContainer.getFavouriteItemsArrayList().add(new FavouriteItem("1", "2"));
        favouriteElementsContainer.getFavouriteItemsArrayList().add(new FavouriteItem("1", "2"));
        favouriteElementsContainer.getFavouriteItemsArrayList().add(new FavouriteItem("1", "2"));
        favouriteElementsAdapter = new FavouriteElementsAdapter(activity, favouriteElementsContainer);
        favouriteListView.setAdapter(favouriteElementsAdapter);
        return rootView;
    }
}
