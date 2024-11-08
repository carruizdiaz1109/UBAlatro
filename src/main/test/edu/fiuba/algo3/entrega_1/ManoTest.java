package edu.fiuba.algo3.entrega_1;

/*
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.List;
*/

class ManoTest {
    /*
    @Mock
    Mazo mazoMock;

    @InjectMocks
    Mano mano;

    private int idCounter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mano = new Mano();
        idCounter = 1;
        when(mazoMock.darCarta()).thenAnswer(invocation -> {
            new CartaPoker(idCounter++);
            return carta;
        });
    }

    @Test
    void testLlenarManoHastaCompleta() {
        while (!mano.manoLlena()) {
            mano.recibirCarta(mazoMock.darCarta());
        }

        // Verifica que la mano esté llena
        assertTrue(mano.manoLlena());
        verify(mazoMock, times(mano.getCantidadCartasMaxima())).darCarta();
    }

    @Test
    void testDescartarYRellenarMano() {
        when(mazoMock.darCarta()).thenReturn(new CartaPoker(2, "Espadas"));
        while (!mano.manoLlena()) {
            mano.recibirCarta(mazoMock.darCarta());
        }

        List<CartaPoker> cartasSeleccionadas = mano.seleccionarCartas(4);
        mano.descartar(cartasSeleccionadas);

        assertFalse(mano.manoLlena());

        while (!mano.manoLlena()) {
            mano.recibirCarta(mazoMock.darCarta());
        }

        assertTrue(mano.manoLlena());
        verify(mazoMock, times(mano.getCantidadCartasMaxima() + 4)).darCarta();
    }

    @Test
    void testJugarCincoCartasYRellenarMano() {
        when(mazoMock.darCarta()).thenReturn(new CartaPoker(2, "Espadas"));
        while (!mano.manoLlena()) {
            mano.recibirCarta(mazoMock.darCarta());
        }

        List<CartaPoker> cartasAJugar = mano.seleccionarCartas(5);
        mano.jugar(cartasAJugar);

        // Verificar que la mano no esté completa
        assertFalse(mano.manoLlena());

        // Volver a llenar la mano
        for (int i = 0; i < 5; i++) {
            mano.recibirCarta(mazoMock.darCarta());
        }

        assertTrue(mano.manoLlena());
        verify(mazoMock, times(mano.getCantidadCartasMaxima() + 5)).darCarta();
    }
    */
}
