package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.*;
import edu.fiuba.algo3.comodines.EfectoDescarte;
import edu.fiuba.algo3.comodines.EfectoJugada;
import edu.fiuba.algo3.comodines.EfectoPuntaje;
import edu.fiuba.algo3.jugadas.Descarte;
import edu.fiuba.algo3.jugadas.Escalera;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class IntegracionTest2 {
    @Test
    public void test01SeVerificaQueSeApliqueComodinAEscaleraCorrectamente () {
        Mazo mazoMock = mock(Mazo.class);
        when(mazoMock.tieneCartas()).thenReturn(true);

        Ronda ronda = new Ronda(1,10000,3,3);
        CartaPoker carta1 = new CartaPoker(Valor.DOS, Palo.PICAS);
        CartaPoker carta2 = new CartaPoker(Valor.TRES, Palo.DIAMANTES);
        CartaPoker carta3 = new CartaPoker(Valor.CUATRO, Palo.DIAMANTES);
        CartaPoker carta4 = new CartaPoker(Valor.CINCO, Palo.CORAZONES);
        CartaPoker carta5 = new CartaPoker(Valor.SEIS, Palo.PICAS);

        ArrayList<CartaPoker> cartas = new ArrayList<>(List.of(carta1, carta2, carta3, carta4, carta5));
        Mano mano = new Mano(cartas);

        Jugador jugador = new Jugador("Pepe", mazoMock) {
            @Override
            public void iniciarRonda(Ronda rondaActual){
                this.rondaActual = ronda;
                this.mazo = mazoMock;
                this.manoActual = mano;
            }
        };
        jugador.iniciarRonda(ronda);

        EfectoJugada unComodin = new EfectoJugada(Escalera.class, 0,3);

        jugador.seleccionarCarta(carta1);
        jugador.seleccionarCarta(carta2);
        jugador.seleccionarCarta(carta3);
        jugador.seleccionarCarta(carta4);
        jugador.seleccionarCarta(carta5);

        int puntajeEsperado1 = (2+3+4+5+6+30)*12;

        jugador.aniadirComodin(unComodin);
        jugador.jugar();

        int puntajeObtenido = ronda.calcularTotalRonda();

        assertEquals(puntajeEsperado1, puntajeObtenido);
    }

    @Test
    public void test02SeVerificaQueSeAplicaComodinPuntajeCorrectamente() {
        Mazo mazoMock = mock(Mazo.class);
        when(mazoMock.tieneCartas()).thenReturn(true);
        Ronda ronda = new Ronda(1,10000,3,3);
        CartaPoker carta1 = new CartaPoker(Valor.DOS, Palo.PICAS);
        CartaPoker carta2 = new CartaPoker(Valor.DOS, Palo.DIAMANTES);
        CartaPoker carta3 = new CartaPoker(Valor.DOS, Palo.DIAMANTES);
        CartaPoker carta4 = new CartaPoker(Valor.DOS, Palo.CORAZONES);
        CartaPoker carta5 = new CartaPoker(Valor.SEIS, Palo.PICAS);
        ArrayList<CartaPoker> cartas = new ArrayList<>(List.of(carta1, carta2, carta3, carta4, carta5));
        Mano mano = new Mano(cartas);
        Jugador jugador = new Jugador("Pepe", mazoMock) {
            @Override
            public void iniciarRonda(Ronda rondaActual){
                this.rondaActual = ronda;
                this.mazo = mazoMock;
                this.manoActual = mano;
            }
        };
        jugador.iniciarRonda(ronda);
        Jugada unaJugada = Jugada.crearJugada(cartas);
        int puntajeEsperado = (2*4+60)*7*8;

        Comodin unComodin = new EfectoPuntaje( 0,8);
        jugador.seleccionarCarta(carta1);
        jugador.seleccionarCarta(carta2);
        jugador.seleccionarCarta(carta3);
        jugador.seleccionarCarta(carta4);
        jugador.seleccionarCarta(carta5);
        unComodin.aplicar(unaJugada);

        jugador.aniadirComodin(unComodin);
        jugador.jugar();

        int puntajeObtenido = ronda.calcularTotalRonda();

        assertEquals(puntajeEsperado, puntajeObtenido);
    }

    @Test
    public void test03SeVerificaQueSeAplicaComodinDescarteCorrectamente () {
        Mazo mazoMock = mock(Mazo.class);
        when(mazoMock.tieneCartas()).thenReturn(true);
        Ronda ronda = new Ronda(1,10000,3,3);
        CartaPoker carta1 = new CartaPoker(Valor.DOS, Palo.PICAS);
        CartaPoker carta2 = new CartaPoker(Valor.DOS, Palo.DIAMANTES);
        CartaPoker carta3 = new CartaPoker(Valor.DOS, Palo.DIAMANTES);
        CartaPoker carta4 = new CartaPoker(Valor.DOS, Palo.CORAZONES);
        CartaPoker carta5 = new CartaPoker(Valor.SEIS, Palo.PICAS);
        ArrayList<CartaPoker> cartas = new ArrayList<>(List.of(carta1, carta2, carta3, carta4, carta5));
        Mano mano = new Mano(cartas);
        Jugador jugador = new Jugador("Pepe", mazoMock) {
            @Override
            public void iniciarRonda(Ronda rondaActual){
                this.rondaActual = ronda;
                this.mazo = mazoMock;
                this.manoActual = mano;
            }
        };
        jugador.iniciarRonda(ronda);
        int puntajeEsperado = 10;

        Comodin unComodin = new EfectoDescarte(Descarte.class, 10,1);
        jugador.seleccionarCarta(carta1);
        jugador.seleccionarCarta(carta2);
        jugador.seleccionarCarta(carta3);
        jugador.seleccionarCarta(carta4);
        jugador.seleccionarCarta(carta5);

        jugador.aniadirComodin(unComodin);
        jugador.descartar();

        int puntajeObtenido = ronda.calcularTotalRonda();

        assertEquals(puntajeEsperado, puntajeObtenido);
    }
}
