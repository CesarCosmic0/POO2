package SnakeGUI;

import java.io.Serializable;

public class Juego implements Serializable {

    private String jugador1;
    private String jugador2;
    private int puntajeJugador1;
    private int puntajeJugador2;

    public Juego(String jugador1, String jugador2, int puntajeJugador1, int puntajeJugador2) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.puntajeJugador1 = puntajeJugador1;
        this.puntajeJugador2 = puntajeJugador2;
    }

    public String getJugador1() {
        return jugador1;
    }

    public String getJugador2() {
        return jugador2;
    }

    public int getPuntajeJugador1() {
        return puntajeJugador1;
    }

    public int getPuntajeJugador2() {
        return puntajeJugador2;
    }
}
