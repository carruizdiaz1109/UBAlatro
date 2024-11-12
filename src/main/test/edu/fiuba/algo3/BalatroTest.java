package edu.fiuba.algo3;

import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;


public class BalatroTest {

    @Test
    public void test01IniciarBalatroLlamaAMetodoDeRondaOchoVeces() {
        Ronda rondaMock = Mockito.mock(Ronda.class);
        Balatro balatro = new Balatro("Pablo") {
            @Override
            protected Ronda crearRonda() {
                return rondaMock;
            }
        };

        when(rondaMock.verificarPuntaje()).thenReturn(true);

        balatro.iniciarJuego();

        verify(rondaMock, times(8)).iniciarRonda();
    }

    @Test
    public void test02BalatroCrea8RondasMaximo() {
        Ronda rondaMock = Mockito.mock(Ronda.class);
        Balatro balatro = new Balatro("Pedro");
        Balatro balatroMock = Mockito.spy(balatro);

        doReturn(rondaMock).when(balatroMock).crearRonda();
        when(rondaMock.verificarPuntaje()).thenReturn(true);

        balatroMock.iniciarJuego();
        verify(balatroMock, times(8)).crearRonda();
    }
}


