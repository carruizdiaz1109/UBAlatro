package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.entidades.CartaPoker;
import edu.fiuba.algo3.modelo.entidades.ConjuntoCartas;
import edu.fiuba.algo3.modelo.entidades.Palo;
import edu.fiuba.algo3.modelo.entidades.Valor;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



public class ConjuntoCartasTest {

    @Test
    public void test01SeAgregaUnaCarta(){
        CartaPoker carta = new CartaPoker(Valor.DOS, Palo.PICAS);

        List<CartaPoker> listaEsperada = new ArrayList<>();
        listaEsperada.add(carta);
        ConjuntoCartas conjuntoEsperado = new ConjuntoCartas(listaEsperada);

        ConjuntoCartas conjunto = new ConjuntoCartas();
        conjunto.agregarCarta(carta);

        assert(conjunto.compararCon(conjuntoEsperado));
    }

    @Test
    public void test02DevuelveTrueSiElConjuntoNoEstaVacio(){
        CartaPoker carta = new CartaPoker(Valor.DOS, Palo.PICAS);

        ConjuntoCartas conjunto = new ConjuntoCartas();
        conjunto.agregarCarta(carta);

        assertTrue(conjunto.tieneCartas());
    }

    @Test
    public void test03EliminarCartasDevuelveConjuntoVacio(){
        ConjuntoCartas conjuntoEsperado = new ConjuntoCartas();
        CartaPoker carta = new CartaPoker(Valor.DOS, Palo.PICAS);

        ConjuntoCartas conjunto = new ConjuntoCartas();
        conjunto.agregarCarta(carta);

        conjunto.eliminarCartas();

        assert(conjuntoEsperado.compararCon(conjuntoEsperado));
    }
}
