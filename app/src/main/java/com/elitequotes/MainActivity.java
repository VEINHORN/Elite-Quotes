package com.elitequotes;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
        //quoteTextView.setAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_in));
        reloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quoteLoader = new QuoteLoader(MainActivity.this, quoteTextView, quoteAuthorTextView);
                quoteLoader.execute();
            }
        });
        quoteLoader = new QuoteLoader(MainActivity.this, quoteTextView, quoteAuthorTextView);
        quoteLoader.execute();
    }
}
