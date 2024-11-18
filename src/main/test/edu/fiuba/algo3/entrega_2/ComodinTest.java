package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.*;
import edu.fiuba.algo3.comodines.*;
import edu.fiuba.algo3.jugadas.Escalera;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;

public class ComodinTest {

    @Test
    public void test01SeAplicaUnComodinAJugada() {
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

        assertEquals(puntajeEsperado1, puntajeObtenido);
    }


}
