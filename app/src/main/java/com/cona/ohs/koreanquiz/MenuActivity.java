/*
 * Copyright (c) 2016 Hyeonseok Oh. All Rights Reserved.
 */

package com.cona.ohs.koreanquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class MenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setTitle("우리말 겨루기");

        /* Set each frame's click event handler */
        FrameLayout frmCrossword = (FrameLayout) findViewById(R.id.frm_crossword);
        frmCrossword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "crossword");
                getKoreanWordAndStartQuiz(new Intent(MenuActivity.this, CrossQuizActivity.class));
            }
        });
        FrameLayout frmInitial  = (FrameLayout) findViewById(R.id.frm_initial);
        frmInitial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "initial");
                getKoreanWordAndStartQuiz(new Intent(MenuActivity.this, InitialQuizActivity.class));
            }
        });
        FrameLayout frmStatistics = (FrameLayout) findViewById(R.id.frm_statistics);
        frmStatistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "statistics");
                Intent intent = new Intent(MenuActivity.this, StatisticsActivity.class);
                startActivity(intent);
            }
        });
        FrameLayout frmContact = (FrameLayout) findViewById(R.id.frm_contact);
        frmContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "contact");
                Intent intent = new Intent(MenuActivity.this, ContactActivity.class);
                startActivity(intent);
            }
        });
    }

    /* get koreanword from KoreanWordAPITask asynchronously and put koreanWord in intent before move to next Activity */
    private void getKoreanWordAndStartQuiz(Intent intent){
        KoreanWordAPITask task = new KoreanWordAPITask();
        try{
            /* Get koreanWord using asnycTask */
            KoreanWord word = task.execute().get();
            if (word != null) {
                /* Store koreanWord in intent */
                intent.putExtra("word", word);
                startActivity(intent);
            }
            else {
                Toast.makeText(getApplicationContext(), "오류가 발생했습니다", Toast.LENGTH_SHORT).show();
            }

        } catch(InterruptedException e){
            Log.d("TAG", e.toString());
            e.printStackTrace();
        } catch(ExecutionException e){
            Log.d("TAG", e.toString());
            e.printStackTrace();
        }
    }
}
