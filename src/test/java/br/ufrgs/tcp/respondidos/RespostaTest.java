package br.ufrgs.tcp.respondidos;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import br.ufrgs.tcp.respondidos.model.Categoria;
import br.ufrgs.tcp.respondidos.model.Resposta;

public class RespostaTest {
    @Test
    public void testCalculaPontos_pontua(){
        List<String> alternativas = new ArrayList<String>();
        alternativas.add("A");        alternativas.add("C");
        alternativas.add("B");        alternativas.add("D");

        Resposta resposta = new Resposta(1, "pergunta C", alternativas, "C", Categoria.Esporte);

        assertEquals(0, resposta.calculaPontos("B", 25 ));
        assertEquals(0, resposta.calculaPontos("B", 10 ));
        assertEquals(0, resposta.calculaPontos("B", 0 ));

        assertEquals(100, resposta.calculaPontos("C", 30 ));
        assertEquals(10, resposta.calculaPontos("C", 0 ));
    }
}
