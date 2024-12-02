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

        for (Tarot carta : cartasTarot) {
            String imagePath = "/imagenes/tarot/" + carta.getNombre() + ".png";
            TarotVisual tarotVisual = new TarotVisual(carta, imagePath, 100, 150);

            // Crear un StackPane para la carta y sus botones
            StackPane stackPane = new StackPane();
            stackPane.setPrefSize(100, 150);

            // Crear un VBox para alinear los botones verticalmente
            VBox vboxBotones = new VBox();
            vboxBotones.setSpacing(5); // Espaciado entre botones
            vboxBotones.setAlignment(Pos.CENTER); // Centrar los botones dentro del VBox
            vboxBotones.setVisible(false); // Inicialmente oculto

            // Botón "Usar"
            Button botonUsar = new Button("Usar");
            botonUsar.setStyle("-fx-background-color: #FFA94D; -fx-border-radius: 10; -fx-background-radius: 10;");
            botonUsar.setOnAction(event -> realizarAccionTarot(carta, botonUsar));

            // Botón "Eliminar"
            Button botonEliminar = new Button("X");
            botonEliminar.setStyle("-fx-background-color: #d24431; -fx-border-radius: 10; -fx-background-radius: 10;");
            botonEliminar.setOnAction(event -> realizarAccionTarot(carta, botonEliminar));

            // Añadir botones al VBox
            vboxBotones.getChildren().addAll(botonUsar, botonEliminar);

            // Añadir carta y VBox al StackPane
            stackPane.getChildren().addAll(tarotVisual, vboxBotones);

            // Configurar el clic en la carta para alternar la visibilidad del VBox
            tarotVisual.setOnMouseClicked(event -> {
                boolean visible = vboxBotones.isVisible();
                vboxBotones.setVisible(!visible);
            });

            // Centrar los botones sobre la carta
            StackPane.setAlignment(vboxBotones, Pos.CENTER);

            // Añadir el StackPane al contenedor principal
            lblTarot.getChildren().add(stackPane);
        }
    }

    private void alternarBoton(Button boton) {
        boton.setVisible(!boton.isVisible());
    }

    private void realizarAccionTarot(Tarot carta, Button boton) {
        // Lógica para ejecutar la acción con el tarot
        System.out.println("Usando tarot: " + carta.getNombre());
        boton.setVisible(false); // Ocultar el botón después de usarlo
    }
}
