package com.elitequotes.favourite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.elitequotes.R;

/**
 * Created by veinhorn on 25.3.14.
 */
public class FavouriteElementsAdapter extends BaseAdapter {
    private static class ViewHolder {
        public TextView quoteText;
        public TextView quoteAuthor;
    }
    private Context context;
    private LayoutInflater layoutInflater;
    private FavouriteElementsContainer favouriteElementsContainer;

    public FavouriteElementsAdapter(Context context, FavouriteElementsContainer favouriteElementsContainer) {
        this.context = context;
        this.favouriteElementsContainer = favouriteElementsContainer;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return favouriteElementsContainer.getFavouriteItemsArrayList().size();
    }

    @Override
    public Object getItem(int position) {
        return favouriteElementsContainer.getFavouriteItemsArrayList().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null) {
            convertView = layoutInflater.inflate(R.layout.favourite_item, null);
            viewHolder = new ViewHolder();
            viewHolder.quoteText = (TextView)convertView.findViewById(R.id.favouriteQuoteText);
            viewHolder.quoteAuthor = (TextView)convertView.findViewById(R.id.favouriteQuoteTextAuthor);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.quoteText.setText(favouriteElementsContainer.getFavouriteItemsArrayList().get(position).getQuoteText());
        viewHolder.quoteAuthor.setText(favouriteElementsContainer.getFavouriteItemsArrayList().get(position).getQuoteAuthor());
        return convertView;
    }
}
