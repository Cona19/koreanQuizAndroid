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
        FrameLayout frmToday = (FrameLayout) findViewById(R.id.frm_today);
        frmToday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "today");
                Toast.makeText(getApplicationContext(), "준비중입니다.", Toast.LENGTH_SHORT).show();
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
        FrameLayout frmShare = (FrameLayout) findViewById(R.id.frm_share);
        frmShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "share");
                Toast.makeText(getApplicationContext(), "준비중입니다.", Toast.LENGTH_SHORT).show();
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

    private void getKoreanWordAndStartQuiz(Intent intent){
        KoreanWordAPITask task = new KoreanWordAPITask();
        try{
            KoreanWord word = task.execute().get();
            if (word != null) {
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
