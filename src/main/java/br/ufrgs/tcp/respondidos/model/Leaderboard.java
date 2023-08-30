package br.ufrgs.tcp.respondidos.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class Leaderboard {
    private static final String NOME_D0_ARQUIVO = "score.json";
    private List<Score> scores;

    public static Leaderboard carregarDoArquivo(){
        try {
            InputStream stream = new FileInputStream(NOME_D0_ARQUIVO);
            InputStreamReader streamReader = new InputStreamReader(stream, Charset.forName("UTF-8"));
            JSONArray leaderboardJson = (JSONArray) JSONValue.parse( streamReader );
            Leaderboard leaderboard = carregarDoJson( leaderboardJson );

            return leaderboard;

        } catch ( FileNotFoundException ex ) {
            System.out.println("Arquivo de Leaderboard não encontrado.");
            return new Leaderboard();
        }
    }

    public static Leaderboard carregarDoJson( JSONArray jsonList ){
        Leaderboard leaderboard = new Leaderboard();
        for(Object scoreObj : jsonList ){
            JSONObject scoreJson = (JSONObject) scoreObj;
            String player = (String) scoreJson.get("nome");
            int pontos = ((Long)scoreJson.get("pontos")).intValue();
            Date data = new Date(0);
            Score score = new Score(player, pontos, data);
            leaderboard.scores.add(score);
        }
        return leaderboard;
    }


    public List<Score> getScores(){
        return scores;
    }

    public void addScore(Score score){
        this.scores.add(score);
        this.scores.sort((a, b) -> {
            if( a.getPontos() < b.getPontos() ) return 1;
            if( a.getPontos() > b.getPontos() ) return -1;
            return 0;
        });

        this.save();
    }

    public void ordena(){
        this.scores.sort((a, b) -> {
            if( a.getPontos() < b.getPontos() ) return 1;
            if( a.getPontos() > b.getPontos() ) return -1;
            return 0;
        });
    }

    public void save(){
        try {
            OutputStream stream = new FileOutputStream(NOME_D0_ARQUIVO);

            JSONArray array = criarJson();
            String string = JSONValue.toJSONString(array);
            stream.write( string.getBytes("UTF-8") );
        } catch ( FileNotFoundException ex ) {
            System.out.println("Arquivo de Leaderboard não encontrado.");
        }  catch ( IOException ex ) {
            System.out.println("Não foi possível escrever no arquivo de Leaderboard!");
        }
    }

    private JSONArray criarJson(){
        JSONArray array = new JSONArray();

        for( Score sc : scores ){
            JSONObject object = new JSONObject();
            object.put("nome", sc.getNomeJogador());
            object.put("pontos", sc.getPontos());
            array.add(object);
        }
        return array;
    }

    private Leaderboard(){
        this.scores = new ArrayList<Score>();
    }
}