package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.CartaPoker;
import edu.fiuba.algo3.Tarot;
import edu.fiuba.algo3.modelo.Message;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class TarotTest {

    @Test
    public void test01SeModificaElValorDeUnaCarta() {
        Tarot cartaTarot = new Tarot();
        CartaPoker cartaMock = Mock(CartaPoker.class);

        cartaTarot.modificarPuntaje(cartaMock);
    }

}