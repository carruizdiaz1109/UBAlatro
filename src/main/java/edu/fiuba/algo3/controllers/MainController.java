package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.*;
import edu.fiuba.algo3.vistas.CartaVisual;
import edu.fiuba.algo3.vistas.RondaVisual;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Duration;
import java.util.ArrayList;

public class MainController {
    @FXML
    private HBox lblMano;
    @FXML
    private Label lblPuntajeAcumulado;
    @FXML
    private Label lblJugadasDisponibles;
    @FXML
    private Label lblObjetivo;
    @FXML
    private Label lblDescartesDisponibles;

    private Jugador jugador;
    private final ArrayList<CartaPoker> cartasSeleccionadas;
    private final Ronda rondaActual;
    private RondaVisual rondaVisual;

    public MainController() {
        this.cartasSeleccionadas = new ArrayList<>();
        this.rondaActual = new Ronda(1, 2000, 4,5,new Tienda());
    }

    // Método para inicializar al jugador desde el controlador principal
    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
        actualizarMano();
    }

    public void iniciarRonda() {
        this.jugador.iniciarRonda(this.rondaActual);
        this.rondaVisual = new RondaVisual(this.rondaActual, lblPuntajeAcumulado, lblJugadasDisponibles, lblObjetivo, lblDescartesDisponibles);
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

    private void seleccionarCarta(CartaVisual cartaVisual) {
        TranslateTransition transition = new TranslateTransition(Duration.millis(300), cartaVisual);

        if (cartasSeleccionadas.contains(cartaVisual.getCarta())) {
            cartasSeleccionadas.remove(cartaVisual.getCarta());
            transition.setToY(0); // Baja la carta a la posición original
            cartaVisual.getStyleClass().remove("seleccionada");
        } else if(cartasSeleccionadas.size() < 5) {
            cartasSeleccionadas.add(cartaVisual.getCarta());
            transition.setToY(-20); // Eleva la carta 20px hacia arriba
            cartaVisual.getStyleClass().add("seleccionada");
        }

        transition.play(); // Inicia la animación

        // Opcional: Imprimir en consola para verificar
        System.out.println("Cartas seleccionadas: " + cartasSeleccionadas.size());
    }

    @FXML
    public void clickJugar() {
        if (this.rondaActual.estadoRonda()) {
            jugador.seleccionarCarta(this.cartasSeleccionadas);
            jugador.jugar();
            actualizarMano();
            this.cartasSeleccionadas.removeAll(this.cartasSeleccionadas);
            rondaVisual.actualizarVista();
        }
    }
    @FXML
    public void clickDescartar() {
        jugador.seleccionarCarta(this.cartasSeleccionadas);
        jugador.descartar();
        actualizarMano();
        this.cartasSeleccionadas.removeAll(this.cartasSeleccionadas);
        rondaVisual.actualizarVista();
    }
}
