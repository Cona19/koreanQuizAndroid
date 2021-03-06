/*
 * Copyright (c) 2016 Hyeonseok Oh. All Rights Reserved.
 */

package com.cona.ohs.koreanquiz;

/**
 * Created by hyeonseok on 2016. 7. 22..
 * This class is used to store user's try and result record.
 */
public class Record {
    private String facebookUserId;
    private boolean succeed;
    private KoreanWord word;

    public Record(String facebookUserId, KoreanWord word) {
        this.facebookUserId = facebookUserId;
        this.succeed = false;
        this.word = word;
    }

    public String getFacebookUserId() {
        return facebookUserId;
    }

    public KoreanWord getWord() {
        return word;
    }

    public boolean isSucceed() {
        return succeed;
    }

    public void setSucceed(boolean succed) {
        succeed = succed;
    }
}
