package com.elitequotes;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {
    private TextView quoteTextView;
    private TextView quoteAuthorTextView;
    private ImageButton reloadButton;
    private ImageButton twitterButton;
    private QuoteLoader quoteLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0A2229")));
        getSupportActionBar().setTitle(Html.fromHtml("<b><font color=\"#BDBDBD\">" + "Elite Quotes" + "</font></b>"));
        quoteTextView = (TextView)findViewById(R.id.quoteTextView);
        quoteAuthorTextView = (TextView)findViewById(R.id.quoteAuthorTextView);
        reloadButton = (ImageButton)findViewById(R.id.reloadButton);
        twitterButton = (ImageButton)findViewById(R.id.twitterButton);
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
                String tweetUrl = "https://twitter.com/intent/tweet?text=" + ShareUtil.twitterFormat(quoteLoader.getLastQuote());
                        //+ "&url=https://www.google.com";
                Uri uri = Uri.parse(tweetUrl);
                startActivity(new Intent(Intent.ACTION_VIEW, uri));
            }
        });
        quoteLoader = new QuoteLoader(MainActivity.this, quoteTextView, quoteAuthorTextView);
        quoteLoader.execute();
    }
}
