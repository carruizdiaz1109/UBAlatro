package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.entidades.*;
import edu.fiuba.algo3.modelo.entidades.cartas.CartaFactory;
import edu.fiuba.algo3.modelo.entidades.cartas.CartaPoker;
import edu.fiuba.algo3.modelo.entidades.tarots.Tarot;
import edu.fiuba.algo3.modelo.excepciones.TarotsNoDisponiblesError;
import edu.fiuba.algo3.modelo.entidades.tarots.TarotCarta;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class JugadorTest {

//    @Test
//    public void test01JugadorTieneUnTarotYLoAplicaACarta () {
//        Jugador jugador = new Jugador("Nombre", new Mazo());
//
//        CartaPoker carta;
//        ArrayList<CartaPoker> cartas = new ArrayList<>(List.of(
//                carta = CartaFactory.crearCarta(Valor.CINCO, Palo.PICAS),
//                CartaFactory.crearCarta(Valor.TRES, Palo.CORAZONES),
//                CartaFactory.crearCarta(Valor.OCHO, Palo.DIAMANTES),
//                CartaFactory.crearCarta(Valor.DOS, Palo.TREBOLES),
//                CartaFactory.crearCarta(Valor.CUATRO, Palo.PICAS)
//        ));
//        ArrayList<CartaPoker> seleccionadas = new ArrayList<>(List.of(carta));
//        Mano mano = new Mano(cartas);
//        mano.seleccionarCartas(seleccionadas);
//
//        Tarot unTarot = new TarotCarta("El Tonto", "Mejora la mano carta mas alta", new Puntaje(3, 3));
//
//        int puntajeEsperado = (3 * 3);
//        jugador.aniadirTarot(unTarot);
//        jugador.utilizarTarot(unTarot);
//        int puntajeObtenido = carta.calcularPuntaje();
//        assertEquals(puntajeEsperado,puntajeObtenido);
//    }

    @Test
    public void test02JugadorNoPuedeAplicarUnTarotQueNoTiene () {
        Jugador jugador = new Jugador("Nombre", new Mazo());
        CartaPoker carta = CartaFactory.crearCarta(Valor.TRES, Palo.DIAMANTES);
        Tarot unTarot = new TarotCarta("El Tonto", "Mejora la mano carta mas alta", new Puntaje(3, 3));
        int puntajeEsperado = carta.calcularPuntaje();

        assertThrows(TarotsNoDisponiblesError.class,   () -> jugador.utilizarTarot(unTarot) );

    }
}