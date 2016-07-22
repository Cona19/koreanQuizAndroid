package com.cona.ohs.koreanquiz;

/**
 * Created by hyeonseok on 2016. 7. 22..
 */
public class Statistics {
    private int cntCorrectTry;
    private int cntWrongTry;
    private int cntCorrectProblem;
    private int cntWrongProblem;
    
    public int getCntWrongProblem() {
        return cntWrongProblem;
    }

    public void setCntWrongProblem(int cntWrongProblem) {
        this.cntWrongProblem = cntWrongProblem;
    }

    public int getCntCorrectTry() {
        return cntCorrectTry;
    }

    public void setCntCorrectTry(int cntCorrectTry) {
        this.cntCorrectTry = cntCorrectTry;
    }

    public int getCntWrongTry() {
        return cntWrongTry;
    }

    public void setCntWrongTry(int cntWrongTry) {
        this.cntWrongTry = cntWrongTry;
    }

    public int getCntCorrectProblem() {
        return cntCorrectProblem;
    }

    public void setCntCorrectProblem(int cntCorrectProblem) {
        this.cntCorrectProblem = cntCorrectProblem;
    }

    public float getCorrectTryPercent(){
        return (float)cntCorrectTry / (cntCorrectTry+cntWrongTry) * 100;
    }
    public float getCorrectProblemPercent(){
        return (float)cntCorrectProblem / (cntCorrectProblem+cntWrongProblem) * 100;
    }
}
