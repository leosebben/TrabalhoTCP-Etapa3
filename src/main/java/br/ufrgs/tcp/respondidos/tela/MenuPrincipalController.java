package br.ufrgs.tcp.respondidos.tela;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;

public class MenuPrincipalController extends TelaController {

    @Override
    public void inicia() {

    }

    @FXML
    private void onQuitButtonClicked(){
        getJogo().sair();
    }

    @FXML
    private void onNewGameButtonClicked(){
        getJogo().criarNovaPartida();
    }

    @FXML
    private void onLeaderboardButtonClicked(){
        getJogo().showLeaderboard();
    }
}
