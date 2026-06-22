import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MotorJuego {

    private static final String MENU = "MENU";
    private static final String JUGANDO = "JUGANDO";
    private static final String PAUSA = "PAUSA";
    private static final String GAME_OVER = "GAME_OVER";

    private String estado;
    private List<EntidadVideojuego> entidades;

    public MotorJuego() {
        estado = MENU;
        entidades = new ArrayList<>();
    }

    public void iniciarPartida() {
        estado = JUGANDO;
        System.out.println("Partida iniciada");
    }

    public void pausar() {
        estado = PAUSA;
        System.out.println("Juego pausado");
    }

    public void reanudar() {
        estado = JUGANDO;
        System.out.println("Juego reanudado");
    }

    public void gameOver() {
        estado = GAME_OVER;
        System.out.println("GAME OVER");
    }

    public void agregarEntidad(EntidadVideojuego entidad) {
        entidades.add(entidad);
    }

    public void eliminarEntidad(EntidadVideojuego entidad) {
        entidades.remove(entidad);
    }

    public void actualizar() {

        if (!estado.equals(JUGANDO)) {
            return;
        }

        System.out.println("=== ACTUALIZANDO JUEGO ===");

        for (EntidadVideojuego entidad : entidades) {

            if (!entidad.getNombre().equals("Jugador")) {

                entidad.setX(entidad.getX() - 1);

                System.out.println(entidad);
            }
        }

        detectarColisiones();
    }

    private void detectarColisiones() {

        Jugador jugador = null;

        for (EntidadVideojuego e : entidades) {
            if (e instanceof Jugador) {
                jugador = (Jugador) e;
            }
        }

        if (jugador == null) {
            return;
        }

        Iterator<EntidadVideojuego> it = entidades.iterator();

        while (it.hasNext()) {

            EntidadVideojuego entidad = it.next();

            if (entidad == jugador) {
                continue;
            }

            boolean colision =
                    jugador.getX() < entidad.getX() + entidad.getAncho() &&
                    jugador.getX() + jugador.getAncho() > entidad.getX() &&
                    jugador.getY() < entidad.getY() + entidad.getAlto() &&
                    jugador.getY() + jugador.getAlto() > entidad.getY();

            if (colision) {

                System.out.println("COLISION con " + entidad.getNombre());

                jugador.setVida(jugador.getVida() - 10);

                System.out.println("Vida jugador: " + jugador.getVida());

                it.remove();

                if (jugador.getVida() <= 0) {
                    gameOver();
                }
            }
        }
    }

    public String quickSave() {

        StringBuilder sb = new StringBuilder();

        sb.append("{\n");
        sb.append("\"estado\":\"").append(estado).append("\",\n");

        for (EntidadVideojuego e : entidades) {

            sb.append("\"")
                    .append(e.getNombre())
                    .append("\":\"")
                    .append(e.getX())
                    .append(",")
                    .append(e.getY())
                    .append(",")
                    .append(e.getVida())
                    .append("\"\n");
        }

        sb.append("}");

        return sb.toString();
    }
}