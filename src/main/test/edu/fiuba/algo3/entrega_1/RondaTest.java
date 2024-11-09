package edu.fiuba.algo3.entrega_1;

import org.junit.jupiter.api.Test;
import edu.fiuba.algo3.Jugador;
import edu.fiuba.algo3.Ronda;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

public class RondaTest {
    @Test
    public void test01AlIniciarRondaSeLlamaAIniciarRondaJugador() {
        Jugador jugadorMock = mock(Jugador.class);
        Ronda ronda1 = new Ronda(jugadorMock);

        ronda1.iniciaronda();
        verify(jugadorMock,times(1)).iniciarRonda();
    }


}
