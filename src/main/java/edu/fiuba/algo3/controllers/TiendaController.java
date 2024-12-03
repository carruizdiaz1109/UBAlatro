package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.entidades.cartas.CartaPoker;
import edu.fiuba.algo3.modelo.entidades.Jugador;
import edu.fiuba.algo3.modelo.entidades.tarots.Tarot;
import edu.fiuba.algo3.modelo.entidades.Tienda;
import edu.fiuba.algo3.modelo.entidades.comodines.Comodin;
import edu.fiuba.algo3.modelo.interfaces.Comprable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import javafx.scene.image.ImageView;

import javafx.scene.control.Tooltip;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.*;

public class TiendaController {

    private Tienda tienda;
    private Jugador jugador;
    private BalatroController balatroController;
    MediaPlayer mediaPlayer;

    @FXML
    private VBox slot1, slot2, slot3, slot4, slot5;

    @FXML
    private Button comprarSlot1, comprarSlot2, comprarSlot3, comprarSlot4, comprarSlot5;

    private final Map<Button, Comprable> slotMap = new HashMap<>(); // Mapeo entre botones y elementos comprables
    private final List<VBox> slots = new ArrayList<>();

    public TiendaController() {}

    public void setTienda(Tienda tienda) {
        this.tienda = tienda;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public void setBalatroController(BalatroController balatroController) {
        this.balatroController = balatroController;
    }

    @FXML
    public void initialize() {
        slots.add(slot1);
        slots.add(slot2);
        slots.add(slot3);
        slots.add(slot4);
        slots.add(slot5);
    }

    public void visualizarComprables() {
        // Limpia todos los slots
        slots.forEach(slot -> slot.getChildren().clear());
        slotMap.clear();

        // Obtén todos los elementos comprables
        ArrayList<Comprable> comprables = new ArrayList<>();
        comprables.addAll(tienda.obtenerComodines());
        comprables.addAll(tienda.obtenerTarots());
        comprables.addAll(tienda.obtenerCartas());

        // Asigna los elementos comprables a los slots
        Button[] botones = {comprarSlot1, comprarSlot2, comprarSlot3, comprarSlot4, comprarSlot5};

        for (int i = 0; i < botones.length; i++) {
            if (i < comprables.size()) {
                Comprable comprable = comprables.get(i);
                Button boton = botones[i];
                VBox slot = slots.get(i);

                // Muestra el elemento en el slot
                mostrarElementoEnSlot(comprable, slot);

                // Asocia el botón con el elemento
                slotMap.put(boton, comprable);

                // Habilita el botón y le asigna un evento
                boton.setDisable(false);
                boton.setOnAction(event -> realizarCompra(boton));
            } else {
                // Si no hay más elementos, deshabilita el botón
                botones[i].setDisable(true);
            }
        }
    }

    private void mostrarElementoEnSlot(Comprable comprable, VBox slot) {

        slot.getChildren().clear();
        String imagePath = null;

        if (comprable instanceof Comodin) {
            Comodin comodin = (Comodin) comprable;
            imagePath = "/imagenes/comodines/" + comodin.getNombre() + ".png";
            VisualManager.mostrarCartelComodin(slot, comodin);
        } else if (comprable instanceof Tarot) {
            Tarot tarot = (Tarot) comprable;
            imagePath = "/imagenes/tarot/" + tarot.getNombre() + ".png";
            VisualManager.mostrarCartelTarot(slot, tarot);
        } else if (comprable instanceof CartaPoker) {
            CartaPoker carta = (CartaPoker) comprable;
            imagePath = "/imagenes/cartas/" + carta.getNombreArchivo();
            VisualManager.mostrarCartelCarta(slot, carta);
        }

        if (imagePath != null) {
            try {
                ImageView imageView = new ImageView(new javafx.scene.image.Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath))));
                imageView.setFitWidth(160);
                imageView.setPreserveRatio(true);

                slot.getChildren().add(imageView);
            } catch (Exception e) {
                System.err.println("No se pudo cargar la imagen: " + imagePath);
                e.printStackTrace();
            }
        }
    }

    private void realizarCompra(Button boton) {
        // Recupera el elemento asociado al botón
        Comprable comprable = slotMap.get(boton);

        if (comprable instanceof Comodin) {
            jugador.aniadirComodin((Comodin) comprable);
        } else if (comprable instanceof Tarot) {
            jugador.aniadirTarot((Tarot) comprable);
        } else if (comprable instanceof CartaPoker) {
            jugador.aniadirCartaPoker((CartaPoker) comprable);
        }

        reproducirSonidoCompra();

        boton.setDisable(true);
        slotMap.keySet().forEach(btn -> btn.setDisable(true));
        System.out.println("Has comprado: " + comprable.getClass().getSimpleName());
    }
    public void reproducirSonidoCompra() {
        // Ruta del archivo de sonido
        String rutaSonido = getClass().getResource("/sample/sonidoCompra.mp3").toExternalForm();
        Media media = new Media(rutaSonido);
        mediaPlayer = new MediaPlayer(media);

        // Ajustar volumen
        mediaPlayer.setVolume(0.9);

        // Reproducir sonido
        mediaPlayer.play();
    }
    @FXML
    public void onContinuarClick() {
        this.balatroController.mostrarRonda();
    }
}
