package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.CartaPoker;
import edu.fiuba.algo3.Jugada;
import edu.fiuba.algo3.jugadas.*;
import edu.fiuba.algo3.Palo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class JugadaTest {

    @Test
    public void testCartaAlta() {
        List<CartaPoker> cartas = List.of(
                new CartaPoker(5, Palo.PICAS),
                new CartaPoker(3, Palo.CORAZONES),
                new CartaPoker(8, Palo.DIAMANTES),
                new CartaPoker(2, Palo.TREBOLES),
                new CartaPoker(4, Palo.PICAS)
        );
        Jugada jugada = Jugada.crearJugada(cartas);

        assertTrue(jugada instanceof CartaAlta);
        assertEquals(5, jugada.calcularPuntaje()); // Verifica puntaje base
        assertEquals(cartas.get(2), jugada.cartasValidas.get(0)); // La carta alta es 8
    }

    @Test
    public void testPar() {
        List<CartaPoker> cartas = List.of(
                new CartaPoker(5, Palo.PICAS),
                new CartaPoker(5, Palo.TREBOLES),
                new CartaPoker(8, Palo.DIAMANTES),
                new CartaPoker(2, Palo.CORAZONES),
                new CartaPoker(3, Palo.PICAS)
        );
        Jugada jugada = Jugada.crearJugada(cartas);

        assertTrue(jugada instanceof Par);
        assertEquals(10, jugada.calcularPuntaje());
        assertEquals(2, jugada.cartasValidas.size());
        assertEquals(5, jugada.cartasValidas.get(0).getValor()); // Verifica el valor de la pareja
    }

    @Test
    public void testDoblePar() {
        List<CartaPoker> cartas = List.of(
                new CartaPoker(3, Palo.PICAS),
                new CartaPoker(3, Palo.TREBOLES),
                new CartaPoker(5, Palo.CORAZONES),
                new CartaPoker(5, Palo.DIAMANTES),
                new CartaPoker(2, Palo.PICAS)
        );
        Jugada jugada = Jugada.crearJugada(cartas);

        assertTrue(jugada instanceof DoblePar);
        assertEquals(20, jugada.calcularPuntaje());
        assertEquals(4, jugada.cartasValidas.size()); // Verifica que hay dos pares
    }

    @Test
    public void testTrio() {
        List<CartaPoker> cartas = List.of(
                new CartaPoker(7, Palo.PICAS),
                new CartaPoker(7, Palo.TREBOLES),
                new CartaPoker(7, Palo.CORAZONES),
                new CartaPoker(2, Palo.DIAMANTES),
                new CartaPoker(4, Palo.PICAS)
        );
        Jugada jugada = Jugada.crearJugada(cartas);

        assertTrue(jugada instanceof Trio);
        assertEquals(30, jugada.calcularPuntaje());
        assertEquals(3, jugada.cartasValidas.size());
        assertEquals(7, jugada.cartasValidas.get(0).getValor()); // Verifica el valor del trío
    }

    @Test
    public void testEscalera() {
        List<CartaPoker> cartas = List.of(
                new CartaPoker(2, Palo.PICAS),
                new CartaPoker(3, Palo.TREBOLES),
                new CartaPoker(4, Palo.CORAZONES),
                new CartaPoker(5, Palo.DIAMANTES),
                new CartaPoker(6, Palo.PICAS)
        );
        Jugada jugada = Jugada.crearJugada(cartas);

        assertTrue(jugada instanceof Escalera);
        assertEquals(40, jugada.calcularPuntaje());
        assertEquals(5, jugada.cartasValidas.size());
    }

    @Test
    public void testColor() {
        List<CartaPoker> cartas = List.of(
                new CartaPoker(2, Palo.PICAS),
                new CartaPoker(5, Palo.PICAS),
                new CartaPoker(8, Palo.PICAS),
                new CartaPoker(11, Palo.PICAS),
                new CartaPoker(13, Palo.PICAS)
        );
        Jugada jugada = Jugada.crearJugada(cartas);

        assertTrue(jugada instanceof Color);
        assertEquals(50, jugada.calcularPuntaje());
        assertEquals(5, jugada.cartasValidas.size());
    }

    @Test
    public void testFullHouse() {
        List<CartaPoker> cartas = List.of(
                new CartaPoker(10, Palo.PICAS),
                new CartaPoker(10, Palo.TREBOLES),
                new CartaPoker(10, Palo.CORAZONES),
                new CartaPoker(5, Palo.DIAMANTES),
                new CartaPoker(5, Palo.PICAS)
        );
        Jugada jugada = Jugada.crearJugada(cartas);

        assertTrue(jugada instanceof FullHouse);
        assertEquals(60, jugada.calcularPuntaje());
        assertEquals(5, jugada.cartasValidas.size());
    }

    @Test
    public void testPoker() {
        List<CartaPoker> cartas = List.of(
                new CartaPoker(9, Palo.PICAS),
                new CartaPoker(9, Palo.TREBOLES),
                new CartaPoker(9, Palo.CORAZONES),
                new CartaPoker(9, Palo.DIAMANTES),
                new CartaPoker(5, Palo.PICAS)
        );
        Jugada jugada = Jugada.crearJugada(cartas);

        assertTrue(jugada instanceof Poker);
        assertEquals(70, jugada.calcularPuntaje());
        assertEquals(4, jugada.cartasValidas.size());
    }

    @Test
    public void testEscaleraColor() {
        List<CartaPoker> cartas = List.of(
                new CartaPoker(5, Palo.CORAZONES),
                new CartaPoker(6, Palo.CORAZONES),
                new CartaPoker(7, Palo.CORAZONES),
                new CartaPoker(8, Palo.CORAZONES),
                new CartaPoker(9, Palo.CORAZONES)
        );
        Jugada jugada = Jugada.crearJugada(cartas);

        assertTrue(jugada instanceof EscaleraColor);
        assertEquals(80, jugada.calcularPuntaje());
        assertEquals(5, jugada.cartasValidas.size());
    }

    @Test
    public void testEscaleraReal() {
        List<CartaPoker> cartas = List.of(
                new CartaPoker(10, Palo.PICAS),
                new CartaPoker(11, Palo.PICAS),
                new CartaPoker(12, Palo.PICAS),
                new CartaPoker(13, Palo.PICAS),
                new CartaPoker(14, Palo.PICAS) // As como 14
        );
        Jugada jugada = Jugada.crearJugada(cartas);

        assertTrue(jugada instanceof EscaleraReal);
        assertEquals(100, jugada.calcularPuntaje());
        assertEquals(5, jugada.cartasValidas.size());
    }
}

