public class GestorEntradas {

    public void moverJugador(Jugador jugador, String direccion) {

        switch (direccion) {

            case "ARRIBA":
                jugador.setY(jugador.getY() - 1);
                break;

            case "ABAJO":
                jugador.setY(jugador.getY() + 1);
                break;

            case "IZQUIERDA":
                jugador.setX(jugador.getX() - 1);
                break;

            case "DERECHA":
                jugador.setX(jugador.getX() + 1);
                break;

            default:
                System.out.println("Direccion no valida");
        }
    }

    public void pulsarBotonAccion() {
        System.out.println("ACCION ejecutada");
    }
}