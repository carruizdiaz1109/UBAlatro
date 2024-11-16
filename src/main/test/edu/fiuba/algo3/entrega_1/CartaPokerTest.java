package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.CartaPoker;
import edu.fiuba.algo3.Puntaje;
import edu.fiuba.algo3.Palo;
import edu.fiuba.algo3.Tarot;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CartaPokerTest {

    @Test
    public void test01AlComprarCartaSeDevuelveASiMisma() {
        CartaPoker cartaPoker = new CartaPoker(10, Palo.CORAZONES);

        assertSame(cartaPoker, cartaPoker.comprar());
    }

    @Test
    public void test02SeCalculaElPuntajeDeLaCarta(){
        Puntaje puntajeEsperado = new Puntaje(2, 1);
        CartaPoker cartaPoker = new CartaPoker(2, Palo.PICAS);

        int puntajeObtenido = cartaPoker.calcularPuntaje();

        assert(puntajeEsperado.compararPuntajecon(puntajeObtenido));
    }

    @Test
    public void test03SeModificaElPuntajeDeLaCarta(){
        Puntaje puntajeEsperado = new Puntaje(9, 1);

        CartaPoker cartaPoker = new CartaPoker(5, Palo.CORAZONES);
        Puntaje unPuntaje = new Puntaje(4,1);

        cartaPoker.modificarPuntaje(unPuntaje);
        int puntajeObtenido = cartaPoker.calcularPuntaje();

        assert(puntajeEsperado.compararPuntajecon(puntajeObtenido));
    }

    @Test
    public void test04SeSumaCartaConOtroValor(){
        CartaPoker cartaPoker = new CartaPoker(5, Palo.CORAZONES);
        int resultadoEsperado = 11;

        int resultadoObtenido = cartaPoker.sumarValorCon(6);

        assertEquals(resultadoEsperado, resultadoObtenido);
    }

    @Test
    public void test05CompararConCartasIguales(){
        CartaPoker carta1 = new CartaPoker(5, Palo.CORAZONES);
        CartaPoker carta2 = new CartaPoker(5, Palo.CORAZONES);


        assertTrue(carta1.compararCartaCon(carta2));
    }


    @Test
    public void test06CompararConDiferenteValor(){
        CartaPoker carta1 = new CartaPoker(5, Palo.CORAZONES);
        CartaPoker carta2 = new CartaPoker(10, Palo.CORAZONES);


        assertFalse(carta1.compararCartaCon(carta2));
    }


    @Test
    public void test07CompararConDiferentePalo(){
        CartaPoker carta1 = new CartaPoker(5, Palo.CORAZONES);
        CartaPoker carta2 = new CartaPoker(5, Palo.PICAS);


        assertFalse(carta1.compararCartaCon(carta2));
    }

    @Test
    public void test08SeAplicaTarotALaCarta(){
        Puntaje puntajeEsperado = new Puntaje(7,1);

        CartaPoker carta = new CartaPoker(4, Palo.CORAZONES);
        Puntaje puntaje = new Puntaje(3,1);
        Tarot cartaTarot = new Tarot(puntaje);

        carta.activarTarot(cartaTarot);

        int puntajeObtenido = carta.calcularPuntaje();

        assert(puntajeEsperado.compararPuntajecon(puntajeObtenido));
    }


}
