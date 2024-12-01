/*package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.entidades.Jugador;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

import java.io.IOException;*/

package edu.fiuba.algo3.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import edu.fiuba.algo3.modelo.entidades.CartaPoker;
import edu.fiuba.algo3.modelo.entidades.Jugador;
import edu.fiuba.algo3.modelo.entidades.Tarot;
import edu.fiuba.algo3.modelo.entidades.Tienda;
import edu.fiuba.algo3.modelo.entidades.comodines.Comodin;
import edu.fiuba.algo3.vistas.CartaVisual;
import edu.fiuba.algo3.vistas.ComodinVisual;
import edu.fiuba.algo3.vistas.TarotVisual;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class TiendaController {
    private Jugador jugador;
    private Stage stage;

    @FXML
    private Button btnContinuar;

    @FXML
    private HBox lblComodinTienda1;
    @FXML
    private HBox lblComodinTienda2;
    @FXML
    private HBox lblTarotTienda1;
    @FXML
    private HBox lblTarotTienda2;
    @FXML
    private HBox lblCartaTienda1;
    @FXML
    private Button clickComodinTienda1;
    @FXML
    private Button clickComodinTienda2;
    @FXML
    private Button clickTarotTienda1;
    @FXML
    private Button clickTarotTienda2;
    @FXML
    private Button CartaTienda1;

    private Tienda tienda;


    public void inicializarTienda(JsonNode tiendaNode) {
        tienda = new Tienda(tiendaNode);

        // Agregar comodines
        Comodin comodin1 = tienda.obtenerComodines().get(0);
        //System.out.println(comodin1.getNombre());
        String cm1 = comodin1.getNombre();
        ComodinVisual comodinVisual1 = new ComodinVisual(comodin1, "/imagenes/comodines/" + cm1 + ".png", 150, 200);
        lblComodinTienda1.getChildren().add(comodinVisual1);

        Comodin comodin2 = tienda.obtenerComodines().get(1);
        String cm2 = comodin2.getNombre();
        ComodinVisual comodinVisual2 = new ComodinVisual(comodin2, "/imagenes/comodines/" + cm2 + ".png", 150, 200);
        lblComodinTienda2.getChildren().add(comodinVisual2);

        // Agregar cartas de tarot
        Tarot tarot1 = tienda.obtenerTarots().get(0);
        String tr1 = tarot1.getNombre();
        TarotVisual tarotVisual1 = new TarotVisual(tarot1, "/imagenes/tarot/" + tr1 + ".png", 150, 200);
        lblTarotTienda1.getChildren().add(tarotVisual1);

        Tarot tarot2 = tienda.obtenerTarots().get(1);
        String tr2 = tarot2.getNombre();
        TarotVisual tarotVisual2 = new TarotVisual(tarot2, "/imagenes/tarot/" + tr2 + ".png", 150, 200);
        lblTarotTienda2.getChildren().add(tarotVisual2);

        // Agregar carta de póker
        CartaPoker carta = tienda.obtenerCartas().get(0);
        String ct = carta.getNombreArchivo();
        //String ct = carta.getNombreArchivo(); no funciona no se porque, me trae el nombre pero rompe si lo mesclo con el imagePath
        CartaVisual cartaVisual = new CartaVisual(carta, "/imagenes/cartas/2C.png", 150, 200);
        lblCartaTienda1.getChildren().add(cartaVisual);

    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
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

