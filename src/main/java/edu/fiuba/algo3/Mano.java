package edu.fiuba.algo3;

import edu.fiuba.algo3.jugadas.Descarte;

import java.util.ArrayList;

public class Mano extends ConjuntoCartas{

    private final ArrayList<CartaPoker> seleccionadas;
    private final int capacidad;
    private Mazo mazo;

    public Mano(Mazo unMazo) {
        super();
        this.seleccionadas = new ArrayList<CartaPoker>();
        this.capacidad = 8;
        this.mazo = unMazo;
    }

    public Mano(ArrayList<CartaPoker> cartas) {
        super();
        this.cartas = cartas;
        this.seleccionadas = new ArrayList<CartaPoker>();
        this.capacidad = 8;
    }

    public boolean manoLlena() {
        return cartas.size() == capacidad;
    }

    public void rellenarse() {
        while (!manoLlena() && this.mazo.tieneCartas()) {
            this.agregarCarta(this.mazo.darCarta());
        }
    }

    public void seleccionarCartas(ArrayList<CartaPoker> cartasASeleccionar) {
        for (CartaPoker carta : cartasASeleccionar) {
            if (this.seleccionadas.size() < this.capacidad) {
                this.seleccionadas.add(carta);
            }else{
                throw new ErrorCapacidadLlena();
            }
        }
    }

    public void deseleccionarCarta(ArrayList<CartaPoker> cartasADeseleccionar) {
        for (CartaPoker carta : cartasADeseleccionar){
            this.seleccionadas.remove(carta);
        }
    }

    public Descarte descartar() {
        Descarte unDescarte = null;
        if(!this.seleccionadas.isEmpty()) {
            unDescarte = new Descarte(this.seleccionadas);
        }
        this.cartas.removeAll(this.seleccionadas);
        this.seleccionadas.clear();
        return unDescarte;
    }

    public boolean compararSeleccionadasCon(ArrayList<CartaPoker> otrasSeleccionadas) {
        if (this.seleccionadas.size() != otrasSeleccionadas.size()) {
            return false;
        }
        for (int i = 0; i < this.seleccionadas.size(); i++) {
            CartaPoker carta1 = this.seleccionadas.get(i);
            CartaPoker carta2 = otrasSeleccionadas.get(i);
            if (!carta1.compararCartaCon(carta2)) {
                return false;
            }
        }
        return true;
    }

    public Jugada jugar() {
        Jugada jugadaNueva = Jugada.crearJugada(this.seleccionadas);
        this.cartas.removeAll(this.seleccionadas);
        this.seleccionadas.clear();
        //rellenarse();
        return jugadaNueva;
    }
}
