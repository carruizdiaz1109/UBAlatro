package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.*;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class Test02 {
    @Test
    public void test02VerificarQueAlJugarUnaManoSeApliqueElValorCorrespondiente(){

        List<CartaPoker> cartas = List.of(
                new CartaPoker(3, Palo.PICAS),
                new CartaPoker(7, Palo.CORAZONES),
                new CartaPoker(5, Palo.DIAMANTES)
        );
        Jugada jugada = Jugada.crearJugada(cartas);

        System.out.println("Tipo de jugada creada: " + jugada.getClass().getSimpleName());
        System.out.println("Cartas utilizadas: " + cartas);
        System.out.println("PUNTAJE¨" + jugada.getSumaValores());
        int puntajeObtenido = jugada.calcularPuntaje();
        System.out.println("Puntaje calculado: " + puntajeObtenido);
        int puntajeEsperado = 7;

        assertEquals(puntajeEsperado, puntajeObtenido);
    }
}
