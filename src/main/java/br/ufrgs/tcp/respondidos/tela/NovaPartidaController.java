package br.ufrgs.tcp.respondidos.tela;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import br.ufrgs.tcp.respondidos.Jogo;
import br.ufrgs.tcp.respondidos.model.Jogador;
import br.ufrgs.tcp.respondidos.model.Partida;

public class NovaPartidaController extends TelaController  {

    @FXML
    TextField playerNameField;
    @FXML
    ListView<String> addedPlayersView;

    ObservableList<String> addedPlayersList;

    @Override
    public void inicia() {
        addedPlayersList = FXCollections.observableArrayList(new ArrayList<String>());
        addedPlayersView.setItems(addedPlayersList);
    }

    @FXML
    private void onAddPlayerClicked(){
        if( isPlayerNameValid() ){
            addedPlayersList.add( playerNameField.getText().trim() );
            playerNameField.setText("");
        }
    }

    @FXML
    private void onStartGameClicked(){
        if( isPlayerListValid() ){
            ArrayList<Jogador> jogadores = new ArrayList<Jogador>();

            for( String playerName : addedPlayersList ){
                jogadores.add(new Jogador(playerName));
            }
            //(List<Jogador> jogadores, int turno, Timer timer, Resposta respAtual) {
            Partida p = new Partida( jogadores );
            getJogo().setPartida(p);
            getJogo().iniciaPartida();
        }
    }

    private boolean isPlayerListValid(){
        return addedPlayersList.size() >= 2;
    }

    private boolean isPlayerNameValid(){
        String playerName = playerNameField.getText().trim();
        return !playerName.isEmpty()
            && !addedPlayersList.contains(playerName);
    }

}