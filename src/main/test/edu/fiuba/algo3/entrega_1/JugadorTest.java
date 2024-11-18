package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.*;
import edu.fiuba.algo3.jugadas.CartaAlta;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class JugadorTest {

    @Test
    public void test01JugadorTieneUnTarotYLoAplicaACarta () {
        Jugador jugador = new Jugador("Nombre", new Mazo());
        CartaPoker carta = new CartaPoker(Valor.TRES, Palo.DIAMANTES);
        Tarot unTarot = new Tarot(3,3);
        int puntajeEsperado = (carta.calcularPuntaje() +3)*3;
        jugador.aniadirTarots(unTarot);
        jugador.utilizarTarot(unTarot, carta);
        int puntajeObtenido = carta.calcularPuntaje();
        assertEquals(puntajeEsperado,puntajeObtenido);
    }

    @Test
    public void test02JugadorNoPuedeAplicarUnTarotQueNoTiene () {
        Jugador jugador = new Jugador("Nombre", new Mazo());
        CartaPoker carta = new CartaPoker(Valor.TRES, Palo.DIAMANTES);
        Tarot unTarot = new Tarot(3,3);
        int puntajeEsperado = carta.calcularPuntaje();

        assertThrows(TarotsNoDisponiblesError.class,   () -> jugador.utilizarTarot(unTarot, carta) );

    }
}