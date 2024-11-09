package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.ErrorMazoVacio;
import edu.fiuba.algo3.Mazo;
import edu.fiuba.algo3.CartaPoker;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MazoTest {
    @Test
    public void test01CantidadDeCartasEnMazoInicializado(){
        Mazo mazo = new Mazo();

        int cantidadCartasEsperadas = 52;
        int cantidadCartasObtenidas = 0;

        cantidadCartasObtenidas = mazo.getCantidadCartas();

        assertEquals(cantidadCartasEsperadas, cantidadCartasObtenidas);
    }

    @Test
    public void test02DarCartaReduceCantidad(){
        int cantidadEsperada = 51;
        int cantidadFinal = 0;

        Mazo mazo = new Mazo();
        mazo.darCarta();
        cantidadFinal = mazo.getCantidadCartas();

        assertEquals(cantidadFinal, cantidadEsperada);
    }
}
