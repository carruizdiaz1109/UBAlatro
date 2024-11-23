package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.Balatro;
import edu.fiuba.algo3.Jugador;
import edu.fiuba.algo3.Ronda;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;


public class BalatroTest {

    @Test
    public void testIniciarJuego() throws Exception {
        // Arrange
        Jugador mockJugador = mock(Jugador.class);
        Balatro balatro = new Balatro(mockJugador);
        Ronda mockRonda = mock(Ronda.class);
        when(mockRonda.verificarPuntaje()).thenReturn(true);
        Field rondasField = Balatro.class.getDeclaredField("rondas");
        rondasField.setAccessible(true);
        rondasField.set(balatro, List.of(mockRonda));

        // Act
        balatro.iniciarJuego();

        // Assert
        verify(mockJugador, atLeastOnce()).iniciarRonda(any(Ronda.class));
    }

    @Test
    public void testRondasSeCarganCorrectamente() throws Exception {
        // Arrange
        Jugador mockJugador = mock(Jugador.class);
        Balatro balatro = new Balatro(mockJugador);

        // Act
        Field rondasField = Balatro.class.getDeclaredField("rondas");
        rondasField.setAccessible(true);
        List<Ronda> rondas = (List<Ronda>) rondasField.get(balatro);

        // Assert
        assertEquals(8, rondas.size());
    }

    @Test
    public void testTodasLasRondasValidasSonJugadas() throws Exception {
        // Arrange
        Jugador mockJugador = mock(Jugador.class);
        Balatro balatro = new Balatro(mockJugador);

        Ronda mockRonda1 = mock(Ronda.class);
        when(mockRonda1.verificarPuntaje()).thenReturn(true);

        Ronda mockRonda2 = mock(Ronda.class);
        when(mockRonda2.verificarPuntaje()).thenReturn(true);

        Field rondasField = Balatro.class.getDeclaredField("rondas");
        rondasField.setAccessible(true);
        rondasField.set(balatro, List.of(mockRonda1, mockRonda2));

        // Act
        balatro.iniciarJuego();

        // Assert
        verify(mockJugador, times(2)).iniciarRonda(any(Ronda.class));
    }

    /*
    @Test
    public void testMazoSeMezclaAlInicializarse() {
        // Arrange
        Mazo mockMazo = mock(Mazo.class);
        Jugador mockJugador = mock(Jugador.class);

        // Inject the mocked mazo into Balatro
        Balatro balatro = spy(new Balatro("TestPlayer", mockJugador));
        doReturn(mockMazo).when(balatro).getMazo();

        // Act
        verify(mockMazo, times(1)).mezclar(); // Mazo's shuffle should have been called
    }
     */
}
