package edu.fiuba.algo3.modelo.entidades;

import com.fasterxml.jackson.databind.JsonNode;
import edu.fiuba.algo3.modelo.entidades.comodines.*;
import edu.fiuba.algo3.modelo.entidades.comodines.EfectoJugada;
import edu.fiuba.algo3.modelo.entidades.jugadas.*;
import edu.fiuba.algo3.modelo.entidades.tarots.*;

import java.util.ArrayList;


public class Tienda {
    private final ArrayList<CartaPoker> cartasALaVenta;
    private final ArrayList<Tarot> tarotsALaVenta;
    private final ArrayList<Comodin> comodinesALaVenta;


    public Tienda (JsonNode tiendaNode) {
        this.cartasALaVenta = new ArrayList<CartaPoker>();
        this.tarotsALaVenta = new ArrayList<Tarot>();
        this.comodinesALaVenta = new ArrayList<Comodin>();

        inicializarComodines(tiendaNode);
        inicializarTarots(tiendaNode);
        inicializarCartas(tiendaNode);
    }

    private void inicializarComodines(JsonNode tiendaNode) {
        JsonNode comodinesNode = tiendaNode.path("comodines");

        for (JsonNode comodinNode : comodinesNode) {
            if (comodinNode.has("comodines")) {
                JsonNode subComodines = comodinNode.get("comodines");
                String nombre = comodinNode.get("nombre").asText();
                String descripcion = comodinNode.get("descripcion").asText();
                EfectoCombinado comodinCombinado = new EfectoCombinado(nombre, descripcion, new NoAleatorio());
                for (JsonNode subComodin : subComodines) {
                    Comodin comodinAñadir = procesarComodin(subComodin);
                    if (comodinAñadir != null) {
                        comodinCombinado.agregar(comodinAñadir);
                    }
                }
                this.comodinesALaVenta.add(comodinCombinado);
            } else {
                Comodin comodinIndividual = procesarComodin(comodinNode);
                if (comodinIndividual != null) {
                    this.comodinesALaVenta.add(comodinIndividual);
                }
            }
        }
    }

    private Comodin procesarComodin(JsonNode comodinIndividual) {
            JsonNode activacionNode = comodinIndividual.get("activacion");
            String nombre = comodinIndividual.get("nombre").asText();
            String descripcion = comodinIndividual.get("descripcion").asText();
            JsonNode efectoNode = comodinIndividual.get("efecto");
            int multiplicador = efectoNode.get("multiplicador").asInt();
            int valor = efectoNode.get("puntos").asInt();
            Puntaje puntaje = new Puntaje(valor, multiplicador);

            if (activacionNode != null && activacionNode.has("1 en")){
                int chance = activacionNode.get("1 en").asInt();
                Comodin comodinAleatorio  = new EfectoPuntaje(puntaje, nombre, descripcion, new Aleatorio(chance));
                return comodinAleatorio;
            }

            if (activacionNode != null && activacionNode.has("Mano Jugada")) {
                String tipoJugada = activacionNode.get("Mano Jugada").asText();
                Class<? extends Jugada> claseJugada = obtenerClaseJugada(tipoJugada);
                Comodin comodinJugada = new EfectoJugada(claseJugada, puntaje, nombre, descripcion, new NoAleatorio());
                return comodinJugada;
            }

            if (activacionNode != null && activacionNode.asText().equals("Descarte")) {
                Comodin comodinDescarte = new EfectoJugada(Descarte.class, puntaje, nombre, descripcion, new NoAleatorio());
                return comodinDescarte;
            }
        return null;
    }

    private void inicializarTarots(JsonNode tiendaNode) {
        JsonNode tarotsNode = tiendaNode.path("tarots");

        for (JsonNode tarotNode : tarotsNode) {
            Tarot tarot = crearTarot(tarotNode);
            if (tarot != null) {
                this.tarotsALaVenta.add(tarot);
            }
        }
    }

    private Tarot crearTarot(JsonNode tarotNode) {
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

    public ArrayList<Comodin> obtenerComodines() {
        return this.comodinesALaVenta;
    }

    public ArrayList<Tarot> obtenerTarots() {
        return this.tarotsALaVenta;
    }

    public ArrayList<CartaPoker> obtenerCartas() {
        return this.cartasALaVenta;
    }
    private void inicializarCartas(JsonNode tiendaNode) {
        JsonNode cartaNode = tiendaNode.path("carta");
        String paloStr = cartaNode.get("palo").asText();
        String numeroStr = cartaNode.get("numero").asText();

        Palo palo = Palo.obtenerPaloDesdeString(paloStr);
        Valor valor = Valor.obtenerValorDesdeString(numeroStr);

        this.cartasALaVenta.add(new CartaPoker(valor, palo));
    }
}
