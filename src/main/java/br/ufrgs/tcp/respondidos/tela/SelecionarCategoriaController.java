package br.ufrgs.tcp.respondidos.tela;

import java.net.URL;
import java.util.ResourceBundle;

import br.ufrgs.tcp.respondidos.Jogo;
import br.ufrgs.tcp.respondidos.model.Categoria;
import br.ufrgs.tcp.respondidos.model.Jogador;
import br.ufrgs.tcp.respondidos.model.Partida;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class SelecionarCategoriaController extends TelaController {

    @FXML
    Label jogadorLabel;
    @FXML
    Label pontosLabel;

    @FXML
    Button historiaButton;
    @FXML
    Button matematicaButton;
    @FXML
    Button esporteButton;
    @FXML
    Button entretenimentoButton;

    @Override
    public void inicia(){
        Partida partida = getJogo().getPartidaAtual();
        Jogador jogador = partida.getJogadorAtual();

        jogadorLabel.setText(jogador.getNome());
        pontosLabel.setText("Pontos: " + jogador.getPontuacaoTotal());

        if( jogador.getPontuacao(Categoria.Entretenimento) > 0 ) entretenimentoButton.setDisable(true);
        if( jogador.getPontuacao(Categoria.Matematica) > 0 ) matematicaButton.setDisable(true);
        if( jogador.getPontuacao(Categoria.Historia) > 0 ) historiaButton.setDisable(true);
        if( jogador.getPontuacao(Categoria.Esporte) > 0 ) esporteButton.setDisable(true);
    }

    @FXML
    private void onMatematica(){
        escolhe(Categoria.Matematica);
    }

    @FXML
    private void onEsporte(){
        escolhe(Categoria.Esporte);
    }

    @FXML
    private void onHistoria(){
        escolhe(Categoria.Historia);
    }

    @FXML
    private void onEntretenimento(){
        escolhe(Categoria.Entretenimento);
    }

    private void escolhe(Categoria categoria){
        getJogo().escolherPergunta(categoria);
    }

}
