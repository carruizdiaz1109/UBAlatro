package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.CartaPoker;
import edu.fiuba.algo3.Tarot;
import edu.fiuba.algo3.Palo;
import edu.fiuba.algo3.Puntaje;


import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TarotTest {

    @Test
    public void test01SeModificaElValorDeUnaCarta() {
        Puntaje puntajeEsperado = new Puntaje(7,1);

        CartaPoker cartaPoker = new CartaPoker(4, Palo.PICAS);
        Puntaje puntaje = new Puntaje(3,1);
        Tarot cartaTarot = new Tarot(puntaje);

        cartaTarot.modificarPuntaje(cartaPoker);
        int puntajeObtenido = cartaPoker.calcularPuntaje();

        assert (puntajeEsperado.compararPuntajecon(puntajeObtenido));
    }

}