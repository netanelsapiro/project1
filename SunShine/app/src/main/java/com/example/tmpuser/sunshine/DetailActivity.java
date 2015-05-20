package com.example.tmpuser.sunshine;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class DetailActivity extends ActionBarActivity {

    private static String LOG_TAG = DetailActivity.class.getSimpleName();

    private static String FORECAST_SHARE_HASHTAG= " #SunShine";
    private String m_ForecastStr;

    private ShareActionProvider m_ShareActionProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        TextView t = (TextView) findViewById(R.id.detail_forecast);
        Intent intent = getIntent();
        m_ForecastStr = intent.getStringExtra(Intent.EXTRA_TEXT);
        t.setText(m_ForecastStr);


    }

    private Intent createForecastShareIntent() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        //Make the return button return to our app instead of the handler ACTION_SEND app
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(shareIntent.EXTRA_TEXT,
                m_ForecastStr + FORECAST_SHARE_HASHTAG);
        return shareIntent;
    }

    @TargetApi(14)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.detailfragment, menu);

        MenuItem item = menu.findItem(R.id.action_share);

        m_ShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);
        m_ShareActionProvider.setShareIntent(createForecastShareIntent());
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
