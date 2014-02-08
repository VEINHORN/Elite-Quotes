package com.elitequotes;

import android.os.AsyncTask;
import android.widget.TextView;

import com.forismastic.Forismatic;
import com.forismastic.Forismatic.Quote;

/**
 * Created by VEINHORN on 08/02/14.
 */
public class QuoteLoader extends AsyncTask<String, Integer, Quote>{
    private TextView quoteTextView;
    private TextView quoteAuthorTextView;

    public QuoteLoader(TextView quoteTextView, TextView quoteAuthorTextView) {
        this.quoteTextView = quoteTextView;
        this.quoteAuthorTextView = quoteAuthorTextView;
    }

    protected Quote doInBackground(String... params) {
        Quote quote = new Forismatic(Forismatic.ENGLISH).getQuote();
        return quote;
    }

    protected void onPostExecute(Quote quote) {
        quoteTextView.setText(quote.getQuoteText());
        quoteAuthorTextView.setText(quote.getQuoteAuthor());
    }
}
