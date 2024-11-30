package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.entidades.comodines.*;
import edu.fiuba.algo3.modelo.entidades.Tienda;

import java.util.List;

import javafx.scene.Node;
import javafx.scene.control.Tooltip;

import javafx.scene.input.*;
import javafx.scene.layout.HBox;

public class ComodinController {

    private final HBox lblComodin;
    private final Tienda tienda;

    public ComodinController(Tienda tienda, HBox lblComodin){
        this.tienda = tienda;
        this.lblComodin = lblComodin;
    }

    private javafx.scene.image.ImageView encontrarCartaPorURL(String url) {
        for (Node node : lblComodin.getChildren()) {
            if (node instanceof javafx.scene.image.ImageView) {
                javafx.scene.image.ImageView carta = (javafx.scene.image.ImageView) node;
                if (carta.getImage().getUrl().equals(url)) {
                    return carta;
                }
            }
        }
        return null;
    }

    private void manejarDragDrop(javafx.scene.image.ImageView cartaDestino, DragEvent event) {
        Dragboard dragboard = event.getDragboard();
        if (dragboard.hasString()) {
            String url = dragboard.getString();

            javafx.scene.image.ImageView cartaArrastrada = encontrarCartaPorURL(url);
            if (cartaArrastrada != null) {

                int indexDestino = lblComodin.getChildren().indexOf(cartaDestino);
                int indexArrastrada = lblComodin.getChildren().indexOf(cartaArrastrada);

                if (indexDestino != -1 && indexArrastrada != -1) {
                    if (indexDestino < indexArrastrada) {
                        lblComodin.getChildren().remove(cartaArrastrada);
                        lblComodin.getChildren().add(indexDestino, cartaArrastrada);
                    } else {
                        lblComodin.getChildren().remove(cartaArrastrada);
                        lblComodin.getChildren().add(indexDestino, cartaArrastrada);
                    }
                }
            }
        }
        event.setDropCompleted(true);
        event.consume();
    }

    private void manejarDragOver(DragEvent event) {
        if (event.getGestureSource() != event.getTarget() && event.getDragboard().hasString()) {
            event.acceptTransferModes(TransferMode.MOVE);
        }
        event.consume();
    }

    private void iniciarArrastre(javafx.scene.image.ImageView carta, MouseEvent event) {
        Dragboard dragboard = carta.startDragAndDrop(TransferMode.MOVE);
        ClipboardContent content = new ClipboardContent();
        content.putString(carta.getImage().getUrl());

        dragboard.setContent(content);
        event.consume();
    }

    private void arrastrar(javafx.scene.image.ImageView vistaCarta) {
        vistaCarta.setOnDragDetected(event -> iniciarArrastre(vistaCarta, event));
        vistaCarta.setOnDragOver(event -> manejarDragOver(event));
        vistaCarta.setOnDragDropped(event -> manejarDragDrop(vistaCarta, event));
    }

    private void mostrarDescripcion(javafx.scene.image.ImageView vistaCarta, Comodin comodin){
        Tooltip tooltip = new Tooltip(comodin.getDescripcion());
        Tooltip.install(vistaCarta, tooltip);
    }

    public void mostrarCartasComodin(List<Comodin> comodines) {
        lblComodin.getChildren().clear();

        for (Comodin comodin : comodines) {
            javafx.scene.image.Image imagenCarta;
            try {
                imagenCarta = new javafx.scene.image.Image(
                        getClass()
                                .getResource("/imagenes/comodines/" + comodin.getNombre() + ".png")
                                .toExternalForm()
                );
            } catch (NullPointerException | IllegalArgumentException e) {
                imagenCarta = new javafx.scene.image.Image(
                        getClass()
                                .getResource("/imagenes/comodines/Comodin Astuto.png")
                                .toExternalForm()
                );
            }

            javafx.scene.image.ImageView vistaCarta = new javafx.scene.image.ImageView(imagenCarta);
            vistaCarta.setFitWidth(120);
            vistaCarta.setFitHeight(180);
            vistaCarta.setPreserveRatio(true);

            arrastrar(vistaCarta);
            mostrarDescripcion(vistaCarta, comodin);

            HBox.setMargin(vistaCarta, new javafx.geometry.Insets(50, 10, 50, 10));

            lblComodin.getChildren().add(vistaCarta);
        }
    }

    public void cargarCartasComodin(){
        List<Comodin> comodines = tienda.obtenerComodines();
        mostrarCartasComodin(comodines);
    }
}
