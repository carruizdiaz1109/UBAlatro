package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.Jugador;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class JugadorTest {

    @Test
    public void test01JugadorSeInicializaConNombreCorrecto() {
        Jugador jugador = new Jugador("Juan");
        String nombreEsperado = "Juan";

        assertTrue(jugador.esMismoNombre(nombreEsperado));
    }

    @Test
    public void test02JugadorSeInicializaConPuntajeCorrecto() {
        Jugador jugador = new Jugador("Juan");
        int puntajeEsperado = 0;

        assertTrue(jugador.esMismoPuntaje(puntajeEsperado));
    }
}