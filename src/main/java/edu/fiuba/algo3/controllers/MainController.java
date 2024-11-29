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

    // Método para inicializar al jugador desde el controlador principal
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


    public void cargarCartasTarot() {
        List<String> nombresCartasTarot = List.of(
                "ahorcado.png",
                "amantes.png"
        );

        mostrarCartasTarot(nombresCartasTarot);
    }

    public void mostrarCartasTarot(List<String> nombresCartasTarot) {
        lblTarot.getChildren().clear();

        for (String nombreArchivo : nombresCartasTarot) {
            javafx.scene.image.Image imagenCarta = new javafx.scene.image.Image(
                    getClass().getResource("/imagenes/tarot/" + nombreArchivo).toExternalForm()
            );

            javafx.scene.image.ImageView vistaCarta = new javafx.scene.image.ImageView(imagenCarta);
            vistaCarta.setFitWidth(120); // Ancho de la carta
            vistaCarta.setFitHeight(180); // Alto de la carta
            vistaCarta.setPreserveRatio(true); // Mantener la proporción de la imagen

            //HBox.setMargin(vistaCarta, new javafx.geometry.Insets(50, 10, 50, 10));

            lblTarot.getChildren().add(vistaCarta);
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

    public void mostrarCartasComodin(List<String> nombresCartasComodin) {
        lblComodin.getChildren().clear();

        for (String nombreArchivo : nombresCartasComodin) {
            javafx.scene.image.Image imagenCarta = new javafx.scene.image.Image(
                    getClass().getResource("/imagenes/comodines/" + nombreArchivo).toExternalForm()
            );

            javafx.scene.image.ImageView vistaCarta = new javafx.scene.image.ImageView(imagenCarta);
            vistaCarta.setFitWidth(120); // Ancho de la carta
            vistaCarta.setFitHeight(180); // Alto de la carta
            vistaCarta.setPreserveRatio(true); // Mantener la proporción de la imagen

            HBox.setMargin(vistaCarta, new javafx.geometry.Insets(30, 0, 0, 0));

            lblComodin.getChildren().add(vistaCarta);
        }
    }

    public void actualizarMano() {
        Mano mano = this.jugador.getManoActual();
        List<CartaPoker> cartasActuales = mano.getCartas();

        lblMano.getChildren().removeIf(node -> {
            if (node instanceof CartaVisual) {
                CartaVisual cartaVisual = (CartaVisual) node;
                return !cartasActuales.contains(cartaVisual.getCarta());
            }
            return false;
        });

        for (CartaPoker cartaPoker : cartasActuales) {
            boolean yaEstaEnMano = lblMano.getChildren().stream()
                    .filter(node -> node instanceof CartaVisual)
                    .anyMatch(node -> ((CartaVisual) node).getCarta().equals(cartaPoker));

            if (!yaEstaEnMano) {
                CartaVisual cartaVisual = new CartaVisual(
                        cartaPoker,
                        "/imagenes/cartas/" + cartaPoker.getNombreArchivo(),
                        120,
                        180
                );
                cartaVisual.setOnMouseClicked(event -> seleccionarCarta(cartaVisual));
                agregarCarta(cartaVisual);
            }
        }
        reacomodarCartas();
    }

    private void agregarCarta(CartaVisual cartaVisual) {
        // Posiciona la carta fuera del área visible (extremo derecho)
        cartaVisual.setTranslateX(lblMano.getWidth());
        cartaVisual.setTranslateY(0);

        // Agrega la carta al HBox
        lblMano.getChildren().add(cartaVisual);

        // Anima la carta desde el borde derecho hacia su posición final
        TranslateTransition transition = new TranslateTransition(Duration.millis(500), cartaVisual);
        transition.setToX(0);
        transition.setOnFinished(event -> reacomodarCartas());
        transition.play();
    }

    private void reacomodarCartas() {
        reacomodarCartas(null);
    }

    private void reacomodarCartas(Runnable onComplete) {
        double espaciado = 30; // Espaciado entre cartas
        double anchoTotalCartas = lblMano.getChildren().size() * (100 + espaciado) - espaciado;
        double posicionInicialX = Math.max((lblMano.getWidth() - anchoTotalCartas) / 2, 0);

        List<Node> cartas = new ArrayList<>(lblMano.getChildren());
        for (int i = 0; i < cartas.size(); i++) {
            Node carta = cartas.get(i);
            double nuevaPosicionX = posicionInicialX + i * (100 + espaciado);

            TranslateTransition transition = new TranslateTransition(Duration.millis(300), carta);
            transition.setToX(nuevaPosicionX - carta.getLayoutX());

            // Si es la última carta, ejecuta el callback al finalizar la animación
            if (i == cartas.size() - 1 && onComplete != null) {
                transition.setOnFinished(event -> onComplete.run());
            }
            transition.play();
        }

        // Si no hay animación porque no hay cartas, ejecuta el callback de inmediato
        if (cartas.isEmpty() && onComplete != null) {
            onComplete.run();
        }
    }

    private void seleccionarCarta(CartaVisual cartaVisual) {
        TranslateTransition transition = new TranslateTransition(Duration.millis(150), cartaVisual);

        if (cartasSeleccionadas.contains(cartaVisual.getCarta())) {
            cartasSeleccionadas.remove(cartaVisual.getCarta());
            transition.setToY(0);
            cartaVisual.getStyleClass().remove("seleccionada");
        } else if(cartasSeleccionadas.size() < 5) {
            cartasSeleccionadas.add(cartaVisual.getCarta());
            transition.setToY(-30);
            cartaVisual.getStyleClass().add("seleccionada");
        }

        transition.play();
        System.out.println("Cartas seleccionadas: " + cartasSeleccionadas.size());
    }

    private void animarCartaHaciaAbajo(CartaVisual cartaVisual, Runnable onFinished) {
        TranslateTransition transition = new TranslateTransition(Duration.millis(100), cartaVisual);
        transition.setToY(500);

        transition.setOnFinished(event -> {
            lblMano.getChildren().remove(cartaVisual);
            reacomodarCartas();
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
        if (rondaActual.estadoRonda()) {
            manejarAccionCartaSeleccionada(() -> jugador.jugar());
        }
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
            cartasSeleccionadas.clear();
            rondaVisual.actualizarVista();

            rellenarMano();
        };

        animarCartasSeleccionadas(onComplete);
    }

    private void rellenarMano() {
        Mano mano = jugador.getManoActual();
        List<CartaPoker> cartasFaltantes = mano.getCartas().stream()
                .filter(carta -> lblMano.getChildren().stream()
                        .noneMatch(node -> node instanceof CartaVisual && ((CartaVisual) node).getCarta().equals(carta)))
                .collect(Collectors.toList());

        agregarCartasSecuencialmente(cartasFaltantes, 0);
    }

    private void agregarCartasSecuencialmente(List<CartaPoker> cartas, int index) {
        if (index >= cartas.size()) {
            reacomodarCartas();
            return;
        }

        CartaPoker cartaPoker = cartas.get(index);
        CartaVisual cartaVisual = new CartaVisual(
                cartaPoker,
                "/imagenes/cartas/" + cartaPoker.getNombreArchivo(),
                120,
                180
        );

        cartaVisual.setTranslateX(lblMano.getWidth());
        cartaVisual.setTranslateY(0);

        cartaVisual.setOnMouseClicked(event -> seleccionarCarta(cartaVisual));
        lblMano.getChildren().add(cartaVisual);

        TranslateTransition transition = new TranslateTransition(Duration.millis(100), cartaVisual);
        transition.setToX(0);
        transition.setOnFinished(event -> agregarCartasSecuencialmente(cartas, index + 1));
        transition.play();
    }

}
