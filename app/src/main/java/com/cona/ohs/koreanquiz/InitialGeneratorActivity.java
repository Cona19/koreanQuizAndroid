package com.cona.ohs.koreanquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class InitialGeneratorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_generator);
        setTitle("초성 문제번호 입력");

        Button btnInitialQuizByNum = (Button) findViewById(R.id.btn_initialQuizByNum);
        btnInitialQuizByNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "share");
                Intent intent = new Intent(InitialGeneratorActivity.this, InitialQuizActivity.class);
                startActivity(intent);
            }
        });
    }
}
