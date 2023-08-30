package br.ufrgs.tcp.respondidos.tela;

import br.ufrgs.tcp.respondidos.model.Jogador;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class FinalPartidaController extends TelaController {

    @FXML
    Label winnerLabel;

    @Override
    public void inicia() {
        Jogador winner = getJogo().getPartidaAtual().getJogadorAtual();
        this.winnerLabel.setText( "Vencedor: " + winner.getNome() + " (" + winner.getPontuacaoTotal() + ")" );
    }

    @FXML
    public void onHome(){
        getJogo().showHome();
    }

    @FXML
    public void onLeaderboard(){
        getJogo().showLeaderboard();
    }
}
