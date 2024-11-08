package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.CartaPoker;
import edu.fiuba.algo3.Puntaje;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

public class PuntajeTest {

    @Test
    public void test01SeCreaUnPuntajeYSeCalculaElValor() {
        Puntaje puntaje = new Puntaje(3,6);
        int resultadoEsperado = 18;
        assertEquals(resultadoEsperado, puntaje.calcularPuntaje());
    }

    @Test
    public void test02SeModificaElValorDelPuntaje(){
        Puntaje puntaje = new Puntaje(3,2);
        puntaje.incrementarPuntos(4);
        int resultadoEsperado = 14;

        assertEquals(resultadoEsperado, puntaje.calcularPuntaje());
    }

    @Test
    public void test03SeModificaElMultiplicadorDelPuntaje(){
        Puntaje puntaje = new Puntaje(3,2);
        puntaje.incrementarMultiplicador(3);
        int resultadoEsperado = 15;

        assertEquals(resultadoEsperado, puntaje.calcularPuntaje());
    }


}