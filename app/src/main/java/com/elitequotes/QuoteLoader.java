package com.elitequotes;

import android.content.Context;
import android.os.AsyncTask;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.forismastic.Forismatic;
import com.forismastic.Forismatic.Quote;

/**
 * Created by VEINHORN on 08/02/14.
 */
public class QuoteLoader extends AsyncTask<String, Integer, Quote>{
    private TextView quoteTextView;
    private TextView quoteAuthorTextView;
    private Context context;
    private Quote lastQuote;

    public QuoteLoader(Context context, TextView quoteTextView, TextView quoteAuthorTextView) {
        this.context = context;
        this.quoteTextView = quoteTextView;
        this.quoteAuthorTextView = quoteAuthorTextView;
    }

    protected Quote doInBackground(String... params) {
        Quote quote = new Forismatic(Forismatic.ENGLISH).getQuote();
        return quote;
    }

    protected void onPostExecute(Quote quote) {
        quoteTextView.startAnimation(AnimationUtils.loadAnimation(context, android.R.anim.fade_in));
        quoteAuthorTextView.startAnimation(AnimationUtils.loadAnimation(context, android.R.anim.fade_in));
        quoteTextView.setText(quote.getQuoteText());
        quoteAuthorTextView.setText(quote.getQuoteAuthor());
        lastQuote = quote;
    }

    public Quote getLastQuote() {
        return lastQuote;
    }
}
