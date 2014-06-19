package com.elitequotes;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.elitequotes.database.DatabaseHandler;
import com.elitequotes.favourite.FavouriteItem;
import com.elitequotes.loaders.BackgroundLoader;
import com.elitequotes.loaders.QuoteLoader;
import com.elitequotes.share.ShareUtil;
import com.google.ads.AdRequest;
import com.google.ads.AdView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by veinhorn on 20.3.14.
 */
public class QuoteFragment extends Fragment {
    @InjectView(R.id.quoteTextView) TextView quoteTextView;
    @InjectView(R.id.quoteAuthorTextView) TextView quoteAuthorTextView;
    @InjectView(R.id.favourite_button) ImageButton favouriteButton;
    @InjectView(R.id.reloadButton) ImageButton reloadButton;
    @InjectView(R.id.twitterButton) ImageButton twitterButton;
    @InjectView(R.id.facebookButton) ImageButton facebookButton;
    @InjectView(R.id.vkButton) ImageButton vkButton;
    @InjectView(R.id.main_layout) RelativeLayout mainLayout;
    private QuoteLoader quoteLoader;
    private BackgroundLoader backgroundLoader;
    private DatabaseHandler databaseHandler;
    private Activity activity;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState) {
        activity = getActivity();
        databaseHandler = new DatabaseHandler(activity);
        View rootView = layoutInflater.inflate(R.layout.quote_fragment, container, false);
        ButterKnife.inject(this, rootView);
        favouriteButton.setTag(R.drawable.ic_bottom_favorite_no);

        backgroundLoader = new BackgroundLoader(activity, mainLayout);
        backgroundLoader.selectBackground();

        AdView adView = (AdView)rootView.findViewById(R.id.activity_main_adView);
        adView.loadAd(new AdRequest());
        quoteLoader = new QuoteLoader(activity, quoteTextView, quoteAuthorTextView);
        quoteLoader.execute();
        return rootView;
    }

    @OnClick(R.id.reloadButton) public void reload() {
        quoteLoader = new QuoteLoader(activity, quoteTextView, quoteAuthorTextView);
        quoteLoader.execute();
    }

    @OnClick(R.id.favourite_button) public void addToFavourite() {
        int id = (int)favouriteButton.getTag();
        if(id == R.drawable.ic_bottom_favorite_no) {
            if(!new DatabaseHandler(activity).getAllFavouriteElements().search(quoteTextView.getText().toString())) {
                databaseHandler.addFavourite(new FavouriteItem(quoteTextView.getText().toString(), quoteAuthorTextView.getText().toString()));
            }
            favouriteButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_bottom_favorite_ok));
            favouriteButton.setTag(R.drawable.ic_bottom_favorite_ok);
        } else {
            favouriteButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_bottom_favorite_no));
            favouriteButton.setTag(R.drawable.ic_bottom_favorite_no);
        }
    }

    @OnClick(R.id.twitterButton) public void shareOnTwitter() {
        ShareUtil shareUtil = new ShareUtil(activity, quoteLoader.getLastQuote());
        shareUtil.shareOnTwitter();
    }

    @OnClick(R.id.facebookButton) public void shareOnFacebook() {
        ShareUtil shareUtil = new ShareUtil(activity, quoteLoader.getLastQuote());
        shareUtil.shareOnFacebook();
    }

    @OnClick(R.id.vkButton) public void shareOnVk() {
        ShareUtil shareUtil = new ShareUtil(activity, quoteLoader.getLastQuote());
        shareUtil.shareOnVk();
    }
}