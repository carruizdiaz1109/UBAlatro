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

    public TarotController(Jugador jugador, HBox lblTarot) {
        this.jugador = jugador;
        this.lblTarot = lblTarot;
    }

    public void visualizarTarots() {
        lblTarot.getChildren().clear();
        ArrayList<Tarot> cartasTarot = jugador.obtenerTarots();

        for (Tarot tarot : cartasTarot) {
            String imagePath = "/imagenes/tarot/" + tarot.getNombre() + ".png";
            TarotVisual tarotVisual = new TarotVisual(tarot, imagePath);

            // Crear un StackPane para la carta y sus botones
            StackPane stackPane = new StackPane();
            stackPane.setPrefSize(100, 150);

            // Crear un VBox para alinear los botones verticalmente
            VBox vboxBotones = new VBox();
            vboxBotones.setSpacing(5);
            vboxBotones.setAlignment(Pos.CENTER);
            vboxBotones.setVisible(false); // Ocultar botones inicialmente

            // Botón "Usar"
            Button botonUsar = new Button("Usar");
            botonUsar.setStyle("-fx-background-color: #FFA94D; -fx-border-radius: 10; -fx-background-radius: 10;");
            //botonUsar.setOnAction(event -> manejarUsarTarot());

            // Botón "Eliminar"
            Button botonEliminar = new Button("X");
            botonEliminar.setStyle("-fx-background-color: #d24431; -fx-border-radius: 10; -fx-background-radius: 10;");
            botonEliminar.setOnAction(event -> manejarEliminarTarot(tarot, stackPane));

            // Añadir botones al VBox
            vboxBotones.getChildren().addAll(botonUsar, botonEliminar);

            // Añadir carta y VBox al StackPane
            stackPane.getChildren().addAll(tarotVisual, vboxBotones);

            // Configurar el clic en la carta para alternar la visibilidad del VBox
            tarotVisual.setOnMouseClicked(event -> {
                boolean esVisible = vboxBotones.isVisible();

                // Ocultar los botones de todas las demás cartas
                lblTarot.getChildren().forEach(node -> {
                    if (node instanceof StackPane) {
                        StackPane sp = (StackPane) node;
                        sp.getChildren().stream()
                                .filter(child -> child instanceof VBox)
                                .forEach(child -> ((VBox) child).setVisible(false));
                    }
                });

                // Alternar visibilidad del VBox en la carta actual
                vboxBotones.setVisible(!esVisible);
            });

            // Centrar los botones sobre la carta
            StackPane.setAlignment(vboxBotones, Pos.CENTER);

            // Añadir el StackPane al contenedor principal
            lblTarot.getChildren().add(stackPane);
        }
    }

    private void manejarEliminarTarot(Tarot unTarot, StackPane stackPane) {
        jugador.eliminarTarot(unTarot);
        lblTarot.getChildren().remove(stackPane);
        System.out.println("Tarot eliminado: " + unTarot.getNombre());
    }

}
