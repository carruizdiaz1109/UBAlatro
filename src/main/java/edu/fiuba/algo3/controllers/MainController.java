package edu.fiuba.algo3.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.fiuba.algo3.modelo.entidades.*;
import edu.fiuba.algo3.modelo.entidades.comodines.*;
import edu.fiuba.algo3.vistas.CartaVisual;
import edu.fiuba.algo3.vistas.RondaVisual;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


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
    private HBox lblComodin;
    @FXML
    private HBox lblTarot;


    private Jugador jugador;
    private final ArrayList<CartaPoker> cartasSeleccionadas;
    private final Ronda rondaActual;
    private RondaVisual rondaVisual;
    private Tienda tienda;

    public MainController() {
        this.cartasSeleccionadas = new ArrayList<>();
        try {
            String json = "{" +
                    "\"comodines\": [" +
                    "{ \"nombre\": \"Comodin Astuto\", \"descripcion\": \"+50 fichas si la mano jugada contiene un par\", \"activacion\": { \"Mano Jugada\": \"par\" }, \"efecto\": { \"puntos\": 50, \"multiplicador\": 1 } }, " +
                    "{ \"nombre\": \"Cumbre Mistica\", \"descripcion\": \"x15 multiplicación por cada descarte\", \"activacion\": \"Descarte\", \"efecto\": { \"puntos\": 1, \"multiplicador\": 15 } } " +
                    "], " +
                    "\"tarots\": [" +
                    "{ \"nombre\": \"El Mago\", \"descripcion\": \"Mejora la mano par\", \"efecto\": { \"puntos\": 15, \"multiplicador\": 2 }, \"sobre\": \"mano\", \"ejemplar\": \"par\" }, " +
                    "{ \"nombre\": \"El Carro\", \"descripcion\": \"Mejora 1 carta seleccionada y la convierte en una carta de acero.\", \"efecto\": { \"puntos\": 1, \"multiplicador\": 1.5 }, \"sobre\": \"carta\", \"ejemplar\": \"cualquiera\" }" +
                    "], " +
                    "\"carta\": {" +
                    "\"nombre\": \"10 de Corazones\", " +
                    "\"palo\": \"Corazones\", " +
                    "\"numero\": \"10\", " +
                    "\"puntos\": 10, " +
                    "\"multiplicador\": \"1\"" +
                    "}" + "}";

            // Convertir el JSON a JsonNode usando ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode tiendaNode = objectMapper.readTree(json);

            // Crear la tienda con el JSON
            tienda = new Tienda(tiendaNode);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        this.rondaActual = new Ronda(1, 2000, 4, 5, tienda);
    }

    // Método para inicializar al jugador desde el controlador principal
    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
        actualizarMano();

        ComodinController comodinController = new ComodinController(tienda, lblComodin);
        comodinController.cargarCartasComodin();

        TarotController tarotController = new TarotController(tienda, lblTarot);
        tarotController.cargarCartasTarot();
    }

    public void iniciarRonda() {
        this.jugador.iniciarRonda(this.rondaActual);
        this.rondaVisual = new RondaVisual(this.rondaActual, lblPuntajeAcumulado, lblJugadasDisponibles, lblObjetivo, lblDescartesDisponibles);

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
