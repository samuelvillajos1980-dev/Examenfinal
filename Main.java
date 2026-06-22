public class Main {

    public static void main(String[] args) {

        MotorJuego motor = new MotorJuego();

        Jugador jugador = new Jugador("Jugador", 5, 5);

        EntidadVideojuego enemigo =
                new EntidadVideojuego(
                        "Enemigo",
                        7,
                        5,
                        1,
                        1,
                        50,
                        "enemigo.png"
                );

        motor.agregarEntidad(jugador);
        motor.agregarEntidad(enemigo);

        GestorEntradas gestor = new GestorEntradas();

        motor.iniciarPartida();

        gestor.moverJugador(jugador, "DERECHA");

        motor.actualizar();

        gestor.pulsarBotonAccion();

        motor.pausar();

        motor.reanudar();

        System.out.println("\nQUICK SAVE:");
        System.out.println(motor.quickSave());

        motor.gameOver();
    }
}