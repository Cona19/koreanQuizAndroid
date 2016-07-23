package com.cona.ohs.koreanquiz;

import java.io.Serializable;
import java.util.Random;

/**
 * Created by hyeonseok on 2016. 7. 21..
 */
public class KoreanWord implements Serializable{
    private int id;
    private String word;
    private String explanation;
    private static final Random rand = new Random();
    private static final char hiddenChar = '☆';

    public KoreanWord(String word, String explanation) {
        this.id = 0;
        this.word = word;
        this.explanation = explanation;
    }
    public KoreanWord(int id, String word, String explanation) {
        this.id = id;
        this.word = word;
        this.explanation = explanation;
    }

    public KoreanWord() {
        id = 0;
        word = "";
        explanation = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getInitial() {
        return Hangul.exportFirstSound(word);
    }

    public String getCrossword() {
        int length = word.length();
        int showIdx = rand.nextInt(length);
        StringBuilder crossWord = new StringBuilder();
        for (int i = 0; i <length; i++){
            if (showIdx == i){
                crossWord.append(word.charAt(i));
            }
            else{
                crossWord.append(hiddenChar);
            }
        }
        return crossWord.toString();
    }
}
