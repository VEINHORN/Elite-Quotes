package com.elitequotes;

import com.forismastic.Forismatic.Quote;

/**
 * Created by VEINHORN on 25/02/14.
 */
public class ShareUtil {
    public static String twitterFormat(Quote quote) {
        return "\"" + quote.getQuoteText() + "\"" + " - " + quote.getQuoteAuthor() + ".";
    }
}
