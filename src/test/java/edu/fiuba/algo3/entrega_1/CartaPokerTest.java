package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.entidades.tarots.Tarot;
import edu.fiuba.algo3.modelo.excepciones.CartaNulaError;
import edu.fiuba.algo3.modelo.excepciones.PuntajeNuloError;
import edu.fiuba.algo3.modelo.entidades.tarots.TarotCarta;
import edu.fiuba.algo3.modelo.entidades.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CartaPokerTest {

    @Test
    public void test01AlComprarCartaSeDevuelveASiMisma() {
        CartaPoker cartaPoker = new CartaPoker(Valor.DIEZ, Palo.CORAZONES);

        assertSame(cartaPoker, cartaPoker.comprar());
    }

    @Test
    public void test02SeCalculaElPuntajeDeLaCarta(){
        Puntaje puntajeEsperado = new Puntaje(2, 1);
        CartaPoker cartaPoker = new CartaPoker(Valor.DOS, Palo.PICAS);

        int puntajeObtenido = cartaPoker.calcularPuntaje();

        assert(puntajeEsperado.compararPuntajecon(puntajeObtenido));
    }

    @Test
    public void test03SeModificaElPuntajeDeLaCarta(){
        Puntaje puntajeEsperado = new Puntaje(9, 1);

        CartaPoker cartaPoker = new CartaPoker(Valor.CINCO, Palo.CORAZONES);
        Puntaje unPuntaje = new Puntaje(4,1);

        cartaPoker.modificarPuntaje(unPuntaje);
        int puntajeObtenido = cartaPoker.calcularPuntaje();

        assert(puntajeEsperado.compararPuntajecon(puntajeObtenido));
    }

    @Test
    public void test04SeSumaCartaConOtroValor(){
        CartaPoker cartaPoker = new CartaPoker(Valor.CINCO, Palo.CORAZONES);
        int resultadoEsperado = 11;

        int resultadoObtenido = cartaPoker.sumarValorCon(6);

        assertEquals(resultadoEsperado, resultadoObtenido);
    }

    @Test
    public void test05CompararConCartasIguales(){
        CartaPoker carta1 = new CartaPoker(Valor.CINCO, Palo.CORAZONES);
        CartaPoker carta2 = new CartaPoker(Valor.CINCO, Palo.CORAZONES);


        assertTrue(carta1.compararCartaCon(carta2));
    }


    @Test
    public void test06CompararConDiferenteValor(){
        CartaPoker carta1 = new CartaPoker(Valor.CINCO, Palo.CORAZONES);
        CartaPoker carta2 = new CartaPoker(Valor.DIEZ, Palo.CORAZONES);


        assertFalse(carta1.compararCartaCon(carta2));
    }


    @Test
    public void test07CompararConDiferentePalo(){
        CartaPoker carta1 = new CartaPoker(Valor.CINCO, Palo.CORAZONES);
        CartaPoker carta2 = new CartaPoker(Valor.CINCO, Palo.PICAS);


        assertFalse(carta1.compararCartaCon(carta2));
    }

    @Test
    public void test08SeAplicaTarotALaCarta(){
        Puntaje puntajeEsperado = new Puntaje(3,1);

        CartaPoker carta = new CartaPoker(Valor.CUATRO, Palo.CORAZONES);
        Tarot unTarot = new TarotCarta("El Tonto", "Mejora la mano carta mas alta", new Puntaje(3, 1));

        unTarot.aplicar(carta);

        int puntajeObtenido = carta.calcularPuntaje();

        assert(puntajeEsperado.compararPuntajecon(puntajeObtenido));
    }

    @Test
    public void test09SeLePideLaEscaleraRealALaCarta(){
        CartaPoker carta = new CartaPoker(Valor.TRES, Palo.CORAZONES);
        ConjuntoCartas escaleraEsperada = new ConjuntoCartas(List.of(
                new CartaPoker(Valor.TRES, Palo.CORAZONES),
                new CartaPoker(Valor.CUATRO, Palo.CORAZONES),
                new CartaPoker(Valor.CINCO, Palo.CORAZONES),
                new CartaPoker(Valor.SEIS, Palo.CORAZONES),
                new CartaPoker(Valor.SIETE, Palo.CORAZONES)
        ));

       ConjuntoCartas escaleraObtenida = carta.obtenerEscalera();
       System.out.println(escaleraEsperada);
        assert(escaleraObtenida.compararCon(escaleraEsperada));
    }

    @Test
    public void test10ModificarPuntajeNuloTiraExcepcion(){
        CartaPoker carta = new CartaPoker(Valor.TRES, Palo.CORAZONES);

        assertThrows(PuntajeNuloError.class, () -> carta.modificarPuntaje(null));
    }

    @Test
    public void test11CompararValorConCartaNulaTiraExcepcion(){
        CartaPoker carta = new CartaPoker(Valor.TRES, Palo.CORAZONES);

        assertThrows(CartaNulaError.class, () -> carta.esMismoValor(null));
    }

    @Test
    public void test12CompararPaloConCartaNulaTiraExcepcion(){
        CartaPoker carta = new CartaPoker(Valor.TRES, Palo.CORAZONES);

        assertThrows(CartaNulaError.class, () -> carta.esMismoPalo(null));
    }

    @Test
    public void test13ConsecutivaConCartaNulaTiraExcepcion(){
        CartaPoker carta = new CartaPoker(Valor.TRES, Palo.CORAZONES);

        assertThrows(CartaNulaError.class, () -> carta.esConsecutiva(null));
    }

    @Test
    public void test14CompararCartaConCartaNulaTiraExcepcion(){
        CartaPoker carta = new CartaPoker(Valor.TRES, Palo.CORAZONES);

        assertThrows(CartaNulaError.class, () -> carta.compararCartaCon(null));
    }

    @Test
    public void test15AplicarComodinConPuntajeNuloTiraExcepcion(){
        CartaPoker carta = new CartaPoker(Valor.TRES, Palo.CORAZONES);

        assertThrows(PuntajeNuloError.class, () -> carta.aplicarComodin(null));
    }

    @Test
    public void test16AplicarTarotConPuntajeNuloTiraExcepcion(){
        CartaPoker carta = new CartaPoker(Valor.TRES, Palo.CORAZONES);

        assertThrows(PuntajeNuloError.class, () -> carta.aplicarTarot(null));
    }
}
