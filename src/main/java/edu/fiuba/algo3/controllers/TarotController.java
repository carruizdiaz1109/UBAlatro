package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.Tarot;
import edu.fiuba.algo3.Tienda;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;

import java.util.List;

public class TarotController {

    private final HBox lblTarot;
    private final Tienda tienda;

    public TarotController(Tienda tienda, HBox lblTarot) {
        this.lblTarot = lblTarot;
        this.tienda = tienda;
    }

    private void mostrarDescripcion(javafx.scene.image.ImageView vistaCarta, Tarot tarot){
        Tooltip tooltip = new Tooltip(tarot.getDescripcion());
        Tooltip.install(vistaCarta, tooltip);
    }

    public void mostrarCartasTarot(List<Tarot> tarots) {
        lblTarot.getChildren().clear();

        for (Tarot tarot : tarots) {
            javafx.scene.image.Image imagenCarta;
            try {
                imagenCarta = new javafx.scene.image.Image(
                        getClass()
                                .getResource("/imagenes/tarot/" + tarot.getNombre() + ".png")
                                .toExternalForm()
                );
            } catch (NullPointerException | IllegalArgumentException e) {
                imagenCarta = new javafx.scene.image.Image(
                        getClass()
                                .getResource("/imagenes/tarot/El Ahorcado.png")
                                .toExternalForm()
                );
            }

            javafx.scene.image.ImageView vistaCarta = new javafx.scene.image.ImageView(imagenCarta);
            vistaCarta.setFitWidth(120);
            vistaCarta.setFitHeight(180);
            vistaCarta.setPreserveRatio(true);

            mostrarDescripcion(vistaCarta, tarot);

            HBox.setMargin(vistaCarta, new javafx.geometry.Insets(50, 10, 50, 10));

            lblTarot.getChildren().add(vistaCarta);
        }
    }

    public void cargarCartasTarot() {
        List<Tarot> tarots = tienda.obtenerTarots();
        mostrarCartasTarot(tarots);
    }
}
