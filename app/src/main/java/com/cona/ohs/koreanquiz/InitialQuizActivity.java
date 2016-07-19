package com.cona.ohs.koreanquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class InitialQuizActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("초성 문제");
        setContentView(R.layout.activity_initial_quiz);
    }
}
