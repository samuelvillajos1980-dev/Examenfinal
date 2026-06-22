classDiagram
    class EntidadVideojuego {
        -String nombre
        -int x
        -int y
        -int ancho
        -int alto
        -int vida
        -String imagen
        +getX() int
        +getY() int
        +setX(int x) void
        +setY(int y) void
    }

    class Jugador {
        -int puntuacion
        +sumarPuntos(int puntos) void
    }

    class MotorJuego {
        -String estado
        -List~EntidadVideojuego~ entidades
        +iniciarPartida() void
        +pausar() void
        +reanudar() void
        +actualizar() void
        +quickSave() String
    }

    class GestorEntradas {
        +moverJugador(String direccion) void
        +pulsarBotonAccion() void
    }

    class Main {
        +main(args: String[]) void
    }

    EntidadVideojuego <|-- Jugador
    MotorJuego "1" --> "*" EntidadVideojuego : Gestiona
    GestorEntradas ..> Jugador : Controla
    Main ..> MotorJuego : Ejecuta