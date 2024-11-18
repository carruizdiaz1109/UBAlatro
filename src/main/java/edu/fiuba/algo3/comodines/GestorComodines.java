package edu.fiuba.algo3.comodines;

import java.util.ArrayList;
import java.util.List;


public class GestorComodines {
    private final List<ComandoComodin> comandos = new ArrayList<>();

    public void agregarComando(ComandoComodin comando) {
        comandos.add(comando);
    }

    public void ejecutarComandos() {
        for (ComandoComodin comando : comandos) {
            comando.ejecutar();
        }
    }
}

