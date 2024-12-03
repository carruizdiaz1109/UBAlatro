package edu.fiuba.algo3.modelo.entidades;

import java.util.HashMap;
import java.util.Map;

public class ConfiguracionJugadas {
    private static ConfiguracionJugadas instancia;
    private static Map<String, Puntaje> puntajes = new HashMap<>();

    static {
        puntajes.put("EscaleraReal", new Puntaje(100, 8));
        puntajes.put("EscaleraColor", new Puntaje(100, 8));
        puntajes.put("Poker", new Puntaje(60, 7));
        puntajes.put("FullHouse", new Puntaje(40, 4));
        puntajes.put("Color", new Puntaje(35, 4));
        puntajes.put("Escalera", new Puntaje(30, 4));
        puntajes.put("Trio", new Puntaje(30, 3));
        puntajes.put("DoblePar", new Puntaje(20, 2));
        puntajes.put("Par", new Puntaje(10, 2));
        puntajes.put("CartaAlta", new Puntaje(5, 1));
        puntajes.put("Descarte", new Puntaje(0, 1));
    }

    private ConfiguracionJugadas() {}

    public static ConfiguracionJugadas getInstancia() {
        if (instancia == null) {
            instancia = new ConfiguracionJugadas();
        }
        return instancia;
    }

    public Puntaje getPuntuacion(String tipoJugada) {
        return puntajes.get(tipoJugada);
    }

    public void actualizarPuntuacion(String tipoJugada, Puntaje nuevoPuntaje) {
        if (puntajes.containsKey(tipoJugada)) {
            puntajes.put(tipoJugada, nuevoPuntaje);
        } else {
            System.out.println("La jugada especificada no existe en la configuración.");
        }
    }
}