package br.ufrgs.tcp.respondidos;

import org.junit.Test;

import br.ufrgs.tcp.respondidos.model.BancoDeRespostas;
import br.ufrgs.tcp.respondidos.model.Categoria;
import br.ufrgs.tcp.respondidos.model.Resposta;

import static org.junit.Assert.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class BancoTest {

    @Test
    public void testLoadFromFile_ShouldLoad() {
        BancoDeRespostas banco = BancoDeRespostas.carregarDoArquivo();
        assertNotNull( banco );
    }

    @Test
    public void testCarregaResposta_StoresValues(){
        JSONObject respostaJson = makeRespostaDemo();
        Resposta resposta = BancoDeRespostas.carregaRespostaJson(respostaJson);

        assertEquals(5, resposta.getId());
        assertEquals("lorem ipsum", resposta.getResposta());
        assertEquals( "b", resposta.getPerguntaCorreta());
    }

    @Test
    public void testCarregaResposta_AcertaCategoria(){
        JSONObject jsonHist = makeRespostaDemo();
        JSONObject jsonMix = makeRespostaDemo();
        JSONObject jsonEnt = makeRespostaDemo();

        jsonHist.put("categoria", "Historia");
        jsonMix.put("categoria", "Matematica");
        jsonEnt.put("categoria", "Entretenimento");

        Resposta respHist = BancoDeRespostas.carregaRespostaJson(jsonHist);
        Resposta respMix = BancoDeRespostas.carregaRespostaJson(jsonMix);
        Resposta respEnt = BancoDeRespostas.carregaRespostaJson(jsonEnt);

        assertEquals(Categoria.Historia,  respHist.getCategoria() );
        assertEquals(Categoria.Matematica, respMix.getCategoria() );
        assertEquals(Categoria.Entretenimento, respEnt.getCategoria());
    }


    @Test
    public void testNovaDaCategoria_Muda(){
        JSONObject jsonHist1 = makeRespostaDemo();
        JSONObject jsonHist2 = makeRespostaDemo();

        jsonHist1.put("categoria", "Historia");
        jsonHist2.put("categoria", "Historia");

        jsonHist1.put("resposta", "abc");
        jsonHist1.put("resposta", "xyz");

        JSONArray lista = new JSONArray();
        lista.add(jsonHist1); lista.add(jsonHist2);

        BancoDeRespostas banco = BancoDeRespostas.carregarDoJson(lista);

        Resposta sort1 = banco.novaDaCategoria(Categoria.Historia);
        Resposta sort2 = banco.novaDaCategoria(Categoria.Historia);

        assertNotEquals(sort1.getResposta(), sort2.getResposta());
    }

    private static JSONObject makeRespostaDemo(){
        JSONObject resposta = new JSONObject();

        resposta.put("id", 5L);
        resposta.put("resposta", "lorem ipsum");
        resposta.put("categoria", "Historia");

        JSONArray alternativas = new JSONArray();

        alternativas.add("a");
        alternativas.add("b");
        alternativas.add("c");
        alternativas.add("d");

        resposta.put("perguntas", alternativas);
        resposta.put("perguntaCorreta", "b");

        return resposta;
    }
}
