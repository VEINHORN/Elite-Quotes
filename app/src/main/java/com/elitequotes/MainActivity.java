package com.elitequotes;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.ads.AdRequest;
import com.google.ads.AdView;

import java.util.ArrayList;

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

    private ArrayList<SpinnerNavigationItem> spinnerNavigationItems;
    private ArrayList<String> titles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0A2229")));
        actionBar.setDisplayShowTitleEnabled(false);
        //actionBar.setTitle(Html.fromHtml("<b><font color=\"#BDBDBD\">" + getString(R.string.app_name) + "</font></b>"));
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        spinnerNavigationItems = new ArrayList<SpinnerNavigationItem>();
        titles = new ArrayList<String>();
        titles.add("Elite Quotes");
        titles.add("Favourite");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.spinner_list_item, R.id.spinner_item_title, titles);
        actionBar.setListNavigationCallbacks(arrayAdapter, new ActionBar.OnNavigationListener() {
            @Override
            public boolean onNavigationItemSelected(int i, long l) {
                return false;
            }
        });
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
