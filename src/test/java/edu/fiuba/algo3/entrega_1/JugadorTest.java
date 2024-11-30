package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.entidades.*;
import edu.fiuba.algo3.modelo.excepciones.TarotsNoDisponiblesError;
import edu.fiuba.algo3.modelo.entidades.tarots.EfectoCarta;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class JugadorTest {

    @Test
    public void test01JugadorTieneUnTarotYLoAplicaACarta () {
        Jugador jugador = new Jugador("Nombre", new Mazo());
        CartaPoker carta = new CartaPoker(Valor.TRES, Palo.DIAMANTES);
        Tarot unTarot = new EfectoCarta("El Tonto", "Mejora la mano carta mas alta", new Puntaje(3, 3));

        int puntajeEsperado = (3 * 3);
        jugador.aniadirTarots(unTarot);
        jugador.utilizarTarot(unTarot, carta);
        int puntajeObtenido = carta.calcularPuntaje();
        assertEquals(puntajeEsperado,puntajeObtenido);
    }

    @Test
    public void test02JugadorNoPuedeAplicarUnTarotQueNoTiene () {
        Jugador jugador = new Jugador("Nombre", new Mazo());
        CartaPoker carta = new CartaPoker(Valor.TRES, Palo.DIAMANTES);
        Tarot unTarot = new EfectoCarta("El Tonto", "Mejora la mano carta mas alta", new Puntaje(3, 3));
        int puntajeEsperado = carta.calcularPuntaje();

        assertThrows(TarotsNoDisponiblesError.class,   () -> jugador.utilizarTarot(unTarot, carta) );

    }
}