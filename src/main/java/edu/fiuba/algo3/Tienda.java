package edu.fiuba.algo3;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.fiuba.algo3.jugadas.*;
import edu.fiuba.algo3.comodines.*;
import edu.fiuba.algo3.tarots.*;
import edu.fiuba.algo3.tarots.EfectoJugada;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Tienda {
    private final List<CartaPoker> cartasAComprar;
    private final List<Tarot> tarotsAComprar;
    private final List<Comodin> comodinesAComprar;
    private final List<Tarot> tarotsALaVenta;
    private final List<Comodin> comodinesALaVenta;
    private int capacidadComodin;
    private int capacidadTarots;

    public Tienda () {
        this.cartasAComprar = new ArrayList<CartaPoker>();
        this.tarotsALaVenta = new ArrayList<Tarot>();
        this.comodinesALaVenta = new ArrayList<Comodin>();
        this.tarotsAComprar = new ArrayList<Tarot>();
        this.comodinesAComprar = new ArrayList<Comodin>();
        this.capacidadComodin = 5;
        this.capacidadTarots = 2;

        inicializarComodines();
        inicializarTarots();
    }

    private void inicializarComodines() {
        ObjectMapper objectMapper = new ObjectMapper();
        try (InputStream inputStream = getClass().getResourceAsStream("/json/Comodines.json")) {
            if (inputStream == null) {
                System.out.println("Recurso no encontrado: json/Comodines.json");
            } else {
                System.out.println("Recurso encontrado correctamente.");
            }
            JsonNode rootNode = objectMapper.readTree(inputStream);
            JsonNode alPuntajeNode = rootNode.get("Al Puntaje");
            JsonNode comodinesPuntajeNode = alPuntajeNode.get("comodines");

            for (JsonNode comodinPuntajeNode : comodinesPuntajeNode) {
                Comodin efectoPuntaje = inicializarComodin(comodinPuntajeNode, EfectoPuntaje.class);
                if (efectoPuntaje != null) {
                    this.comodinesAComprar.add(efectoPuntaje);
                }
            }

            JsonNode aJugadaNode = rootNode.get("Bonus por Mano Jugada");
            JsonNode comodinesaJugadaNode = aJugadaNode.get("comodines");

            for (JsonNode comodinJugadaNode : comodinesaJugadaNode) {
                Comodin efectoJugada = inicializarComodin(comodinJugadaNode, edu.fiuba.algo3.comodines.EfectoJugada.class);
                if (efectoJugada != null) {
                    this.comodinesAComprar.add(efectoJugada);
                }
            }

            JsonNode alDescarteNode = rootNode.get("Bonus por Descarte");
            JsonNode comodinesDescarteNode = alDescarteNode.get("comodines");

            for (JsonNode comodinDescarte : comodinesDescarteNode) {
                Comodin efectoDescarte = inicializarComodin(comodinDescarte, edu.fiuba.algo3.comodines.EfectoJugada.class);
                if (efectoDescarte != null) {
                    this.comodinesAComprar.add(efectoDescarte);
                }
            }

            JsonNode chanceNode = rootNode.get("Chance de activarse aleatoriamente");
            JsonNode comodinesAleatoriosNode = chanceNode.get("comodines");

            for (JsonNode comodinAleatorioNode : comodinesAleatoriosNode) {
                Comodin efectoPuntaje = inicializarComodin(comodinAleatorioNode, EfectoPuntaje.class);
                if (efectoPuntaje != null) {
                    this.comodinesAComprar.add(efectoPuntaje);
                }
            }

            JsonNode combinacionNode = rootNode.get("Combinación");
            JsonNode comodinesCombinacionNode = combinacionNode.get("comodines");
            for (JsonNode comodinCombinacionNode : comodinesCombinacionNode) {
                String nombreCombinacion = comodinCombinacionNode.get("nombre").asText();
                String descripcionCombinacion = comodinCombinacionNode.get("descripcion").asText();
                EfectoCombinado comodinCombinado = new EfectoCombinado(nombreCombinacion, descripcionCombinacion, new NoAleatorio());
                JsonNode comodinesSubNode = comodinCombinacionNode.get("comodines");

                // Procesar subcomodines dentro de la combinación
                for (JsonNode subComodinNode : comodinesSubNode) {
                    Comodin subComodin = inicializarComodin(subComodinNode, EfectoPuntaje.class); // O tipo adecuado
                    if (subComodin != null) {
                        comodinCombinado.agregar(subComodin);
                    }
                }
                this.comodinesAComprar.add(comodinCombinado);
            }
        } catch (IOException e) {
            e.printStackTrace(); // Manejo
        }
    }

    private Comodin inicializarComodin(JsonNode comodinNode, Class<? extends Comodin> tipoEfecto) {
        String nombre = comodinNode.get("nombre").asText();
        String descripcion = comodinNode.get("descripcion").asText();
        JsonNode efectoNode = comodinNode.get("efecto");
        int multiplicador = efectoNode.get("multiplicador").asInt();
        int valor = efectoNode.get("puntos").asInt();
        Puntaje puntaje = new Puntaje(valor, multiplicador);

        if (tipoEfecto.equals(EfectoPuntaje.class)) {
            JsonNode activacionNode = comodinNode.get("activacion");
            if (activacionNode != null && activacionNode.has("1 en")){
                int chance = activacionNode.get("1 en").asInt();
                return new EfectoPuntaje(puntaje, nombre, descripcion, new Aleatorio(chance));
            }
            return new EfectoPuntaje(puntaje, nombre, descripcion, new NoAleatorio());

        } else if (tipoEfecto.equals(edu.fiuba.algo3.comodines.EfectoJugada.class)) {
            JsonNode activacionNode = comodinNode.get("activacion");
            if (activacionNode != null && activacionNode.has("Mano Jugada")) {
                String tipoJugada = activacionNode.get("Mano Jugada").asText();
                Class<? extends Jugada> claseJugada = obtenerClaseJugada(tipoJugada);
                return new edu.fiuba.algo3.comodines.EfectoJugada(claseJugada, puntaje, nombre, descripcion, new NoAleatorio());
            } else {
                // Caso para "Bonus por Descarte"
                return new edu.fiuba.algo3.comodines.EfectoJugada(Descarte.class, puntaje, nombre, descripcion, new NoAleatorio());
            }
        }
        return null;
    }

    private void inicializarTarots() {
        ObjectMapper objectMapper = new ObjectMapper();
        try (InputStream inputStream = getClass().getResourceAsStream("/json/Tarot.json")) {
            JsonNode rootNode = objectMapper.readTree(inputStream);
            JsonNode tarotsNode = rootNode.get("tarots");

            for (JsonNode tarotNode : tarotsNode) {
                Tarot tarot = inicializarTarot(tarotNode);
                if (tarot != null) {
                    this.tarotsAComprar.add(tarot);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
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
            return new EfectoCarta(nombre, descripcion, puntaje);
        } else if (sobre.equalsIgnoreCase("mano")) {
            return new EfectoJugada(nombre, descripcion, puntaje, ejemplar);
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

    private boolean comodinLLeno(){
        return (comodinesALaVenta.size() == capacidadComodin);
    }

    private boolean tarotLLeno(){
        return (tarotsALaVenta.size() == capacidadTarots);
    }

    public List<Comodin> actualizarComodines(){
        Random random = new Random();

        while (!comodinLLeno() && !comodinesAComprar.isEmpty()) {
            int indiceAleatorio = random.nextInt(comodinesAComprar.size());
            Comodin comodin = comodinesAComprar.remove(indiceAleatorio);
            comodinesALaVenta.add(comodin);
        }
        return new ArrayList<>(comodinesALaVenta);

    }

    public List<Tarot> actualizarTarots(){
        Random random = new Random();

        while (!tarotLLeno() && !tarotsAComprar.isEmpty()) {
            int indiceAleatorio = random.nextInt(tarotsAComprar.size());

            Tarot tarot = tarotsAComprar.remove(indiceAleatorio);
            tarotsALaVenta.add(tarot);
        }
        return new ArrayList<>(tarotsALaVenta);

    }

    public List<Comodin> obtenerComodines() {
        return this.comodinesAComprar;
    }

    public List<Tarot> obtenerTarots() {
        return this.tarotsAComprar;
    }
}
