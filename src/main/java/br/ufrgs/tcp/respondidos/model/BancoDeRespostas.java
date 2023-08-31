package br.ufrgs.tcp.respondidos.model;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class BancoDeRespostas {
    private static final String NOME_D0_ARQUIVO = "/data/banco.json";
    private Map<Categoria, List<Resposta>> respostas;

    public Resposta sortearResposta(Categoria c){
        Resposta resp = new Resposta(0, NOME_D0_ARQUIVO, null, NOME_D0_ARQUIVO, c);

        return resp;
    }

    public static BancoDeRespostas carregarDoArquivo(){

        InputStream stream = BancoDeRespostas.class.getResourceAsStream(NOME_D0_ARQUIVO);

        if( stream == null ) {
            throw new RuntimeException("Arquivo do Banco de Respostas não encontrado!");
        }

        InputStreamReader streamReader = new InputStreamReader(stream, Charset.forName("UTF-8"));

        JSONArray questionList = (JSONArray) JSONValue.parse( streamReader );

        return carregarDoJson(questionList);
    }

    private BancoDeRespostas(){
        this.respostas = new TreeMap<>();

        for( Categoria cat : Categoria.getAll()){
            this.respostas.put(cat, new ArrayList<>());
        }
    }

    private void shuffle(){
        for( Categoria cat : Categoria.getAll()){
            Collections.shuffle(this.respostas.get(cat));
        }
    }

    public static BancoDeRespostas carregarDoJson( JSONArray jsonList ){
        BancoDeRespostas banco = new BancoDeRespostas();

        for( Object respostaJsonObj : jsonList ){
            JSONObject respostaJson = (JSONObject) respostaJsonObj;
            Resposta resposta = carregaRespostaJson(respostaJson);
            banco.respostas.get(resposta.getCategoria()).add(resposta);
        }

        banco.shuffle();
        return banco;

    }


    public static Resposta carregaRespostaJson(JSONObject respostaJson){

        int id = ((Long)respostaJson.get("id")).intValue();
        String enunciado = (String)respostaJson.get("resposta");
        String perguntaCorreta = (String) respostaJson.get("perguntaCorreta");
        String categoriaString = (String) respostaJson.get("categoria");
        ArrayList<String> alternativas = new ArrayList<>();

        Categoria categoria = Categoria.getFromString(categoriaString);

        JSONArray alternativasList = (JSONArray) respostaJson.get("perguntas");

        for( Object alternativaJsonObj : alternativasList ){
            alternativas.add( (String)alternativaJsonObj );
        }

        Resposta resposta = new Resposta(id, enunciado, alternativas, perguntaCorreta, categoria);

        return resposta;
    }

    public Resposta novaDaCategoria(Categoria categoria) {
        // Pegamos a última e removemos para não ser sorteada de novo.
        int tamCategoria = respostas.get(categoria).size();
        Resposta ret = respostas.get(categoria).get( tamCategoria - 1 );
        respostas.get(categoria).remove(tamCategoria - 1);

        return ret;
    }
}
