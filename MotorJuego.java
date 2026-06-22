import java.util.ArrayList;
import java.util.List;

public class MotorJuego {
    private String estado;
    private List<EntidadJuego> entidades;
    private NaveJugador jugador;
    private SistemaPuntuacion sistemaPuntuacion;

    public MotorJuego() {
        this.estado = "MENU";
        this.entidades = new ArrayList<>();
        this.sistemaPuntuacion = new SistemaPuntuacion();
    }

    public void iniciarPartida() {
        this.estado = "JUGANDO";
        System.out.println("Partida Iniciada");
        
        // Instanciar y añadir entidades básicas iniciales
        this.jugador = new NaveJugador(10, 15);
        this.entidades.add(jugador);
        
        this.entidades.add(new NaveEnemiga("Invasor_Alfa", 5, 2));
        this.entidades.add(new NaveEnemiga("Invasor_Beta", 12, 2));
        this.entidades.add(new Defensa(4, 12));
        this.entidades.add(new Defensa(16, 12));
    }

    public void pausar() {
        if (this.estado.equals("JUGANDO")) {
            this.estado = "PAUSA";
            System.out.println("Juego pausado");
        }
    }

    public void reanudar() {
        if (this.estado.equals("PAUSA")) {
            this.estado = "JUGANDO";
            System.out.println("Juego reanudado");
        }
    }

    public void actualizar() {
        if (!this.estado.equals("JUGANDO")) return;

        System.out.println("--- ACTUALIZANDO JUEGO ---");
        List<EntidadJuego> aEliminar = new ArrayList<>();
        List<Proyectil> proyectilesA_Avanzar = new ArrayList<>();

        // 1. Recorrer y procesar la IA/patrones de las entidades
        for (EntidadJuego entidad : entidades) {
            if (entidad instanceof NaveEnemiga) {
                ((NaveEnemiga) entidad).actualizarPatron();
            } else if (entidad instanceof Proyectil) {
                proyectilesA_Avanzar.add((Proyectil) entidad);
            }

            // Marcar muertas si su vida es 0
            if (entidad.getVida() <= 0) {
                aEliminar.add(entidad);
            }
        }

        // Avanzar proyectiles
        for (Proyectil p : proyectilesA_Avanzar) {
            p.avanzar();
            // Eliminar si sale del mapa vertical
            if (p.getY() < 0 || p.getY() > 20) {
                aEliminar.add(p);
            }
        }

        // Limpiar entidades muertas
        for (EntidadJuego muerta : aEliminar) {
            entidades.remove(muerta);
            if (muerta instanceof NaveEnemiga) {
                sistemaPuntuacion.sumarPuntos(100);
            } else if (muerta instanceof NaveJugador) {
                this.estado = "GAME_OVER";
                System.out.println("GAME OVER");
            }
        }

        // 2. Ejecutar Detección Avanzada de Colisiones
        verificarColisionesAABB();
    }

    public void verificarColisionesAABB() {
        // Doble iteración clásica para comparar intersecciones de cajas
        for (int i = 0; i < entidades.size(); i++) {
            for (int j = i + 1; j < entidades.size(); j++) {
                EntidadJuego e1 = entidades.get(i);
                EntidadJuego e2 = entidades.get(j);

                // Algoritmo matemático AABB
                if (e1.getX() < e2.getX() + e2.getAncho() &&
                    e1.getX() + e1.getAncho() > e2.getX() &&
                    e1.getY() < e2.getY() + e2.getAlto() &&
                    e1.getY() + e1.getAlto() > e2.getY()) {
                    
                    // Colisión detectada: Desencadenar lógica según las reglas de negocio
                    procesarInterseccion(e1, e2);
                }
            }
        }
    }

    private void procesarInterseccion(EntidadJuego e1, EntidadJuego e2) {
        // Regla: Proyectil Aliado choca con Nave Enemiga
        if (e1 instanceof Proyectil && !((Proyectil)e1).isEsEnemigo() && e2 instanceof NaveEnemiga) {
            System.out.println("COLISION con Enemigo");
            e2.recibirDanio(20);
            e1.setVida(0); // Destruir proyectil
        } else if (e2 instanceof Proyectil && !((Proyectil)e2).isEsEnemigo() && e1 instanceof NaveEnemiga) {
            System.out.println("COLISION con Enemigo");
            e1.recibirDanio(20);
            e2.setVida(0);
        }
    }

    public String quickSave() {
        System.out.println("QUICK SAVE:");
        StringBuilder json = new StringBuilder();
        json.append("{\n");
        json.append("  \"estado\": \"").append(this.estado).append("\",\n");
        if (jugador != null) {
            json.append("  \"Jugador\": \"").append(jugador.getX()).append(",")
                .append(jugador.getY()).append(",").append(jugador.getVida()).append("\",\n");
        }
        json.append("  \"Puntuacion\": ").append(sistemaPuntuacion.getScore()).append("\n");
        json.append("}");
        return json.toString();
    }

    public void agregarEntidad(EntidadJuego e) { this.entidades.add(e); }
    public String getEstado() { return estado; }
    public NaveJugador getJugador() { return jugador; }
}