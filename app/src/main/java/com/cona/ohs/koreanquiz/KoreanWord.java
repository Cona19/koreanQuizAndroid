package com.cona.ohs.koreanquiz;

import java.io.Serializable;

/**
 * Created by hyeonseok on 2016. 7. 21..
 */
public class KoreanWord implements Serializable{
    private int id;
    private String word;
    private String explanation;

    public KoreanWord(String word, String explanation) {
        this.word = word;
        this.explanation = explanation;
    }
    public KoreanWord(int id, String word, String explanation) {
        this.id = id;
        this.word = word;
        this.explanation = explanation;
    }

    public KoreanWord() {
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
        return "";
    }
}
