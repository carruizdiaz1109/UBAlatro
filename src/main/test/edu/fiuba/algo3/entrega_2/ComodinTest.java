package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.*;
import edu.fiuba.algo3.Comodin;
import edu.fiuba.algo3.comodines.*;
import edu.fiuba.algo3.jugadas.*;
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
        EfectoPuntaje comodin = new EfectoPuntaje(10, 1, "Estrella Fugaz", "+10 fichines");

        // Act
        comodin.aplicar(jugada);
        int puntajeCalculado = jugada.calcularPuntaje();

        // Assert
        int puntajeEsperado = (10 + 2 + 2 + 10) * 2;
        assertEquals(puntajeEsperado, puntajeCalculado);
    }

    @Test
    public void test02SeAplicaUnComodinEscaleraAJugadaEscalera() {
        List<CartaPoker> cartas = List.of(
                new CartaPoker(Valor.DOS, Palo.PICAS),
                new CartaPoker(Valor.TRES, Palo.DIAMANTES),
                new CartaPoker(Valor.CUATRO, Palo.DIAMANTES),
                new CartaPoker(Valor.CINCO, Palo.CORAZONES),
                new CartaPoker(Valor.SEIS, Palo.PICAS)
        );
        Jugada unaJugada = Jugada.crearJugada(cartas);
        int puntajeEsperado1 = (2+3+4+5+6+30)*12;

        EfectoJugada unComodin = new EfectoJugada(Escalera.class, 0,3, "Escalinatas", "x3 en escalera");
        unComodin.aplicar(unaJugada);

        int puntajeObtenido = unaJugada.calcularPuntaje();

        assertEquals(puntajeEsperado1, puntajeObtenido);
    }

    @Test
    public void test03NoSeAplicaUnComodinJugadaSiNoEsLaJugada() {
        List<CartaPoker> cartas = List.of(
                new CartaPoker(Valor.DOS, Palo.PICAS),
                new CartaPoker(Valor.TRES, Palo.DIAMANTES),
                new CartaPoker(Valor.CUATRO, Palo.DIAMANTES),
                new CartaPoker(Valor.CINCO, Palo.CORAZONES),
                new CartaPoker(Valor.SEIS, Palo.PICAS)
        );
        Jugada unaJugada = Jugada.crearJugada(cartas);
        Comodin unComodin = new EfectoJugada(CartaAlta.class, 0,3, "" , "");
        unComodin.aplicar(unaJugada);

        int puntajeObtenido = unaJugada.calcularPuntaje();

        Assertions.assertEquals(200, puntajeObtenido);
    }

    @Test
    public void test04SeAplicaUnComodinAlPuntaje() {
        List<CartaPoker> cartas = List.of(
                new CartaPoker(Valor.DOS, Palo.PICAS),
                new CartaPoker(Valor.DOS, Palo.DIAMANTES),
                new CartaPoker(Valor.DOS, Palo.DIAMANTES),
                new CartaPoker(Valor.DOS, Palo.CORAZONES),
                new CartaPoker(Valor.SEIS, Palo.PICAS)
        );
        Jugada unaJugada = Jugada.crearJugada(cartas);
        int puntajeEsperado = (2*4+60)*7*8;
        Comodin unComodin = new EfectoPuntaje( 0,8, "", "");
        unComodin.aplicar(unaJugada);

        int puntajeObtenido = unaJugada.calcularPuntaje();

        Assertions.assertEquals(puntajeEsperado, puntajeObtenido);
    }

    @Test
    public void test05SeAplicaUnComodinAleatorio() {
        List<CartaPoker> cartas = List.of(
                new CartaPoker(Valor.DOS, Palo.PICAS),
                new CartaPoker(Valor.DOS, Palo.DIAMANTES),
                new CartaPoker(Valor.DOS, Palo.DIAMANTES),
                new CartaPoker(Valor.DOS, Palo.CORAZONES),
                new CartaPoker(Valor.SEIS, Palo.PICAS)
        );
        Jugada unaJugada = Jugada.crearJugada(cartas);
        int puntajeEsperado = (2*4+60+10)*7;
        Comodin unComodin = new EfectoAleatorio(1000, 10, 1, "" , "") {
            @Override
            public boolean seAplica() {
                return true;
            }
        };
        unComodin.aplicar(unaJugada);

        int puntajeObtenido = unaJugada.calcularPuntaje();

        Assertions.assertEquals(puntajeEsperado, puntajeObtenido);
    }


}
