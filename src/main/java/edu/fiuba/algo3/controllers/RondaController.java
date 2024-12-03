package edu.fiuba.algo3.controllers;


import edu.fiuba.algo3.modelo.entidades.*;

import edu.fiuba.algo3.modelo.entidades.tarots.Tarot;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
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
    @FXML
    private Button btnSalir;

    private Jugador jugador;
    private ArrayList<CartaPoker> cartasSeleccionadas;
    private Ronda rondaActual;
    private RondaVisual rondaVisual;
    private Tienda tienda;
    private BalatroController balatroController;
    private MediaPlayer mediaPlayer;


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
        this.rondaVisual = new RondaVisual(this.rondaActual, lblPuntajeAcumulado, lblJugadasDisponibles, lblObjetivo, lblDescartesDisponibles);
        actualizarMano();
        reproducirSonidoDeFondo();
        btnSalir.setOnAction(event -> salir());

    }


    public void reproducirSonidoDeFondo() {
        // Ruta del archivo de sonido
        String rutaSonido = getClass().getResource("/sample/sonidoDeFondo.mp3").toExternalForm();
        Media media = new Media(rutaSonido);
        mediaPlayer = new MediaPlayer(media);

        // Configurar el sonido para que se repita en bucle
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

        // Ajustar volumen
        mediaPlayer.setVolume(0.1);

        // Reproducir sonido
        mediaPlayer.play();
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
            manejarAccionCartaSeleccionada(() -> jugador.descartar());
            try {
                jugador.descartar();
                verificarFinDeRonda();
            } catch (NoHayDescarteDisponiblesError e ){
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
        btnSalir.setOnAction(e -> {
            System.exit(0);
        });

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

    @FXML
    private void manejarClickCarta(Node cartaVisual, Node contenedorBoton) {
        // Si el botón ya está visible, lo ocultamos
        if (contenedorBoton.isVisible()) {
            contenedorBoton.setVisible(false);
            return;
        }

        // Ocultar cualquier otro botón visible
        lblComodin.getChildren().forEach(node -> {
            if (node instanceof HBox) {
                node.setVisible(false);
            }
        });
        lblTarot.getChildren().forEach(node -> {
            if (node instanceof HBox) {
                node.setVisible(false);
            }
        });

        // Mostrar el botón asociado a la carta
        contenedorBoton.setVisible(true);
    }

    private HBox crearContenedorConBoton(Node cartaVisual, String textoBoton) {
        // Crear un botón
        Button boton = new Button(textoBoton);
        boton.getStyleClass().add("boton-accion"); // Clase CSS para el botón
        boton.setOnAction(event -> {
            // Aquí se define la acción del botón
            System.out.println("Botón de carta clickeado");
        });

        // Crear un contenedor que incluye la carta y el botón
        HBox contenedor = new HBox();
        contenedor.getChildren().addAll(cartaVisual, boton);
        contenedor.setSpacing(5); // Espaciado entre carta y botón
        contenedor.setVisible(false); // Inicialmente oculto

        return contenedor;
    }

    public void utilizarTarot(Tarot tarotAAplicar){
        System.out.println("Cantidad de cartas seleccionadas: " + cartasSeleccionadas.size());
        if (this.cartasSeleccionadas.size() == 1 ) {
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
        } else {
            throw new TarotsNoDisponiblesError("No se selecciono nignuna carta");
        }
    }
}

