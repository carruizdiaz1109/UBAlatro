package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.entidades.CartaPoker;
import edu.fiuba.algo3.modelo.entidades.Tarot;
import edu.fiuba.algo3.modelo.entidades.Tienda;
import edu.fiuba.algo3.modelo.entidades.comodines.Comodin;
import edu.fiuba.algo3.vistas.CartaVisual;
import edu.fiuba.algo3.vistas.ComodinVisual;
import edu.fiuba.algo3.vistas.TarotVisual;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

import java.io.IOException;


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

import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class TiendaController {
    private Tienda tienda;
    private BalatroController balatroController;

    @FXML
    private Button btnContinuar;

    @FXML
    private HBox comodines;


    public TiendaController() {}

   public void setTienda(Tienda tienda) {
        this.tienda = tienda;
    }

    public void setBalatroController(BalatroController balatroController) {
        this.balatroController = balatroController;
    }

    @FXML
    public void onContinuarClick() {
        this.balatroController.mostrarRonda();
    }


    public void visualizarComprables() {
        this.comodines.getChildren().clear();
        ArrayList<Comodin> listaComodines = this.tienda.obtenerComodines();
        ArrayList<Tarot> listTarot = this.tienda.obtenerTarots();
        ArrayList<CartaPoker> listCartasPoker = this.tienda.obtenerCartas();

        for (Comodin comodin : listaComodines) {
            String imagePath = "/imagenes/comodines/" + comodin.getNombre() + ".png";
            ComodinVisual comodinVisual = new ComodinVisual(comodin, imagePath, 100, 150);
            mostrarDescripcionComodin(comodinVisual, comodin);
            this.comodines.getChildren().add(comodinVisual);
        }
        for (Tarot tarot : listTarot) {
            String imagePath = "/imagenes/tarot/" + tarot.getNombre() + ".png"; // Asumimos que las cartas de tarot tienen un nombre
            TarotVisual tarotVisual = new TarotVisual(tarot, imagePath, 100, 150); // Tamaño fijo o dinámico
            mostrarDescripcionTarot(tarotVisual, tarot);
            this.comodines.getChildren().add(tarotVisual);
        }
        for (CartaPoker carta: listCartasPoker) {
            CartaVisual cartaVisual = new CartaVisual( carta,
                    "/imagenes/cartas/" + carta.getNombreArchivo(),
                    120,
                    180
            );
            this.comodines.getChildren().add(cartaVisual);
        }
    }

    private void mostrarDescripcionComodin(javafx.scene.image.ImageView vistaCarta, Comodin unComodin){
        Tooltip tooltip = new Tooltip(unComodin.getDescripcion());
        Tooltip.install(vistaCarta, tooltip);
    }

    private void mostrarDescripcionTarot(javafx.scene.image.ImageView vistaCarta, Tarot unTarot){
        Tooltip tooltip = new Tooltip(unTarot.getDescripcion());
        Tooltip.install(vistaCarta, tooltip);
    }
}
