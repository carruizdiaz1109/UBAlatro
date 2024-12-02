package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.entidades.Balatro;
import edu.fiuba.algo3.modelo.entidades.Ronda;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;


public class BalatroTest {

    @Test
    public void testRondasSeCarganCorrectamente() throws Exception {
        // Arrange
        Balatro balatro = new Balatro("Javier");

        // Act
        Field rondasField = Balatro.class.getDeclaredField("rondas");
        rondasField.setAccessible(true);
        List<Ronda> rondas = (List<Ronda>) rondasField.get(balatro);

        // Assert
        assertEquals(8, rondas.size());
    }

    @Test
    public void test02BalatroSeIniciaCorrectamente() throws Exception {
        // Arrange
        Balatro balatro = new Balatro("Javier");

        Ronda mockRonda1 = mock(Ronda.class);
        when(mockRonda1.rondaSuperada()).thenReturn(true);
        Ronda mockRonda2 = mock(Ronda.class);
        when(mockRonda2.rondaSuperada()).thenReturn(true);

        Field rondasField = Balatro.class.getDeclaredField("rondas");
        rondasField.setAccessible(true);
        rondasField.set(balatro, List.of(mockRonda1, mockRonda2));

        // Act
        balatro.jugarRonda();
        balatro.verificarEstadoJuego();
        balatro.avanzarRonda();
        balatro.jugarRonda();
        balatro.verificarEstadoJuego();
        assertEquals(Balatro.EstadoJuego.GANADO, balatro.getEstadoJuego());
    }

    @Test
    public void test03BalatroTieneEstadoPerdidoCuandoSePierdeRonda() throws Exception {
        // Arrange
        Balatro balatro = new Balatro("Javier");

        Ronda mockRonda1 = mock(Ronda.class);
        when(mockRonda1.rondaSuperada()).thenReturn(false);

        Field rondasField = Balatro.class.getDeclaredField("rondas");
        rondasField.setAccessible(true);
        rondasField.set(balatro, List.of(mockRonda1));

        // Act
        balatro.jugarRonda();
        balatro.verificarEstadoJuego();
        assertEquals(Balatro.EstadoJuego.PERDIDO, balatro.getEstadoJuego());
    }
}
