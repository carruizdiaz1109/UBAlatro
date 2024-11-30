package edu.fiuba.algo3.entrega_3;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.fiuba.algo3.Tienda;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;


import static org.junit.Assert.assertEquals;

public class TiendaTest {
    private Tienda tienda;
    private JsonNode tiendaNode;

    @BeforeEach
    public void setUp() throws Exception {
        // JSON de ejemplo
        String json = "{" +
                "\"comodines\": [" +
                "{ \"nombre\": \"Comodin Astuto\", \"descripcion\": \"+50 fichas si la mano jugada contiene un par\", \"activacion\": { \"Mano Jugada\": \"par\" }, \"efecto\": { \"puntos\": 50, \"multiplicador\": 1 } }, " +
                "{ \"nombre\": \"Cumbre Mistica\", \"descripcion\": \"x15 multiplicación por cada descarte\", \"activacion\": \"Descarte\", \"efecto\": { \"puntos\": 1, \"multiplicador\": 15 } } " +
                "], " +
                "\"tarots\": [" +
                "{ \"nombre\": \"El Mago\", \"descripcion\": \"Mejora la mano par\", \"efecto\": { \"puntos\": 15, \"multiplicador\": 2 }, \"sobre\": \"mano\", \"ejemplar\": \"par\" }, " +
                "{ \"nombre\": \"El Carro\", \"descripcion\": \"Mejora 1 carta seleccionada y la convierte en una carta de acero.\", \"efecto\": { \"puntos\": 1, \"multiplicador\": 1.5 }, \"sobre\": \"carta\", \"ejemplar\": \"cualquiera\" }" +
                "]" +
                "}";

        // Convertir el JSON a JsonNode usando ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        tiendaNode = objectMapper.readTree(json);

        // Crear la tienda con el JSON
        tienda = new Tienda(tiendaNode);
    }

    @Test
    public void test01SeInicializanCorrectamenteComodines() {
        int cantidadComodines = tienda.obtenerComodines().size();
        assertEquals(2, cantidadComodines); // Esperamos que haya 2 tarots
    }

    @Test
    public void test02SeInicializanCorrectamenteTarots() {
        int cantidadTarots = tienda.obtenerTarots().size();
        assertEquals(2, cantidadTarots); // Esperamos que haya 2 tarots

    }
}
