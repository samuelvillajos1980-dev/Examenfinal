public class NaveEnemiga extends EntidadJuego {
    private int velocidadLateral;

    public NaveEnemiga(String nombre, int x, int y) {
        super(nombre, x, y, 2, 2, 20, "invader.png");
        this.velocidadLateral = 1; 
    }

    public void actualizarPatron() {
        // Simulación de movimiento de vaivén lateral automático en el Game Loop
        setX(getX() + velocidadLateral);
        if (getX() > 20 || getX() < 1) {
            velocidadLateral = -velocidadLateral; // Rebota lateralmente
            setY(getY() + 1); // Desciende un bloque al rebotar (Invasión)
            System.out.println("[LOG] " + getNombre() + " cambio de direccion y desciende a Y=" + getY());
        }
    }
}