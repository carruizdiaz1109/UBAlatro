package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MazoTest {
    @Test
    public void test01CantidadDeCartasEnMazoInicializado(){
        Mazo mazo = new Mazo();

        int cantidadCartasEsperadas = 52;
        int cantidadCartasObtenidas = 0;

        cantidadCartasObtenidas = mazo.getCartas().size();

        assertEquals(cantidadCartasEsperadas, cantidadCartasObtenidas);
    }

    @Test
    public void test02DarCartaReduceCantidad(){
        int cantidadEsperada = 51;
        int cantidadFinal = 0;

        Mazo mazo = new Mazo();
        mazo.darCarta();
        cantidadFinal = mazo.getCartas().size();

        assertEquals(cantidadFinal, cantidadEsperada);
    }

    @Test
    public void test03DarCartaDevuelvePrimerCarta(){
        Mazo mazo = new Mazo();
        List<CartaPoker> cartas = mazo.getCartas();
        CartaPoker cartaEsperada = cartas.get(0);
        CartaPoker cartaObtenida = mazo.darCarta();

        assertEquals(cartaEsperada, cartaObtenida);
    }

    @Test
    public void test04DarCartaConMazoVacioTiraError(){
        Mazo mazo = new Mazo();
        while (!mazo.getCartas().isEmpty()) {
            mazo.darCarta();
        }
        assertThrows(ErrorMazoVacio.class, mazo::darCarta);
    }

    @Test
    public void test06GuardarCartaAumentaCantidad(){
        Mazo mazo = new Mazo();
        int cantidadEsperada = 50;
        int cantidadObtenida = 0;

        mazo.darCarta();
        mazo.darCarta();
        CartaPoker cartaObtenida = mazo.darCarta();

        mazo.agregarCarta(cartaObtenida);

        cantidadObtenida = mazo.getCartas().size();

        assertEquals(cantidadEsperada, cantidadObtenida);

    }

    @Test
    public void test07GuardarCartaColocaAlFinalDelMazoCarta(){
        Mazo mazo = new Mazo();

        CartaPoker cartaObtenida = mazo.darCarta();
        mazo.agregarCarta(cartaObtenida);
        List<CartaPoker> cartas = mazo.getCartas();

        CartaPoker ultimaCarta = cartas.get(cartas.size() - 1);

        assertEquals(ultimaCarta, cartaObtenida);
    }

    @Test
    public void test08CompararMazosIguales(){
        ArrayList<CartaPoker> cartas1 = new ArrayList<>(List.of(
                new CartaPoker(Valor.TRES, Palo.PICAS),
                new CartaPoker(Valor.SIETE, Palo.CORAZONES),
                new CartaPoker(Valor.CINCO, Palo.DIAMANTES)
        ));


        ArrayList<CartaPoker> cartas2 = new ArrayList<>(List.of(
                new CartaPoker(Valor.TRES, Palo.PICAS),
                new CartaPoker(Valor.SIETE, Palo.CORAZONES),
                new CartaPoker(Valor.CINCO, Palo.DIAMANTES)
        ));


        Mazo mazo1 = new Mazo(cartas1);
        Mazo mazo2 = new Mazo(cartas2);


        assertTrue(mazo1.compararCon(mazo2));
    }


    @Test
    public void test09CompararMazosDistintaCatidadCartas(){
        ArrayList<CartaPoker> cartas1 = new ArrayList<CartaPoker>(List.of(
                new CartaPoker(Valor.TRES, Palo.PICAS),
                new CartaPoker(Valor.SIETE, Palo.CORAZONES),
                new CartaPoker(Valor.CINCO, Palo.DIAMANTES)
        ));


        ArrayList<CartaPoker> cartas2 = new ArrayList<CartaPoker>(List.of(
                new CartaPoker(Valor.TRES, Palo.PICAS),
                new CartaPoker(Valor.SIETE, Palo.CORAZONES)
        ));


        Mazo mazo1 = new Mazo(cartas1);
        Mazo mazo2 = new Mazo(cartas2);


        assertFalse(mazo1.compararCon(mazo2));
    }


    @Test
    public void test10CompararMazosDistintos(){
        ArrayList<CartaPoker> cartas1 = new ArrayList<CartaPoker>(List.of(
                new CartaPoker(Valor.TRES, Palo.PICAS),
                new CartaPoker(Valor.SIETE, Palo.CORAZONES),
                new CartaPoker(Valor.CINCO, Palo.DIAMANTES)
        ));


        ArrayList<CartaPoker> cartas2 = new ArrayList<CartaPoker>(List.of(
                new CartaPoker(Valor.TRES, Palo.PICAS),
                new CartaPoker(Valor.DOS, Palo.CORAZONES),
                new CartaPoker(Valor.CINCO, Palo.DIAMANTES)
        ));


        Mazo mazo1 = new Mazo(cartas1);
        Mazo mazo2 = new Mazo(cartas2);


        assertFalse(mazo1.compararCon(mazo2));
    }

    @Test
    public void test11SeVerificaQueSentercambiaConElDeDescarteCorrectamente() {
        ArrayList<CartaPoker> cartas = new ArrayList<CartaPoker>(List.of(
                new CartaPoker(Valor.TRES, Palo.PICAS),
                new CartaPoker(Valor.SIETE, Palo.CORAZONES)
        ));
        Mazo mazo1 = new Mazo(cartas);
        Mazo mazo2 = new Mazo(cartas);
        CartaPoker carta1 = mazo1.darCarta();
        CartaPoker carta2 = mazo1.darCarta();

        mazo1.recargarMazo();

        assert(mazo2.compararCon(mazo2));
    }

}
