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
    public void test01SeModificaElValor() {
        Puntaje puntajeEsperado = new Puntaje(5,1);

        Tarot tarot = new Tarot(2,1);
        Puntaje puntaje = new Puntaje(3,1);

        Puntaje puntajeObtenido = tarot.modificarPuntaje(puntaje);

        assert (puntajeObtenido.compararPuntajecon(puntajeEsperado));
    }

}