package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.Tarot;
import edu.fiuba.algo3.vistas.TarotVisual;
import javafx.scene.layout.Pane;
import javafx.scene.input.MouseEvent;

import java.util.List;

public class TarotController {
    private final Pane contenedorTarot;

    public TarotController(Pane contenedor) {
        this.contenedorTarot = contenedor;
    }

    public void mostrarCartasTarot(List<Tarot> cartasTarot) {
        contenedorTarot.getChildren().clear(); // Limpiar el contenedor antes de mostrar nuevas cartas de Tarot

        for (Tarot carta : cartasTarot) {
            String imagePath = "/images/tarot/" + carta + ".png"; // Asumimos que las cartas de tarot tienen un nombre
            TarotVisual tarotVisual = new TarotVisual(carta, imagePath, 100, 150); // Tamaño fijo o dinámico

            // Agregar la carta de Tarot visual al contenedor
            contenedorTarot.getChildren().add(tarotVisual);

            // Lógica para manejar eventos de arrastre si es necesario
            tarotVisual.setOnMouseClicked(this::manejarClickTarot);
        }
    }

    private void manejarClickTarot(MouseEvent event) {
        // Aquí puedes implementar la lógica que se debe ejecutar cuando se haga clic en una carta de tarot
        TarotVisual tarotVisual = (TarotVisual) event.getSource();
        Tarot carta = tarotVisual.getCartaTarot();
        System.out.println("Carta de Tarot clickeada: " + carta);
        // Aquí puedes llamar a métodos de tu lógica de juego
    }
}
