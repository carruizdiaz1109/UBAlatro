package edu.fiuba.algo3.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;


public class ResultadoController {

    @FXML
    private Label lblResultado;

    @FXML
    private Button btnVolverAJugar;

    @FXML
    private Button btnSalir;

    @FXML
    public void initialize() {
        btnVolverAJugar.setOnAction(event -> volverAJugar());
        btnSalir.setOnAction(event -> salir());
    }

    public void mostrarMensaje(String mensaje) {
        lblResultado.setText(mensaje);
    }

    private void volverAJugar() {
        System.out.println("Volver a jugar presionado");
        // Aquí puedes reiniciar el juego o cargar otra escena
    }

    private void salir() {
        System.exit(0);
    }
}