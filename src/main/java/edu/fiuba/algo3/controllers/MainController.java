package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.CartaPoker;
import edu.fiuba.algo3.Mano;
import edu.fiuba.algo3.Mazo;
import edu.fiuba.algo3.vistas.CartaVisual;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

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

    public void actualizarMano() {
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
    }

    // Método para manejar la selección de una carta
    private void seleccionarCarta(CartaVisual cartaVisual) {
        if (cartasSeleccionadas.contains(cartaVisual)) {
            // Si ya está seleccionada, quítala de la lista y cambia su estilo
            cartasSeleccionadas.remove(cartaVisual);
            cartaVisual.getStyleClass().remove("seleccionada");
        } else {
            // Si no está seleccionada, agrégala a la lista y cambia su estilo
            cartasSeleccionadas.add(cartaVisual);
            cartaVisual.getStyleClass().add("seleccionada");
        }

        // Opcional: Imprimir las seleccionadas en consola para verificar
        System.out.println("Cartas seleccionadas: " + cartasSeleccionadas.size());
    }

    @FXML
    public void click(ActionEvent event) {
        System.out.println("El botón ha sido presionado.");
    }
}
