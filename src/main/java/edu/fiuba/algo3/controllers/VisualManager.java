package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.entidades.cartas.CartaPoker;
import edu.fiuba.algo3.modelo.entidades.comodines.Comodin;
import edu.fiuba.algo3.modelo.entidades.tarots.Tarot;
import javafx.scene.Node;
import javafx.scene.control.Tooltip;

public class VisualManager {
    public static void aplicarTooltip(Node nodo, String textoTooltip) {
        Tooltip tooltip = new Tooltip(textoTooltip);
        tooltip.setWrapText(true);
        tooltip.setMaxWidth(200);
        tooltip.getStyleClass().add("tooltip");
        Tooltip.install(nodo, tooltip);
    }

    public static void mostrarCartelComodin(Node nodo, Comodin unComodin) {
        aplicarTooltip(nodo, unComodin.getDescripcion());
    }

    public static void mostrarCartelTarot(Node nodo, Tarot unTarot) {
        aplicarTooltip(nodo, unTarot.getDescripcion());
    }

    public static void mostrarCartelCarta(Node nodo, CartaPoker unaCarta) {
        String puntos = unaCarta.getPuntaje().mostrarPuntos();
        String multiplicador = unaCarta.getPuntaje().mostrarMultiplicador();
        aplicarTooltip(nodo, puntos +"\n"+ multiplicador);

    }
}
