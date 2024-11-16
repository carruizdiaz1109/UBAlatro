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
                new CartaPoker(Valor.TRES, Palo.PICAS)));

        ArrayList<CartaPoker> cartas = new ArrayList<CartaPoker>(List.of(
                new CartaPoker(Valor.TRES, Palo.PICAS)));

        Mazo mazo = new Mazo(cartas);
        Mazo mazoEsperado = new Mazo(cartasEsperadas);

        assert (mazo.compararCon(mazoEsperado));
    }

    @Test
    public void test02SeVerificaQueAUnJugadorSeLeReparten8CartasDeSuMazo(){
        ArrayList<CartaPoker> cartasEsperadas = new ArrayList<CartaPoker>(List.of(
                new CartaPoker(Valor.TRES, Palo.PICAS),
                new CartaPoker(Valor.SIETE, Palo.CORAZONES),
                new CartaPoker(Valor.CINCO, Palo.DIAMANTES),
                new CartaPoker(Valor.REY, Palo.PICAS),
                new CartaPoker(Valor.AS, Palo.PICAS),
                new CartaPoker(Valor.DOS, Palo.CORAZONES),
                new CartaPoker(Valor.SIETE, Palo.DIAMANTES),
                new CartaPoker(Valor.REY, Palo.CORAZONES)
        ));
        Mano manoEsperada = new Mano(cartasEsperadas);

        ArrayList<CartaPoker> cartas = new ArrayList<CartaPoker>(List.of(
                new CartaPoker(Valor.TRES, Palo.PICAS),
                new CartaPoker(Valor.SIETE, Palo.CORAZONES),
                new CartaPoker(Valor.CINCO, Palo.DIAMANTES),
                new CartaPoker(Valor.REY, Palo.PICAS),
                new CartaPoker(Valor.AS, Palo.PICAS),
                new CartaPoker(Valor.DOS, Palo.CORAZONES),
                new CartaPoker(Valor.SIETE, Palo.DIAMANTES),
                new CartaPoker(Valor.REY, Palo.CORAZONES)
        ));
        Mazo mazo = new Mazo(cartas);
        Mano mano = new Mano();
        mano.rellenarse(mazo);

        assert (mano.compararCon(manoEsperada));
    }

    @Test
    public void test03SeVerificaQueSePuedaJugarUnaManoDeUnMazo() {

        ArrayList<CartaPoker> cartasEsperadas = new ArrayList<CartaPoker>(List.of(
                new CartaPoker(Valor.SIETE, Palo.CORAZONES),
                new CartaPoker(Valor.DIEZ, Palo.PICAS),
                new CartaPoker(Valor.SIETE, Palo.DIAMANTES),
                new CartaPoker(Valor.DIEZ, Palo.CORAZONES)
        ));
        Mano manoEsperada = new Mano(cartasEsperadas);

        ArrayList<CartaPoker> cartas = new ArrayList<CartaPoker>(List.of(
                new CartaPoker(Valor.TRES, Palo.PICAS),
                new CartaPoker(Valor.SIETE, Palo.CORAZONES),
                new CartaPoker(Valor.CINCO, Palo.DIAMANTES),
                new CartaPoker(Valor.REY, Palo.PICAS),
                new CartaPoker(Valor.AS, Palo.PICAS),
                new CartaPoker(Valor.DOS, Palo.CORAZONES),
                new CartaPoker(Valor.SIETE, Palo.DIAMANTES),
                new CartaPoker(Valor.REY, Palo.CORAZONES)
        ));
        Mazo mazo = new Mazo(cartas);
        Mano mano = new Mano();
        mano.rellenarse(mazo);

        mano.seleccionarCarta(new CartaPoker(Valor.SIETE, Palo.CORAZONES));
        mano.seleccionarCarta(new CartaPoker(Valor.DIEZ, Palo.PICAS));
        mano.seleccionarCarta(new CartaPoker(Valor.SIETE, Palo.DIAMANTES));
        mano.seleccionarCarta(new CartaPoker(Valor.DIEZ, Palo.CORAZONES));

        assert(mano.compararSeleccionadasCon(cartasEsperadas));
    }

    @Test
    public void test04VerificarQueAlJugarUnaManoSeApliqueElValorCorrespondiente(){
        Puntaje puntajeEsperado = new Puntaje(12, 1);

        ArrayList<CartaPoker> cartas = new ArrayList<>(List.of(
                new CartaPoker(Valor.TRES, Palo.PICAS),
                new CartaPoker(Valor.SIETE, Palo.CORAZONES),
                new CartaPoker(Valor.CINCO, Palo.DIAMANTES)
        ));
        Jugada jugada = Jugada.crearJugada(cartas);

        Puntaje puntajeObtenido = jugada.calcularPuntaje();

        assert(puntajeEsperado.compararPuntajecon(puntajeObtenido));
    }

    @Test
    public void test05SeRespetaElOrdenDePrioridadDeLasManosDePoker(){
        List<CartaPoker> cartas = List.of(
            new CartaPoker(Valor.DIEZ, Palo.PICAS),
            new CartaPoker(Valor.DIEZ, Palo.TREBOLES),
            new CartaPoker(Valor.DIEZ, Palo.CORAZONES),
            new CartaPoker(Valor.CINCO, Palo.DIAMANTES),
            new CartaPoker(Valor.CINCO, Palo.PICAS)
        );

        Jugada jugada = Jugada.crearJugada(cartas);

        assertFalse(jugada instanceof Trio);
        assertFalse(jugada instanceof Par);
        assertTrue(jugada instanceof FullHouse);
    }

    @Test
    public void test06JugadorAplicaTarotAUnaCartaYSeLeModificaElValor(){
        Puntaje puntajeEsperado = new Puntaje(12, 1);

        CartaPoker cartaPoker = new CartaPoker(Valor.DOS, Palo.PICAS);
        Puntaje puntaje = new Puntaje(10,1);
        Tarot cartaTarot = new Tarot(puntaje);

        cartaPoker.activarTarot(cartaTarot);

        int puntajeObtenido = cartaPoker.calcularPuntaje();

        assert(puntajeEsperado.compararPuntajecon(puntajeObtenido));
    }

    @Test
    public void test07JugadorAplicaTarotAUnaCartaYSeLeModificaElMultiplicador(){
        Puntaje puntajeEsperado = new Puntaje(60, 1);

        CartaPoker cartaPoker = new CartaPoker(Valor.DIEZ, Palo.CORAZONES);

        Puntaje puntaje1 = new Puntaje(0,6);
        Tarot cartaTarot1 = new Tarot(puntaje1);

        cartaPoker.activarTarot(cartaTarot1);

        int puntajeObtenido = cartaPoker.calcularPuntaje();

        assert(puntajeEsperado.compararPuntajecon(puntajeObtenido));
    }


}
