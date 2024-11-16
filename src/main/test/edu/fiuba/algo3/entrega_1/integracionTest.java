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
                new CartaPoker(3, Palo.PICAS)));

        ArrayList<CartaPoker> cartas = new ArrayList<CartaPoker>(List.of(
                new CartaPoker(3, Palo.PICAS)));

        Mazo mazo = new Mazo(cartas);
        Mazo mazoEsperado = new Mazo(cartasEsperadas);

        assert (mazo.compararCon(mazoEsperado));
    }

    @Test
    public void test02SeVerificaQueAUnJugadorSeLeReparten8CartasDeSuMazo(){
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
        Mano mano = new Mano();
        mano.rellenarse(mazo);

        assert (mano.compararCon(manoEsperada));
    }

    @Test
    public void test03SeVerificaQueSePuedaJugarUnaManoDeUnMazo() {

        ArrayList<CartaPoker> cartasEsperadas = new ArrayList<CartaPoker>(List.of(
                new CartaPoker(7, Palo.CORAZONES),
                new CartaPoker(10, Palo.PICAS),
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
        Mano mano = new Mano();
        mano.rellenarse(mazo);

        CartaPoker cartaSeleccionada1 = mano.getCartas().get(1);
        CartaPoker cartaSeleccionada2 = mano.getCartas().get(3);
        CartaPoker cartaSeleccionada3 = mano.getCartas().get(6);
        CartaPoker cartaSeleccionada4 = mano.getCartas().get(7);

        mano.seleccionarCarta(cartaSeleccionada1);
        mano.seleccionarCarta(cartaSeleccionada2);
        mano.seleccionarCarta(cartaSeleccionada3);
        mano.seleccionarCarta(cartaSeleccionada4);

        assert(mano.compararSeleccionadasCon(cartasEsperadas));
    }

    @Test
    public void test04VerificarQueAlJugarUnaManoSeApliqueElValorCorrespondiente(){
        Puntaje puntajeEsperado = new Puntaje(12, 1);

        ArrayList<CartaPoker> cartas = new ArrayList<>(List.of(
                new CartaPoker(3, Palo.PICAS),
                new CartaPoker(7, Palo.CORAZONES),
                new CartaPoker(5, Palo.DIAMANTES)
        ));
        Jugada jugada = Jugada.crearJugada(cartas);

        Puntaje puntajeObtenido = jugada.calcularPuntaje();

        assert(puntajeEsperado.compararPuntajecon(puntajeObtenido));
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
        Puntaje puntajeEsperado = new Puntaje(12, 1);

        CartaPoker cartaPoker = new CartaPoker(2, Palo.PICAS);
        Puntaje puntaje = new Puntaje(10,1);
        Tarot cartaTarot = new Tarot(puntaje);

        cartaPoker.activarTarot(cartaTarot);

        int puntajeObtenido = cartaPoker.calcularPuntaje();

        assert(puntajeEsperado.compararPuntajecon(puntajeObtenido));
    }

    @Test
    public void test07JugadorAplicaTarotAUnaCartaYSeLeModificaElMultiplicador(){
        Puntaje puntajeEsperado = new Puntaje(60, 1);

        CartaPoker cartaPoker = new CartaPoker(10, Palo.CORAZONES);

        Puntaje puntaje1 = new Puntaje(0,6);
        Tarot cartaTarot1 = new Tarot(puntaje1);

        cartaPoker.activarTarot(cartaTarot1);

        int puntajeObtenido = cartaPoker.calcularPuntaje();

        assert(puntajeEsperado.compararPuntajecon(puntajeObtenido));
    }


}
