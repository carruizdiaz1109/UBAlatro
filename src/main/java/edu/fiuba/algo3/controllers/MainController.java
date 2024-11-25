package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.*;
import edu.fiuba.algo3.vistas.CartaVisual;
import edu.fiuba.algo3.vistas.RondaVisual;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @FXML
    private HBox lblTarot;
    @FXML
    private HBox lblComodin;

    private Jugador jugador;
    private final ArrayList<CartaPoker> cartasSeleccionadas;
    private final Ronda rondaActual;
    private RondaVisual rondaVisual;

    public MainController() {
        this.cartasSeleccionadas = new ArrayList<>();
        this.rondaActual = new Ronda(1, 2000, 4,5,new Tienda());
    }

    // Metodo para inicializar al jugador desde el controlador principal
    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
        actualizarMano();

        cargarCartasTarot();
        cargarCartasComodin();
    }

    public void iniciarRonda() {
        this.jugador.iniciarRonda(this.rondaActual);
        this.rondaVisual = new RondaVisual(this.rondaActual, lblPuntajeAcumulado, lblJugadasDisponibles, lblObjetivo, lblDescartesDisponibles);

        actualizarMano();
    }

    public void mostrarCartasTarot(List<String> nombresCartasTarot) {
        lblTarot.getChildren().clear();

        for (String nombreArchivo : nombresCartasTarot) {
            javafx.scene.image.Image imagenCarta = new javafx.scene.image.Image(
                    getClass().getResource("/imagenes/tarot/" + nombreArchivo).toExternalForm()
            );

            javafx.scene.image.ImageView vistaCarta = new javafx.scene.image.ImageView(imagenCarta);
            vistaCarta.setFitWidth(100); // Ancho de la carta
            vistaCarta.setFitHeight(150); // Alto de la carta
            vistaCarta.setPreserveRatio(true); // Mantener la proporción de la imagen

            HBox.setMargin(vistaCarta, new javafx.geometry.Insets(50, 10, 50, 10));

            lblTarot.getChildren().add(vistaCarta);
        }
    }
    public void cargarCartasTarot() {
        List<String> nombresCartasTarot = List.of(
                "ahorcado.png",
                "amantes.png"
        );

        mostrarCartasTarot(nombresCartasTarot);
    }

    public void mostrarCartasComodin(List<String> nombresCartasComodin) {
        lblComodin.getChildren().clear();

        for (String nombreArchivo : nombresCartasComodin) {
            javafx.scene.image.Image imagenCarta = new javafx.scene.image.Image(
                    getClass().getResource("/imagenes/comodines/" + nombreArchivo).toExternalForm()
            );

            javafx.scene.image.ImageView vistaCarta = new javafx.scene.image.ImageView(imagenCarta);
            vistaCarta.setFitWidth(100); // Ancho de la carta
            vistaCarta.setFitHeight(150); // Alto de la carta
            vistaCarta.setPreserveRatio(true); // Mantener la proporción de la imagen

            HBox.setMargin(vistaCarta, new javafx.geometry.Insets(50, 10, 50, 10));

            lblComodin.getChildren().add(vistaCarta);
        }
    }
    public void cargarCartasComodin() {
        List<String> nombresCartasComodin = List.of(
                "abundante.png",
                "arriesgado.png",
                "astuto.png",
                "bandera.png"
        );

        mostrarCartasComodin(nombresCartasComodin);
    }

    public void actualizarMano() {
        lblMano.getChildren().clear();
        Mano mano = this.jugador.getManoActual();

        for (CartaPoker cartaPoker : mano.getCartas()) {
            CartaVisual cartaVisual = new CartaVisual(cartaPoker,
                    "/imagenes/cartas/" + cartaPoker.getNombreArchivo(),
                    100,
                    150);

            cartaVisual.setOnMouseClicked(event -> seleccionarCarta(cartaVisual));
            agregarCarta(cartaVisual);
        }
    }

    private void agregarCarta(CartaVisual cartaVisual) {
        cartaVisual.setTranslateY(-500);
        lblMano.getChildren().add(cartaVisual);

        TranslateTransition transition = new TranslateTransition(Duration.millis(300), cartaVisual);
        transition.setToY(0);
        transition.setOnFinished(event -> animarDisposicionCartas());
        transition.play();
    }

    private void animarDisposicionCartas() {
        double espaciado = 10;
        double posicionInicialX = (lblMano.getWidth() - lblMano.getChildren().size() * (100 + espaciado)) / 2;

        for (int i = 0; i < lblMano.getChildren().size(); i++) {
            Node carta = lblMano.getChildren().get(i);
            double nuevaPosicionX = posicionInicialX + i * (100 + espaciado);

            TranslateTransition transition = new TranslateTransition(Duration.millis(300), carta);
            transition.setToX(nuevaPosicionX - carta.getLayoutX());
            transition.play();
        }
    }

    // Metodo para manejar la selección de una carta
    private void seleccionarCarta(CartaVisual cartaVisual) {
        TranslateTransition transition = new TranslateTransition(Duration.millis(150), cartaVisual);

        if (cartasSeleccionadas.contains(cartaVisual.getCarta())) {
            cartasSeleccionadas.remove(cartaVisual.getCarta());
            transition.setToY(0); // Baja la carta a la posición original
            cartaVisual.getStyleClass().remove("seleccionada");
        } else {
            cartasSeleccionadas.add(cartaVisual.getCarta());
            transition.setToY(-30); // Eleva la carta 20px hacia arriba
            cartaVisual.getStyleClass().add("seleccionada");
        }

        transition.play(); // Inicia la animación

        // Opcional: Imprimir en consola para verificar
        System.out.println("Cartas seleccionadas: " + cartasSeleccionadas.size());
    }

    private void animarCartaHaciaAbajo(CartaVisual cartaVisual, Runnable onFinished) {
        TranslateTransition transition = new TranslateTransition(Duration.millis(200), cartaVisual);
        transition.setToY(500);

        transition.setOnFinished(event -> {
            lblMano.getChildren().remove(cartaVisual);
            animarDisposicionCartas();
            if (onFinished != null) {
                onFinished.run();
            }
        });

        transition.play();
    }


    private void animarCartasSeleccionadas(Runnable onComplete) {
        List<CartaVisual> cartasParaAnimar = lblMano.getChildren().stream()
                .filter(node -> node instanceof CartaVisual)
                .map(node -> (CartaVisual) node)
                .filter(cartaVisual -> cartasSeleccionadas.contains(cartaVisual.getCarta()))
                .collect(Collectors.toList());

        if (cartasParaAnimar.isEmpty()) {
            if (onComplete != null) onComplete.run();
            return;
        }

        animarCartaUnaPorUna(cartasParaAnimar, 0, onComplete);
    }

    private void animarCartaUnaPorUna(List<CartaVisual> cartasParaAnimar, int index, Runnable onComplete) {
        if (index >= cartasParaAnimar.size()) {
            if (onComplete != null) {
                onComplete.run();
            }
            return;
        }
        CartaVisual cartaVisual = cartasParaAnimar.get(index);
        animarCartaHaciaAbajo(cartaVisual, () -> animarCartaUnaPorUna(cartasParaAnimar, index + 1, onComplete));
    }

    @FXML
    public void clickJugar() {
        manejarAccionCartaSeleccionada(() -> jugador.jugar());
    }

    @FXML
    public void clickDescartar() {
        manejarAccionCartaSeleccionada(() -> jugador.descartar());
    }

    private void manejarAccionCartaSeleccionada(Runnable accionEspecifica) {
        if (cartasSeleccionadas.isEmpty()) {
            return;
        }

        Runnable onComplete = () -> {
            jugador.seleccionarCarta(cartasSeleccionadas);
            accionEspecifica.run();
            actualizarMano();
            cartasSeleccionadas.clear();
            rondaVisual.actualizarVista();
        };

        animarCartasSeleccionadas(onComplete);
    }
}
