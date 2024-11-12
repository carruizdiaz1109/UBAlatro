package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class integracionTest {
    @Test
    public void test01VerificarQueUnJugadorPoseaCartasSuficientesParaEmpezarElJuegoEnSuMazo(){
        Jugador jugador = new Jugador("Juan");
        boolean esCantidadSuficienteDeCartas = jugador.esPosibleIniciarRonda();

        assertTrue(esCantidadSuficienteDeCartas);
    }

    @Test
    public void test04VerificarQueAlJugarUnaManoSeApliqueElValorCorrespondiente(){

        List<CartaPoker> cartas = List.of(
                new CartaPoker(3, Palo.PICAS),
                new CartaPoker(7, Palo.CORAZONES),
                new CartaPoker(5, Palo.DIAMANTES)
        );
        Jugada jugada = Jugada.crearJugada(cartas);
        int puntajeObtenido = jugada.calcularPuntaje();
        int puntajeEsperado = 7;

        assertEquals(puntajeEsperado, puntajeObtenido);
    }

    @Test
    public void test05JugadorAplicaTarotAUnaCartaYSeLeModificaElValor(){
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
    public void test06JugadorAplicaTarotAUnaCartaYSeLeModificaElMultiplicador(){
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
}
