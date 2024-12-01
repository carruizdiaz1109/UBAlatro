package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.entidades.cartas.CartaPoker;
import edu.fiuba.algo3.modelo.entidades.Jugada;
import edu.fiuba.algo3.modelo.entidades.Valor;
import edu.fiuba.algo3.modelo.entidades.Palo;
import edu.fiuba.algo3.modelo.entidades.jugadas.*;
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
        int puntajeEsperado = (8+5);

        //Act
        Jugada jugada = Jugada.crearJugada(cartas);
        int puntajeObtenido = jugada.calcularPuntaje();

        //Assert
        assertTrue(jugada instanceof CartaAlta);
        assertEquals(puntajeEsperado, puntajeObtenido);
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
        int puntajeObtenido = jugada.calcularPuntaje();

        //Assert
        assertTrue(jugada instanceof Par);
        assertEquals(puntajeEsperado, puntajeObtenido);
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
        int puntajeObtenido = jugada.calcularPuntaje();

        //Assert
        assertTrue(jugada instanceof DoblePar);
        assertEquals(puntajeEsperado, puntajeObtenido);
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
        int puntajeObtenido = jugada.calcularPuntaje();

        //Assert
        assertTrue(jugada instanceof Trio);
        assertEquals(puntajeEsperado, puntajeObtenido);
    }
    @Test
    public void testEscalera() {
        //Arrange
        List<CartaPoker> cartas = List.of(
                new CartaPoker(Valor.DOS, Palo.PICAS),
                new CartaPoker(Valor.TRES, Palo.TREBOLES),
                new CartaPoker(Valor.CUATRO, Palo.CORAZONES),
                new CartaPoker(Valor.CINCO, Palo.DIAMANTES),
                new CartaPoker(Valor.SEIS, Palo.PICAS)
        );
        int puntajeEsperado = ((2+3+4+5+6)+30)*4;

        //Act
        Jugada jugada = Jugada.crearJugada(cartas);
        int puntajeObtenido = jugada.calcularPuntaje();

        //Assert
        assertTrue(jugada instanceof Escalera);
        assertEquals(puntajeEsperado, puntajeObtenido);
    }

    @Test
    public void testColor() {
        //Arrange
        List<CartaPoker> cartas = List.of(
                new CartaPoker(Valor.DOS, Palo.PICAS),
                new CartaPoker(Valor.CINCO, Palo.PICAS),
                new CartaPoker(Valor.OCHO, Palo.PICAS),
                new CartaPoker(Valor.JOTA, Palo.PICAS),
                new CartaPoker(Valor.REINA, Palo.PICAS)
        );
        int puntajeEsperado = ((2+5+8+10+10)+35)*4;

        //Act
        Jugada jugada = Jugada.crearJugada(cartas);
        int puntajeObtenido = jugada.calcularPuntaje();

        //Assert
        assertTrue(jugada instanceof Color);
        assertEquals(puntajeEsperado, puntajeObtenido);
    }

    @Test
    public void testFullHouse() {
        //Arrange
        List<CartaPoker> cartas = List.of(
                new CartaPoker(Valor.DIEZ, Palo.PICAS),
                new CartaPoker(Valor.DIEZ, Palo.TREBOLES),
                new CartaPoker(Valor.DIEZ, Palo.CORAZONES),
                new CartaPoker(Valor.CINCO, Palo.DIAMANTES),
                new CartaPoker(Valor.CINCO, Palo.PICAS)
        );
        int puntajeEsperado = ((10+10+10+5+5)+40)*4;

        //Act
        Jugada jugada = Jugada.crearJugada(cartas);
        int puntajeObtenido = jugada.calcularPuntaje();

        //Assert
        assertTrue(jugada instanceof FullHouse);
        assertEquals(puntajeEsperado, puntajeObtenido);
    }

    @Test
    public void testPoker() {
        //Arrange
        List<CartaPoker> cartas = List.of(
                new CartaPoker(Valor.NUEVE, Palo.PICAS),
                new CartaPoker(Valor.NUEVE, Palo.TREBOLES),
                new CartaPoker(Valor.NUEVE, Palo.CORAZONES),
                new CartaPoker(Valor.NUEVE, Palo.DIAMANTES),
                new CartaPoker(Valor.CINCO, Palo.PICAS)
        );
        int puntajeEsperado = ((9+9+9+9)+60)*7;

        //Act
        Jugada jugada = Jugada.crearJugada(cartas);
        int puntajeObtenido = jugada.calcularPuntaje();

        //Assert
        assertTrue(jugada instanceof Poker);
        assertEquals(puntajeEsperado, puntajeObtenido);

    }

    @Test
    public void testEscaleraColor() {
        //Arrange
        List<CartaPoker> cartas = List.of(
                new CartaPoker(Valor.CINCO, Palo.CORAZONES),
                new CartaPoker(Valor.SEIS, Palo.CORAZONES),
                new CartaPoker(Valor.SIETE, Palo.CORAZONES),
                new CartaPoker(Valor.OCHO, Palo.CORAZONES),
                new CartaPoker(Valor.NUEVE, Palo.CORAZONES)
        );
        int puntajeEsperado = ((5+6+7+8+9)+100)*8;

        //Act
        Jugada jugada = Jugada.crearJugada(cartas);
        int puntajeObtenido = jugada.calcularPuntaje();

        //Assert
        assertTrue(jugada instanceof EscaleraColor);
        assertEquals(puntajeEsperado, puntajeObtenido);
    }

    @Test
    public void testEscaleraReal() {
        //Arrange
        List<CartaPoker> cartas = List.of(
                new CartaPoker(Valor.DIEZ, Palo.PICAS),
                new CartaPoker(Valor.JOTA, Palo.PICAS),
                new CartaPoker(Valor.REINA, Palo.PICAS),
                new CartaPoker(Valor.REY, Palo.PICAS),
                new CartaPoker(Valor.AS, Palo.PICAS) // As como 14
        );
        int valorEsperado = ((10+10+10+10+11)+100)*8;

        //Act
        Jugada jugada = Jugada.crearJugada(cartas);
        int puntajeObtenido = jugada.calcularPuntaje();

        //Assert
        assertTrue(jugada instanceof EscaleraReal);
        assertEquals(valorEsperado, puntajeObtenido);
    }

    @Test
    public void testEscaleraHastaAs() {
        //Arrange
        List<CartaPoker> cartas = List.of(
                new CartaPoker(Valor.DIEZ, Palo.PICAS),
                new CartaPoker(Valor.JOTA, Palo.DIAMANTES),
                new CartaPoker(Valor.REINA, Palo.TREBOLES),
                new CartaPoker(Valor.REY, Palo.CORAZONES),
                new CartaPoker(Valor.AS, Palo.PICAS)
        );
        int puntajeEsperado = ((10+10+10+10+11)+30)*4;

        //Act
        Jugada jugada = Jugada.crearJugada(cartas);
        int puntajeObtenido = jugada.calcularPuntaje();

        //Assert
        assertTrue(jugada instanceof Escalera);
        assertEquals(puntajeEsperado, puntajeObtenido);
    }

    @Test
    public void testEscaleraDesdeAs() {
        //Arrange
        List<CartaPoker> cartas = List.of(
                new CartaPoker(Valor.AS, Palo.PICAS),
                new CartaPoker(Valor.DOS, Palo.PICAS),
                new CartaPoker(Valor.TRES, Palo.TREBOLES),
                new CartaPoker(Valor.CUATRO, Palo.CORAZONES),
                new CartaPoker(Valor.CINCO, Palo.DIAMANTES)
        );
        int puntajeEsperado = ((11+2+3+4+5)+30)*4;

        //Act
        Jugada jugada = Jugada.crearJugada(cartas);
        int puntajeObtenido = jugada.calcularPuntaje();

        //Assert
        assertTrue(jugada instanceof Escalera);
        assertEquals(puntajeEsperado, puntajeObtenido);
    }

    @Test
    public void testEscaleraNoValida() {
        //Arrange
        List<CartaPoker> cartas = List.of(
                new CartaPoker(Valor.REINA, Palo.CORAZONES),
                new CartaPoker(Valor.REY, Palo.DIAMANTES),
                new CartaPoker(Valor.AS, Palo.PICAS),
                new CartaPoker(Valor.DOS, Palo.PICAS),
                new CartaPoker(Valor.TRES, Palo.TREBOLES)
        );
        int puntajeEsperado = (11+5);

        //Act
        Jugada jugada = Jugada.crearJugada(cartas);
        int puntajeObtenido = jugada.calcularPuntaje();

        //Assert
        assertTrue(jugada instanceof CartaAlta);
        assertEquals(puntajeEsperado, puntajeObtenido);
    }
}

