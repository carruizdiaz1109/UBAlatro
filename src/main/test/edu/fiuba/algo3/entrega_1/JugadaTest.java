package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.CartaPoker;
import edu.fiuba.algo3.Jugada;
import edu.fiuba.algo3.Valor;
import edu.fiuba.algo3.jugadas.*;
import edu.fiuba.algo3.Palo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class JugadaTest {

    @Test
    public void testCartaAlta() {
        //Arrange
        ArrayList<CartaPoker> cartas = new ArrayList<>(List.of(
                new CartaPoker(Valor.CINCO, Palo.PICAS),
                new CartaPoker(Valor.TRES, Palo.CORAZONES),
                new CartaPoker(Valor.OCHO, Palo.DIAMANTES),
                new CartaPoker(Valor.DOS, Palo.TREBOLES),
                new CartaPoker(Valor.CUATRO, Palo.PICAS)
        ));
        int puntajeEsperado = (8 + 5);

        //Act
        Jugada jugada = Jugada.crearJugada(cartas);

        //Assert
        assertTrue(jugada instanceof CartaAlta);
        assertEquals(puntajeEsperado, jugada.calcularValor());
        //assertEquals(cartas.get(2), jugada.cartasValidas.get(0)); // La carta alta es 8
    }

    @Test
    public void testPar() {
        //Arrange
        ArrayList<CartaPoker> cartas = new ArrayList<>(List.of(
                new CartaPoker(Valor.CINCO, Palo.PICAS),
                new CartaPoker(Valor.CINCO, Palo.TREBOLES),
                new CartaPoker(Valor.OCHO, Palo.DIAMANTES),
                new CartaPoker(Valor.DOS, Palo.CORAZONES),
                new CartaPoker(Valor.TRES, Palo.PICAS)
        ));
        int puntajeEsperado = ((5+5)+10)*2;

        //Act
        Jugada jugada = Jugada.crearJugada(cartas);

        //Assert
        assertTrue(jugada instanceof Par);
        assertEquals(puntajeEsperado, jugada.calcularValor());
        //assertEquals(5, jugada.cartasValidas.get(0).getValor()); // Verifica el valor de la pareja
    }

    @Test
    public void testDoblePar() {
        //Arrange
        ArrayList<CartaPoker> cartas = new ArrayList<>(List.of(
                new CartaPoker(Valor.TRES, Palo.PICAS),
                    new CartaPoker(Valor.TRES, Palo.TREBOLES),
                new CartaPoker(Valor.CINCO, Palo.DIAMANTES),
                new CartaPoker(Valor.CINCO, Palo.DIAMANTES),
                new CartaPoker(Valor.DOS, Palo.PICAS)
        ));
        int puntajeEsperado = ((3+3+5+5)+20)*2;

        //Act
        Jugada jugada = Jugada.crearJugada(cartas);

        //Assert
        assertTrue(jugada instanceof DoblePar);
        assertEquals(puntajeEsperado, jugada.calcularValor());
        //assertEquals(4, jugada.cartasValidas.size()); // Verifica que hay dos pares
    }

    @Test
    public void testTrio() {
        //Arrange
        ArrayList<CartaPoker> cartas = new ArrayList<>(List.of(
                new CartaPoker(Valor.SIETE, Palo.PICAS),
                new CartaPoker(Valor.SIETE, Palo.TREBOLES),
                new CartaPoker(Valor.SIETE, Palo.CORAZONES),
                new CartaPoker(Valor.DOS, Palo.DIAMANTES),
                new CartaPoker(Valor.CUATRO, Palo.PICAS)
        ));
        int puntajeEsperado = ((7+7+7)+30)*3;

        //Act
        Jugada jugada = Jugada.crearJugada(cartas);

        //Assert
        assertTrue(jugada instanceof Trio);
        assertEquals(puntajeEsperado, jugada.calcularValor());
        //assertEquals(3, jugada.cartasValidas.size());
        //assertEquals(7, jugada.cartasValidas.get(0).getValor()); // Verifica el valor del trío
    }
    @Test
    public void testEscalera() {
        List<CartaPoker> cartas = List.of(
                new CartaPoker(Valor.DOS, Palo.PICAS),
                new CartaPoker(Valor.TRES, Palo.TREBOLES),
                new CartaPoker(Valor.CUATRO, Palo.CORAZONES),
                new CartaPoker(Valor.CINCO, Palo.DIAMANTES),
                new CartaPoker(Valor.SEIS, Palo.PICAS)
        );
        Jugada jugada = Jugada.crearJugada(cartas);

        assertTrue(jugada instanceof Escalera);
        assertEquals(200, jugada.calcularValor());
    }

    @Test
    public void testColor() {
        List<CartaPoker> cartas = List.of(
                new CartaPoker(Valor.DOS, Palo.PICAS),
                new CartaPoker(Valor.CINCO, Palo.PICAS),
                new CartaPoker(Valor.OCHO, Palo.PICAS),
                new CartaPoker(Valor.JOTA, Palo.PICAS),
                new CartaPoker(Valor.REINA, Palo.PICAS)
        );
        Jugada jugada = Jugada.crearJugada(cartas);

        assertTrue(jugada instanceof Color);
        assertEquals(280, jugada.calcularValor());
    }

    @Test
    public void testFullHouse() {
        List<CartaPoker> cartas = List.of(
                new CartaPoker(Valor.DIEZ, Palo.PICAS),
                new CartaPoker(Valor.DIEZ, Palo.TREBOLES),
                new CartaPoker(Valor.DIEZ, Palo.CORAZONES),
                new CartaPoker(Valor.CINCO, Palo.DIAMANTES),
                new CartaPoker(Valor.CINCO, Palo.PICAS)
        );
        Jugada jugada = Jugada.crearJugada(cartas);
        int valorEsperado = ((10+10+10+5+5)+40)*4;
        int valorObtenido = jugada.calcularValor();

        assertTrue(jugada instanceof FullHouse);
        assertEquals(valorEsperado, valorObtenido);
    }

    @Test
    public void testPoker() {
        List<CartaPoker> cartas = List.of(
                new CartaPoker(Valor.NUEVE, Palo.PICAS),
                new CartaPoker(Valor.NUEVE, Palo.TREBOLES),
                new CartaPoker(Valor.NUEVE, Palo.CORAZONES),
                new CartaPoker(Valor.NUEVE, Palo.DIAMANTES),
                new CartaPoker(Valor.CINCO, Palo.PICAS)
        );
        Jugada jugada = Jugada.crearJugada(cartas);

        assertTrue(jugada instanceof Poker);
        assertEquals(672, jugada.calcularValor());

    }

    @Test
    public void testEscaleraColor() {
        List<CartaPoker> cartas = List.of(
                new CartaPoker(Valor.CINCO, Palo.CORAZONES),
                new CartaPoker(Valor.SEIS, Palo.CORAZONES),
                new CartaPoker(Valor.SIETE, Palo.CORAZONES),
                new CartaPoker(Valor.OCHO, Palo.CORAZONES),
                new CartaPoker(Valor.NUEVE, Palo.CORAZONES)
        );
        Jugada jugada = Jugada.crearJugada(cartas);

        assertTrue(jugada instanceof EscaleraColor);
        assertEquals(1080, jugada.calcularValor());

    }
/*
    @Test
    public void testEscaleraReal() {
        List<CartaPoker> cartas = List.of(
                new CartaPoker(Valor.DIEZ, Palo.PICAS),
                new CartaPoker(Valor.JOTA, Palo.PICAS),
                new CartaPoker(Valor.REINA, Palo.PICAS),
                new CartaPoker(Valor.REY, Palo.PICAS),
                new CartaPoker(Valor.AS, Palo.PICAS) // As como 14
        );
        Jugada jugada = Jugada.crearJugada(cartas);

        assertTrue(jugada instanceof EscaleraReal);
        assertEquals(1280, jugada.calcularValor());

    }*/
}

