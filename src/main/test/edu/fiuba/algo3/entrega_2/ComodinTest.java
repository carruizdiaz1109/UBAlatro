package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.*;
import edu.fiuba.algo3.comodines.*;
import edu.fiuba.algo3.jugadas.CartaAlta;
import edu.fiuba.algo3.jugadas.Escalera;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;

public class ComodinTest {

    @Test
    public void test01SeAplicaUnComodinEscaleraAJugadaEscalera() {
        List<CartaPoker> cartas = List.of(
                new CartaPoker(Valor.DOS, Palo.PICAS),
                new CartaPoker(Valor.TRES, Palo.DIAMANTES),
                new CartaPoker(Valor.CUATRO, Palo.DIAMANTES),
                new CartaPoker(Valor.CINCO, Palo.CORAZONES),
                new CartaPoker(Valor.SEIS, Palo.PICAS)
        );
        Jugada unaJugada = Jugada.crearJugada(cartas);
        int puntajeEsperado1 = (2+3+4+5+6+30)*12;

        EfectoJugada unComodin = new EfectoJugada(Escalera.class, 0,3);
        unComodin.aplicar(unaJugada);

        int puntajeObtenido = unaJugada.calcularPuntaje();

        Assertions.assertEquals(puntajeEsperado1, puntajeObtenido);
    }

    @Test
    public void test02NoSeAplicaUnComodinJugadaSiNoEsLaJugada() {
        List<CartaPoker> cartas = List.of(
                new CartaPoker(Valor.DOS, Palo.PICAS),
                new CartaPoker(Valor.TRES, Palo.DIAMANTES),
                new CartaPoker(Valor.CUATRO, Palo.DIAMANTES),
                new CartaPoker(Valor.CINCO, Palo.CORAZONES),
                new CartaPoker(Valor.SEIS, Palo.PICAS)
        );
        Jugada unaJugada = Jugada.crearJugada(cartas);
        Comodin unComodin = new EfectoJugada(CartaAlta.class, 0,3);
        unComodin.aplicar(unaJugada);

        int puntajeObtenido = unaJugada.calcularPuntaje();

        Assertions.assertEquals(200, puntajeObtenido);
    }

    @Test
    public void test03SeAplicaUnComodinAlPuntaje() {
        List<CartaPoker> cartas = List.of(
                new CartaPoker(Valor.DOS, Palo.PICAS),
                new CartaPoker(Valor.DOS, Palo.DIAMANTES),
                new CartaPoker(Valor.DOS, Palo.DIAMANTES),
                new CartaPoker(Valor.DOS, Palo.CORAZONES),
                new CartaPoker(Valor.SEIS, Palo.PICAS)
        );
        Jugada unaJugada = Jugada.crearJugada(cartas);
        int puntajeEsperado = (2*4+60)*7*8;
        Comodin unComodin = new EfectoPuntaje( 0,8);
        unComodin.aplicar(unaJugada);

        int puntajeObtenido = unaJugada.calcularPuntaje();

        Assertions.assertEquals(puntajeEsperado, puntajeObtenido);
    }

    @Test
    public void test04SeAplicaUnComodinAleatorio() {
        List<CartaPoker> cartas = List.of(
                new CartaPoker(Valor.DOS, Palo.PICAS),
                new CartaPoker(Valor.DOS, Palo.DIAMANTES),
                new CartaPoker(Valor.DOS, Palo.DIAMANTES),
                new CartaPoker(Valor.DOS, Palo.CORAZONES),
                new CartaPoker(Valor.SEIS, Palo.PICAS)
        );
        Jugada unaJugada = Jugada.crearJugada(cartas);
        int puntajeEsperado = (2*4+60+10)*7;
        Comodin unComodin = new EfectoAleatorio(1000, 10, 1) {
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
