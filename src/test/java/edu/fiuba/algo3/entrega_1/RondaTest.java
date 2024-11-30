package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.*;
import edu.fiuba.algo3.jugadas.Descarte;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RondaTest {

    private Ronda ronda;
    private Tienda tiendaMock;
    private Jugada jugadaMock;
    private Descarte descarteMock;

    @BeforeEach
    public void setup() {
        tiendaMock = mock(Tienda.class);
        jugadaMock = mock(Jugada.class);
        descarteMock = mock(Descarte.class);

        ronda = new Ronda(1, 3000, 3, 3, tiendaMock);
    }

    @Test
    public void test01VerificarPuntajeConPuntajeInsuficiente() {
        when(jugadaMock.calcularPuntaje()).thenReturn(2500);
        ronda.agregarJugada(jugadaMock);
        assertFalse(ronda.verificarPuntaje());
    }

    @Test
    void test02VerificarPuntajeConPuntajeSuficiente() {
        // Simular que calcularTotalRonda devuelve 3500
        when(jugadaMock.calcularPuntaje()).thenReturn(3000);
        ronda.agregarJugada(jugadaMock);
        assertTrue(ronda.verificarPuntaje());
    }

    @Test
    void test03AgregarJugadaExitosamente() {
        when(jugadaMock.calcularPuntaje()).thenReturn(1000);

        ronda.agregarJugada(jugadaMock);
        int puntajeRonda = ronda.calcularTotalRonda();
        assertEquals(1000, puntajeRonda);
    }

    @Test
    void test04ErrorAgregarJugadaSinJugadasDisponibles() {
        when(jugadaMock.calcularPuntaje()).thenReturn(500);
        ronda.agregarJugada(jugadaMock);
        ronda.agregarJugada(jugadaMock);
        ronda.agregarJugada(jugadaMock);

        assertThrows(ErrorNoHayJugadasDisponibles.class, () -> ronda.agregarJugada(jugadaMock));
        assertFalse(ronda.estadoRonda());
    }

    @Test
    void test05AgregarDescarteExitosamente(){
        when(descarteMock.calcularPuntaje()).thenReturn(500);
        ronda.agregarDescarte(descarteMock);

        assertEquals(500, ronda.calcularTotalRonda());
    }

    @Test
    void test06ErrorAgregarDescarteSinDescarteDisponibles() {
        ronda.agregarDescarte(descarteMock);
        ronda.agregarDescarte(descarteMock);
        ronda.agregarDescarte(descarteMock);

        assertThrows(ErrorNoHayDescarteDisponibles.class, () -> ronda.agregarDescarte(descarteMock));
    }

    @Test
    void test07CalcularTotalRonda() {
        when(jugadaMock.calcularPuntaje()).thenReturn(1000);
        when(descarteMock.calcularPuntaje()).thenReturn(500);

        ronda.agregarJugada(jugadaMock);
        ronda.agregarDescarte(descarteMock);

        assertEquals(1500, ronda.calcularTotalRonda());
    }

}
