package com.elitequotes.share;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.forismastic.Forismatic.Quote;

/**
 * Created by VEINHORN on 25/02/14.
 */
public class ShareUtil {
    private Context context;
    private Quote quote;
    private static class Formatter {
        public static String twitterFormat(Quote quote) {
            return "\"" + quote.getQuoteText() + "\"" + " - " + quote.getQuoteAuthor() + ".";
        }

        public static String facebookFormat(Quote quote) {
            return twitterFormat(quote);
        }

        public static String vkFormat(Quote quote) {
            return twitterFormat(quote);
        }
    }

    public ShareUtil(Context context, Quote quote) {
        this.context = context;
        this.quote = quote;
    }

    public void shareOnTwitter() {
        String tweetUrl = "https://twitter.com/intent/tweet?text=" + Formatter.twitterFormat(quote);
        Uri uri = Uri.parse(tweetUrl);
        context.startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }

    public void shareOnFacebook() {
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, Formatter.facebookFormat(quote));
        shareIntent.setType("text/plain");
        context.startActivity(Intent.createChooser(shareIntent, "Share quote to..."));
    }

    public void shareOnVk() {
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, Formatter.vkFormat(quote));
        shareIntent.setType("text/plain");
        context.startActivity(Intent.createChooser(shareIntent, "Share quote to..."));
    }
}
