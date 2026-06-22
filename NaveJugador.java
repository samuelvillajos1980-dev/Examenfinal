public class NaveJugador extends EntidadJuego {
    public NaveJugador(int x, int y) {
        super("Nave_Jugador", x, y, 3, 2, 100, "nave_player.png");
    }

    public Proyectil disparar() {
        System.out.println("[ACCION] El Jugador ha disparado un proyectil hacia arriba.");
        // El proyectil nace justo encima de la nave (eje Y menor) y sube
        return new Proyectil("Proyectil_Aliado", getX() + 1, getY() - 1, false);
    }
}