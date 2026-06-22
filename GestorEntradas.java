public class GestorEntradas {
    public void procesarComando(String comando, MotorJuego motor) {
        if (!motor.getEstado().equals("JUGANDO")) {
            System.out.println("[INPUT] Comando rechazado de forma segura: El juego no esta en modo JUGANDO.");
            return;
        }

        NaveJugador jugador = motor.getJugador();
        if (jugador == null) return;

        switch (comando.toUpperCase()) {
            case "IZQUIERDA":
                if (jugador.getX() > 0) {
                    jugador.setX(jugador.getX() - 2);
                    System.out.println("[INPUT] Mover izquierda ejecutado. Posicion X=" + jugador.getX());
                }
                break;
            case "DERECHA":
                if (jugador.getX() < 25) {
                    jugador.setX(jugador.getX() + 2);
                    System.out.println("[INPUT] Mover derecha ejecutado. Posicion X=" + jugador.getX());
                }
                break;
            case "DISPARAR":
                Proyectil p = jugador.disparar();
                motor.agregarEntidad(p);
                break;
            default:
                System.out.println("[INPUT] Comando simulado desconocido.");
                break;
        }
    }
}