package edu.fiuba.algo3.entrega_1;


import edu.fiuba.algo3.*;

import edu.fiuba.algo3.tarots.SobreCarta;
import edu.fiuba.algo3.tarots.SobreMano;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TarotTest {
    @Test
    public void test01TarotModificaValorCarta() {

        // Arrange
        CartaPoker carta = new CartaPoker(Valor.DOS, Palo.PICAS);

        Puntaje puntaje = new Puntaje(10, 2);
        Tarot tarot = new SobreCarta("El Tonto", "Mejora la mano carta mas alta", puntaje);

        // Act
        tarot.aplicar(carta);
        int puntajeCalculado = carta.calcularPuntaje();

        // Assert
        int puntajeEsperado = (10 * 2);
        Assert.assertEquals(puntajeEsperado, puntajeCalculado);
    }


    @Test
    public void test02TarotModificaValorJugada() {

        // Arrange
        ArrayList<CartaPoker> cartas = new ArrayList<>(List.of(
                new CartaPoker(Valor.DOS, Palo.PICAS),
                new CartaPoker(Valor.DOS, Palo.TREBOLES)
        ));
        Jugada jugada = Jugada.crearJugada(cartas);
        Tarot tarot = new SobreMano("El Tonto", "Mejora la mano carta mas alta", new Puntaje(15, 2), "par");
        // Act
        tarot.aplicar(jugada);
        int puntajeCalculado = jugada.calcularPuntaje();
        // Assert
        int puntajeEsperado = (2 + 2 + 15) * 2;
        Assert.assertEquals(puntajeEsperado, puntajeCalculado);
    }


}
