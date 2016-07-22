package com.cona.ohs.koreanquiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.concurrent.ExecutionException;

public class StatisticsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        StatisticsAPITask task = new StatisticsAPITask();
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        String facebookUserId = pref.getString("facebookUserId", "");
        Log.d("TAG", "onCreate_facebookUserId : " + facebookUserId);
        try{
            Statistics statistics = task.execute(facebookUserId).get();
            Log.d("TAG", "cntCorrectTry : " + Integer.toString(statistics.getCntCorrectTry()));
            if (statistics != null) {
                TextView textCntCorrectTry = (TextView) findViewById(R.id.text_statistics_count_correct_try);
                TextView textCntWrongTry = (TextView) findViewById(R.id.text_statistics_count_wrong_try);
                TextView textCorrectTryPercent = (TextView) findViewById(R.id.text_statistics_correct_try_percent);
                RatingBar rtbCorrectTryPercent = (RatingBar) findViewById(R.id.rtb_statistics_correct_try_percent);
                TextView textCntCorrectProblem = (TextView) findViewById(R.id.text_statistics_count_correct_problem);
                TextView textCntWrongProblem = (TextView) findViewById(R.id.text_statistics_count_wrong_problem);
                TextView textCorrectProblemPercent = (TextView) findViewById(R.id.text_statistics_correct_problem_percent);
                RatingBar rtbCorrectProblemPercent = (RatingBar) findViewById(R.id.rtb_statistics_correct_problem_percent);

                textCntCorrectTry.setText("정답 횟수: " + statistics.getCntCorrectTry());
                textCntWrongTry.setText("오답 횟수: " + statistics.getCntWrongTry());
                textCorrectTryPercent.setText(String.format("%.2f%%", statistics.getCorrectTryPercent()));
                rtbCorrectTryPercent.setRating(statistics.getCorrectTryPercent()/20);
                textCntCorrectProblem.setText("해결한 문제 수: " + statistics.getCntCorrectProblem());
                textCntWrongProblem.setText("실패한 문제 수: " + statistics.getCntWrongProblem());
                textCorrectProblemPercent.setText(String.format("%.2f%%", statistics.getCorrectProblemPercent()));
                rtbCorrectProblemPercent.setRating(statistics.getCorrectProblemPercent()/20);
            }
            else{
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
