package com.cona.ohs.koreanquiz;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.HttpURLConnection;

/**
 * Created by hyeonseok on 2016. 7. 21..
 */
public class KoreanWordAPIClient {
    final static String koreanWordAPIURL = "http://52.196.178.200:8080/api/";

    public static KoreanWord getKoreanWord(int id){
        KoreanWord word = null;
        String urlString = koreanWordAPIURL + "words/" + (id == 0 ? "random" : id);
        try {
            URL url = new URL(urlString);

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            JSONObject json = new JSONObject(getStringFromInputStream(in));

            word = parseJSON(json);
            word.setId(id);
        } catch (MalformedURLException e) {
            Log.d("TAG", e.toString());
            e.printStackTrace();
        } catch (JSONException e) {
            Log.d("TAG", e.toString());
            e.printStackTrace();
        } catch (IOException e) {
            Log.d("TAG", e.toString());
            e.printStackTrace();
        }
        return word;
    }

    public static KoreanWord getKoreanWord(){
        return getKoreanWord(0);
    }

    private static KoreanWord parseJSON(JSONObject json) throws JSONException {
        KoreanWord word = new KoreanWord();
        word.setId(json.getInt("id"));
        word.setWord(json.getString("word"));
        word.setExplanation(json.getString("explanation"));

        return word;
    }

    private static String getStringFromInputStream(InputStream is) {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try{
            br = new BufferedReader(new InputStreamReader(is));
            while((line = br.readLine()) != null){
                sb.append(line);
            }
        }catch(IOException e){
            Log.d("TAG", "IOException at getStringFromInputStream");
        }finally {
            if (br != null){
                try{
                    br.close();
                }catch (IOException e){
                    Log.d("TAG", "IOException at BufferedReader close");
                }
            }
        }
        return sb.toString();
    }
}
