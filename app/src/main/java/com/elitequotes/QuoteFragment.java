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

import com.google.ads.AdRequest;
import com.google.ads.AdView;

/**
 * Created by veinhorn on 20.3.14.
 */
public class QuoteFragment extends Fragment {
    private TextView quoteTextView;
    private TextView quoteAuthorTextView;
    private ImageButton reloadButton;
    private ImageButton twitterButton;
    private ImageButton facebookButton;
    private ImageButton vkButton;
    private QuoteLoader quoteLoader;
    private RelativeLayout mainLayout;
    private BackgroundLoader backgroundLoader;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState) {
        final Activity activity = getActivity();
        View rootView = layoutInflater.inflate(R.layout.quote_fragment, container, false);
        quoteTextView = (TextView)rootView.findViewById(R.id.quoteTextView);
        quoteAuthorTextView = (TextView)rootView.findViewById(R.id.quoteAuthorTextView);
        reloadButton = (ImageButton)rootView.findViewById(R.id.reloadButton);
        twitterButton = (ImageButton)rootView.findViewById(R.id.twitterButton);
        facebookButton = (ImageButton)rootView.findViewById(R.id.facebookButton);
        vkButton = (ImageButton)rootView.findViewById(R.id.vkButton);
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
