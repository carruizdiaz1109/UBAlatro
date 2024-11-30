package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.entidades.*;
import edu.fiuba.algo3.modelo.entidades.jugadas.FullHouse;
import edu.fiuba.algo3.modelo.entidades.jugadas.Par;
import edu.fiuba.algo3.modelo.entidades.jugadas.Trio;
import edu.fiuba.algo3.modelo.entidades.tarots.TarotCarta;
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
        Mano mano = new Mano(mazo);
        mano.rellenarse();

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

        ArrayList<CartaPoker> cartas = new ArrayList<>(cartasEsperadas);

        Mazo mazo = new Mazo(cartas);
        Mano mano = new Mano(mazo);
        mano.rellenarse();

        mano.seleccionarCartas(cartasEsperadas);

        assert(mano.compararSeleccionadasCon(cartasEsperadas));
    }

    @Test
    public void test04VerificarQueAlJugarUnaManoSeApliqueElValorCorrespondiente(){
       int puntajeEsperado = 12;

        ArrayList<CartaPoker> cartas = new ArrayList<>(List.of(
                new CartaPoker(Valor.TRES, Palo.PICAS),
                new CartaPoker(Valor.SIETE, Palo.CORAZONES),
                new CartaPoker(Valor.CINCO, Palo.DIAMANTES)
        ));
        Jugada jugada = Jugada.crearJugada(cartas);

        int puntajeObtenido = jugada.calcularPuntaje();

        assertEquals(puntajeEsperado, puntajeObtenido);
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
        int puntajeEsperado = 10;

        CartaPoker cartaPoker = new CartaPoker(Valor.DOS, Palo.PICAS);

        Tarot tarot = new TarotCarta("El Tonto", "Mejora la mano carta mas alta", new Puntaje(10, 1));
        tarot.aplicar(cartaPoker);

        int puntajeObtenido = cartaPoker.calcularPuntaje();

        assertEquals(puntajeEsperado, puntajeObtenido);
    }

    @Test
    public void test07JugadorAplicaTarotAUnaCartaYSeLeModificaElMultiplicador(){
        int puntajeEsperado = 60;

        CartaPoker cartaPoker = new CartaPoker(Valor.DIEZ, Palo.CORAZONES);

        Tarot tarot = new TarotCarta("El Tonto", "Mejora la mano carta mas alta", new Puntaje(10, 6));
        tarot.aplicar(cartaPoker);

        int puntajeObtenido = cartaPoker.calcularPuntaje();

        assertEquals(puntajeEsperado, puntajeObtenido);
    }

}
