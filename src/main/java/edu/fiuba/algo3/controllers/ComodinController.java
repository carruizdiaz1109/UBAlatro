package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.entidades.Jugador;
import edu.fiuba.algo3.modelo.entidades.Tarot;
import edu.fiuba.algo3.modelo.entidades.comodines.Comodin;
import edu.fiuba.algo3.vistas.ComodinVisual;
import edu.fiuba.algo3.vistas.TarotVisual;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;

public class ComodinController {
    private final HBox lblComodin;
    private final Jugador jugador;
    private final HBox lblMano;
    private Button botonEliminar;

    public ComodinController(Jugador jugador, HBox lblcomodin, HBox lblMano) {
        this.jugador = jugador;
        this.lblComodin = lblcomodin;
        this.lblMano = lblMano;
        inicializarBotonEliminar();
    }

    private void inicializarBotonEliminar(){
        botonEliminar = new Button("Eliminar");
        botonEliminar.setVisible(false);
        botonEliminar.setOnAction(event -> manejarEliminarComodin());
        lblMano.getChildren().add(botonEliminar);
    }

    public void visualizarComodines() {
        this.lblComodin.getChildren().clear(); // Limpiar el contenedor antes de mostrar nuevas cartas de Tarot
        ArrayList<Comodin> comodines = this.jugador.obtenerComodines();

        for (Comodin comodin : comodines) {
            String imagePath = "/imagenes/comodines/" + comodin.getNombre() + ".png";
            ComodinVisual comodinVisual = new ComodinVisual(comodin, imagePath, 100, 150);

            this.lblComodin.getChildren().add(comodinVisual);

            comodinVisual.setOnMouseClicked(this::manejarClickComodin);
        }
    }

    private void manejarClickComodin(MouseEvent event) {
        ComodinVisual comodinVisual = (ComodinVisual) event.getSource();
        Comodin carta = comodinVisual.getComodin();

        System.out.println("Carta de Tarot clickeada: " + carta);
        //this.jugador.utilizarTarot(carta, );

        botonEliminar.setLayoutX(comodinVisual.getLayoutX() + comodinVisual.getFitWidth() + 10);
        botonEliminar.setLayoutY(comodinVisual.getLayoutY());
        botonEliminar.setVisible(true);

        botonEliminar.setUserData(comodinVisual);
    }

    private void manejarEliminarComodin() {
        ComodinVisual  comodinVisual = (ComodinVisual) botonEliminar.getUserData();
        Comodin cartaComodin = comodinVisual.getComodin();

        jugador.eliminarComodin(cartaComodin);
        lblComodin.getChildren().remove(comodinVisual);

        System.out.println("Carta Tarot eliminada: " + cartaComodin);
        botonEliminar.setVisible(false);
    }
}
