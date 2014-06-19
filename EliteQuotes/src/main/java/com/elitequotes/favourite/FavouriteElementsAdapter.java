package com.elitequotes.favourite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.elitequotes.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by veinhorn on 25.3.14.
 */
public class FavouriteElementsAdapter extends BaseAdapter {
    static class ViewHolder {
        @InjectView(R.id.favouriteQuoteText) TextView quoteText;
        @InjectView(R.id.favouriteQuoteTextAuthor) TextView quoteAuthor;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
    private LayoutInflater layoutInflater;
    private FavouriteElementsContainer favouriteElementsContainer;

    public FavouriteElementsAdapter(Context context, FavouriteElementsContainer favouriteElementsContainer) {
        this.favouriteElementsContainer = favouriteElementsContainer;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override public int getCount() {
        return favouriteElementsContainer.size();
    }

    @Override public Object getItem(int position) {
        return favouriteElementsContainer.getFavouriteItem(position);
    }

    @Override public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null) {
            convertView = layoutInflater.inflate(R.layout.favourite_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.quoteText.setText(favouriteElementsContainer.getFavouriteItem(position).getQuoteText());
        viewHolder.quoteAuthor.setText(favouriteElementsContainer.getFavouriteItem(position).getQuoteAuthor());
        return convertView;
    }
}