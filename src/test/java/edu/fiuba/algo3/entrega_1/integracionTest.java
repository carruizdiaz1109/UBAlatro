package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.entidades.*;
import edu.fiuba.algo3.modelo.entidades.cartas.CartaFactory;
import edu.fiuba.algo3.modelo.entidades.cartas.CartaPoker;
import edu.fiuba.algo3.modelo.entidades.jugadas.FullHouse;
import edu.fiuba.algo3.modelo.entidades.jugadas.Par;
import edu.fiuba.algo3.modelo.entidades.jugadas.Trio;
import edu.fiuba.algo3.modelo.entidades.tarots.EfectoCarta;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class integracionTest {
    @Test
    public void test01VerificarQueUnJugadorPoseaCartasSuficientesParaEmpezarElJuegoEnSuMazo(){
        ArrayList<CartaPoker> cartasEsperadas = new ArrayList<CartaPoker>(List.of(
                CartaFactory.crearCarta(Valor.TRES, Palo.PICAS)));

        ArrayList<CartaPoker> cartas = new ArrayList<CartaPoker>(List.of(
                CartaFactory.crearCarta(Valor.TRES, Palo.PICAS)));

        Mazo mazo = new Mazo(cartas);
        Mazo mazoEsperado = new Mazo(cartasEsperadas);

        assert (mazo.compararCon(mazoEsperado));
    }

    @Test
    public void test02SeVerificaQueAUnJugadorSeLeReparten8CartasDeSuMazo(){
        ArrayList<CartaPoker> cartasEsperadas = new ArrayList<CartaPoker>(List.of(
                CartaFactory.crearCarta(Valor.TRES, Palo.PICAS),
                CartaFactory.crearCarta(Valor.SIETE, Palo.CORAZONES),
                CartaFactory.crearCarta(Valor.CINCO, Palo.DIAMANTES),
                CartaFactory.crearCarta(Valor.REY, Palo.PICAS),
                CartaFactory.crearCarta(Valor.AS, Palo.PICAS),
                CartaFactory.crearCarta(Valor.DOS, Palo.CORAZONES),
                CartaFactory.crearCarta(Valor.SIETE, Palo.DIAMANTES),
                CartaFactory.crearCarta(Valor.REY, Palo.CORAZONES)
        ));
        Mano manoEsperada = new Mano(cartasEsperadas);

        ArrayList<CartaPoker> cartas = new ArrayList<CartaPoker>(List.of(
                CartaFactory.crearCarta(Valor.TRES, Palo.PICAS),
                CartaFactory.crearCarta(Valor.SIETE, Palo.CORAZONES),
                CartaFactory.crearCarta(Valor.CINCO, Palo.DIAMANTES),
                CartaFactory.crearCarta(Valor.REY, Palo.PICAS),
                CartaFactory.crearCarta(Valor.AS, Palo.PICAS),
                CartaFactory.crearCarta(Valor.DOS, Palo.CORAZONES),
                CartaFactory.crearCarta(Valor.SIETE, Palo.DIAMANTES),
                CartaFactory.crearCarta(Valor.REY, Palo.CORAZONES)
        ));
        Mazo mazo = new Mazo(cartas);
        Mano mano = new Mano(mazo);
        mano.rellenarse();

        assert (mano.compararCon(manoEsperada));
    }

    @Test
    public void test03SeVerificaQueSePuedaJugarUnaManoDeUnMazo() {

        ArrayList<CartaPoker> cartasEsperadas = new ArrayList<CartaPoker>(List.of(
                CartaFactory.crearCarta(Valor.SIETE, Palo.CORAZONES),
                CartaFactory.crearCarta(Valor.DIEZ, Palo.PICAS),
                CartaFactory.crearCarta(Valor.SIETE, Palo.DIAMANTES),
                CartaFactory.crearCarta(Valor.DIEZ, Palo.CORAZONES)
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
                CartaFactory.crearCarta(Valor.TRES, Palo.PICAS),
                CartaFactory.crearCarta(Valor.SIETE, Palo.CORAZONES),
                CartaFactory.crearCarta(Valor.CINCO, Palo.DIAMANTES)
        ));
        Jugada jugada = Jugada.crearJugada(cartas);

        int puntajeObtenido = jugada.calcularPuntaje();

        assertEquals(puntajeEsperado, puntajeObtenido);
    }

    @Test
    public void test05SeRespetaElOrdenDePrioridadDeLasManosDePoker(){
        List<CartaPoker> cartas = List.of(
            CartaFactory.crearCarta(Valor.DIEZ, Palo.PICAS),
            CartaFactory.crearCarta(Valor.DIEZ, Palo.TREBOLES),
            CartaFactory.crearCarta(Valor.DIEZ, Palo.CORAZONES),
            CartaFactory.crearCarta(Valor.CINCO, Palo.DIAMANTES),
            CartaFactory.crearCarta(Valor.CINCO, Palo.PICAS)
        );

        Jugada jugada = Jugada.crearJugada(cartas);

        assertFalse(jugada instanceof Trio);
        assertFalse(jugada instanceof Par);
        assertTrue(jugada instanceof FullHouse);
    }

    @Test
    public void test06JugadorAplicaTarotAUnaCartaYSeLeModificaElValor(){
        int puntajeEsperado = 10;

        CartaPoker cartaPoker = CartaFactory.crearCarta(Valor.DOS, Palo.PICAS);

        Tarot tarot = new EfectoCarta("El Tonto", "Mejora la mano carta mas alta", new Puntaje(10, 1));
        tarot.aplicar(cartaPoker);

        int puntajeObtenido = cartaPoker.calcularPuntaje();

        assertEquals(puntajeEsperado, puntajeObtenido);
    }

    @Test
    public void test07JugadorAplicaTarotAUnaCartaYSeLeModificaElMultiplicador(){
        int puntajeEsperado = 60;

        CartaPoker cartaPoker = CartaFactory.crearCarta(Valor.DIEZ, Palo.CORAZONES);

        Tarot tarot = new EfectoCarta("El Tonto", "Mejora la mano carta mas alta", new Puntaje(10, 6));
        tarot.aplicar(cartaPoker);

        int puntajeObtenido = cartaPoker.calcularPuntaje();

        assertEquals(puntajeEsperado, puntajeObtenido);
    }

}
