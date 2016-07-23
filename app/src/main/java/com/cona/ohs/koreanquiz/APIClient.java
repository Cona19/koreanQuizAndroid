package com.cona.ohs.koreanquiz;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.HttpURLConnection;

/**
 * Created by hyeonseok on 2016. 7. 21..
 */
public class APIClient {
    final static String APIURL = "http://koreanquiz-hs5.rhcloud.com/api/";

    public static void postResult(Record record){
        String url = APIURL + "record/";
        Log.d("TAG", "Post Result");
        try {
            URL object = new URL(url);

            HttpURLConnection con = (HttpURLConnection) object.openConnection();
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestMethod("POST");

            JSONObject json = new JSONObject();

            json.put("facebookUserId", record.getFacebookUserId());
            json.put("wordId", record.getWord().getId());
            json.put("succeed", record.isSucceed());
            OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
            wr.write(json.toString());
            wr.flush();
            Log.d("TAG", json.toString());
            int responseCode = con.getResponseCode();
            Log.d("TAG", "ResponseCode : " + responseCode);
        } catch (IOException e) {
            Log.d("TAG", e.toString());
            e.printStackTrace();
        } catch (JSONException e) {
            Log.d("TAG", e.toString());
            e.printStackTrace();
        }
    }

    public static Statistics getStatistics(String facebookUserId){
        Statistics statistics = null;
        String urlString = APIURL + "record/" + facebookUserId;
        Log.d("TAG", urlString);
        try {
            URL url = new URL(urlString);

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            JSONObject json = new JSONObject(getStringFromInputStream(in));

            Log.d("TAG", json.toString());
            statistics = parseStatisticsJSON(json);
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
        return statistics;
    }

    public static KoreanWord getKoreanWord(int id){
        KoreanWord word = null;
        String urlString = APIURL + "words/" + (id == 0 ? "random" : id);
        try {
            URL url = new URL(urlString);

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            JSONObject json = new JSONObject(getStringFromInputStream(in));

            word = parseKoreanWordJSON(json);
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

    private static KoreanWord parseKoreanWordJSON(JSONObject json) throws JSONException {
        Log.d("TAG", "JSON : " + json.toString());
        KoreanWord word = new KoreanWord();
        word.setId(json.getInt("id"));
        word.setWord(json.getString("word"));
        word.setExplanation(json.getString("explanation"));

        return word;
    }

    private static Statistics parseStatisticsJSON(JSONObject json) throws JSONException {
        Statistics statistics = new Statistics();
        statistics.setCntCorrectTry(json.getInt("cntCorrectTry"));
        statistics.setCntWrongTry(json.getInt("cntWrongTry"));
        statistics.setCntCorrectProblem(json.getInt("cntCorrectProblem"));
        statistics.setCntWrongProblem(json.getInt("cntWrongProblem"));

        return statistics;
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
