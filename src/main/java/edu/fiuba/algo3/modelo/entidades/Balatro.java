package edu.fiuba.algo3.modelo.entidades;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.fiuba.algo3.modelo.excepciones.CargarArchivoError;
import edu.fiuba.algo3.modelo.excepciones.ArchivoNoEncontradoError;

import java.io.IOException;

public class Balatro {

    private final List<Ronda> rondas;
    private final Mazo mazo;
    private final Jugador jugador;
    private int indiceRondaActual;
    private EstadoJuego estadoJuego;

    public enum EstadoJuego {
        EMPEZADO,
        GANADO,
        PERDIDO
    }

    public Balatro(String nombreJugador) {
        this.rondas = new ArrayList<Ronda>();
        this.mazo = new Mazo();
        cargarRondasDesdeJSON();
        this.jugador = new Jugador(nombreJugador, this.mazo);
        this.indiceRondaActual = 0;
        this.estadoJuego = EstadoJuego.EMPEZADO;
    }

    public void cargarRondasDesdeJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        try (InputStream inputStream = getClass().getResourceAsStream("/json/Balatro.json")) {
            if (inputStream == null) {
                throw new ArchivoNoEncontradoError();
            }
            JsonNode rootNode = objectMapper.readTree(inputStream);
            JsonNode rondasNode = rootNode.path("rondas");

            for (JsonNode rondaNode : rondasNode) {
                int numero = rondaNode.path("nro").asInt();
                int puntajeMinimo = rondaNode.path("puntajeASuperar").asInt();
                int descartesDisponibles = rondaNode.path("descartes").asInt();
                int jugadasDisponibles = rondaNode.path("manos").asInt();

                JsonNode tiendaNode = rondaNode.path("tienda");
                Tienda tienda = new Tienda(tiendaNode);
                Ronda ronda = new Ronda(numero, puntajeMinimo, descartesDisponibles, jugadasDisponibles, tienda);
                rondas.add(ronda);
            }
        } catch (IOException e) {
            throw new CargarArchivoError(e);
        }
    }

    public void jugarRonda() {
        Ronda rondaActual = getRondaActual();
        this.jugador.iniciarRonda(rondaActual);
    }

    public void avanzarRonda() {
        if (indiceRondaActual < this.rondas.size() -1) {
            this.indiceRondaActual++;
        }
    }

    public Ronda getRondaActual() {
        if (indiceRondaActual < this.rondas.size() ) {
            return this.rondas.get(this.indiceRondaActual);
        }
        return null;
    }

    public Jugador getJugador() {
        return this.jugador;
    }

    public EstadoJuego getEstadoJuego(){
        return this.estadoJuego;
    }

    public void verificarEstadoJuego() {
        if (indiceRondaActual == this.rondas.size() - 1) {
            if (this.getRondaActual().rondaSuperada()) {
                this.estadoJuego = EstadoJuego.GANADO;
            } else {
                this.estadoJuego = EstadoJuego.PERDIDO;
            }
        } else if (!getRondaActual().rondaSuperada()) {
            this.estadoJuego = EstadoJuego.PERDIDO;
        }
    }


}
