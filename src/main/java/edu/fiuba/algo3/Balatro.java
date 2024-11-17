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

    public Balatro() {
        this.rondas = new ArrayList<>();
        this.mazo = new Mazo();
        cargarRondasDesdeJSON("/home/carolina/Documentos/UBAlatro/recursos/Balatro.json");
        inicializarMazo("/home/carolina/Documentos/UBAlatro/recursos/Balatro.json");
        this.mazo.mezclar();
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

    public void inicializarMazo(String rutaArchivo) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Cargar el archivo JSON y obtener el nodo 'mazo'
            JsonNode rootNode = objectMapper.readTree(new File(rutaArchivo));
            JsonNode mazoNode = rootNode.path("mazo");

            // Iterar sobre las cartas del mazo y crear cada carta
            for (JsonNode cartaNode : mazoNode) {
                // Obtener el palo y número de la carta
                String paloStr = cartaNode.path("palo").asText();
                String numeroStr = cartaNode.path("numero").asText();

                // Convertir el nombre del palo al enum Palo
                Palo palo = Palo.obtenerPaloDesdeString(paloStr);

                // Convertir el número de la carta al enum Valor usando el método personalizado
                Valor valor = Valor.obtenerValorDesdeString(numeroStr);

                // Crear la carta con el palo y número
                CartaPoker carta = new CartaPoker(valor, palo);

                // Agregar la carta al mazo
                mazo.agregarCarta(carta); // Asegúrate de que 'mazo' sea una colección ya inicializada
            }
        } catch (IOException e) {
            System.err.println("Error al cargar el mazo desde el archivo JSON: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /*
    public void iniciarJuego() {
        int i = 0;

        do {
            this.rondaActual = this.crearRonda();
            this.rondaActual.iniciarRonda();
            i++;
        } while (i < this.rondas && this.verificarResultado());
    }

    protected Ronda crearRonda() {
        return (new Ronda(this.jugador));
    }

   public boolean verificarResultado(){
        return (this.rondaActual.verificarPuntaje());
    }
    */
}
