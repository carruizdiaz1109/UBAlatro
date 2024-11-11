package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.Jugador;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Test01 {
    @Test
    public void test01VerificarQueUnJugadorPoseaCartasSuficientesParaEmpezarElJuegoEnSuMazo(){
        Jugador jugador = new Jugador("Juan");
        boolean esCantidadSuficienteDeCartas = jugador.esPosibleIniciarRonda();

        assertTrue(esCantidadSuficienteDeCartas);
    }
}
