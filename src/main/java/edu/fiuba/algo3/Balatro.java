package edu.fiuba.algo3;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;


public class Balatro {

    private final List<Ronda> rondas;
    private final Mazo mazo;
    private final Jugador jugador;

    public Balatro(String nombreJugador) {
        this.rondas = new ArrayList<>();
        this.mazo = new Mazo();
        cargarRondasDesdeJSON("/home/carolina/Documentos/UBAlatro/recursos/Balatro.json");
        mazo.inicializarMazo("/home/carolina/Documentos/UBAlatro/recursos/Balatro.json");
        this.mazo.mezclar();
        this.jugador = new Jugador(nombreJugador, this.mazo);
    }

    public void cargarRondasDesdeJSON(String rutaArchivo) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Cargar el archivo JSON y obtener las rondas
            JsonNode rootNode = objectMapper.readTree(new File(rutaArchivo));
            JsonNode rondasNode = rootNode.path("rondas");

            // Iterar sobre las rondas y crear una por una
            for (JsonNode rondaNode : rondasNode) {
                int numero = rondaNode.path("nro").asInt();
                int puntajeMinimo = rondaNode.path("puntajeASuperar").asInt();
                int descartesDisponibles = rondaNode.path("descartes").asInt();
                int jugadasDisponibles = rondaNode.path("manos").asInt();

                // Crear la instancia de Ronda y agregarla a la lista
                Ronda ronda = new Ronda(numero, puntajeMinimo, descartesDisponibles, jugadasDisponibles);
                rondas.add(ronda);
            }
        } catch (IOException e) {
            System.err.println("Error al cargar las rondas desde el archivo JSON: " + e.getMessage());
            e.printStackTrace();
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
