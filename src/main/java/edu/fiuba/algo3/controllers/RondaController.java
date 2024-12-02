package edu.fiuba.algo3.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.fiuba.algo3.modelo.entidades.*;
import edu.fiuba.algo3.modelo.entidades.comodines.*;
import edu.fiuba.algo3.modelo.entidades.tarots.*;
import edu.fiuba.algo3.modelo.excepciones.NoHayJugadasDisponiblesError;
import edu.fiuba.algo3.vistas.CartaVisual;
import edu.fiuba.algo3.vistas.RondaVisual;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RondaController {
    @FXML
    private HBox lblMano;
    @FXML
    public Label lblPuntajeAcumulado;
    @FXML
    public Label lblJugadasDisponibles;
    @FXML
    public Label lblObjetivo;
    @FXML
    public Label lblDescartesDisponibles;

    @FXML
    private HBox lblTarot;
    @FXML
    private HBox lblComodin;

    @FXML
    private Label lblResultado;

    private Jugador jugador;
    private final ArrayList<CartaPoker> cartasSeleccionadas;
    private Ronda rondaActual;
    private RondaVisual rondaVisual;
    private Tienda tienda;
    private BalatroController balatroController;

    public RondaController() {
        this.cartasSeleccionadas = new ArrayList<>();
    }

    public void setBalatroController(BalatroController balatroController) {
        this.balatroController = balatroController;
    }

    public void setRondaActual(Ronda rondaActual) {
        this.rondaActual = rondaActual;
    }


    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
        ComodinController comodinController = new ComodinController(this.jugador, lblComodin);
        comodinController.visualizarComodines();

        TarotController tarotController = new TarotController(this.jugador, lblTarot);
        tarotController.visualizarTarots();
    }

    public void setRondaVisual(RondaVisual rondaVisual) {
        this.rondaVisual = rondaVisual;
    }

    public void iniciarRonda() {
        actualizarMano();
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

        if (this.cartasSeleccionadas.contains(cartaVisual.getCarta())) {
            this.cartasSeleccionadas.remove(cartaVisual.getCarta());
            transition.setToY(0);
            cartaVisual.getStyleClass().remove("seleccionada");
        } else if(this.cartasSeleccionadas.size() < 5) {
            this.cartasSeleccionadas.add(cartaVisual.getCarta());
            transition.setToY(-30);
            cartaVisual.getStyleClass().add("seleccionada");
        }

        transition.play();
        System.out.println("Cartas seleccionadas: " + this.cartasSeleccionadas.size());
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
        if (rondaActual.sePuedeSeguirJugando()) {
            manejarAccionCartaSeleccionada(() -> {
                try {
                    jugador.jugar();
                    verificarFinDeRonda();
                } catch (NoHayJugadasDisponiblesError e) {
                    verificarFinDeRonda();
                }
            });
        }
        verificarFinDeRonda();
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
            System.out.println("Cartas a jugar:" + this.cartasSeleccionadas.size());
            jugador.seleccionarCarta(cartasSeleccionadas);
            accionEspecifica.run();
            this.cartasSeleccionadas.clear();
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

    public void verificarFinDeRonda() {
        boolean gano;
        if (!this.rondaActual.sePuedeSeguirJugando()) {
            gano = rondaActual.rondaSuperada();
            this.balatroController.finDeRonda(gano);
        }
    }
}

