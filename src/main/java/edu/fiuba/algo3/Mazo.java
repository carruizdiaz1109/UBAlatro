package edu.fiuba.algo3;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Mazo extends ConjuntoCartas {
    private final List<CartaPoker> mazoDescarte;

    public Mazo(){
        super();
        inicializarMazo("/home/carolina/Documentos/UBAlatro/recursos/Balatro.json");
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
        if (tieneCartas()) {
            throw new ErrorMazoVacio();
        }
        this.mazoDescarte.add(this.cartas.get(0));
        return cartas.remove(0);
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
                this.agregarCarta(carta); // Asegúrate de que 'mazo' sea una colección ya inicializada
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
