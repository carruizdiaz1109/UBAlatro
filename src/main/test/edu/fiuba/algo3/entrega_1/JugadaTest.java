package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.CartaPoker;
import edu.fiuba.algo3.Jugada;
import edu.fiuba.algo3.jugadas.*;
import edu.fiuba.algo3.Palo;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class JugadaTest {

    @Test
    public void testValorCorrectoCartaAlta() {
        List<CartaPoker> cartas = List.of(
            new CartaPoker(2, Palo.PICAS),
            new CartaPoker(5, Palo.CORAZONES),
            new CartaPoker(7, Palo.DIAMANTES),
            new CartaPoker(9, Palo.TREBOLES),
            new CartaPoker(12, Palo.PICAS) // Esta es la carta más alta
        );

        Jugada jugada = Jugada.crearJugada(cartas);

        assertTrue(jugada instanceof CartaAlta, "Se esperaba una jugada de tipo CartaAlta");
    }
}
