package edu.fiuba.algo3.entrega_1;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import edu.fiuba.algo3.modelo.entidades.*;
import edu.fiuba.algo3.modelo.entidades.cartas.CartaFactory;
import edu.fiuba.algo3.modelo.entidades.cartas.CartaPoker;
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
            return CartaFactory.crearCarta(valor, palo);
        });
    }

    @Test
    void test01LlenarManoHastaCompleta() {
        while (!mano.manoLlena()) {
            mano.agregarCarta(mazoMock.darCarta());
        }
        Assertions.assertTrue(mano.manoLlena());
    }

    @Test
    void test02DescartarYRellenarMano() {

        CartaPoker c1 = CartaFactory.crearCarta(Valor.CINCO, Palo.PICAS);
        CartaPoker c2 = CartaFactory.crearCarta(Valor.CINCO, Palo.TREBOLES);
        CartaPoker c3 = CartaFactory.crearCarta(Valor.OCHO, Palo.DIAMANTES);
        CartaPoker c4 = CartaFactory.crearCarta(Valor.DOS, Palo.CORAZONES);
        CartaPoker c5 = CartaFactory.crearCarta(Valor.TRES, Palo.PICAS);

        Mazo unMazo = new Mazo(new ArrayList<>(List.of(c1, c2, c3, c4, c5)));
        Mano mano = new Mano(unMazo);

        mano.seleccionarCartas(new ArrayList<>(List.of(c1, c3)));
        mano.descartar();

        while (!mano.manoLlena()) {
            mano.agregarCarta(mazoMock.darCarta());
        }

        Assertions.assertTrue(mano.manoLlena());
    }

    @Test
    public void test03CompararManosIguales(){
        ArrayList<CartaPoker> cartas1 = new ArrayList<CartaPoker>(List.of(
                CartaFactory.crearCarta(Valor.TRES, Palo.PICAS),
                CartaFactory.crearCarta(Valor.SIETE, Palo.CORAZONES),
                CartaFactory.crearCarta(Valor.CINCO, Palo.DIAMANTES)
        ));

        ArrayList<CartaPoker> cartas2 = new ArrayList<CartaPoker>(List.of(
                CartaFactory.crearCarta(Valor.TRES, Palo.PICAS),
                CartaFactory.crearCarta(Valor.SIETE, Palo.CORAZONES),
                CartaFactory.crearCarta(Valor.CINCO, Palo.DIAMANTES)
        ));

        Mano mano1 = new Mano(cartas1);
        Mano mano2 = new Mano(cartas2);

        assert(mano1.compararCon(mano2));
    }

    @Test
    public void test04CompararManosDistintas(){
        ArrayList<CartaPoker> cartas1 = new ArrayList<CartaPoker>(List.of(
                CartaFactory.crearCarta(Valor.TRES, Palo.PICAS),
                CartaFactory.crearCarta(Valor.SIETE, Palo.CORAZONES),
                CartaFactory.crearCarta(Valor.CUATRO, Palo.DIAMANTES)
        ));

        ArrayList<CartaPoker> cartas2 = new ArrayList<CartaPoker>(List.of(
                CartaFactory.crearCarta(Valor.TRES, Palo.PICAS),
                CartaFactory.crearCarta(Valor.SIETE, Palo.CORAZONES),
                CartaFactory.crearCarta(Valor.CINCO, Palo.DIAMANTES)
        ));

        Mano mano1 = new Mano(cartas1);
        Mano mano2 = new Mano(cartas2);

        assertFalse(mano1.compararCon(mano2));
    }

    @Test
    public void test05CompararCartasSeleccionadas(){
        ArrayList<CartaPoker> cartasSeleccionadas = new ArrayList<CartaPoker>(List.of(
                CartaFactory.crearCarta(Valor.TRES, Palo.PICAS)
        ));

        ArrayList<CartaPoker> cartas = new ArrayList<CartaPoker>(List.of(
                CartaFactory.crearCarta(Valor.TRES, Palo.PICAS),
                CartaFactory.crearCarta(Valor.SIETE, Palo.CORAZONES),
                CartaFactory.crearCarta(Valor.CUATRO, Palo.DIAMANTES)
        ));

        Mano mano = new Mano(cartas);
        mano.seleccionarCartas(new ArrayList<>(List.of(cartas.get(0))));

        assertTrue(mano.compararSeleccionadasCon(cartasSeleccionadas));
    }

    @Test
    public void test06SeDescartanCartas() {
        ArrayList<CartaPoker> cartas = new ArrayList<CartaPoker>(List.of(
                CartaFactory.crearCarta(Valor.TRES, Palo.PICAS),
                CartaFactory.crearCarta(Valor.SIETE, Palo.CORAZONES),
                CartaFactory.crearCarta(Valor.CUATRO, Palo.DIAMANTES)
        ));
        Mazo mazo = new Mazo(cartas);
        Mano mano = new Mano(mazo);
        mano.seleccionarCartas(new ArrayList<>(List.of(
                CartaFactory.crearCarta(Valor.TRES, Palo.PICAS),
                CartaFactory.crearCarta(Valor.SIETE, Palo.CORAZONES)
        )));
        assertNotNull(mano.descartar());
    }

}
