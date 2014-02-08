package com.elitequotes;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {
    private TextView quoteTextView;
    private TextView quoteAuthorTextView;
    private Button reloadButton;
    private QuoteLoader quoteLoader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        quoteTextView = (TextView)findViewById(R.id.quoteTextView);
        quoteAuthorTextView = (TextView)findViewById(R.id.quoteAuthorTextView);
        reloadButton = (Button)findViewById(R.id.reloadButton);
        reloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quoteLoader = new QuoteLoader(quoteTextView, quoteAuthorTextView);
                quoteLoader.execute();
            }
        });
        quoteLoader = new QuoteLoader(quoteTextView, quoteAuthorTextView);
        quoteLoader.execute();
    }
}
