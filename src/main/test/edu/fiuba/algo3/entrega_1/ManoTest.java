package edu.fiuba.algo3.entrega_1;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import edu.fiuba.algo3.Mano;
import edu.fiuba.algo3.CartaPoker;
import edu.fiuba.algo3.Mazo;
import edu.fiuba.algo3.Palo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

class ManoTest {

    @Mock
    Mazo mazoMock;

    Mano mano;

    private int idCounter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mano = new Mano(new ArrayList<>());
        idCounter = 1;

        when(mazoMock.darCarta()).thenAnswer(invocation -> {
            int valor = idCounter++;
            Palo palo = Palo.PICAS;
            return new CartaPoker(valor, palo);
        });
    }

    @Test
    void testLlenarManoHastaCompleta() {
        while (!mano.manoLlena()) {
            mano.agregarCarta(mazoMock.darCarta());
        }
        Assertions.assertTrue(mano.manoLlena());
    }

    @Test
    void testDescartarYRellenarMano() {

        CartaPoker c1 = new CartaPoker(5, Palo.PICAS);
        CartaPoker c2 = new CartaPoker(5, Palo.TREBOLES);
        CartaPoker c3 = new CartaPoker(8, Palo.DIAMANTES);
        CartaPoker c4 = new CartaPoker(2, Palo.CORAZONES);
        CartaPoker c5 = new CartaPoker(3, Palo.PICAS);

        Mano mano = new Mano(new ArrayList<>(List.of(c1, c2, c3, c4, c5)));

        mano.seleccionarCarta(c1);
        mano.seleccionarCarta(c3);

        mano.descartar();

        while (!mano.manoLlena()) {
            mano.agregarCarta(mazoMock.darCarta());
        }

        Assertions.assertTrue(mano.manoLlena());
    }

    /*
    @Test
    void testOrdenarCartas() {
    }
    */

}
