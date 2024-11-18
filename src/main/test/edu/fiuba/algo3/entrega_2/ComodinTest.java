package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.*;
import edu.fiuba.algo3.comodines.*;
import edu.fiuba.algo3.jugadas.Poker;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ComodinTest {


    @Test
    public void test01SeAplicaUnComodinAJugada() {
        List<CartaPoker> cartas = List.of(
                new CartaPoker(Valor.NUEVE, Palo.PICAS),
                new CartaPoker(Valor.TRES, Palo.DIAMANTES),
                new CartaPoker(Valor.TRES, Palo.DIAMANTES),
                new CartaPoker(Valor.TRES, Palo.DIAMANTES),
                new CartaPoker(Valor.TRES, Palo.DIAMANTES)
        );
        Jugada unaJugada = Jugada.crearJugada(cartas);
        Jugada otraJugada = Jugada.crearJugada(cartas);

        int puntajeEsperado1 = unaJugada.calcularPuntaje() * 3;
        int puntajeEsperado2 = otraJugada.calcularPuntaje() * 3;

        EfectoJugada unComodin = new EfectoJugada(Poker.class, 1,3);
        unComodin.aplicar(unaJugada);
        unComodin.aplicar(otraJugada);

        int puntajeObtenido = unaJugada.calcularPuntaje();
        int otroPuntaje = otraJugada.calcularPuntaje();
        assertEquals(puntajeEsperado1, puntajeObtenido);
        assertEquals(puntajeEsperado2, otroPuntaje);

    }


    /*
    @Test
    public void comodinAfectaPuntosDePuntaje() {

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

    @Test
    public void comodinAfectaMultiplciadorDePuntaje() {

    }*/


}
