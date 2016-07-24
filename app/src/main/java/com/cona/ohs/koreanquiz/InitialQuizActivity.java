/*
 * Copyright (c) 2016 Hyeonseok Oh. All Rights Reserved.
 */

package com.cona.ohs.koreanquiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class InitialQuizActivity extends AppCompatActivity {
    KoreanWord word;
    Record record;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("초성 문제");
        setContentView(R.layout.activity_initial_quiz);
        Intent intent = getIntent();

        /* Get koreanWord from intent */
        word = (KoreanWord) intent.getSerializableExtra("word");

        /* Get facebookUserID from sharedPreference */
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        record = new Record(pref.getString("facebookUserId", ""), word);

        TextView textInitials = (TextView) findViewById(R.id.text_initial_quiz_initial);
        textInitials.setText(word.getInitial());

        TextView textHint = (TextView) findViewById(R.id.text_initial_quiz_hint);
        textHint.setText(word.getExplanation());

        Button btnSubmit = (Button) findViewById(R.id.btn_initial_quiz_submit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editAnswer = (EditText) findViewById(R.id.edit_initial_quiz_answer);
                String answer = editAnswer.getText().toString();
                /* Match with original word and determine whether it is correct or not */
                if (answer.equals(word.getWord())){
                    /* Post correct result to server */
                    postRecord(true);

                    /* Remove editView and button */
                    ViewGroup vg = (ViewGroup) editAnswer.getParent();
                    vg.removeView(editAnswer);
                    Button btnSubmit = (Button) findViewById(R.id.btn_initial_quiz_submit);
                    vg = (ViewGroup) btnSubmit.getParent();
                    vg.removeView(btnSubmit);

                    /* Show correct answer */
                    TextView textInitials = (TextView) findViewById(R.id.text_initial_quiz_initial);
                    textInitials.setText(word.getWord());

                    /* move some views to be looked cool */
                    Animation move = AnimationUtils.loadAnimation(InitialQuizActivity.this, R.anim.move);
                    findViewById(R.id.layout_initial_quiz_info).startAnimation(move);
                }
                else{
                    /* Post wrong result to server */
                    postRecord(false);

                    /* shake editView to show that it is wrong */
                    Animation shake = AnimationUtils.loadAnimation(InitialQuizActivity.this, R.anim.shake);
                    findViewById(R.id.edit_initial_quiz_answer).startAnimation(shake);
                }
            }
        });
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    /**
     * post try result to server. It is executed asynchronously.
     * @param succeed
     */
    private void postRecord(boolean succeed){
        record.setSucceed(succeed);
        RecordAPITask task = new RecordAPITask();
        task.execute(record);
    }
}
