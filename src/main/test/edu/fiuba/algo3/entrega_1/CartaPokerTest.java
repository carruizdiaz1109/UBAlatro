package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
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
        Puntaje puntajeEsperado = new Puntaje(7,1);

        CartaPoker carta = new CartaPoker(Valor.CUATRO, Palo.CORAZONES);
        Tarot cartaTarot = new Tarot(3,1);

        carta.activarTarot(cartaTarot);

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
        assert(escaleraObtenida.compararCon(escaleraEsperada));
    }
}
