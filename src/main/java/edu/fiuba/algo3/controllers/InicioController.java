/*package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.entidades.Jugador;
import edu.fiuba.algo3.modelo.entidades.Mazo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Objects;*/

package edu.fiuba.algo3.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.fiuba.algo3.modelo.entidades.Jugador;
import edu.fiuba.algo3.modelo.entidades.Mazo;
import edu.fiuba.algo3.modelo.entidades.Tienda;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class InicioController {

    @FXML
    private TextField nombreTextField;
    @FXML
    private Button jugarButton;
    @FXML
    private MediaView backgroundMediaView;

    private Stage stage;

    private Tienda tienda; // Agregar una referencia a la tienda
    private JsonNode tiendaNode;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void initialize() {
        String videoPath = Objects.requireNonNull(getClass().getResource("/videos/background.mp4")).toExternalForm();
        cargarTienda();
//        Media media = new Media(videoPath);
//        MediaPlayer mediaPlayer = new MediaPlayer(media);
//        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
//        mediaPlayer.setAutoPlay(true);
//        backgroundMediaView.setMediaPlayer(mediaPlayer);
        jugarButton.setOnAction(event -> iniciarJuego());
    }

    private void cargarTienda() {
        try {
            String json = "{"
                    + "\"comodines\": ["
                    + "{ \"nombre\": \"Comodin Astuto\", \"descripcion\": \"+50 fichas si la mano jugada contiene un par\", \"activacion\": { \"Mano Jugada\": \"par\" }, \"efecto\": { \"puntos\": 50, \"multiplicador\": 1 } }, "
                    + "{ \"nombre\": \"Cumbre Mistica\", \"descripcion\": \"x15 multiplicación por cada descarte\", \"activacion\": \"Descarte\", \"efecto\": { \"puntos\": 1, \"multiplicador\": 15 } } "
                    + "], "
                    + "\"tarots\": ["
                    + "{ \"nombre\": \"El Mago\", \"descripcion\": \"Mejora la mano par\", \"efecto\": { \"puntos\": 15, \"multiplicador\": 2 }, \"sobre\": \"mano\", \"ejemplar\": \"par\" }, "
                    + "{ \"nombre\": \"El Carro\", \"descripcion\": \"Mejora 1 carta seleccionada y la convierte en una carta de acero.\", \"efecto\": { \"puntos\": 1, \"multiplicador\": 1.5 }, \"sobre\": \"carta\", \"ejemplar\": \"cualquiera\" }"
                    + "], "
                    + "\"carta\": {"
                    + "\"nombre\": \"10 de Corazones\", "
                    + "\"palo\": \"Corazones\", "
                    + "\"numero\": \"10\", "
                    + "\"puntos\": 10, "
                    + "\"multiplicador\": \"1\""
                    + "}"
                    + "}";

            ObjectMapper objectMapper = new ObjectMapper();
            tiendaNode = objectMapper.readTree(json);

            tienda = new Tienda(tiendaNode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void iniciarJuego() {
        String nombreJugador = nombreTextField.getText();
        if (nombreJugador == null || nombreJugador.trim().isEmpty()) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Nombre inválido");
            alerta.setHeaderText("Debe ingresar un nombre para jugar.");
            alerta.showAndWait();
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/tienda.fxml"));
            Parent root = loader.load();
            TiendaController tiendaController = loader.getController();

            // Pasar el jugador y la tienda al TiendaController
            tiendaController.setJugador(new Jugador(nombreJugador, new Mazo()));
            tiendaController.setStage(stage);

            // Inicializar la tienda en el TiendaController
            tiendaController.inicializarTienda(tiendaNode);

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("UBAlatro - Tienda");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

