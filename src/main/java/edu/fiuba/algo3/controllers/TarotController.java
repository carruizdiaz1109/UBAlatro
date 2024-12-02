package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.entidades.Tarot;
import edu.fiuba.algo3.modelo.entidades.Jugador;
import edu.fiuba.algo3.vistas.TarotVisual;
import javafx.scene.layout.HBox;
import javafx.scene.input.MouseEvent;

import javafx.scene.control.Button;

import java.util.ArrayList;


public class TarotController {
    private final HBox lblTarot;
    private final Jugador jugador;
    private final HBox lblMano;
    private Button botonEliminar;


    public TarotController(Jugador jugador, HBox lbltarot, HBox lblMano) {
        this.jugador = jugador;
        this.lblTarot = lbltarot;
        this.lblMano = lblMano;
        inicializarBotonEliminar();
    }

    private void inicializarBotonEliminar(){

        botonEliminar = new Button("Eliminar");
        botonEliminar.setVisible(false);
        botonEliminar.setOnAction(event -> manejarEliminarTarot());
        lblMano.getChildren().add(botonEliminar);
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

        botonEliminar.setLayoutX(tarotVisual.getLayoutX() + tarotVisual.getFitWidth() + 10);
        botonEliminar.setLayoutY(tarotVisual.getLayoutY());
        botonEliminar.setVisible(true);

        botonEliminar.setUserData(tarotVisual);
    }

    private void manejarEliminarTarot() {
        TarotVisual  tarotVisual = (TarotVisual) botonEliminar.getUserData();
        Tarot cartaTarot = tarotVisual.getCartaTarot();

        jugador.eliminarTarot(cartaTarot);
        lblTarot.getChildren().remove(tarotVisual);

        System.out.println("Carta Tarot eliminada: " + cartaTarot);
        botonEliminar.setVisible(false);
    }
}
