package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.CartaPoker;
import edu.fiuba.algo3.Mano;
import edu.fiuba.algo3.Mazo;
import edu.fiuba.algo3.vistas.CartaVisual;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class MainController {
    @FXML
    private HBox lblMano;

    private final Mano mano;// La mano de cartas
    private final List<CartaVisual> cartasSeleccionadas;

    public MainController() {
        this.mano = new Mano(new Mazo());
        this.mano.rellenarse();
        this.cartasSeleccionadas = new ArrayList<>();
    }

    /*public void actualizarMano() {
        this.mano.rellenarse();
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
    }*/

    //Metodo para manejar la selección de una carta
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

    //Cuando se hace click al boton de Jugar aparecen las 8 cartas que son la mano del jugador
    @FXML
    public void clickJugar() {
        this.mano.rellenarse();
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
        //System.out.println("El botón ha sido presionado.");
    }
}
