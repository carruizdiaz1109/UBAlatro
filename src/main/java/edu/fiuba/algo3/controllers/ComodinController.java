package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.entidades.Jugador;
import edu.fiuba.algo3.modelo.entidades.Tarot;
import edu.fiuba.algo3.modelo.entidades.comodines.Comodin;
import edu.fiuba.algo3.vistas.ComodinVisual;
import edu.fiuba.algo3.vistas.TarotVisual;
import javafx.scene.Node;
import javafx.scene.control.Tooltip;
import javafx.scene.input.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;

public class ComodinController {
    private final HBox lblComodin;
    private final Jugador jugador;

    public ComodinController(Jugador jugador, HBox lblcomodin) {
        this.jugador = jugador;
        this.lblComodin = lblcomodin;
    }

    public void visualizarComodines() {
        this.lblComodin.getChildren().clear(); // Limpiar el contenedor antes de mostrar nuevas cartas de Tarot
        ArrayList<Comodin> comodines = this.jugador.obtenerComodines();

        for (Comodin comodin : comodines) {
            String imagePath = "/imagenes/comodines/" + comodin.getNombre() + ".png";
            ComodinVisual comodinVisual = new ComodinVisual(comodin, imagePath, 100, 150);

            this.lblComodin.getChildren().add(comodinVisual);

        }
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
}
