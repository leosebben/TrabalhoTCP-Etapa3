package br.ufrgs.tcp.respondidos;

import javafx.application.Application;
import javafx.stage.Stage;
import br.ufrgs.tcp.respondidos.model.Categoria;
import br.ufrgs.tcp.respondidos.model.Jogador;
import br.ufrgs.tcp.respondidos.model.Leaderboard;
import br.ufrgs.tcp.respondidos.model.Partida;
import br.ufrgs.tcp.respondidos.model.Score;
import br.ufrgs.tcp.respondidos.tela.Janela;

public class Jogo extends Application{
    private Janela janela;
    private Partida partidaAtual;
    private Leaderboard leaderboard;

    @Override
    public void start(Stage stage) throws Exception {
        this.janela = new Janela(this,stage);
        this.partidaAtual = null;
        this.leaderboard = Leaderboard.carregarDoArquivo();

        janela.mudaTela("MenuPrincipal");
    }

    public void iniciaPartida(){
        this.partidaAtual.inicia();
        this.janela.mudaTela("SelecionarCategoria");
    }

    public void sair() {
        try {
            janela.getStage().close();
            this.stop();
        } catch (Exception ex) {
            System.out.println("Impossível sair! " + ex.getMessage());
            ex.printStackTrace();
        }
    }
 

    public void escolherPergunta(Categoria categoria) {
        partidaAtual.escolherPergunta(categoria);
        janela.mudaTela("MostraResposta");
    }

    public void proximoTurno() {
        partidaAtual.proximoTurno();
        if( partidaAtual.acabou() ){
            janela.mudaTela("FinalPartida");
            salvaScores();
        } else {
            janela.mudaTela("SelecionarCategoria");
        }
    }

    public boolean temPartida(){
        return partidaAtual != null;
    }

    public Partida getPartidaAtual(){
        return partidaAtual;
    }

    public void setPartida(Partida partida){
        this.partidaAtual = partida;
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void showHome() {
        janela.mudaTela("MenuPrincipal");
    }

    public void showLeaderboard() {
        // TODO
        janela.mudaTela("Leaderboard");
    }

    public void criarNovaPartida() {
        janela.mudaTela("NovaPartida");
   }

    public void salvaScores(){
        for( Jogador jogador : partidaAtual.getJogadores() ){
            Score score = jogador.getScore();
            leaderboard.addScore(score);
        }
    }


    public Leaderboard getLeaderboard() {
        return leaderboard;
    }

}
