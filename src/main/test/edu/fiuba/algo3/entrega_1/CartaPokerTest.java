package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.CartaPoker;
import edu.fiuba.algo3.modelo.Message;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartaPokerTest {

    @Test
    public void test1AlUtilizarMetodoComprarSeDevuelveUnaCarta() {
        CartaPoker cartaPoker = new CartaPoker(3, "Basto");

        CartaPoker resultado = cartaPoker.comprar();
    }

}
