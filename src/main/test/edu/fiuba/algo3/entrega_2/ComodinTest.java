package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.*;
import edu.fiuba.algo3.comodines.*;
import edu.fiuba.algo3.jugadas.CartaAlta;
import edu.fiuba.algo3.jugadas.Escalera;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ComodinTest {


    @Test
    public void test01SeAplicaUnComodinAJugada() {
        List<CartaPoker> cartas = List.of(
                new CartaPoker(Valor.NUEVE, Palo.PICAS)
        );
        Jugada unaJugada = new CartaAlta(cartas);
        Jugada otraJugada = new CartaAlta(cartas);

        int puntajeEsperado1 = unaJugada.calcularPuntaje() * 3;
        int puntajeEsperado2 = otraJugada.calcularPuntaje() * 3;

        EfectoJugada unComodin = new EfectoJugada(unaJugada, 1,3);
        unComodin.aplicar(unaJugada);
        unComodin.aplicar(otraJugada);

        int puntajeObtenido = unaJugada.calcularPuntaje();
        int otroPuntaje = otraJugada.calcularPuntaje();
        assertEquals(puntajeEsperado1, puntajeObtenido);
        assertEquals(puntajeEsperado2, otroPuntaje);

    }

    @Test
    public void test02() {
        List<CartaPoker> cartas = List.of(
                new CartaPoker(Valor.NUEVE, Palo.PICAS)
        );
        Jugada unaJugada = new CartaAlta(cartas);
        int puntajeEsperado = unaJugada.calcularPuntaje() + 10;
        ComandoComodin comandoJugada = new ComandoComodinJugada(unaJugada, 10,1);
        GestorComodines gestor = new GestorComodines();
        gestor.agregarComando(comandoJugada);
        gestor.ejecutarComandos();

        int puntajeObtenido = unaJugada.calcularPuntaje();

        assertEquals(puntajeEsperado, puntajeObtenido);

    }
    /*-----------------------------------------------------------------------------------------------*/
    @Test
    public void test01ComodinSumaMultiplicadorCorrecto() {
        Puntaje puntaje = new Puntaje(10, 1);
        EfectoPuntaje efecto = new EfectoPuntaje(0, 8);
        efecto.aplicar(puntaje);

        int puntajeEsperado = 10 * 8;
        int puntajeObtenido = puntaje.calcularPuntaje();

        assertEquals(puntajeEsperado, puntajeObtenido);
    }

    @Test
    public void test02ComodinEscalera() {
        List<CartaPoker> cartas = List.of(
                new CartaPoker(Valor.CUATRO, Palo.TREBOLES),
                new CartaPoker(Valor.CINCO, Palo.TREBOLES),
                new CartaPoker(Valor.SEIS, Palo.TREBOLES),
                new CartaPoker(Valor.SIETE, Palo.TREBOLES),
                new CartaPoker(Valor.OCHO, Palo.TREBOLES)
        );

        Jugada escalera = new Escalera(cartas);
        ComandoComodin comando = new ComandoComodinJugada(escalera, 1, 3);
        GestorComodines gestor = new GestorComodines();
        gestor.agregarComando(comando);
        gestor.ejecutarComandos();

        int puntajeEsperado = 30 * 12; // Base: 30 puntos con multiplicador 4
        int puntajeObtenido = escalera.calcularPuntaje();//120
        assertEquals(puntajeEsperado, puntajeObtenido);
    }

    @Test
    public void test03ComodinDescartes() {
        Puntaje puntaje = new Puntaje(0, 1);
        EfectoDescarte efecto = new EfectoDescarte(10, 0);

        efecto.aplicar(puntaje, 3); // Tres descartes

        int puntajeEsperado = 30; // 10 puntos por cada descarte
        int puntajeObtenido = puntaje.calcularPuntaje();

        assertEquals(puntajeEsperado, puntajeObtenido);
    }


    /*
    @Test
    public void comodinAfectaPuntosDePuntaje() {

        // Arrange
        ArrayList<CartaPoker> cartas = new ArrayList<>(List.of(
            new CartaPoker(2, Palo.PICAS),
            new CartaPoker(2, Palo.TREBOLES)
        ));
        Jugada jugada = Jugada.crearJugada(cartas);
        Comodin comodin = new ComodinPuntaje(10, 1);

        // Act
        comodin.aplicar(jugada);
        int puntajeCalculado = jugada.calcularPuntaje();

        // Assert
        int puntajeEsperado = (10 + 2 + 2 + 10) * 2;
        assertEquals(puntajeEsperado, puntajeCalculado);

    @Test
    public void comodinAfectaMultiplciadorDePuntaje() {

    }*/


}
