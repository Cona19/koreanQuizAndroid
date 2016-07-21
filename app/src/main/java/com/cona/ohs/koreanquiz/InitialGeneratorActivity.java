package com.cona.ohs.koreanquiz;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class InitialGeneratorActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_generator);
        setTitle("초성문제 번호 입력");

        Button btnInitialQuizByNum = (Button) findViewById(R.id.btn_initialQuizByNum);
        btnInitialQuizByNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "share");
                EditText editNum = (EditText) findViewById(R.id.edit_initia_gen_num);
                String strNum = editNum.getText().toString();

                if (!strNum.isEmpty() && isNumberString(strNum)){
                    int id = Integer.parseInt(strNum);

                    KoreanWordAPITask task = new KoreanWordAPITask();
                    try{
                        KoreanWord word = task.execute(id).get();
                        if (word != null) {
                            Intent intent = new Intent(InitialGeneratorActivity.this, InitialQuizActivity.class);
                            intent.putExtra("word", word);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "입력에 실패하였습니다", Toast.LENGTH_SHORT).show();
                        }

                    } catch(InterruptedException e){
                        Log.d("TAG", e.toString());
                        e.printStackTrace();
                    } catch(ExecutionException e){
                        Log.d("TAG", e.toString());
                        e.printStackTrace();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "숫자만 입력하세요", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean isNumberString(String str){
        return str.matches("[0-9]*");
    }
}
