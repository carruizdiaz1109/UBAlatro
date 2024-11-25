package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.*;
import edu.fiuba.algo3.vistas.CartaVisual;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class MainController {
    @FXML
    private HBox lblMano;

    private Jugador jugador; // La mano de cartas
    private final List<CartaVisual> cartasSeleccionadas;

    public MainController() {
        this.cartasSeleccionadas = new ArrayList<>();
    }

    // Método para inicializar al jugador desde el controlador principal
    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
        actualizarMano();
    }

    public void actualizarMano() {
        this.jugador.iniciarRonda(new Ronda(1, 10000,3,5, new Tienda()));
        Mano mano = this.jugador.getManoActual();
        for (CartaPoker cartaPoker : mano.getCartas()) {
            CartaVisual cartaVisual = new CartaVisual(cartaPoker,
                    "/imagenes/cartas/" + cartaPoker.getNombreArchivo(),
                    100,
                    150);

            // Agrega un manejador de clic a la carta visual
            cartaVisual.setOnMouseClicked(event -> {
                seleccionarCarta(cartaVisual);
            });

            lblMano.getChildren().add(cartaVisual);
        }
    }

    // Método para manejar la selección de una carta
    private void seleccionarCarta(CartaVisual cartaVisual) {
        if (cartasSeleccionadas.contains(cartaVisual)) {
            // Si ya está seleccionada, quítala de la lista y vuelve a su posición original
            cartasSeleccionadas.remove(cartaVisual);
            cartaVisual.getStyleClass().remove("seleccionada");

            // Vuelve la carta a su posición original
            cartaVisual.setLayoutY(0);
        } else {
            // Si no está seleccionada, agrégala a la lista y cambia su estilo
            cartasSeleccionadas.add(cartaVisual);
            cartaVisual.getStyleClass().add("seleccionada");

            // Mueve la carta hacia arriba
            cartaVisual.setLayoutY(-50); // Ajusta esta distancia según lo que necesites
        }

        // Opcional: Imprimir las seleccionadas en consola para verificar
        System.out.println("Cartas seleccionadas: " + cartasSeleccionadas.size());
    }

    @FXML
    public void click(ActionEvent event) {
        System.out.println("Jugar");
    }
}
