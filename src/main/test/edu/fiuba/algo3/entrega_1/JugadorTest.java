package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.Jugador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

public class JugadorTest {
    @Test
    public void test01JugadorSeInicializaConNombreYPuntajeCero() {
        Jugador jugador = new Jugador("Juan");
        String nombreEsperado = "Juan";
        int puntajeEsperado = 0;

        String nombreObtenido = jugador.getNombre();
        int puntajeObtenido = jugador.getPuntaje();

        assertEquals(nombreEsperado, nombreObtenido);
        assertEquals(puntajeEsperado, puntajeObtenido);
    }
}
