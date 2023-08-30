package br.ufrgs.tcp.respondidos.tela;

import java.util.ArrayList;
import java.util.List;

import br.ufrgs.tcp.respondidos.model.Score;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class LeaderboardController extends TelaController {

    @FXML
    ListView leaderboardView;

    @Override
    public void inicia(){

        getJogo().getLeaderboard().ordena();
        List<Score> scores = getJogo().getLeaderboard().getScores();
        List<String> scoreStrings = new ArrayList<String>();

        for( Score score : scores ){
            scoreStrings.add( score.getNomeJogador() + " (" + score.getPontos() + ")");
        }

        leaderboardView.setItems( FXCollections.observableArrayList( scoreStrings ));
        leaderboardView.refresh();
    }

    @FXML
    public void onBackClicked(){
        getJogo().showHome();
    }
}
