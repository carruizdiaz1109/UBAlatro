package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.entidades.Jugador;
import edu.fiuba.algo3.modelo.entidades.Tarot;
import edu.fiuba.algo3.modelo.entidades.comodines.Comodin;
import edu.fiuba.algo3.vistas.ComodinVisual;
import edu.fiuba.algo3.vistas.TarotVisual;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;

public class ComodinController {
    private final HBox lblComodin;
    private final Jugador jugador;

    public ComodinController(Jugador jugador, HBox lblcomodin) {
        this.jugador = jugador;
        this.lblComodin = lblcomodin;
    }

    public void visualizarComodines() {
        this.lblComodin.getChildren().clear(); // Limpiar el contenedor antes de mostrar nuevas cartas de Tarot
        ArrayList<Comodin> comodines = this.jugador.obtenerComodines();

        for (Comodin comodin : comodines) {
            String imagePath = "/imagenes/comodines/" + comodin.getNombre() + ".png";
            ComodinVisual comodinVisual = new ComodinVisual(comodin, imagePath, 100, 150);

            this.lblComodin.getChildren().add(comodinVisual);

        }
    }
}
