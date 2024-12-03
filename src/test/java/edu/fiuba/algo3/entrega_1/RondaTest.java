package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.entidades.*;
import edu.fiuba.algo3.modelo.entidades.cartas.CartaFactory;
import edu.fiuba.algo3.modelo.entidades.cartas.CartaPoker;
import edu.fiuba.algo3.modelo.entidades.jugadas.Descarte;

import edu.fiuba.algo3.modelo.excepciones.NoHayDescarteDisponiblesError;
import edu.fiuba.algo3.modelo.excepciones.NoHayJugadasDisponiblesError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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
    public void test01SeVerificaQueSePuedeSeguirJugando() {
        when(jugadaMock.calcularPuntaje()).thenReturn(2500);
        ronda.agregarJugada(jugadaMock);

        assertTrue(ronda.sePuedeSeguirJugando());
    }

    @Test
    public void test02SeJuegaUnaJugadaYSeVerificaElPuntaje() {
        when(jugadaMock.calcularPuntaje()).thenReturn(1000);

        ronda.agregarJugada(jugadaMock);
        int puntajeRonda = ronda.calcularTotalRonda();
        assertEquals(jugadaMock.calcularPuntaje(), puntajeRonda);
    }

    @Test
   public  void test03NoSePuedeSeguirJugandoSiNoQuedanJugadas() {
        when(jugadaMock.calcularPuntaje()).thenReturn(50);
        ronda.agregarJugada(jugadaMock);
        ronda.agregarJugada(jugadaMock);
        ronda.agregarJugada(jugadaMock);

        assertThrows(NoHayJugadasDisponiblesError.class, () -> ronda.agregarJugada(jugadaMock));
        assertFalse(ronda.sePuedeSeguirJugando());
    }

    @Test
    public void test05AgregarDescarteExitosamente(){
        when(descarteMock.calcularPuntaje()).thenReturn(500);
        ronda.agregarDescarte(descarteMock);

        assertEquals(descarteMock.calcularPuntaje(), ronda.calcularTotalRonda());
    }

    @Test
    void test06ErrorAgregarDescarteSinDescarteDisponibles() {
        ronda.agregarDescarte(descarteMock);
        ronda.agregarDescarte(descarteMock);
        ronda.agregarDescarte(descarteMock);

        assertThrows(NoHayDescarteDisponiblesError.class, () -> ronda.agregarDescarte(descarteMock));
    }

    @Test
    void test07CalcularTotalRondaTrasUnaJugadaYUnDescarte() {
        when(jugadaMock.calcularPuntaje()).thenReturn(1000);
        when(descarteMock.calcularPuntaje()).thenReturn(500);

        ronda.agregarJugada(jugadaMock);
        ronda.agregarDescarte(descarteMock);

        assertEquals(1500, ronda.calcularTotalRonda());
    }

    @Test
    void test08SeJuegaUnaRondaCompletaConTresManos(){
        Ronda ronda = new Ronda(1, 60, 3, 3, tiendaMock);
        ArrayList<CartaPoker> cartas = new ArrayList<>(List.of(
                CartaFactory.crearCarta(Valor.CINCO, Palo.PICAS),
                CartaFactory.crearCarta(Valor.CINCO, Palo.TREBOLES)
        ));

        Jugada jugada1 = Jugada.crearJugada(cartas);
        Jugada jugada2 = Jugada.crearJugada(cartas);
        Jugada jugada3 = Jugada.crearJugada(cartas);

        ronda.agregarJugada(jugada1);
        ronda.agregarJugada(jugada2);
        ronda.agregarJugada(jugada3);

        assertFalse(ronda.sePuedeSeguirJugando());
        assertTrue(ronda.rondaSuperada());
    }

    @Test
    void test09SeJuegaUnaRondaConTodasLasJugadasPosiblesYNoLLegaAlPuntaje(){
        Ronda ronda = new Ronda(1, 2000, 3, 3, tiendaMock);
        ArrayList<CartaPoker> cartas = new ArrayList<>(List.of(
                CartaFactory.crearCarta(Valor.CINCO, Palo.PICAS),
                CartaFactory.crearCarta(Valor.CINCO, Palo.TREBOLES)
        ));

        Jugada jugada1 = Jugada.crearJugada(cartas);
        Jugada jugada2 = Jugada.crearJugada(cartas);
        Jugada jugada3 = Jugada.crearJugada(cartas);

        ronda.agregarJugada(jugada1);
        ronda.agregarJugada(jugada2);
        ronda.agregarJugada(jugada3);

        assertFalse(ronda.rondaSuperada());
    }

}
