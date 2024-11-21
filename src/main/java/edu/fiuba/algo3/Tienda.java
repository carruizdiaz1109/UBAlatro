package edu.fiuba.algo3;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.fiuba.algo3.jugadas.*;
import edu.fiuba.algo3.comodines.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class Tienda {
    private final List<CartaPoker> cartasAComprar;
    private final List<Tarot> tarotsAComprar;
    private final List<Comodin> comodinesAComprar;

    public Tienda () {
        this.cartasAComprar = new ArrayList<CartaPoker>();
        this.tarotsAComprar = new ArrayList<Tarot>();
        this.comodinesAComprar = new ArrayList<Comodin>();
        inicializarComodines("Comodines.json");
    }

    public void inicializarComodines(String rutaArchivo) {
        ObjectMapper objectMapper = new ObjectMapper();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(rutaArchivo)) {
            JsonNode rootNode = objectMapper.readTree(inputStream);
            JsonNode alPuntajeNode = rootNode.get("Al Puntaje");
            JsonNode comodinesPuntajeNode = alPuntajeNode.get("comodines");

            for (JsonNode comodinPuntajeNode : comodinesPuntajeNode) {
                String nombre = comodinPuntajeNode.get("nombre").asText();
                String descripcion =  comodinPuntajeNode.get("descripcion").asText();
                JsonNode efectoNode = comodinPuntajeNode.get("efecto");
                int multiplicador = efectoNode.get("multiplicador").asInt();
                int valor = efectoNode.get("puntos").asInt();
                Puntaje puntaje = new Puntaje(valor, multiplicador);
                EfectoPuntaje efectoPuntaje = new EfectoPuntaje(puntaje, nombre, descripcion);
                this.comodinesAComprar.add(efectoPuntaje);
            }

            JsonNode aJugadaNode = rootNode.get("Bonus por Mano Jugada");
            JsonNode comodinesaJugadaNode = aJugadaNode.get("comodines");

            for (JsonNode comodinJugadaNode : comodinesaJugadaNode) {
                String nombre = comodinJugadaNode.get("nombre").asText();
                String descripcion =  comodinJugadaNode.get("descripcion").asText();
                JsonNode efectoNode = comodinJugadaNode.get("efecto");
                int multiplicador = efectoNode.get("multiplicador").asInt();
                int valor = efectoNode.get("puntos").asInt();
                Puntaje puntaje = new Puntaje(valor, multiplicador);
                JsonNode activacionNode = comodinJugadaNode.get("activacion");

                if (activacionNode != null && activacionNode.has("Mano Jugada")) {
                    // Obtenemos el valor dentro de "Mano Jugada"
                    String tipoJugada = activacionNode.get("Mano Jugada").asText();

                    // Usamos el tipo de jugada para obtener la clase correspondiente
                    Class<? extends Jugada> claseJugada = obtenerClaseJugada(tipoJugada);

                    // Crear el efecto de jugada, pasando el tipo de jugada
                    EfectoJugada efectoJugada = new EfectoJugada(claseJugada, puntaje, nombre, descripcion);
                    this.comodinesAComprar.add(efectoJugada);
                }
            }

            JsonNode alDescarteNode = rootNode.get("Bonus por Descarte");
            JsonNode comodinesDescarteNode = alDescarteNode.get("comodines");

            for (JsonNode comodinDescarte : comodinesDescarteNode) {
                String nombre = comodinDescarte.get("nombre").asText();
                String descripcion =  comodinDescarte.get("descripcion").asText();
                JsonNode efectoNode = comodinDescarte.get("efecto");
                int multiplicador = efectoNode.get("multiplicador").asInt();
                int valor = efectoNode.get("puntos").asInt();
                Puntaje puntaje = new Puntaje(valor, multiplicador);
                EfectoDescarte efectoDescarte = new EfectoDescarte(Descarte.class, puntaje, nombre, descripcion);
                this.comodinesAComprar.add(efectoDescarte);
            }



        } catch (IOException e) {
            e.printStackTrace(); // Manejo
        }
    }

    private Class<? extends Jugada> obtenerClaseJugada(String tipoJugada) {
        switch (tipoJugada) {
            case "par":
                return Par.class;
            case "trio":
                return Trio.class;
            case "escalera":
                return Escalera.class;
            case "full":
                return FullHouse.class;
            case "color":
                return Color.class;
            case "escalera de color":
                return EscaleraColor.class;
            case "escalera real":
                return EscaleraReal.class;
            case "poker":
                return Poker.class;
            default:
                throw new IllegalArgumentException("Tipo de jugada desconocido: " + tipoJugada);
        }
    }

    public List<Comodin> obtenerComodines() {
        return this.comodinesAComprar;
    }
}
