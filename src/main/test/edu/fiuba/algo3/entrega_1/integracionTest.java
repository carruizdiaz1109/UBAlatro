package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class integracionTest {
    @Test
    public void test05JugadorAplicaTarotAUnaCartaYSeLeModificaPuntaje(){
        Jugador jugador = new Jugador("Esteban");
        CartaPoker cartaPoker = new CartaPoker(2, Palo.PICAS);
        Puntaje puntaje = new Puntaje(10,0);
        Tarot cartaTarot = new Tarot(puntaje);
        jugador.aniadirTarots(cartaTarot);
        int puntajeEsperado = 12;

        jugador.utilizarTarot(0, cartaPoker);
        int puntajeObtenido = cartaPoker.calcularPuntaje();

        assertEquals(puntajeEsperado, puntajeObtenido);
    }
}
