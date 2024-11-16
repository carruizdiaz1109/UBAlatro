package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.Balatro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import edu.fiuba.algo3.Jugador;
import edu.fiuba.algo3.Jugada;
import edu.fiuba.algo3.Ronda;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class RondaTest {
    Jugador jugadorMock;
    Jugada jugadaMock;
    Balatro balatroMock;

    @BeforeEach
    public void setup() {
        jugadorMock = mock(Jugador.class);
        jugadaMock = mock(Jugada.class);
        balatroMock = mock(Balatro.class);
    }

    @Test
    public void test01AlIniciarRondaSeLlamaAIniciarRondaJugador() {
        Ronda ronda1 = new Ronda(jugadorMock);

        when(jugadorMock.jugar()).thenReturn(jugadaMock);
        when(jugadaMock.calcularValor()).thenReturn(600);

        ronda1.iniciarRonda();
        //verify(jugadorMock,times(1)).iniciarRonda();
    }

    @Test
    public void test02SeVerificaElPuntajeDeLaRonda() {
        Ronda ronda1 = new Ronda(jugadorMock);

        when(jugadorMock.jugar()).thenReturn(jugadaMock);
        when(jugadaMock.calcularValor()).thenReturn(600);

        ronda1.iniciarRonda();
        assertTrue(ronda1.verificarPuntaje());
    }

}
