package edu.fiuba.algo3.entrega_1;


import edu.fiuba.algo3.modelo.entidades.*;
import edu.fiuba.algo3.modelo.entidades.comodines.EfectoJugada;
import edu.fiuba.algo3.modelo.entidades.tarots.Tarot;
import edu.fiuba.algo3.modelo.entidades.cartas.CartaFactory;
import edu.fiuba.algo3.modelo.entidades.cartas.CartaPoker;
import edu.fiuba.algo3.modelo.excepciones.TarotDistintaJugadaError;
import edu.fiuba.algo3.modelo.entidades.tarots.TarotCarta;
import edu.fiuba.algo3.modelo.entidades.tarots.TarotJugada;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TarotTest {
//    @Test
//    public void test01TarotModificaValorCarta() {
//
//        // Arrange
//        CartaPoker carta;
//        ArrayList<CartaPoker> cartas = new ArrayList<>(List.of(
//                carta = CartaFactory.crearCarta(Valor.OCHO, Palo.DIAMANTES)
//        ));
//        Puntaje puntaje = new Puntaje(10, 2);
//        Tarot tarot = new TarotCarta("El Tonto", "Mejora la mano cartas mas alta", puntaje);
//
//        // Act
//        Jugada jugada = Jugada.crearJugada(cartas);
//        tarot.aplicar(carta);
//        int puntajeCalculado = jugada.calcularPuntaje();
//
//        // Assert
//        int puntajeEsperado = (8+5+10) * (2*2);
//        Assert.assertEquals(puntajeEsperado, puntajeCalculado);
//    }


    @Test
    public void test02TarotModificaValorJugada() {

        // Arrange
        ArrayList<CartaPoker> cartas = new ArrayList<>(List.of(
                CartaFactory.crearCarta(Valor.DOS, Palo.PICAS),
                CartaFactory.crearCarta(Valor.DOS, Palo.TREBOLES)
        ));
        TarotJugada tarot = new TarotJugada("El Tonto", "Mejora la mano carta mas alta", new Puntaje(15, 2), "par");
        // Act
        tarot.aplicarNuevo();
        Jugada jugada = Jugada.crearJugada(cartas);
        int puntajeCalculado = jugada.calcularPuntaje();
        // Assert
        int puntajeEsperado = (2 + 2 + 15) * 2;
        Assert.assertEquals(puntajeEsperado, puntajeCalculado);
    }

    @Test
    public void test03AplicarTarotDeDistintoEjemplarTiraError() {

        // Arrange
        ArrayList<CartaPoker> cartas = new ArrayList<>(List.of(
                CartaFactory.crearCarta(Valor.DOS, Palo.PICAS),
                CartaFactory.crearCarta(Valor.DOS, Palo.TREBOLES)
        ));

        TarotJugada tarot = new TarotJugada("Fuerza", "Mejora la mano poker", new Puntaje(30, 3), "par");
        tarot.aplicarNuevo();
        int puntajeEsperado = (2+2+30)*3;
        Jugada jugada = Jugada.crearJugada(cartas);
        int puntajeObtenido = jugada.calcularPuntaje();

        Assert.assertEquals(puntajeEsperado, puntajeObtenido);
    }

}
