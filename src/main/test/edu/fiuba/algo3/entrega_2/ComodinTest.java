package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.CartaPoker;
import edu.fiuba.algo3.ConjuntoCartas;
import edu.fiuba.algo3.Jugada;
import edu.fiuba.algo3.Palo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ComodinTest {

    @Test
    public void test01ComodinAfectaPuntosDePuntaje() {

        // Arrange
        ArrayList<CartaPoker> cartas = new ArrayList<>(List.of(
            new CartaPoker(2, Palo.PICAS),
            new CartaPoker(2, Palo.TREBOLES)
        ));
        Jugada jugada = Jugada.crearJugada(cartas);
        Comodin comodin = new ComodinPuntaje(10, 1);

        // Act
        comodin.aplicar(jugada);
        int puntajeCalculado = jugada.calcularPuntaje();

        // Assert
        int puntajeEsperado = (10 + 2 + 2 + 10) * 2;
        assertEquals(puntajeEsperado, puntajeCalculado);
    }
    /*
    @Test
    public void comodinAfectaMultiplciadorDePuntaje() {

        }*/
}
