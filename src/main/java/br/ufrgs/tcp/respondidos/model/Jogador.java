package br.ufrgs.tcp.respondidos.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Jogador {
    String nome;
    Map<Categoria, Integer> pontuacao;

    public Jogador(String nome) {
        this.nome = nome;
        this.pontuacao = new HashMap<Categoria, Integer>();

        for( Categoria categoria : Categoria.getAll()) {
            pontuacao.put(categoria, 0);
        }
    }

    public Jogador(String nome, Map<Categoria, Integer> pontuacao) {
        this.nome = nome;
        this.pontuacao = pontuacao;

    }

    public void pontua(Categoria cat, int pontos){
        int novosPontos = pontos + pontuacao.get(cat);
        pontuacao.put(cat, novosPontos);
    }

    public String getNome() {
        return nome;
    }

    public Map<Categoria, Integer> getPontuacao() {
        return pontuacao;
    }

    public int getPontuacao(Categoria categoria) {
        return pontuacao.get(categoria);
    }

    public boolean jaPontuouCategoria(Categoria cat){
        return  pontuacao.get(cat) > 0 ;
    }

    public boolean jaGanhou(){
        boolean ganhou = true;
        for(Categoria categoria : Categoria.getAll()){
            ganhou &= pontuacao.get(categoria) > 0 ;
        }
        return ganhou;
    }

    public int getPontuacaoTotal() {
        int pontos = 0;

        for(Categoria categoria : Categoria.getAll()){
            pontos += pontuacao.get(categoria);
        }

        return pontos;
    }

    public Score getScore() {
        Score score = new Score(getNome(), getPontuacaoTotal(), new Date(0));
        return score;
    }

}
