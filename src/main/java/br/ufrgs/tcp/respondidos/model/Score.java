package br.ufrgs.tcp.respondidos.model;
import java.util.Date;

public class Score {
    private String nomeJogador;
    private int pontos;
    private Date data;

    public Score(String nomeJogador, int pontos, Date data) {
        this.nomeJogador = nomeJogador;
        this.pontos = pontos;
        this.data = data;
    }

    public String getNomeJogador() {
        return nomeJogador;
    }

    public int getPontos() {
        return pontos;
    }

    public Date getData() {
        return data;
    }


}


