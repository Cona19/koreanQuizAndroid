package com.cona.ohs.koreanquiz;

import android.os.AsyncTask;

/**
 * Created by hyeonseok on 2016. 7. 22..
 */
public class StatisticsAPITask extends AsyncTask<String, Void, Statistics> {
    @Override
    protected Statistics doInBackground(String... strings) {
        return APIClient.getStatistics(strings[0]);
    }
}
