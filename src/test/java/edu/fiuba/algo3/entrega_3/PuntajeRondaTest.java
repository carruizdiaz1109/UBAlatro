package edu.fiuba.algo3.entrega_3;

import edu.fiuba.algo3.modelo.entidades.*;
import edu.fiuba.algo3.modelo.entidades.cartas.CartaFactory;
import edu.fiuba.algo3.modelo.entidades.cartas.CartaPoker;
import edu.fiuba.algo3.modelo.entidades.comodines.EfectoJugada;
import edu.fiuba.algo3.modelo.entidades.comodines.NoAleatorio;
import edu.fiuba.algo3.modelo.entidades.jugadas.Descarte;
import edu.fiuba.algo3.modelo.entidades.jugadas.Escalera;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class PuntajeRondaTest {

    private Ronda ronda;
    private Tienda tiendaMock;
    private Jugada jugadaMock;
    private Descarte descarteMock;
    private Mazo mazoMock;

    @Before
    public void setup() {
        tiendaMock = mock(Tienda.class);
        jugadaMock = mock(Jugada.class);
        descarteMock = mock(Descarte.class);
        mazoMock = mock(Mazo.class);
        ronda = new Ronda(1, 3000, 3, 3, tiendaMock);
    }


    @Test
    public void test1SeJuegaCartaAltaYSeVerificaElPuntajeDeLaRonda(){
        //Arrange
        CartaPoker carta1 = CartaFactory.crearCarta(Valor.DOS, Palo.PICAS);
        ArrayList<CartaPoker> cartas = new ArrayList<>(List.of(carta1));
        Mano mano = new Mano(mazoMock);
        Jugador jugador = new Jugador("Pepe", mazoMock) {
            @Override
            public void iniciarRonda(Ronda rondaActual){
                this.rondaActual = rondaActual;
                this.mazo = mazoMock;
                this.manoActual = mano;
            }
        };

        int puntajeEsperado1 = 7;
        jugador.iniciarRonda(ronda);
        jugador.seleccionarCarta(cartas);

        //Act
        jugador.jugar();
        int puntajeObtenido = ronda.calcularTotalRonda();
        //Assert
        assertEquals(puntajeEsperado1, puntajeObtenido);
    }

    @Test
    public void test02SeJuegaCartaAltaConComodinYSeVerificaElPuntaje() {

    }

}
