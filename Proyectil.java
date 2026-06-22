public class Proyectil extends EntidadJuego {
    private boolean esEnemigo;

    public Proyectil(String nombre, int x, int y, boolean esEnemigo) {
        super(nombre, x, y, 1, 1, 1, "laser.png");
        this.esEnemigo = esEnemigo;
    }

    public void avanzar() {
        if (esEnemigo) {
            setY(getY() + 2); // Baja en la pantalla
        } else {
            setY(getY() - 2); // Sube en la pantalla
        }
    }

    public boolean isEsEnemigo() { return esEnemigo; }
}