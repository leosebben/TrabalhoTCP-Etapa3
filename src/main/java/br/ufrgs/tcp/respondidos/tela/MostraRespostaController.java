package br.ufrgs.tcp.respondidos.tela;

import java.util.Random;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.TimerTask;

import br.ufrgs.tcp.respondidos.model.Jogador;
import br.ufrgs.tcp.respondidos.model.Partida;
import br.ufrgs.tcp.respondidos.model.Resposta;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class MostraRespostaController extends TelaController {

    @FXML
    Label jogadorLabel;
    @FXML
    Label pontuacaoLabel;

    @FXML
    Label idRespostaLabel;
    @FXML
    Label respostaLabel;

    @FXML
    Label tempoLabel;

    @FXML
    Button alt1Button;
    @FXML
    Button alt2Button;
    @FXML
    Button alt3Button;
    @FXML
    Button alt4Button;
    @FXML
    Button dica;

    Resposta resposta;
    boolean firstDica = true;
    int numErrada;

    @Override
    public void inicia() {
        Jogador jogador = getJogo().getPartidaAtual().getJogadorAtual();
        this.resposta = getJogo().getPartidaAtual().getRespostaAtual();

        this.idRespostaLabel.setText( "Resposta #"+Integer.toString(resposta.getId()));
        this.respostaLabel.setText(resposta.getResposta());
        
        this.alt1Button.setText(resposta.getPerguntas().get(0));
        this.alt2Button.setText(resposta.getPerguntas().get(1));
        this.alt3Button.setText(resposta.getPerguntas().get(2));
        this.alt4Button.setText(resposta.getPerguntas().get(3));
        
          
        this.dica.setText("dica");
        // this.alt5Button.setText(resposta.getPerguntas().get(4));

        this.pontuacaoLabel.setText( "Pontos: " + jogador.getPontuacaoTotal() );
        this.jogadorLabel.setText( jogador.getNome() );

        updateTimer();
        startTimerAnimation();
    }

    private void startTimerAnimation(){
        final PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setDelay(Duration.millis(100.));
        pause.setOnFinished(event -> {
            updateTimer();
            pause.playFromStart();
        });
        pause.playFromStart();

        // updateTimer();
    }

    public void updateTimer(){
        Partida partida = getJogo().getPartidaAtual();
        tempoLabel.setText("Tempo: " + partida.getTimer().getTempoRestante());
    }

    @FXML
    public void alt1OnClick(){
        onEscolheAlternativa(alt1Button.getText());
    }
    @FXML
    public void alt2OnClick(){
        onEscolheAlternativa(alt2Button.getText());
    }
    @FXML
    public void alt3OnClick(){
        onEscolheAlternativa(alt3Button.getText());
    }
    @FXML
    public void alt4OnClick(){
        onEscolheAlternativa(alt4Button.getText());
    }


    //função adicional para testar alternativas incorretas
    @FXML
    public void dicaOnClick(){
        if(firstDica){
            selecionaAlternativaIncorreta();
        }
    }

    private void selecionaAlternativaIncorreta(){
        Random gerador = new Random();  
        int perguntaErrada = gerador.nextInt(4);     
        while(resposta.getPerguntas().get(perguntaErrada).equals(resposta.getPerguntaCorreta())){
            if(perguntaErrada >= 3) perguntaErrada = 0;
            else perguntaErrada++;
            System.out.println("\n" + resposta.getPerguntas().get(perguntaErrada));
        }  
        // modifica o campo da alternativa errada equivalente a variavel perguntaErrada  
        if(perguntaErrada == 0) alt1Button.setText("ERRADA!");
        else if(perguntaErrada == 1) alt2Button.setText("ERRADA!");
        else if(perguntaErrada == 2) alt3Button.setText("ERRADA!");
        else if(perguntaErrada == 3) alt4Button.setText("ERRADA!");
        firstDica = false;
    }

    private void onEscolheAlternativa(String alternativa){
        Partida partida = getJogo().getPartidaAtual();
        int tempoRestante = partida.getTimer().getTempoRestante();
        int pontos = resposta.calculaPontos( alternativa, tempoRestante  );

        partida.getJogadorAtual().pontua( resposta.getCategoria(), pontos);
        jogo.proximoTurno();
    }

}
