package edu.fiuba.algo3.entrega_3;

import edu.fiuba.algo3.Tienda;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class TiendaTest {
    @Test
    public void test01SeInicializanCorrectamenteComodines() {
        Tienda tienda = new Tienda();
        int cantidad = tienda.obtenerComodines().size();
        assertEquals(33,cantidad);
    }

    @Test
    public void test02SeInicializanCorrectamenteTarots() {
        Tienda tienda = new Tienda();
        int cantidad = tienda.obtenerTarots().size();
        assertEquals(15,cantidad);
    }
}
