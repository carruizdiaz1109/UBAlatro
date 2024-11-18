package edu.fiuba.algo3.entrega_1;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import edu.fiuba.algo3.*;

import edu.fiuba.algo3.jugadas.Descarte;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

class ManoTest {

    @Mock
    Mazo mazoMock;

    Mano mano;

    private int idCounter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mano = new Mano(new ArrayList<>());
        idCounter = 1;

        when(mazoMock.darCarta()).thenAnswer(invocation -> {
            Valor valor = Valor.CUATRO;
            Palo palo = Palo.PICAS;
            return new CartaPoker(valor, palo);
        });
    }

    @Test
    void testLlenarManoHastaCompleta() {
        while (!mano.manoLlena()) {
            mano.agregarCarta(mazoMock.darCarta());
        }
        Assertions.assertTrue(mano.manoLlena());
    }

    @Test
    void testDescartarYRellenarMano() {

        CartaPoker c1 = new CartaPoker(Valor.CINCO, Palo.PICAS);
        CartaPoker c2 = new CartaPoker(Valor.CINCO, Palo.TREBOLES);
        CartaPoker c3 = new CartaPoker(Valor.OCHO, Palo.DIAMANTES);
        CartaPoker c4 = new CartaPoker(Valor.DOS, Palo.CORAZONES);
        CartaPoker c5 = new CartaPoker(Valor.TRES, Palo.PICAS);

        Mano mano = new Mano(new ArrayList<>(List.of(c1, c2, c3, c4, c5)));

        mano.seleccionarCarta(c1);
        mano.seleccionarCarta(c3);

        mano.descartar();

        while (!mano.manoLlena()) {
            mano.agregarCarta(mazoMock.darCarta());
        }

        Assertions.assertTrue(mano.manoLlena());
    }

    @Test
    public void test03CompararManosIguales(){
        ArrayList<CartaPoker> cartas1 = new ArrayList<CartaPoker>(List.of(
                new CartaPoker(Valor.TRES, Palo.PICAS),
                new CartaPoker(Valor.SIETE, Palo.CORAZONES),
                new CartaPoker(Valor.CINCO, Palo.DIAMANTES)
        ));

        ArrayList<CartaPoker> cartas2 = new ArrayList<CartaPoker>(List.of(
                new CartaPoker(Valor.TRES, Palo.PICAS),
                new CartaPoker(Valor.SIETE, Palo.CORAZONES),
                new CartaPoker(Valor.CINCO, Palo.DIAMANTES)
        ));

        Mano mano1 = new Mano(cartas1);
        Mano mano2 = new Mano(cartas2);

        assert(mano1.compararCon(mano2));
    }

    @Test
    public void test04CompararManosDistintas(){
        ArrayList<CartaPoker> cartas1 = new ArrayList<CartaPoker>(List.of(
                new CartaPoker(Valor.TRES, Palo.PICAS),
                new CartaPoker(Valor.SIETE, Palo.CORAZONES),
                new CartaPoker(Valor.CUATRO, Palo.DIAMANTES)
        ));

        ArrayList<CartaPoker> cartas2 = new ArrayList<CartaPoker>(List.of(
                new CartaPoker(Valor.TRES, Palo.PICAS),
                new CartaPoker(Valor.SIETE, Palo.CORAZONES),
                new CartaPoker(Valor.CINCO, Palo.DIAMANTES)
        ));

        Mano mano1 = new Mano(cartas1);
        Mano mano2 = new Mano(cartas2);

        assertFalse(mano1.compararCon(mano2));
    }

    @Test
    public void test05CompararCartasSeleccionadas(){
        ArrayList<CartaPoker> cartasSeleccionadas = new ArrayList<CartaPoker>(List.of(
                new CartaPoker(Valor.TRES, Palo.PICAS)
        ));

        ArrayList<CartaPoker> cartas = new ArrayList<CartaPoker>(List.of(
                new CartaPoker(Valor.TRES, Palo.PICAS),
                new CartaPoker(Valor.SIETE, Palo.CORAZONES),
                new CartaPoker(Valor.CUATRO, Palo.DIAMANTES)
        ));

        Mano mano = new Mano(cartas);
        CartaPoker cartaSeleccionada = mano.getCartas().get(0);

        mano.seleccionarCarta(cartaSeleccionada);

        assert(mano.compararSeleccionadasCon(cartasSeleccionadas));
    }

    @Test
    public void test06SeDescartanCartas() {
        ArrayList<CartaPoker> cartas = new ArrayList<CartaPoker>(List.of(
                new CartaPoker(Valor.TRES, Palo.PICAS),
                new CartaPoker(Valor.SIETE, Palo.CORAZONES),
                new CartaPoker(Valor.CUATRO, Palo.DIAMANTES)
        ));

        Mano mano = new Mano(cartas);
        mano.seleccionarCarta(new CartaPoker(Valor.TRES, Palo.PICAS));
        mano.seleccionarCarta(new CartaPoker(Valor.SIETE, Palo.CORAZONES));
        assertNotNull(mano.descartar());
    }

}
