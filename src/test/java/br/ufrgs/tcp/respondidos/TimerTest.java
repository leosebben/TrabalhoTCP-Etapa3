package br.ufrgs.tcp.respondidos;

import static org.junit.Assert.*;

import org.junit.Test;

import br.ufrgs.tcp.respondidos.model.Timer;

public class TimerTest {

    @Test
    public void testTimer_timeMoves(){
        try {
            Timer timer = new Timer(5);

            assertTrue(4 <= timer.getTempoRestante());
            Thread.sleep(2050); // mais de 2s
            assertTrue(3 >= timer.getTempoRestante());

        } catch( InterruptedException ex ){
            assertFalse( true );
        }
    }
}
