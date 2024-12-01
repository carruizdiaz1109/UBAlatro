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
import javafx.stage.Stage;


public class BalatroController {

    @FXML
    private RondaController rondaController;
    @FXML
    private TiendaController tiendaController;

    private RondaVisual rondaVisual;
    private TiendaVisual tiendaVisual;
    private Stage stage;
    private Balatro balatro;

    public BalatroController() {}

    @FXML
    public void initialize() {}
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setBalatro(Balatro juego) {
        this.balatro = juego;
    }

    public void inicializarRonda() {
        Ronda actual = this.balatro.getRondaActual();
        this.balatro.jugarRonda();
        //Jugador jugador = this.balatro.getJugador();
        mostrarTienda(actual);
    }

    public void mostrarTienda(Ronda actual) {
        try {
            this.tiendaVisual = new TiendaVisual(this.stage);
            this.tiendaVisual.setTienda(actual.getTienda());
            this.tiendaVisual.setBalatroController(this);

            this.tiendaVisual.mostrar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void avanzarRonda() {
        this.balatro.avanzarRonda();
        inicializarRonda();
    }

    public void mostrarRonda() {
        System.out.println("Se llega hasta aca");
        Ronda actual = this.balatro.getRondaActual();
        Jugador jugador = this.balatro.getJugador();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ronda.fxml"));
            Parent root = loader.load();

            RondaController rondaController = loader.getController();

            // Configurar RondaVisual
            RondaVisual rondaVisual = new RondaVisual(
                    actual,
                    rondaController.lblPuntajeAcumulado,
                    rondaController.lblJugadasDisponibles,
                    rondaController.lblObjetivo,
                    rondaController.lblDescartesDisponibles
            );

            // Inyectar dependencias
            rondaController.setJugador(jugador);
            rondaController.setRondaVisual(rondaVisual);
            rondaController.iniciarRonda();

            // Mostrar la escena
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}