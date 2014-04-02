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

/**
 * Created by veinhorn on 20.3.14.
 */
public class QuoteFragment extends Fragment {
    private TextView quoteTextView;
    private TextView quoteAuthorTextView;
    private ImageButton favouriteButton;
    private ImageButton reloadButton;
    private ImageButton twitterButton;
    private ImageButton facebookButton;
    private ImageButton vkButton;
    private QuoteLoader quoteLoader;
    private RelativeLayout mainLayout;
    private BackgroundLoader backgroundLoader;
    private DatabaseHandler databaseHandler;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState) {
        final Activity activity = getActivity();
        databaseHandler = new DatabaseHandler(activity);
        View rootView = layoutInflater.inflate(R.layout.quote_fragment, container, false);
        quoteTextView = (TextView)rootView.findViewById(R.id.quoteTextView);
        quoteAuthorTextView = (TextView)rootView.findViewById(R.id.quoteAuthorTextView);
        reloadButton = (ImageButton)rootView.findViewById(R.id.reloadButton);
        twitterButton = (ImageButton)rootView.findViewById(R.id.twitterButton);
        facebookButton = (ImageButton)rootView.findViewById(R.id.facebookButton);
        vkButton = (ImageButton)rootView.findViewById(R.id.vkButton);
        favouriteButton = (ImageButton)rootView.findViewById(R.id.favourite_button);

        favouriteButton.setTag(R.drawable.ic_bottom_favorite_no);

        favouriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = (int)favouriteButton.getTag();
                if(id == R.drawable.ic_bottom_favorite_no) {
                    databaseHandler.addFavourite(new FavouriteItem(quoteTextView.getText().toString(), quoteAuthorTextView.getText().toString()));
                    favouriteButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_bottom_favorite_ok));
                    favouriteButton.setTag(R.drawable.ic_bottom_favorite_ok);
                } else {
                    favouriteButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_bottom_favorite_no));
                    favouriteButton.setTag(R.drawable.ic_bottom_favorite_no);
                }
            }
        });
        mainLayout = (RelativeLayout)rootView.findViewById(R.id.main_layout);
        backgroundLoader = new BackgroundLoader(activity, mainLayout);
        backgroundLoader.selectBackground();
        reloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quoteLoader = new QuoteLoader(activity, quoteTextView, quoteAuthorTextView);
                quoteLoader.execute();
            }
        });
        twitterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareUtil shareUtil = new ShareUtil(activity, quoteLoader.getLastQuote());
                shareUtil.shareOnTwitter();
            }
        });
        facebookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareUtil shareUtil = new ShareUtil(activity, quoteLoader.getLastQuote());
                shareUtil.shareOnFacebook();
            }
        });
        vkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareUtil shareUtil = new ShareUtil(activity, quoteLoader.getLastQuote());
                shareUtil.shareOnVk();
            }
        });
        AdView adView = (AdView)rootView.findViewById(R.id.activity_main_adView);
        adView.loadAd(new AdRequest());
        quoteLoader = new QuoteLoader(activity, quoteTextView, quoteAuthorTextView);
        quoteLoader.execute();
        return rootView;
    }
}
