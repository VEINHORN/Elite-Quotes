package com.elitequotes.favourite;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.elitequotes.R;
import com.elitequotes.database.DatabaseHandler;

/**
 * Created by veinhorn on 20.3.14.
 */
public class FavouriteFragment extends Fragment {
    private ListView favouriteListView;
    private FavouriteElementsAdapter favouriteElementsAdapter;
    private FavouriteElementsContainer favouriteElementsContainer;
    private DatabaseHandler databaseHandler;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState) {
        Activity activity = getActivity();
        View rootView = layoutInflater.inflate(R.layout.favourite_fragment, container, false);
        favouriteListView = (ListView)rootView.findViewById(R.id.favourite_listview);
        favouriteElementsContainer = new FavouriteElementsContainer();
        databaseHandler = new DatabaseHandler(activity);
        favouriteElementsContainer = databaseHandler.getAllFavouriteElements();
        favouriteElementsAdapter = new FavouriteElementsAdapter(activity, favouriteElementsContainer);
        favouriteListView.setAdapter(favouriteElementsAdapter);
        return rootView;
    }

    public void updateData(FavouriteItem favouriteItem) {
        favouriteElementsContainer.addFavouriteItem(favouriteItem);
        favouriteElementsAdapter.notifyDataSetChanged();
        //Log.d("Boris Korogvich", "100500");
    }
}
