/*
 * Copyright (c) 2016 Hyeonseok Oh. All Rights Reserved.
 */

package com.cona.ohs.koreanquiz;

import android.os.AsyncTask;

/**
 * Created by hyeonseok on 2016. 7. 22..
 * This class is used to do communication with server asynchronously.
 * using this class, we can get statistics to serer.
 */
public class StatisticsAPITask extends AsyncTask<String, Void, Statistics> {
    @Override
    protected Statistics doInBackground(String... strings) {
        return APIClient.getStatistics(strings[0]);
    }
}
