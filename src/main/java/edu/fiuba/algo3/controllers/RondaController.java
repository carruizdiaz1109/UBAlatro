package edu.fiuba.algo3.controllers;


import edu.fiuba.algo3.modelo.entidades.*;

import edu.fiuba.algo3.modelo.entidades.tarots.Tarot;
import edu.fiuba.algo3.modelo.entidades.tarots.TarotCarta;
import edu.fiuba.algo3.modelo.entidades.tarots.TarotJugada;
import edu.fiuba.algo3.modelo.excepciones.NoHayDescarteDisponiblesError;
import edu.fiuba.algo3.modelo.excepciones.NoHayJugadasDisponiblesError;
import edu.fiuba.algo3.modelo.entidades.cartas.CartaPoker;
import edu.fiuba.algo3.modelo.excepciones.TarotsNoDisponiblesError;
import edu.fiuba.algo3.vistas.CartaVisual;
import edu.fiuba.algo3.vistas.RondaVisual;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RondaController {

    @FXML
    private HBox lblMano;
    @FXML
    public Label lblPuntajeAcumulado;
    @FXML
    public Label lblJugada;
    @FXML
    public Label lblPuntajeJugada;
    @FXML
    public Label lblPts;
    @FXML
    public Label lblMult;
    @FXML
    public Label lblJugadasDisponibles;
    @FXML
    public Label lblObjetivo;
    @FXML
    public Label lblDescartesDisponibles;
    @FXML
    public Label lblRonda;
    @FXML
    private HBox lblTarot;
    @FXML
    private HBox lblComodin;
    @FXML
    private Button btnSalir;

    private Jugador jugador;
    private ArrayList<CartaPoker> cartasSeleccionadas;
    private Ronda rondaActual;
    private RondaVisual rondaVisual;
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

        TarotController tarotController = new TarotController(this.jugador, lblTarot, this);
        tarotController.visualizarTarots();
    }

    public void setRondaVisual(RondaVisual rondaVisual) {
        this.rondaVisual = rondaVisual;
    }

    public void iniciarRonda() {
        this.jugador.iniciarRonda(this.rondaActual);
        this.rondaVisual = new RondaVisual(this.rondaActual, lblPuntajeAcumulado, lblJugada,
                                            lblPuntajeJugada, lblPts, lblMult, lblJugadasDisponibles,
                                            lblObjetivo, lblDescartesDisponibles, lblRonda);
        actualizarMano();
        btnSalir.setOnAction(event -> salir());
    }


    public void actualizarMano() {
        Mano mano = this.jugador.getManoActual();
        List<CartaPoker> cartasActuales = mano.getCartas();

        lblMano.getChildren().removeIf(node -> {
            if (node instanceof CartaVisual) {
                CartaVisual cartaVisual = (CartaVisual) node;
                return !cartasActuales.contains(cartaVisual.getReferencia());
            }
            return false;
        });

        for (CartaPoker cartaPoker : cartasActuales) {
            boolean yaEstaEnMano = lblMano.getChildren().stream()
                    .filter(node -> node instanceof CartaVisual)
                    .anyMatch(node -> ((CartaVisual) node).getReferencia().equals(cartaPoker));

            if (!yaEstaEnMano) {
                String ImagePath = "/imagenes/cartas/" + cartaPoker.getNombreArchivo();
                CartaVisual cartaVisual = new CartaVisual(cartaPoker, ImagePath);
                VisualManager.mostrarCartelCarta(cartaVisual, cartaPoker);
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

//    public void deseleccionarCartas() {
//        if(!cartasSeleccionadas.isEmpty()) {
//            for(CartaVisual carta : cartasSeleccionadas) {
//                this.cartasSeleccionadas.remove(carta.getReferencia());
//                transition.setToY(0);
//                cartaVisual.getStyleClass().remove("seleccionada");
//            }
//        }
//    }

    private void seleccionarCarta(CartaVisual cartaVisual) {
        TranslateTransition transition = new TranslateTransition(Duration.millis(150), cartaVisual);

        if (this.cartasSeleccionadas.contains(cartaVisual.getReferencia())) {
            this.cartasSeleccionadas.remove(cartaVisual.getReferencia());
            transition.setToY(0);
            cartaVisual.getStyleClass().remove("seleccionada");
        } else if(this.cartasSeleccionadas.size() < 5) {
            this.cartasSeleccionadas.add(cartaVisual.getReferencia());
            transition.setToY(-30);
            cartaVisual.getStyleClass().add("seleccionada");
        }
        transition.play();
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
                .filter(cartaVisual -> cartasSeleccionadas.contains(cartaVisual.getReferencia()))
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
        if (rondaActual.sePuedeDescartar() && rondaActual.sePuedeSeguirJugando()) {
            try {
                manejarAccionCartaSeleccionada(() -> {
                    jugador.descartar();
                    verificarFinDeRonda();
                });
            } catch (NoHayDescarteDisponiblesError e) {
                verificarFinDeRonda();
            }
        }
    }

    private void salir() {
        Stage stage = new Stage();
        stage.setTitle("Salir del Juego");

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);

        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));

        Label lblMensaje = new Label("¿Estás seguro de que deseas salir?");
        lblMensaje.setStyle("-fx-font-size: 16px; -fx-text-fill: #333;");

        HBox botones = new HBox(10);
        botones.setAlignment(Pos.CENTER);

        Button btnSalir = new Button("Salir");
        btnSalir.setStyle("-fx-background-color: #d9534f; -fx-text-fill: white; -fx-font-size: 14px;");
        btnSalir.setOnAction(e -> System.exit(0));

        Button btnCancelar = new Button("Cancelar");
        btnCancelar.setStyle("-fx-background-color: #5bc0de; -fx-text-fill: white; -fx-font-size: 14px;");
        btnCancelar.setOnAction(e -> stage.close());

        botones.getChildren().addAll(btnSalir, btnCancelar);

        root.getChildren().addAll(lblMensaje, botones);

        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.showAndWait();
    }

    private void manejarAccionCartaSeleccionada(Runnable accionEspecifica) {
        if (cartasSeleccionadas.isEmpty()) {
            return;
        }
        Runnable onComplete = () -> {
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
            .noneMatch(node -> node instanceof CartaVisual && ((CartaVisual) node).getReferencia().equals(carta)))
            .collect(Collectors.toList());

        agregarCartasSecuencialmente(cartasFaltantes, 0);
    }

    private void agregarCartasSecuencialmente(List<CartaPoker> cartas, int index) {
        if (index >= cartas.size()) {
            reacomodarCartas();
            return;
        }

        CartaPoker cartaPoker = cartas.get(index);
        String ImagePath = "/imagenes/cartas/" + cartaPoker.getNombreArchivo();
        CartaVisual cartaVisual = new CartaVisual(cartaPoker, ImagePath);

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
        if (!this.rondaActual.sePuedeSeguirJugando()) {
            this.balatroController.finDeRonda();
        }
    }

    public void utilizarTarot(Tarot tarotAAplicar){
        System.out.println("Cantidad de cartas seleccionadas: " + cartasSeleccionadas.size());
        if (this.cartasSeleccionadas.size() == 1 && tarotAAplicar instanceof TarotCarta) {
            System.out.println("Se aplica el tarot a la carta seleccionada");
            tarotAAplicar.aplicar(this.cartasSeleccionadas.get(0));
            Node nodoSeleccionado = lblMano.getChildren().stream()
                    .filter(node -> node instanceof CartaVisual)
                    .map(node -> (CartaVisual) node)
                    .filter(cartaVisual -> cartasSeleccionadas.contains(cartaVisual.getReferencia()))
                    .findFirst()
                    .orElse(null);
            VisualManager.mostrarCartelCarta(nodoSeleccionado, cartasSeleccionadas.get(0));
            actualizarMano();
        } else if (tarotAAplicar instanceof TarotJugada) {
            tarotAAplicar.aplicar(tarotAAplicar);
        } else {
            throw new TarotsNoDisponiblesError("No se pudo usar el tarot");
        }
    }
}

