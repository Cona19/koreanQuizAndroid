package com.cona.ohs.koreanquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

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
                Toast.makeText(getApplicationContext(), "준비중입니다.", Toast.LENGTH_SHORT).show();
            }
        });
        FrameLayout frmInitial  = (FrameLayout) findViewById(R.id.frm_initial);
        frmInitial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "initial");
                Intent intent = new Intent(MenuActivity.this, InitialGeneratorActivity.class);
                startActivity(intent);
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
                Toast.makeText(getApplicationContext(), "준비중입니다.", Toast.LENGTH_SHORT).show();
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
}
