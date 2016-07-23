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
        word = (KoreanWord) intent.getSerializableExtra("word");
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
                if (answer.equals(word.getWord())){
                    postRecord(true);
                    ViewGroup vg = (ViewGroup) editAnswer.getParent();
                    vg.removeView(editAnswer);
                    Button btnSubmit = (Button) findViewById(R.id.btn_initial_quiz_submit);
                    vg = (ViewGroup) btnSubmit.getParent();
                    vg.removeView(btnSubmit);

                    TextView textInitials = (TextView) findViewById(R.id.text_initial_quiz_initial);
                    textInitials.setText(word.getWord());

                    Animation move = AnimationUtils.loadAnimation(InitialQuizActivity.this, R.anim.move);
                    findViewById(R.id.layout_initial_quiz_info).startAnimation(move);
                }
                else{
                    postRecord(false);
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

    private void postRecord(boolean succeed){
        record.setSucceed(succeed);
        RecordAPITask task = new RecordAPITask();
        task.execute(record);
    }
}
