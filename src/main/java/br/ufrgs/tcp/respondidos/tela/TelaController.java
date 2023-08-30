package br.ufrgs.tcp.respondidos.tela;

import java.net.URL;
import java.util.ResourceBundle;

import br.ufrgs.tcp.respondidos.Jogo;
import javafx.fxml.Initializable;

/**
 * Um controller genérico que apenas sabe a informação do Jogo atual.
 * Todos os controllers deste projeto herdam desta classe.
 */
public abstract class TelaController implements Initializable {
    Jogo jogo;

    /* Método vazio para ser herdado por outros controllers;
     * para ter um método de inicialização depois de a classe
     * Janela invocar setJogo, pois o método initialize()
     * acontece antes.
     */
    public void inicia(){

    }

    public void setJogo(Jogo jogo){
        if(jogo == null){
            throw new RuntimeException("SetJogo com jogo NULL!\n");
        }
        this.jogo = jogo;
    }

    public Jogo getJogo(){
        if(jogo == null){
            throw new RuntimeException("GetJogo com jogo NULL!\n");
        }
        return jogo;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
