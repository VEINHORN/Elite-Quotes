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

    public boolean search(String quoteText) {
        boolean isSuch = false;
        for(FavouriteItem favouriteItem : favouriteItemsArrayList) {
            if(favouriteItem.getQuoteText().equals(quoteText)) {
                isSuch = true;
                return isSuch;
            }
        }
        return isSuch;
    }

    public void addFavouriteItem(FavouriteItem favouriteItem) {
        favouriteItemsArrayList.add(favouriteItem);
    }

    public int size() {
        return favouriteItemsArrayList.size();
    }

    public FavouriteItem getFavouriteItem(int position) {
        return favouriteItemsArrayList.get(position);
    }
}
