public class EntidadVideojuego {

    private String nombre;
    private int x;
    private int y;
    private int ancho;
    private int alto;
    private int vida;
    private String imagen;

    public EntidadVideojuego(String nombre, int x, int y, int ancho, int alto, int vida, String imagen) {
        this.nombre = nombre;
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.vida = vida;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }

    @Override
    public String toString() {
        return nombre + " (" + x + "," + y + ") Vida=" + vida;
    }
}