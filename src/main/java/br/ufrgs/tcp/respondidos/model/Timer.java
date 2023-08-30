package br.ufrgs.tcp.respondidos.model;

import javafx.animation.PauseTransition;
import javafx.util.Duration;

public class Timer {
    private long startMillis;
    private int duracaoSeg;

    public Timer(int duracaoSeg){
        this.startMillis = System.currentTimeMillis();
        this.duracaoSeg = duracaoSeg;
    }

    public void iniciar(){
        this.startMillis = System.currentTimeMillis();
    }

    public int getTempoRestante(){
        long remainingMillis = System.currentTimeMillis() - this.startMillis;
        int passedSecs = ((int)remainingMillis)/1000;
        int remainingSecs = duracaoSeg - passedSecs;
        if( remainingSecs < 0 ) remainingSecs = 0;
        return remainingSecs;
    }


}
