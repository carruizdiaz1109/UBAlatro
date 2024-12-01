package edu.fiuba.algo3.entrega_2;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.fiuba.algo3.modelo.entidades.*;
import edu.fiuba.algo3.modelo.entidades.cartas.CartaFactory;
import edu.fiuba.algo3.modelo.entidades.cartas.CartaPoker;
import edu.fiuba.algo3.modelo.entidades.comodines.*;
import edu.fiuba.algo3.modelo.entidades.jugadas.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class IntegracionTest2 {
    private Tienda tienda;
    private JsonNode tiendaNode;

    @BeforeEach
    public void setUp() throws Exception {
        // JSON de ejemplo
        String json = "{" +
                "\"comodines\": [" +
                "{ \"nombre\": \"Comodin Astuto\", \"descripcion\": \"+50 fichas si la mano jugada contiene un par\", \"activacion\": { \"Mano Jugada\": \"par\" }, \"efecto\": { \"puntos\": 50, \"multiplicador\": 1 } }, " +
                "{ \"nombre\": \"Cumbre Mistica\", \"descripcion\": \"x15 multiplicación por cada descarte\", \"activacion\": \"Descarte\", \"efecto\": { \"puntos\": 1, \"multiplicador\": 15 } } " +
                "], " +
                "\"tarots\": [" +
                "{ \"nombre\": \"El Mago\", \"descripcion\": \"Mejora la mano par\", \"efecto\": { \"puntos\": 15, \"multiplicador\": 2 }, \"sobre\": \"mano\", \"ejemplar\": \"par\" }, " +
                "{ \"nombre\": \"El Carro\", \"descripcion\": \"Mejora 1 carta seleccionada y la convierte en una carta de acero.\", \"efecto\": { \"puntos\": 1, \"multiplicador\": 1.5 }, \"sobre\": \"carta\", \"ejemplar\": \"cualquiera\" }" +
                "]" +
                "}";

        // Convertir el JSON a JsonNode usando ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        tiendaNode = objectMapper.readTree(json);

        // Crear la tienda con el JSON
        tienda = new Tienda(tiendaNode);
    }


    @Test
    public void test01SeVerificaQueSeApliqueComodinAEscaleraCorrectamente () {
        //Arrange
        Ronda ronda = new Ronda(1,10000,3,3, tienda);
        CartaPoker carta1 = CartaFactory.crearCarta(Valor.DOS, Palo.PICAS);
        CartaPoker carta2 = CartaFactory.crearCarta(Valor.TRES, Palo.DIAMANTES);
        CartaPoker carta3 = CartaFactory.crearCarta(Valor.CUATRO, Palo.DIAMANTES);
        CartaPoker carta4 = CartaFactory.crearCarta(Valor.CINCO, Palo.CORAZONES);
        CartaPoker carta5 = CartaFactory.crearCarta(Valor.SEIS, Palo.PICAS);

        ArrayList<CartaPoker> cartas = new ArrayList<>(List.of(carta1, carta2, carta3, carta4, carta5));
        Mazo mazo = new Mazo(cartas);
        Mano mano = new Mano(mazo);
        Puntaje puntaje = new Puntaje(0, 3);
        EfectoJugada unComodin = new EfectoJugada(Escalera.class, puntaje, "Suben los puntos" ,"x3 de multiplicador si se juega escalera", new NoAleatorio());
        Jugador jugador = new Jugador("Pepe", mazo) {
            @Override
            public void iniciarRonda(Ronda rondaActual){
                this.rondaActual = rondaActual;
                this.mazo = mazo;
                this.manoActual = mano;
            }
        };
        int puntajeEsperado1 = (2+3+4+5+6+30)*12;
        jugador.aniadirComodin(unComodin);
        jugador.iniciarRonda(ronda);
        jugador.seleccionarCarta(new ArrayList<>(List.of(carta1, carta2, carta3, carta4, carta5)));

        //Act
        jugador.jugar();
        int puntajeObtenido = ronda.calcularTotalRonda();
        //Assert
        assertEquals(puntajeEsperado1, puntajeObtenido);
    }

    @Test
    public void test02SeVerificaQueSeAplicaComodinPuntajeCorrectamente() {
        //Arrange
        Tienda tiendaMock = mock(Tienda.class);
        Ronda ronda = new Ronda(1,10000,3,3, tiendaMock);

        CartaPoker carta1 = CartaFactory.crearCarta(Valor.DOS, Palo.PICAS);
        CartaPoker carta2 = CartaFactory.crearCarta(Valor.DOS, Palo.DIAMANTES);
        CartaPoker carta3 = CartaFactory.crearCarta(Valor.DOS, Palo.DIAMANTES);
        CartaPoker carta4 = CartaFactory.crearCarta(Valor.DOS, Palo.CORAZONES);
        CartaPoker carta5 = CartaFactory.crearCarta(Valor.SEIS, Palo.PICAS);

        ArrayList<CartaPoker> cartas = new ArrayList<>(List.of(carta1, carta2, carta3, carta4, carta5));
        Mazo mazo = new Mazo(cartas);
        Mano mano = new Mano(mazo);
        Puntaje puntaje = new Puntaje(0, 8);
        Comodin unComodin = new EfectoPuntaje( puntaje, "Lluvia de puntos" ,"x8 de multiplicador", new NoAleatorio());
        Jugador jugador = new Jugador("Pepe", mazo) {
            @Override
            public void iniciarRonda(Ronda rondaActual){
                this.rondaActual = ronda;
                this.mazo = mazo;
                this.manoActual = mano;
            }
        };
        jugador.iniciarRonda(ronda);
        int puntajeEsperado = (2*4+60)*7*8;

        jugador.seleccionarCarta(new ArrayList<>(List.of(carta1, carta2, carta3, carta4, carta5)));
        jugador.aniadirComodin(unComodin);
        //Act
        jugador.jugar();
        int puntajeObtenido = ronda.calcularTotalRonda();
        //Assert
        Assertions.assertEquals(puntajeEsperado, puntajeObtenido);
    }

    @Test
    public void test03SeVerificaQueSeAplicaComodinDescarteCorrectamente () {
        //Arrange

        Tienda tiendaMock = mock(Tienda.class);
        Ronda ronda = new Ronda(1,10000,3,3, tiendaMock);
        CartaPoker carta1 = CartaFactory.crearCarta(Valor.DOS, Palo.PICAS);
        CartaPoker carta2 = CartaFactory.crearCarta(Valor.DOS, Palo.DIAMANTES);
        CartaPoker carta3 = CartaFactory.crearCarta(Valor.DOS, Palo.DIAMANTES);
        CartaPoker carta4 = CartaFactory.crearCarta(Valor.DOS, Palo.CORAZONES);
        CartaPoker carta5 = CartaFactory.crearCarta(Valor.SEIS, Palo.PICAS);
        ArrayList<CartaPoker> cartas = new ArrayList<>(List.of(carta1, carta2, carta3, carta4, carta5));
        Mazo mazo = new Mazo(cartas);
        Mano mano = new Mano(mazo);
        Puntaje puntaje = new Puntaje(10, 1);
        Comodin unComodin = new EfectoJugada(Descarte.class, puntaje, "Descartar suma" ,"+10 si realiza un descarte", new NoAleatorio());
        Jugador jugador = new Jugador("Pepe", mazo) {
            @Override
            public void iniciarRonda(Ronda rondaActual){
                this.rondaActual = ronda;
                this.mazo = mazo;
                this.manoActual = mano;
            }
        };
        jugador.aniadirComodin(unComodin);
        jugador.iniciarRonda(ronda);
        int puntajeEsperado = 10;

        jugador.seleccionarCarta(new ArrayList<>(List.of(carta1, carta2, carta3, carta4, carta5)));
        //Act
        jugador.descartar();
        int puntajeObtenido = ronda.calcularTotalRonda();
        //Assert
        assertEquals(puntajeEsperado, puntajeObtenido);
    }

    @Test
    public void test04SeVerificaQueSeAplicaComodinAleatorioCorrectamente () {
        //Arrange
        Tienda tiendaMock = mock(Tienda.class);
        Ronda ronda = new Ronda(1,10000,3,3, tiendaMock);
        CartaPoker carta1 = CartaFactory.crearCarta(Valor.DOS, Palo.PICAS);
        CartaPoker carta2 = CartaFactory.crearCarta(Valor.DOS, Palo.DIAMANTES);
        CartaPoker carta3 = CartaFactory.crearCarta(Valor.DOS, Palo.DIAMANTES);
        CartaPoker carta4 = CartaFactory.crearCarta(Valor.DOS, Palo.CORAZONES);
        CartaPoker carta5 = CartaFactory.crearCarta(Valor.SEIS, Palo.PICAS);
        ArrayList<CartaPoker> cartas = new ArrayList<>(List.of(carta1, carta2, carta3, carta4, carta5));
        Mazo mazo = new Mazo(cartas);
        Mano mano = new Mano(mazo);
        Jugador jugador = new Jugador("Pepe", mazo) {
            @Override
            public void iniciarRonda(Ronda rondaActual){
                this.rondaActual = ronda;
                this.mazo = mazo;
                this.manoActual = mano;
            }
        };
        int puntajeEsperado = (2*4+60+10)*7;
        Puntaje puntaje = new Puntaje(10, 1);
        Comodin unComodin = new EfectoPuntaje(puntaje, "RayoMcqueen" ,"1 en 1000 de chances", new Aleatorio(1000)) {
            @Override
            public boolean seAplica() {
                return true;
            }
        };
        jugador.iniciarRonda(ronda);
        jugador.aniadirComodin(unComodin);
        jugador.seleccionarCarta(new ArrayList<>(List.of(carta1, carta2, carta3, carta4, carta5)));

        //Act
        jugador.jugar();
        int puntajeObtenido = ronda.calcularTotalRonda();
        //Assert
        assertEquals(puntajeEsperado, puntajeObtenido);
    }

    @Test
    public void test05SeVerificaQueSeAplicaComodinCombinadoCorrectamente() {
        //Arrange
        Tienda tiendaMock = mock(Tienda.class);
        Ronda ronda = new Ronda(1,10000,3,3, tiendaMock);
        CartaPoker carta1 = CartaFactory.crearCarta(Valor.DOS, Palo.PICAS);
        CartaPoker carta2 = CartaFactory.crearCarta(Valor.DOS, Palo.DIAMANTES);
        CartaPoker carta3 = CartaFactory.crearCarta(Valor.DOS, Palo.DIAMANTES);
        CartaPoker carta4 = CartaFactory.crearCarta(Valor.DOS, Palo.CORAZONES);
        CartaPoker carta5 = CartaFactory.crearCarta(Valor.SEIS, Palo.PICAS);
        ArrayList<CartaPoker> cartas = new ArrayList<>(List.of(carta1, carta2, carta3, carta4, carta5));
        Mazo mazo = new Mazo(cartas);
        Mano mano = new Mano(mazo);
        Puntaje puntaje1 = new Puntaje(10, 1);
        Comodin comodin1 = new EfectoPuntaje(puntaje1, "El dibu" ,"1 de cada 1000", new Aleatorio(1000)) {
            @Override
            public boolean seAplica() {
                return true;
            }
        };
        Puntaje puntaje2 = new Puntaje(0, 3);
        Comodin comodin2 = new EfectoPuntaje( puntaje2, "Triplete" ,"x3 a la jugada", new NoAleatorio());
        Comodin comodin3 = new EfectoJugada(Poker.class, puntaje2, "Pokerface" ,"x3 multiplica si juego Poker", new NoAleatorio());
        EfectoCombinado comodinCombinado = new EfectoCombinado("Combina3", "Tiene probabilidad 1 de cada 1000," +
                "x3 al multiplicador y x3 si se juegaPoker", new NoAleatorio());
        comodinCombinado.agregar(comodin1);
        comodinCombinado.agregar(comodin2);
        comodinCombinado.agregar(comodin3);
        Jugador jugador = new Jugador("Pepe", mazo) {
            @Override
            public void iniciarRonda(Ronda rondaActual){
                this.rondaActual = ronda;
                this.mazo = mazo;
                this.manoActual = mano;
            }
        };
        int puntajeEsperado = (2*4+60+10)*7*3*3;

        jugador.iniciarRonda(ronda);
        jugador.aniadirComodin(comodinCombinado);
        jugador.seleccionarCarta(new ArrayList<>(List.of(carta1, carta2, carta3, carta4, carta5)));

        //Act
        jugador.jugar();
        int puntajeObtenido = ronda.calcularTotalRonda();
        //Assert
        assertEquals(puntajeEsperado, puntajeObtenido);

    }
}
