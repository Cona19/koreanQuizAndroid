/*
 * Copyright (c) 2016 Hyeonseok Oh. All Rights Reserved.
 */

package com.cona.ohs.koreanquiz;

import android.os.AsyncTask;

/**
 * Created by hyeonseok on 2016. 7. 22..
 * This class is used to do communication with server asynchronously.
 * using this class, we can post record to serer.
 */
public class RecordAPITask extends AsyncTask<Record, Void, Void>{
    @Override
    protected Void doInBackground(Record... records) {
        APIClient.postResult(records[0]);
        return null;
    }
}
