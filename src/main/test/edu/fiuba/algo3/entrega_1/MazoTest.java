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

    @Test
    public void test03DarCartaDevuelvePrimerCarta(){
        Mazo mazo = new Mazo();
        List<CartaPoker> cartas = mazo.getCartas();
        CartaPoker cartaEsperada = cartas.get(0);
        CartaPoker cartaObtenida = mazo.darCarta();

        assertEquals(cartaEsperada, cartaObtenida);
    }

    @Test
    public void test04DarCartaConMazoVacioTiraError(){
        Mazo mazo = new Mazo();
        while (mazo.getCantidadCartas() > 0) {
            mazo.darCarta();
        }
        assertThrows(ErrorMazoVacio.class, () -> mazo.darCarta());
    }

    @Test
    public void test05Rellena5Cartas(){
        Mazo mazo = new Mazo();
        int cantidadEsperada = 5;
        int cantidadObtenidas = 0;

        List<CartaPoker> cartas = new ArrayList<>();
        cartas = mazo.rellenar(5);
        cantidadObtenidas = cartas.size();

        assertEquals(cantidadEsperada, cantidadObtenidas);
    }

    @Test
    public void test06GuardarCartaAumentaCantidad(){
        Mazo mazo = new Mazo();
        int cantidadEsperada = 50;
        int cantidadObtenida = 0;

        mazo.darCarta();
        mazo.darCarta();
        CartaPoker cartaObtenida = mazo.darCarta();

        mazo.guardarCarta(cartaObtenida);

        cantidadObtenida = mazo.getCantidadCartas();

        assertEquals(cantidadEsperada, cantidadObtenida);

    }
}
