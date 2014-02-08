package com.elitequotes;

import android.os.AsyncTask;

import com.forismastic.Forismatic;
import com.forismastic.Forismatic.Quote;

/**
 * Created by VEINHORN on 08/02/14.
 */
public class QuoteLoader extends AsyncTask<String, Integer, Quote>{

    public QuoteLoader() {

    }

    protected Quote doInBackground(String... params) {
        Quote quote = new Forismatic().getQuote();
        return quote;
    }

    protected void onPostExecute(Quote quote) {

    }
}
