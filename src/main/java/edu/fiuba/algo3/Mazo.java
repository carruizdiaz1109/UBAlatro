package edu.fiuba.algo3;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Mazo extends ConjuntoCartas {
    private final List<CartaPoker> mazoDescarte;

    public Mazo(){
        super();
        inicializarMazo("json/Balatro.json");
        this.mazoDescarte = new ArrayList<CartaPoker>();
    }

    public Mazo(ArrayList<CartaPoker> cartas){
        this.cartas = cartas;
        this.mazoDescarte = new ArrayList<CartaPoker>();
    }

    protected void mezclar() {
        Collections.shuffle(cartas);
    }

    public CartaPoker darCarta() {
        if (!tieneCartas()) {
            throw new ErrorMazoVacio();
        }
        this.mazoDescarte.add(this.cartas.get(0));
        return cartas.remove(0);
    }

    public void inicializarMazo(String rutaArchivo) {
        ObjectMapper objectMapper = new ObjectMapper();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(rutaArchivo)) {
            if (inputStream == null) {
                throw new IOException("File not found in classpath: " + rutaArchivo);
            }

            // Load the JSON and parse the "mazo" node
            JsonNode rootNode = objectMapper.readTree(inputStream);
            JsonNode mazoNode = rootNode.path("mazo");

            // Iterate over the cards and create each one
            for (JsonNode cartaNode : mazoNode) {
                String paloStr = cartaNode.path("palo").asText();
                String numeroStr = cartaNode.path("numero").asText();

                Palo palo = Palo.obtenerPaloDesdeString(paloStr); // Convert string to enum
                Valor valor = Valor.obtenerValorDesdeString(numeroStr); // Convert string to enum

                CartaPoker carta = new CartaPoker(valor, palo);
                this.agregarCarta(carta); // Ensure 'mazo' is initialized as a collection
            }
        } catch (IOException e) {
            System.err.println("Error al cargar el mazo desde el archivo JSON: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void recargarMazo() {
        if (this.cartas.isEmpty() && !this.mazoDescarte.isEmpty()) {
            this.cartas.addAll(this.mazoDescarte);
            mazoDescarte.clear();
            mezclar();
        } else if (cartas.isEmpty()) {
            throw new ErrorMazoVacio();
        }
    }

}
