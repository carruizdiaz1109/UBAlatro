package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.entidades.tarots.Tarot;
import edu.fiuba.algo3.modelo.entidades.Jugador;
import edu.fiuba.algo3.vistas.TarotVisual;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;


public class TarotController {
    private final HBox lblTarot;
    private final Jugador jugador;
    private final RondaController rondaController;

    public TarotController(Jugador jugador, HBox lblTarot, RondaController rondaController) {
        this.jugador = jugador;
        this.lblTarot = lblTarot;
        this.rondaController = rondaController;
    }

    public void visualizarTarots() {
        lblTarot.getChildren().clear();
        ArrayList<Tarot> cartasTarot = jugador.obtenerTarots();

        for (Tarot tarot : cartasTarot) {
            String imagePath = "/imagenes/tarot/" + tarot.getNombre() + ".png";
            TarotVisual tarotVisual = new TarotVisual(tarot, imagePath);
            VisualManager.mostrarCartelTarot(tarotVisual, tarot);

            StackPane stackPane = new StackPane();
            stackPane.setPrefSize(100, 150);

            VBox vboxBotones = new VBox();
            vboxBotones.setSpacing(5);
            vboxBotones.setAlignment(Pos.CENTER);
            vboxBotones.setVisible(false);

            Button botonUsar = new Button("Usar");
            botonUsar.setStyle("-fx-background-color: #6FCAA6; -fx-border-radius: 10; -fx-background-radius: 10;");
            botonUsar.setOnAction(event -> manejarUsarTarot(tarot, stackPane));

            Button botonEliminar = new Button(" X ");
            botonEliminar.setStyle("-fx-background-color: #ff595a; -fx-border-radius: 10; -fx-background-radius: 10;");
            botonEliminar.setOnAction(event -> manejarEliminarTarot(tarot, stackPane));

            vboxBotones.getChildren().addAll(botonUsar, botonEliminar);
            stackPane.getChildren().addAll(tarotVisual, vboxBotones);

            tarotVisual.setOnMouseClicked(event -> {
                boolean esVisible = vboxBotones.isVisible();
                lblTarot.getChildren().forEach(node -> {
                    if (node instanceof StackPane) {
                        StackPane sp = (StackPane) node;
                        sp.getChildren().stream()
                                .filter(child -> child instanceof VBox)
                                .forEach(child -> child.setVisible(false));
                    }
                });
                vboxBotones.setVisible(!esVisible);
            });
            StackPane.setAlignment(vboxBotones, Pos.CENTER);
            lblTarot.getChildren().add(stackPane);
        }
    }

    public void manejarEliminarTarot(Tarot unTarot, StackPane stackPane) {
        jugador.eliminarTarot(unTarot);
        lblTarot.getChildren().remove(stackPane);
        System.out.println("Tarot eliminado: " + unTarot.getNombre());
    }

    private void manejarUsarTarot(Tarot tarot, StackPane stackPane) {
        try {
            rondaController.utilizarTarot(tarot);
            manejarEliminarTarot(tarot, stackPane);
        } catch (Exception e) {
            System.err.println("Error al usar el tarot: " + e.getMessage());
        }
    }
}