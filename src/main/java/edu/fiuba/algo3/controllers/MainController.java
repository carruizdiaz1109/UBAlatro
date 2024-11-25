package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.*;
import edu.fiuba.algo3.vistas.CartaVisual;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Duration;
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

    public void iniciarRonda() {
        this.jugador.iniciarRonda(new Ronda(1, 10000,3,5, new Tienda()));
        actualizarMano();
    }

    public void actualizarMano() {
        lblMano.getChildren().clear();
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
        TranslateTransition transition = new TranslateTransition(Duration.millis(300), cartaVisual);

        if (cartasSeleccionadas.contains(cartaVisual)) {
            cartasSeleccionadas.remove(cartaVisual);
            transition.setToY(0); // Baja la carta a la posición original
            cartaVisual.getStyleClass().remove("seleccionada");
        } else {
            cartasSeleccionadas.add(cartaVisual);
            transition.setToY(-20); // Eleva la carta 20px hacia arriba
            cartaVisual.getStyleClass().add("seleccionada");
        }

        transition.play(); // Inicia la animación

        // Opcional: Imprimir en consola para verificar
        System.out.println("Cartas seleccionadas: " + cartasSeleccionadas.size());
    }


    @FXML
    public void clickJugar() {
        ArrayList <CartaPoker> cartasAJugar = new ArrayList<CartaPoker>();
        for (CartaVisual carta : this.cartasSeleccionadas) {
            cartasAJugar.add(carta.getCarta());
        }
        jugador.seleccionarCarta(cartasAJugar);
        jugador.jugar();
       actualizarMano();
    }
}
