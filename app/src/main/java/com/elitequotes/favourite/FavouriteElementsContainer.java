package com.elitequotes.favourite;

import java.util.ArrayList;

/**
 * Created by veinhorn on 25.3.14.
 */
public class FavouriteElementsContainer {
    private ArrayList<FavouriteItem> favouriteItemsArrayList;

    public FavouriteElementsContainer() {
        favouriteItemsArrayList = new ArrayList<FavouriteItem>();
    }

    public ArrayList<FavouriteItem> getFavouriteItemsArrayList() {
        return favouriteItemsArrayList;
    }

    public void setFavouriteItemsArrayList(ArrayList<FavouriteItem> favouriteItemsArrayList) {
        this.favouriteItemsArrayList = favouriteItemsArrayList;
    }
}