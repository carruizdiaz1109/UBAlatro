package edu.fiuba.algo3.modelo.entidades;

import com.fasterxml.jackson.databind.JsonNode;
import edu.fiuba.algo3.modelo.entidades.comodines.*;
import edu.fiuba.algo3.modelo.entidades.comodines.EfectoJugada;
import edu.fiuba.algo3.modelo.entidades.jugadas.*;
import edu.fiuba.algo3.modelo.entidades.tarots.*;

import java.util.ArrayList;
import java.util.List;


public class Tienda {
    private final List<CartaPoker> cartasAComprar;
    private final List<Tarot> tarotsALaVenta;
    private final List<Comodin> comodinesALaVenta;


    public Tienda (JsonNode tiendaNode) {
        this.cartasAComprar = new ArrayList<CartaPoker>();
        this.tarotsALaVenta = new ArrayList<Tarot>();
        this.comodinesALaVenta = new ArrayList<Comodin>();

        inicializarComodines(tiendaNode);
        inicializarTarots(tiendaNode);
    }

    private void inicializarComodines(JsonNode tiendaNode) {
        JsonNode comodinesNode = tiendaNode.path("comodines");

        for (JsonNode comodinNode : comodinesNode) {
            JsonNode activacionNode = comodinNode.get("activacion");
            String nombre = comodinNode.get("nombre").asText();
            String descripcion = comodinNode.get("descripcion").asText();
            JsonNode efectoNode = comodinNode.get("efecto");
            int multiplicador = efectoNode.get("multiplicador").asInt();
            int valor = efectoNode.get("puntos").asInt();
            Puntaje puntaje = new Puntaje(valor, multiplicador);

            if (activacionNode != null && activacionNode.has("1 en")){
                int chance = activacionNode.get("1 en").asInt();
                Comodin comodinAleatorio  = new EfectoPuntaje(puntaje, nombre, descripcion, new Aleatorio(chance));
                this.comodinesALaVenta.add(comodinAleatorio);
            }

            if (activacionNode != null && activacionNode.has("Mano Jugada")) {
                String tipoJugada = activacionNode.get("Mano Jugada").asText();
                Class<? extends Jugada> claseJugada = obtenerClaseJugada(tipoJugada);
                Comodin comodinJugada = new EfectoJugada(claseJugada, puntaje, nombre, descripcion, new NoAleatorio());
                this.comodinesALaVenta.add(comodinJugada);
            }

            if (activacionNode != null && activacionNode.asText().equals("Descarte")) {
                Comodin comodinDescarte = new EfectoJugada(Descarte.class, puntaje, nombre, descripcion, new NoAleatorio());
                this.comodinesALaVenta.add(comodinDescarte);
            }
        }
    }

    private void inicializarTarots(JsonNode tiendaNode) {
        JsonNode tarotsNode = tiendaNode.path("tarots");

        for (JsonNode tarotNode : tarotsNode) {
            Tarot tarot = inicializarTarot(tarotNode);
            if (tarot != null) {
                this.tarotsALaVenta.add(tarot);
            }
        }
    }

    private Tarot inicializarTarot(JsonNode tarotNode) {
        String nombre = tarotNode.get("nombre").asText();
        String descripcion = tarotNode.get("descripcion").asText();
        JsonNode efectoNode = tarotNode.get("efecto");
        int puntos = efectoNode.get("puntos").asInt();
        int multiplicador = efectoNode.get("multiplicador").asInt();
        Puntaje puntaje = new Puntaje(puntos, multiplicador);

        String sobre = tarotNode.get("sobre").asText();
        String ejemplar = tarotNode.get("ejemplar").asText();

        if (sobre.equalsIgnoreCase("carta")) {
            return new TarotCarta(nombre, descripcion, puntaje);
        } else if (sobre.equalsIgnoreCase("mano")) {
            return new TarotJugada(nombre, descripcion, puntaje, ejemplar);
        }
        return null;
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
        return this.comodinesALaVenta;
    }

    public List<Tarot> obtenerTarots() {
        return this.tarotsALaVenta;
    }
}
