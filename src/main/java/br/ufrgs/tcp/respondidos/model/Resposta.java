package br.ufrgs.tcp.respondidos.model;
import java.util.List;

public class Resposta {
    private int id;
    private String resposta;
    private List<String> perguntas;
    private String perguntaCorreta;
    private Categoria categoria;



    public Resposta(int id, String resposta, List<String> perguntas, String perguntaCorreta, Categoria categoria) {
        this.id = id;
        this.resposta = resposta;
        this.perguntas = perguntas;
        this.perguntaCorreta = perguntaCorreta;
        this.categoria = categoria;
    }

    public void carregaProxPergunta(int id, String resposta, List<String> perguntas, String perguntaCorreta, Categoria categoria){
        setId(id);
        setResposta(resposta);
        setPerguntas(perguntas);
        setPerguntaCorreta(perguntaCorreta);
        setCategoria(categoria);
    }

    public boolean check(String s){
        if(perguntaCorreta.equals(s)){
            return true;
        }else{
            return false;
        }
    }

    public String getResposta() {
        return resposta;
    }
    private void setResposta(String resposta) {
        this.resposta = resposta;
    }
    public List<String> getPerguntas() {
        return perguntas;
    }
    private void setPerguntas(List<String> perguntas) {
        this.perguntas = perguntas;
    }
    public String getPerguntaCorreta() {
        return perguntaCorreta;
    }
    private void setPerguntaCorreta(String perguntaCorreta) {
        this.perguntaCorreta = perguntaCorreta;
    }
    public Categoria getCategoria() {
        return categoria;
    }
    private void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public int calculaPontos(String alternativa, int tempoRestanteResposta){
        if( !alternativa.equals(perguntaCorreta) ) return 0;

        if( tempoRestanteResposta == 0 ) return 10; // minimo 10
        else return 10 + tempoRestanteResposta * 3; // maximo 100
    }

}
