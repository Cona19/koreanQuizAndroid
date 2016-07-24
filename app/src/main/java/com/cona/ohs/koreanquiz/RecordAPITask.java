package com.cona.ohs.koreanquiz;

import android.os.AsyncTask;

/**
 * Created by hyeonseok on 2016. 7. 22..
 */
public class RecordAPITask extends AsyncTask<Record, Void, Void>{
    @Override
    protected Void doInBackground(Record... records) {
        APIClient.postResult(records[0]);
        return null;
    }
}
