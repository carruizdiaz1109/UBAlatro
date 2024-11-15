package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.*;
import edu.fiuba.algo3.jugadas.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class integracionTest {
    @Test
    public void test01VerificarQueUnJugadorPoseaCartasSuficientesParaEmpezarElJuegoEnSuMazo(){
        ArrayList<CartaPoker> cartasEsperadas = new ArrayList<CartaPoker>(List.of(
                new CartaPoker(3, Palo.PICAS),
                new CartaPoker(7, Palo.CORAZONES),
                new CartaPoker(5, Palo.DIAMANTES),
                new CartaPoker(10, Palo.PICAS),
                new CartaPoker(1, Palo.PICAS),
                new CartaPoker(2, Palo.CORAZONES),
                new CartaPoker(7, Palo.DIAMANTES),
                new CartaPoker(10, Palo.CORAZONES)
        ));
        Mano manoEsperada = new Mano(cartasEsperadas);

        ArrayList<CartaPoker> cartas = new ArrayList<CartaPoker>(List.of(
                new CartaPoker(3, Palo.PICAS),
                new CartaPoker(7, Palo.CORAZONES),
                new CartaPoker(5, Palo.DIAMANTES),
                new CartaPoker(10, Palo.PICAS),
                new CartaPoker(1, Palo.PICAS),
                new CartaPoker(2, Palo.CORAZONES),
                new CartaPoker(7, Palo.DIAMANTES),
                new CartaPoker(10, Palo.CORAZONES)
        ));

        Mazo mazo = new Mazo(cartas);
        Mano manoObtenida = mazo.repartir();

        assert (manoObtenida.compararManoCon(manoEsperada));
    }


    @Test
    public void test02SeVerificaQueAUnJugadorSeLeReparten8CartasDeSuMazo(){
        Jugador jugador = new Jugador("Roberto");
        Ronda ronda = new Ronda(jugador);

        ronda.iniciarRonda();
        boolean esManoLlena = jugador.esManoLlena();

        assertTrue(esManoLlena);
    }

    @Test
    public void test03SeVerificaQueSePuedaJugarUnaManoDeUnMazo(){
        Jugador jugador = new Jugador("Javier");
        Mazo mazo = new Mazo();
        Mano mano = mazo.repartir();
        jugador.setMano(mano);
        Jugada jugada = jugador.jugar();

        assertTrue(jugada instanceof Jugada);
    }

    @Test
    public void test04VerificarQueAlJugarUnaManoSeApliqueElValorCorrespondiente(){

        ArrayList<CartaPoker> cartas = new ArrayList<>(List.of(
                new CartaPoker(3, Palo.PICAS),
                new CartaPoker(7, Palo.CORAZONES),
                new CartaPoker(5, Palo.DIAMANTES)
        ));
        Jugada jugada = Jugada.crearJugada(cartas);
        int puntajeObtenido = jugada.calcularPuntaje();
        int puntajeEsperado = 12;

        assertEquals(puntajeEsperado, puntajeObtenido);
    }

    @Test
    public void test05SeRespetaElOrdenDePrioridadDeLasManosDePoker(){
        List<CartaPoker> cartas = List.of(
            new CartaPoker(10, Palo.PICAS),
            new CartaPoker(10, Palo.TREBOLES),
            new CartaPoker(10, Palo.CORAZONES),
            new CartaPoker(5, Palo.DIAMANTES),
            new CartaPoker(5, Palo.PICAS)
        );

        Jugada jugada = Jugada.crearJugada(cartas);

        assertFalse(jugada instanceof Trio);
        assertFalse(jugada instanceof Par);
        assertTrue(jugada instanceof FullHouse);
    }

    @Test
    public void test06JugadorAplicaTarotAUnaCartaYSeLeModificaElValor(){
        Jugador jugador = new Jugador("Esteban");
        CartaPoker cartaPoker = new CartaPoker(2, Palo.PICAS);
        Puntaje puntaje = new Puntaje(10,1);
        Tarot cartaTarot = new Tarot(puntaje);
        jugador.aniadirTarots(cartaTarot);
        int puntajeEsperado = 12;

        jugador.utilizarTarot(0, cartaPoker);
        int puntajeObtenido = cartaPoker.calcularPuntaje();

        assertEquals(puntajeEsperado, puntajeObtenido);
    }

    @Test
    public void test07JugadorAplicaTarotAUnaCartaYSeLeModificaElMultiplicador(){
        Jugador jugador = new Jugador("Lucia");
        CartaPoker cartaPoker = new CartaPoker(10, Palo.CORAZONES);
        Puntaje puntaje1 = new Puntaje(0,6);
        Tarot cartaTarot1 = new Tarot(puntaje1);
        Puntaje puntaje2 = new Puntaje(10,1);
        Tarot cartaTarot2 = new Tarot(puntaje2);
        jugador.aniadirTarots(cartaTarot2);
        jugador.aniadirTarots(cartaTarot1);

        int puntajeEsperado = 60;

        jugador.utilizarTarot(1, cartaPoker);
        int puntajeObtenido = cartaPoker.calcularPuntaje();

        assertEquals(puntajeEsperado, puntajeObtenido);
    }

    @Test
    public void test08(){
        Jugador jugador = new Jugador("Lucia");
        Ronda ronda = new Ronda(jugador);
        ronda.iniciarRonda();
        jugador.jugar();
        ronda.verificarPuntaje();
    }
}
