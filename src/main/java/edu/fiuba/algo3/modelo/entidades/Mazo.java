package edu.fiuba.algo3.modelo.entidades;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.fiuba.algo3.modelo.entidades.cartas.CartaFactory;
import edu.fiuba.algo3.modelo.entidades.cartas.CartaPoker;
import edu.fiuba.algo3.modelo.excepciones.MazoVacioError;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Mazo extends ConjuntoCartas {
    private final List<CartaPoker> mazoDescarte;

    public Mazo(){
        super();
        inicializarMazo();
        this.mazoDescarte = new ArrayList<CartaPoker>();
        mezclar();
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
            throw new MazoVacioError();
        }
        this.mazoDescarte.add(this.cartas.get(0));
        return cartas.remove(0);
    }

    public void inicializarMazo( ) {
        ObjectMapper objectMapper = new ObjectMapper();
        try (InputStream inputStream = getClass().getResourceAsStream("/json/Balatro.json")) {
            if (inputStream == null) {
                throw new IOException("File not found" );
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

                CartaPoker carta = CartaFactory.crearCarta(valor, palo);
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
            throw new MazoVacioError();
        }
    }

}
