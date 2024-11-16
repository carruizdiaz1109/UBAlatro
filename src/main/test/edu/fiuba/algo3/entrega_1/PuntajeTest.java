package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.CartaPoker;
import edu.fiuba.algo3.Puntaje;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PuntajeTest {

    @Test
    public void test01SeCreaUnPuntajeYSeCalculaElValor() {
        Puntaje puntajeEsperado = new Puntaje(18, 1);
        Puntaje puntaje = new Puntaje(3,6);
        int puntajeObtenido = puntaje.calcularValor();

        assert(puntajeEsperado.compararPuntajecon(puntajeObtenido));
    }

    @Test
    public void test02SeModificaElValorDelPuntaje(){
        Puntaje puntajeEsperado = new Puntaje(14, 1);
        Puntaje puntaje = new Puntaje(3,2);
        puntaje.incrementarPuntos(4);

        int puntajeObtenido = puntaje.calcularValor();

        assert(puntajeEsperado.compararPuntajecon(puntajeObtenido));
    }

    @Test
    public void test03SeModificaElMultiplicadorDelPuntaje(){
        Puntaje puntajeEsperado = new Puntaje(18, 1);
        Puntaje puntaje = new Puntaje(3,2);
        puntaje.incrementarMultiplicador(3);

        int puntajeObtenido = puntaje.calcularValor();

        assert(puntajeEsperado.compararPuntajecon(puntajeObtenido));
    }

    @Test
    public void test04CompararDosPuntajesIguales(){
        Puntaje puntaje1 = new Puntaje(3,2);
        Puntaje puntaje2 = new Puntaje(3,2);

        assert(puntaje1.compararPuntajecon(puntaje2));
    }

    @Test
    public void test05CompararDosPuntajesDistintos(){
        Puntaje puntaje1 = new Puntaje(2,2);
        Puntaje puntaje2 = new Puntaje(3,1);

        assertFalse(puntaje1.compararPuntajecon(puntaje2));
    }


}