package edu.fiuba.algo3.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.util.Objects;


public class ResultadoController {

    @FXML
    private Label lblResultado;

    @FXML
    private Button btnVolverAJugar;

    @FXML
    private Button btnSalir;

    @FXML
    private Button btnRondaSiguiente;


    @FXML
    private VBox root;
    private BalatroController balatroController;

    @FXML
    public void initialize() {
        root.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles.css")).toExternalForm());
        btnVolverAJugar.setOnAction(event -> volverAJugar());
        btnSalir.setOnAction(event -> salir());
        btnRondaSiguiente.setOnAction(event -> siguienteRonda());
    }

    public void setBalatroController(BalatroController balatroController) {
        this.balatroController = balatroController;
    }

    public void mostrarMensaje(String mensaje, boolean gano) {
        lblResultado.setText(mensaje);
        if (gano) {
            root.setStyle("-fx-background-color: linear-gradient(to bottom, #4CAF50, #81C784);");
            btnRondaSiguiente.setVisible(true);
            btnVolverAJugar.setVisible(false);
        } else {
            root.setStyle("-fx-background-color: linear-gradient(to bottom, #E53935, #EF9A9A);");
            btnRondaSiguiente.setVisible(false);
            btnVolverAJugar.setVisible(true);
        }
    }

    private void volverAJugar() {
        this.balatroController.reiniciarJuego();
    }

    private void siguienteRonda(){
        this.balatroController.avanzarRonda();
    }

    private void salir() {
        System.exit(0);
    }
}