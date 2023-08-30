package br.ufrgs.tcp.respondidos.tela;

import java.io.IOException;

import br.ufrgs.tcp.respondidos.Jogo;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * A janela em que o jogo ocorre.
 *
 * A scene do stage é carregada de arquivos diferentes
 * a cada vez que mudaTela() é chamada.
 */
public class Janela {
    private Stage stage;
    private Jogo jogo;

    public Janela(Jogo jogo, Stage stage) throws IOException{
        this.jogo = jogo;
        this.stage = stage;

        stage.setTitle("Respondidos Quiz");
        stage.show();
    }

    /* Tenta carregar uma tela, salvar nela qual é o Jogo, e mostrar ela para o usuário */
    public void mudaTela(String nomeTela){
        try {
            String nomeArquivo = "tela/" +  nomeTela + ".fxml";

            FXMLLoader loader = new FXMLLoader(Jogo.class.getResource(nomeArquivo));

            Scene scene = new Scene(loader.load());
            Object controllerObj = loader.getController();

            if( controllerObj == null || !(controllerObj instanceof TelaController ) ){
                System.out.println("ERRO: Seu controller não herda de TelaController!");
                System.out.println("Saindo agora.");
                this.jogo.sair();
                return;
            }

            TelaController controller = (TelaController) controllerObj;

            controller.setJogo( this.jogo );
            controller.inicia();
            stage.setScene(scene);
        } catch ( IOException ex ){
            System.out.println(" Inicialização de Tela Incorreta! ");
            System.out.println(ex);
            System.out.println( ex.getMessage() );
            ex.printStackTrace();
        }
    }

    public Stage getStage(){
        return this.stage;
    }

}
