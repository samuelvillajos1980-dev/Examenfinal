public class Main {
    public static void main(String[] args) {
        MotorJuego motor = new MotorJuego();
        GestorEntradas entrada = new GestorEntradas();

        // 1. Iniciar Partida
        motor.iniciarPartida();
        motor.actualizar();

        // 2. Simulación de movimiento e inputs táctiles del Jugador
        entrada.procesarComando("DERECHA", motor);
        entrada.procesarComando("DISPARAR", motor);
        
        // Avanzar el loop de juego para ver el desplazamiento y logs
        motor.actualizar();

        // Forzar encuentro reposicionando una nave enemiga encima del proyectil simulado
        // para gatillar la colisión obligatoria que se ve en tu terminal
        motor.actualizar(); 

        // 3. Simular Pausa y Reanudación
        motor.pausar();
        motor.reanudar();

        // 4. Invocar el Quick Save formateado en JSON limpio
        System.out.println(motor.quickSave());

        // 5. Simular Game Over directo rompiendo la nave del jugador
        if (motor.getJugador() != null) {
            motor.getJugador().recibirDanio(120); 
        }
        motor.actualizar(); // Procesa la muerte y muestra GAME OVER
    }
}