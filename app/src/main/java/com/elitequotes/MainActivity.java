package com.elitequotes;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.ads.AdRequest;
import com.google.ads.AdView;

public class MainActivity extends ActionBarActivity {
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0A2229")));
        getSupportActionBar().setTitle(Html.fromHtml("<b><font color=\"#BDBDBD\">" + getString(R.string.app_name) + "</font></b>"));
        quoteTextView = (TextView)findViewById(R.id.quoteTextView);
        quoteAuthorTextView = (TextView)findViewById(R.id.quoteAuthorTextView);
        reloadButton = (ImageButton)findViewById(R.id.reloadButton);
        twitterButton = (ImageButton)findViewById(R.id.twitterButton);
        facebookButton = (ImageButton)findViewById(R.id.facebookButton);
        vkButton = (ImageButton)findViewById(R.id.vkButton);
        mainLayout = (RelativeLayout)findViewById(R.id.main_layout);

        backgroundLoader = new BackgroundLoader(this, mainLayout);
        backgroundLoader.selectBackground();

        reloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quoteLoader = new QuoteLoader(MainActivity.this, quoteTextView, quoteAuthorTextView);
                quoteLoader.execute();
            }
        });
        twitterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareUtil shareUtil = new ShareUtil(MainActivity.this, quoteLoader.getLastQuote());
                shareUtil.shareOnTwitter();
            }
        });
        facebookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareUtil shareUtil = new ShareUtil(MainActivity.this, quoteLoader.getLastQuote());
                shareUtil.shareOnFacebook();
            }
        });
        vkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareUtil shareUtil = new ShareUtil(MainActivity.this, quoteLoader.getLastQuote());
                shareUtil.shareOnVk();
            }
        });

        AdView adView = (AdView)findViewById(R.id.activity_main_adView);
        adView.loadAd(new AdRequest());

        quoteLoader = new QuoteLoader(MainActivity.this, quoteTextView, quoteAuthorTextView);
        quoteLoader.execute();
    }
}
