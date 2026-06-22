public class Jugador extends EntidadVideojuego {

    private int puntuacion;

    public Jugador(String nombre, int x, int y) {
        super(nombre, x, y, 1, 1, 100, "jugador.png");
        this.puntuacion = 0;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void sumarPuntos(int puntos) {
        puntuacion += puntos;
    }
}