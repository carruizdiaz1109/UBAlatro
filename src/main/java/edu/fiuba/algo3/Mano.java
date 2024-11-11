package edu.fiuba.algo3;

<<<<<<< HEAD
import java.util.List;

public class Mano {

    private List<CartaPoker> cartas;
    private int cantidadCartas;
    private int capacidad;

    public Mano(List<CartaPoker> cartas) {
        this.cartas = cartas;
=======
import java.util.ArrayList;

public class Mano {

    private ArrayList<CartaPoker> cartas;
    private ArrayList<CartaPoker> seleccionadas;
    private int cantidadCartas;
    private int capacidad;

    public Mano() {
        this.cartas = new ArrayList<CartaPoker>();
        this.seleccionadas = new ArrayList<CartaPoker>();
        this.cantidadCartas = 0;
        this.capacidad = 8;
    }

    public Mano(ArrayList<CartaPoker> cartas) {
        this.cartas = cartas;
        this.seleccionadas = new ArrayList<CartaPoker>();
>>>>>>> 08691c89645fc7c4e2182fa864145bdfcb226064
        this.cantidadCartas = cartas.size();
        this.capacidad = 8;
    }

<<<<<<< HEAD
    public void recibirCarta(CartaPoker carta) {
=======
    public void eliminarCartas() {
        cartas.clear();
    }

    public void agregarCarta(CartaPoker carta) {
>>>>>>> 08691c89645fc7c4e2182fa864145bdfcb226064
        if (cantidadCartas < capacidad) {
            cartas.add(carta);
            cantidadCartas++;
        }
    }

    public boolean manoLlena() {
        return cantidadCartas == capacidad;
    }
<<<<<<< HEAD
    /*
    public seleccionarCarta() {

    }

    public ordenarCartas() {

    }

    public descartar(List<CartaPoker> cartas) {

    }
    */
=======

    public void seleccionarCarta(int indice) {
        if (indice >= 0 && indice < cartas.size()) {
            CartaPoker carta = cartas.get(indice);
            if (!seleccionadas.contains(carta)) {
                seleccionadas.add(carta);
            }
        }
    }

    public void deseleccionarCarta(int indice) {
        if (indice >= 0 && indice < cartas.size()) {
            CartaPoker carta = cartas.get(indice);
            seleccionadas.remove(carta);
        }
    }

    /*
    public void ordenarCartas() {

    }
    */

    public void descartar() {
        if(!seleccionadas.isEmpty()) seleccionadas.clear();
    }
>>>>>>> 08691c89645fc7c4e2182fa864145bdfcb226064
}
