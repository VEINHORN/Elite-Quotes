package com.elitequotes;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends ActionBarActivity {
    @InjectView(R.id.viewpager) ViewPager viewPager;
    private MyFragmentPagerAdapter myFragmentPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0A2229")));
        actionBar.setTitle(Html.fromHtml("<b><font color=\"#BDBDBD\">" + "Elite Quotes" + "</font></b>"));
        myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        ButterKnife.inject(this);
        viewPager.setAdapter(myFragmentPagerAdapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }

            @Override public void onPageSelected(int position) {
                if(position == MyFragmentPagerAdapter.QUOTE_FRAGMENT) {
                    actionBar.setTitle(Html.fromHtml("<b><font color=\"#BDBDBD\">" + "Elite Quotes" + "</font></b>"));
                } else if(position == MyFragmentPagerAdapter.FAVOURITE_FRAGMENT) {
                    actionBar.setTitle(Html.fromHtml("<b><font color=\"#BDBDBD\">" + "Favourite" + "</font></b>"));
                }
            }

            @Override public void onPageScrollStateChanged(int state) { }
        });
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.mainmenu_settings:
                break;
            case R.id.mainmenu_about:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}