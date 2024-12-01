package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.entidades.cartas.CartaFactory;
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
                CartaFactory.crearCarta(Valor.CINCO, Palo.PICAS),
                CartaFactory.crearCarta(Valor.TRES, Palo.CORAZONES),
                CartaFactory.crearCarta(Valor.OCHO, Palo.DIAMANTES),
                CartaFactory.crearCarta(Valor.DOS, Palo.TREBOLES),
                CartaFactory.crearCarta(Valor.CUATRO, Palo.PICAS)
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
                CartaFactory.crearCarta(Valor.CINCO, Palo.PICAS),
                CartaFactory.crearCarta(Valor.CINCO, Palo.TREBOLES),
                CartaFactory.crearCarta(Valor.OCHO, Palo.DIAMANTES),
                CartaFactory.crearCarta(Valor.DOS, Palo.CORAZONES),
                CartaFactory.crearCarta(Valor.TRES, Palo.PICAS)
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
                CartaFactory.crearCarta(Valor.TRES, Palo.PICAS),
                CartaFactory.crearCarta(Valor.TRES, Palo.TREBOLES),
                CartaFactory.crearCarta(Valor.CINCO, Palo.DIAMANTES),
                CartaFactory.crearCarta(Valor.CINCO, Palo.DIAMANTES),
                CartaFactory.crearCarta(Valor.DOS, Palo.PICAS)
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
                CartaFactory.crearCarta(Valor.SIETE, Palo.PICAS),
                CartaFactory.crearCarta(Valor.SIETE, Palo.TREBOLES),
                CartaFactory.crearCarta(Valor.SIETE, Palo.CORAZONES),
                CartaFactory.crearCarta(Valor.DOS, Palo.DIAMANTES),
                CartaFactory.crearCarta(Valor.CUATRO, Palo.PICAS)
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
                CartaFactory.crearCarta(Valor.DOS, Palo.PICAS),
                CartaFactory.crearCarta(Valor.TRES, Palo.TREBOLES),
                CartaFactory.crearCarta(Valor.CUATRO, Palo.CORAZONES),
                CartaFactory.crearCarta(Valor.CINCO, Palo.DIAMANTES),
                CartaFactory.crearCarta(Valor.SEIS, Palo.PICAS)
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
                CartaFactory.crearCarta(Valor.DOS, Palo.PICAS),
                CartaFactory.crearCarta(Valor.CINCO, Palo.PICAS),
                CartaFactory.crearCarta(Valor.OCHO, Palo.PICAS),
                CartaFactory.crearCarta(Valor.JOTA, Palo.PICAS),
                CartaFactory.crearCarta(Valor.REINA, Palo.PICAS)
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
                CartaFactory.crearCarta(Valor.DIEZ, Palo.PICAS),
                CartaFactory.crearCarta(Valor.DIEZ, Palo.TREBOLES),
                CartaFactory.crearCarta(Valor.DIEZ, Palo.CORAZONES),
                CartaFactory.crearCarta(Valor.CINCO, Palo.DIAMANTES),
                CartaFactory.crearCarta(Valor.CINCO, Palo.PICAS)
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
                CartaFactory.crearCarta(Valor.NUEVE, Palo.PICAS),
                CartaFactory.crearCarta(Valor.NUEVE, Palo.TREBOLES),
                CartaFactory.crearCarta(Valor.NUEVE, Palo.CORAZONES),
                CartaFactory.crearCarta(Valor.NUEVE, Palo.DIAMANTES),
                CartaFactory.crearCarta(Valor.CINCO, Palo.PICAS)
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
                CartaFactory.crearCarta(Valor.CINCO, Palo.CORAZONES),
                CartaFactory.crearCarta(Valor.SEIS, Palo.CORAZONES),
                CartaFactory.crearCarta(Valor.SIETE, Palo.CORAZONES),
                CartaFactory.crearCarta(Valor.OCHO, Palo.CORAZONES),
                CartaFactory.crearCarta(Valor.NUEVE, Palo.CORAZONES)
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
                CartaFactory.crearCarta(Valor.DIEZ, Palo.PICAS),
                CartaFactory.crearCarta(Valor.JOTA, Palo.PICAS),
                CartaFactory.crearCarta(Valor.REINA, Palo.PICAS),
                CartaFactory.crearCarta(Valor.REY, Palo.PICAS),
                CartaFactory.crearCarta(Valor.AS, Palo.PICAS)
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
                CartaFactory.crearCarta(Valor.DIEZ, Palo.PICAS),
                CartaFactory.crearCarta(Valor.JOTA, Palo.DIAMANTES),
                CartaFactory.crearCarta(Valor.REINA, Palo.TREBOLES),
                CartaFactory.crearCarta(Valor.REY, Palo.CORAZONES),
                CartaFactory.crearCarta(Valor.AS, Palo.PICAS)
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
                CartaFactory.crearCarta(Valor.AS, Palo.PICAS),
                CartaFactory.crearCarta(Valor.DOS, Palo.PICAS),
                CartaFactory.crearCarta(Valor.TRES, Palo.TREBOLES),
                CartaFactory.crearCarta(Valor.CUATRO, Palo.CORAZONES),
                CartaFactory.crearCarta(Valor.CINCO, Palo.DIAMANTES)
        );
        int puntajeEsperado = ((11+2+3+4+5)+30)*4;

        //Act
        Jugada jugada = Jugada.crearJugada(cartas);
        int puntajeObtenido = jugada.calcularPuntaje();

        //Assert
        assertTrue(jugada instanceof Escalera);
        assertEquals(puntajeEsperado, puntajeObtenido);
    }

//    @Test
//    public void testEscaleraNoValida() {
//        //Arrange
//        List<CartaPoker> cartas = List.of(
//                CartaFactory.crearCarta(Valor.REINA, Palo.CORAZONES),
//                CartaFactory.crearCarta(Valor.REY, Palo.DIAMANTES),
//                CartaFactory.crearCarta(Valor.AS, Palo.PICAS),
//                CartaFactory.crearCarta(Valor.DOS, Palo.PICAS),
//                CartaFactory.crearCarta(Valor.TRES, Palo.TREBOLES)
//        );
//        int puntajeEsperado = (11+5);
//
//        //Act
//        Jugada jugada = Jugada.crearJugada(cartas);
//        int puntajeObtenido = jugada.calcularPuntaje();
//
//        //Assert
//        assertTrue(jugada instanceof CartaAlta);
//        assertEquals(puntajeEsperado, puntajeObtenido);
//    }
}

