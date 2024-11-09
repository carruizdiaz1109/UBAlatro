package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.CartaPoker;
import edu.fiuba.algo3.Palo;
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
        CartaPoker cartaPoker = new CartaPoker(2, Palo.PICAS);

        int resultado = cartaPoker.calcularPuntaje();

        assertEquals(2, resultado);
    }

    @Test
    public void test03SeModificaElPuntajeDeLaCarta(){
        CartaPoker cartaPoker = new CartaPoker(5, Palo.CORAZONES);
        cartaPoker.modificarPuntaje(3);
        int resultado = cartaPoker.calcularPuntaje();

        assertEquals(8,resultado);
    }

}
