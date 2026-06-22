# 🎮 Motor de Videojuego: Space Invaders Simplificado (Examen Final)

## 📝 Temática Elegida
El proyecto consiste en una simulación textual en consola basada en el clásico videojuego **Space Invaders**. El jugador controla una nave que se desplaza de forma lateral y dispara proyectiles para destruir oleadas de naves enemigas invasoras, utilizando estructuras de defensa estáticas para resguardarse de los ataques. El motor gestiona el ciclo de juego (*Game Loop*), los estados de la partida, el cálculo matemático de colisiones y un sistema de volcado de datos (*Quick Save*) en formato JSON.

---

## 🏗️ Arquitectura del Software
El diseño se compone de **9 clases bien definidas**, aplicando herencia, modularidad y encapsulación según los principios de la POO:

* **`Main`**: Clase conductora del programa. Ejecuta de forma secuencial la simulación del ciclo de juego e inyecta los comandos del usuario.
* **`MotorJuego`**: El cerebro del motor. Administra la máquina de estados (`MENU`, `JUGANDO`, `PAUSA`, `GAME_OVER`), la colección de entidades (jugador, enemigos, defensas, proyectiles) y la lógica global.
* **`EntidadJuego`**: Clase abstracta base que unifica los atributos comunes espaciales (`x`, `y`, `ancho`, `alto`), el estado de salud/resistencia (`vida`), el nombre e identificadores de textura.
* **`NaveJugador`**: Hereda de `EntidadJuego`. Añade lógica de movimiento restringido y control del disparo del usuario.
* **`NaveEnemiga`**: Hereda de `EntidadJuego`. Modifica su posición de forma automatizada simulando un patrón de invasión lateral.
* **`Defensa`**: Hereda de `EntidadJuego`. Estructura estática vulnerable encargada de absorber impactos para proteger al jugador.
* **`Proyectil`**: Hereda de `EntidadJuego`. Entidad dinámica con dirección vertical orientada según su origen (jugador o enemigo).
* **`GestorEntradas`**: Encargado de emular los inputs periféricos de teclado/ratón (`desplazarEntidad`, `pulsarBotonAccion`).
* **`SistemaPuntuacion`**: Módulo aislado dedicado al cómputo de estadísticas de destrucción y puntuación acumulada.

---

## 📊 Diagramas UML (Mermaid)

### 1. Diagrama de Clases
```mermaid
classDiagram
    class EntidadJuego {
        <<abstract>>
        -String nombre
        -int x
        -int y
        -int ancho
        -int alto
        -int vida
        +getX() int
        +getY() int
        +setX(int x) void
        +setY(int y) void
        +recibirDanio(int cantidad) void
    }

    class NaveJugador {
        +disparar() Proyectil
    }

    class NaveEnemiga {
        -int velocidadLateral
        +actualizarPatron() void
    }

    class Defensa {
        -int escudoMaximo
    }

    class Proyectil {
        -boolean esEnemigo
        +avanzar() void
    }

    class MotorJuego {
        -String estado
        -List~EntidadJuego~ entidades
        +iniciarPartida() void
        +pausar() void
        +reanudar() void
        +actualizar() void
        +verificarColisionesAABB() void
        +quickSave() String
    }

    class GestorEntradas {
        +procesarComando(String cmd, MotorJuego motor) void
    }

    class SistemaPuntuacion {
        -int score
        +sumarPuntos(int puntos) void
        +getScore() int
    }

    class Main {
        +main(args: String[]) void
    }

    EntidadJuego <|-- NaveJugador
    EntidadJuego <|-- NaveEnemiga
    EntidadJuego <|-- Defensa
    EntidadJuego <|-- Proyectil

    MotorJuego "1" --> "*" EntidadJuego : Administra
    MotorJuego "1" --> "1" SistemaPuntuacion : Registra
    Main ..> MotorJuego : Instancia
    GestorEntradas ..> MotorJuego : Modifica