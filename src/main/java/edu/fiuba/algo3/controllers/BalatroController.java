package edu.fiuba.algo3.controllers;
import edu.fiuba.algo3.modelo.entidades.Balatro;
import edu.fiuba.algo3.modelo.entidades.Jugador;
import edu.fiuba.algo3.modelo.entidades.Ronda;
import edu.fiuba.algo3.vistas.TiendaVisual;
import edu.fiuba.algo3.vistas.RondaVisual;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.IOException;


public class BalatroController {

    @FXML
    private RondaController rondaController;
    @FXML
    private TiendaController tiendaController;

    private TiendaVisual tiendaVisual;
    private Stage stage;
    private Balatro balatro;
    private String nombre;

    public BalatroController() {}

    @FXML
    public void initialize() {

    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setBalatro(Balatro juego) {
        this.balatro = juego;
    }

    public void inicializarRonda() {
        Ronda actual = this.balatro.getRondaActual();
        this.balatro.jugarRonda();
        mostrarTienda(actual);
    }

    public void reiniciarJuego() {
        this.balatro = new Balatro("Prueba");
        this.inicializarRonda();
    }

    public void avanzarRonda() {
        this.balatro.avanzarRonda();
        inicializarRonda();
    }

    public void mostrarTienda(Ronda actual) {
        try {
            this.tiendaVisual = new TiendaVisual(this.stage, balatro.getJugador());
            this.tiendaVisual.setTienda(actual.getTienda());
            this.tiendaVisual.setBalatroController(this);

            this.tiendaVisual.mostrar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mostrarRonda() {
        Ronda actual = this.balatro.getRondaActual();
        Jugador jugador = this.balatro.getJugador();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ronda.fxml"));
            Parent root = loader.load();

            RondaController rondaController = loader.getController();

            RondaVisual rondaVisual = new RondaVisual(
                    actual,
                    rondaController.lblPuntajeAcumulado,
                    rondaController.lblJugada,
                    rondaController.lblJugadasDisponibles,
                    rondaController.lblObjetivo,
                    rondaController.lblDescartesDisponibles,
                    rondaController.lblRonda
            );

            rondaController.setJugador(jugador);
            rondaController.setBalatroController(this);
            rondaController.setRondaVisual(rondaVisual);
            rondaController.setRondaActual(this.balatro.getRondaActual());
            rondaController.iniciarRonda();

            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void mostrarResultado(Balatro.EstadoJuego estadoJuego) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resultado.fxml"));
            Parent root = loader.load();

            ResultadoController resultadoController = loader.getController();

            resultadoController.setBalatroController(this);

            if (estadoJuego == Balatro.EstadoJuego.GANADO) {
                resultadoController.mostrarMensaje("Ganaste el juego", estadoJuego);
            } else if (estadoJuego == Balatro.EstadoJuego.EMPEZADO) {
                resultadoController.mostrarMensaje("Siguiente ronda", estadoJuego);
            } else {
                resultadoController.mostrarMensaje("Perdiste", estadoJuego);
            }

            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void finDeRonda() {
        this.balatro.verificarEstadoJuego();
       mostrarResultado(this.balatro.getEstadoJuego());
    }

}