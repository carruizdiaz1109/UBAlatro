package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.entidades.Jugador;
import edu.fiuba.algo3.modelo.entidades.comodines.Comodin;
import edu.fiuba.algo3.vistas.ComodinVisual;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.util.ArrayList;

public class TiendaController {
    private Jugador jugador;
    private Stage stage;

    @FXML
    private Button btnContinuar;

    @FXML
    private StackPane comodines;


    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void visualizarComodines() {
        this.comodines.getChildren().clear();
        ArrayList<Comodin> comodines = this.jugador.obtenerComodines();

        for (Comodin comodin : comodines) {
            String imagePath = "/imagenes/comodines/" + comodin.getNombre() + ".png";
            ComodinVisual comodinVisual = new ComodinVisual(comodin, imagePath, 100, 150);

            this.comodines.getChildren().add(comodinVisual);
        }
    }

    @FXML
    public void clickContinuar() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ronda.fxml"));
            Parent root = loader.load();
            MainController rondaController = loader.getController();
            rondaController.setJugador(jugador);
            rondaController.iniciarRonda();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("UBAlatro - Ronda");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

