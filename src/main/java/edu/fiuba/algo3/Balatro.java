package edu.fiuba.algo3;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.fiuba.algo3.errores.ErrorAlCargarArchivo;
import edu.fiuba.algo3.errores.ErrorArchivoNoEncontrado;

import java.io.IOException;

public class Balatro {

    private final List<Ronda> rondas;
    private final Mazo mazo;
    private final Jugador jugador;
    private final Tienda tienda;

    public Balatro(String nombre) {
        this.rondas = new ArrayList<>();
        this.mazo = new Mazo();
        this.tienda = new Tienda();
        cargarRondasDesdeJSON();
        this.jugador = new Jugador(nombre, this.mazo);
    }

    public void cargarRondasDesdeJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("Balatro.json")) {
            if (inputStream == null) {
                throw new ErrorArchivoNoEncontrado();
            }
            JsonNode rootNode = objectMapper.readTree(inputStream);
            JsonNode rondasNode = rootNode.path("rondas");

            for (JsonNode rondaNode : rondasNode) {
                int numero = rondaNode.path("nro").asInt();
                int puntajeMinimo = rondaNode.path("puntajeASuperar").asInt();
                int descartesDisponibles = rondaNode.path("descartes").asInt();
                int jugadasDisponibles = rondaNode.path("manos").asInt();

                Ronda ronda = new Ronda(numero, puntajeMinimo, descartesDisponibles, jugadasDisponibles, tienda);
                rondas.add(ronda);
            }
        } catch (IOException e) {
            throw new ErrorAlCargarArchivo(e);
        }
    }

    public void iniciarJuego() {
        for (Ronda ronda : this.rondas) {
            if (ronda.verificarPuntaje()) {
                this.jugador.iniciarRonda(ronda);
            }
        }
    }
}
