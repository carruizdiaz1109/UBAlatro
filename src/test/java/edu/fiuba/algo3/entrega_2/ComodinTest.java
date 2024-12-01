package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.entidades.*;
import edu.fiuba.algo3.modelo.entidades.cartas.CartaPoker;
import edu.fiuba.algo3.modelo.entidades.comodines.*;
import edu.fiuba.algo3.modelo.entidades.jugadas.CartaAlta;
import edu.fiuba.algo3.modelo.entidades.jugadas.Escalera;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;

public class ComodinTest {

    @Test
    public void test01comodinAfectaPuntosDePuntaje() {

        // Arrange
        ArrayList<CartaPoker> cartas = new ArrayList<>(List.of(
                new CartaPoker(Valor.DOS, Palo.PICAS),
                new CartaPoker(Valor.DOS, Palo.TREBOLES)
        ));
        Jugada jugada = Jugada.crearJugada(cartas);
        Puntaje puntaje = new Puntaje(10, 1);
        NoAleatorio noAleatorio = new NoAleatorio();
        EfectoPuntaje comodin = new EfectoPuntaje(puntaje, "Estrella Fugaz", "+10 fichines", noAleatorio);

        // Act
        comodin.aplicar(jugada);
        int puntajeCalculado = jugada.calcularPuntaje();

        // Assert
        int puntajeEsperado = (10 + 2 + 2 + 10) * 2;
        assertEquals(puntajeEsperado, puntajeCalculado);
    }

    @Test
    public void test02SeAplicaUnComodinEscaleraAJugadaEscalera() {
        //Arrange
        List<CartaPoker> cartas = List.of(
                new CartaPoker(Valor.DOS, Palo.PICAS),
                new CartaPoker(Valor.TRES, Palo.DIAMANTES),
                new CartaPoker(Valor.CUATRO, Palo.DIAMANTES),
                new CartaPoker(Valor.CINCO, Palo.CORAZONES),
                new CartaPoker(Valor.SEIS, Palo.PICAS)
        );
        Jugada unaJugada = Jugada.crearJugada(cartas);
        int puntajeEsperado1 = (2+3+4+5+6+30)*12;
        Puntaje puntaje = new Puntaje(0, 3);
        NoAleatorio noAleatorio = new NoAleatorio();
        EfectoJugada unComodin = new EfectoJugada(Escalera.class, puntaje, "Escalinatas", "x3 en escalera", noAleatorio);
        //Act
        unComodin.aplicar(unaJugada);
        int puntajeObtenido = unaJugada.calcularPuntaje();
        //Assert
        assertEquals(puntajeEsperado1, puntajeObtenido);
    }

    @Test
    public void test03NoSeAplicaUnComodinJugadaSiNoEsLaJugada() {
        //Arrange
        List<CartaPoker> cartas = List.of(
                new CartaPoker(Valor.DOS, Palo.PICAS),
                new CartaPoker(Valor.TRES, Palo.DIAMANTES),
                new CartaPoker(Valor.CUATRO, Palo.DIAMANTES),
                new CartaPoker(Valor.CINCO, Palo.CORAZONES),
                new CartaPoker(Valor.SEIS, Palo.PICAS)
        );
        Jugada unaJugada = Jugada.crearJugada(cartas);
        Puntaje puntaje = new Puntaje(0, 3);
        NoAleatorio noAleatorio = new NoAleatorio();
        Comodin unComodin = new EfectoJugada(CartaAlta.class, puntaje, "Alta carta" , "x3 si se juega cartaAlta", noAleatorio);
        //Act
        unComodin.aplicar(unaJugada);
        int puntajeObtenido = unaJugada.calcularPuntaje();
        //Assert
        Assertions.assertEquals(200, puntajeObtenido);
    }

    @Test
    public void test04SeAplicaUnComodinAlPuntaje() {
        //Arrange
        List<CartaPoker> cartas = List.of(
                new CartaPoker(Valor.DOS, Palo.PICAS),
                new CartaPoker(Valor.DOS, Palo.DIAMANTES),
                new CartaPoker(Valor.DOS, Palo.DIAMANTES),
                new CartaPoker(Valor.DOS, Palo.CORAZONES),
                new CartaPoker(Valor.SEIS, Palo.PICAS)
        );
        Jugada unaJugada = Jugada.crearJugada(cartas);
        int puntajeEsperado = (2*4+60)*7*8;
        Puntaje puntaje = new Puntaje(0, 8);
        NoAleatorio noAleatorio = new NoAleatorio();
        Comodin unComodin = new EfectoPuntaje( puntaje, "Pluton", "x8 de multiplicador a la jugada", noAleatorio);
        //Act
        unComodin.aplicar(unaJugada);
        int puntajeObtenido = unaJugada.calcularPuntaje();
        //Assert
        Assertions.assertEquals(puntajeEsperado, puntajeObtenido);
    }

    @Test
    public void test05SeAplicaUnComodinAleatorio() {
        //Arrange
        List<CartaPoker> cartas = List.of(
                new CartaPoker(Valor.DOS, Palo.PICAS),
                new CartaPoker(Valor.DOS, Palo.DIAMANTES),
                new CartaPoker(Valor.DOS, Palo.DIAMANTES),
                new CartaPoker(Valor.DOS, Palo.CORAZONES),
                new CartaPoker(Valor.SEIS, Palo.PICAS)
        );
        Jugada unaJugada = Jugada.crearJugada(cartas);
        int puntajeEsperado = (2*4+60+10)*7;
        Puntaje puntaje = new Puntaje(10, 1);
        Aleatorio esAleatorio = new Aleatorio(1000);
        Comodin unComodin = new EfectoPuntaje(puntaje, "Panico" , "1 en 1000 de probabilidad", esAleatorio) {
            @Override
            public boolean seAplica() {
                return true;
            }
        };
        //Act
        unComodin.aplicar(unaJugada);
        int puntajeObtenido = unaJugada.calcularPuntaje();
        //Assert
        Assertions.assertEquals(puntajeEsperado, puntajeObtenido);
    }

    @Test
    public void test06SeAplicaUnComodinCombinado() {
        //Arrange
        Puntaje puntaje1 = new Puntaje(0, 2);
        NoAleatorio noEsAleatorio = new NoAleatorio();
        Comodin comodin1 = new EfectoPuntaje( puntaje1, "Doblete", "x2 de multiplicador", noEsAleatorio);
        Puntaje puntaje2 = new Puntaje(20, 1);
        Comodin comodin2 = new EfectoJugada(CartaAlta.class, puntaje2, "Algoritmos", "+20 puntos", noEsAleatorio);
        EfectoCombinado comodinCombinado = new EfectoCombinado("Maravilla", "x2 de multiplicador y +20 puntos", noEsAleatorio);
        comodinCombinado.agregar(comodin1);
        comodinCombinado.agregar(comodin2);

        List<CartaPoker> cartas = List.of(
                new CartaPoker(Valor.DOS, Palo.PICAS)
        );
        Jugada jugada = new CartaAlta(cartas);
        int puntajeEsperado = (5+20)*2;
        //Act
        comodinCombinado.aplicar(jugada);
        int puntajeCalculado = jugada.calcularPuntaje();

        //Assert
        Assertions.assertEquals(puntajeEsperado, puntajeCalculado);

    }


}
