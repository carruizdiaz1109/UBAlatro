package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.entidades.tarots.Tarot;
import edu.fiuba.algo3.modelo.entidades.Jugador;
import edu.fiuba.algo3.vistas.TarotVisual;
import javafx.scene.layout.HBox;
import javafx.scene.input.MouseEvent;
import java.util.ArrayList;


public class TarotController {
    private final HBox lblTarot;
    private final Jugador jugador;

    public TarotController(Jugador jugador, HBox lbltarot) {
        this.jugador = jugador;
        this.lblTarot = lbltarot;
    }

    public void visualizarTarots() {
        lblTarot.getChildren().clear(); // Limpiar el contenedor antes de mostrar nuevas cartas de Tarot
        ArrayList<Tarot> cartasTarot = this.jugador.obtenerTarots();

        for (Tarot carta : cartasTarot) {
            String imagePath = "/imagenes/tarot/" + carta.getNombre() + ".png"; // Asumimos que las cartas de tarot tienen un nombre
            TarotVisual tarotVisual = new TarotVisual(carta, imagePath, 100, 150); // Tamaño fijo o dinámico

            // Agregar la carta de Tarot visual al contenedor
            lblTarot.getChildren().add(tarotVisual);

            // Lógica para manejar eventos de arrastre si es necesario
            tarotVisual.setOnMouseClicked(this::manejarClickTarot);
        }
    }

    private void manejarClickTarot(MouseEvent event) {
        // Aquí puedes implementar la lógica que se debe ejecutar cuando se haga clic en una carta de tarot
        TarotVisual tarotVisual = (TarotVisual) event.getSource();
        Tarot carta = tarotVisual.getCartaTarot();

        System.out.println("Carta de Tarot clickeada: " + carta);
        //this.jugador.utilizarTarot(carta, );
    }
}
