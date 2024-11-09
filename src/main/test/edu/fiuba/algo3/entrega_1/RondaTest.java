package edu.fiuba.algo3.entrega_1;

import org.junit.jupiter.api.Test;
import edu.fiuba.algo3.Jugador;
import edu.fiuba.algo3.Jugada;
import edu.fiuba.algo3.Ronda;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class RondaTest {
    @Test
    public void test01AlIniciarRondaSeLlamaAIniciarRondaJugador() {
        Jugador jugadorMock = mock(Jugador.class);
        Jugada jugadaMock = mock(Jugada.class);
        Ronda ronda1 = new Ronda(jugadorMock);

        when(jugadorMock.jugar()).thenReturn(jugadaMock);
        when(jugadaMock.calcularPuntaje()).thenReturn(600);

        ronda1.iniciarRonda();
        verify(jugadorMock,times(1)).iniciarRonda();
    }

    @Test
    public void test02SeVerificaElPuntajeDeLaRonda() {
        Jugador jugadorMock = mock(Jugador.class);
        Jugada jugadaMock = mock(Jugada.class);
        Ronda ronda1 = new Ronda(jugadorMock);

        when(jugadorMock.jugar()).thenReturn(jugadaMock);
        when(jugadaMock.calcularPuntaje()).thenReturn(600);

        ronda1.iniciarRonda();
        assertTrue(ronda1.verificarPuntaje());
    }


}
