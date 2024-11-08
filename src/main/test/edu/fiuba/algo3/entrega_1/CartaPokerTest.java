package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.CartaPoker;
import edu.fiuba.algo3.Puntaje;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

public class CartaPokerTest {

    @Test
    public void test01AlComprarCartaSeDevuelveASiMisma() {
        CartaPoker cartaPoker = new CartaPoker(3,"Pica", 1);

        assertSame(cartaPoker, cartaPoker.comprar());
    }

    @Test
    public void test02SeCalculaElPuntajeDeLaCarta(){
        CartaPoker cartaPoker = new CartaPoker(3,"Pica", 1);

        int resultado = cartaPoker.calcularPuntaje();

        assertEquals(3, resultado);
    }

    @Test
    public void test03SeModificaElPuntajeDeLaCarta(){
        CartaPoker cartaPoker = new CartaPoker(3,"Pica", 1);
        cartaPoker.modificarPuntaje(3);
        int resultado = cartaPoker.calcularPuntaje();

        assertEquals(6,resultado);
    }

    @Test
    public void test04SeConsultaSiTieneCiertoId(){
        CartaPoker cartaPoker = new CartaPoker(7,"Pica", 6);

        assertTrue(cartaPoker.tieneId(6));
    }

}
