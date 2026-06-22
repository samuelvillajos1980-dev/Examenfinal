# 🎮 Motor de Videojuego en Consola (Examen Final)

## 📝 Temática Elegida
El proyecto consiste en un **Motor de Videojuego 2D de Acción/Supervivencia** simulado por consola. El jugador controla a una entidad que se desplaza por un espacio virtual e interactúa con enemigos. El motor simula en tiempo real un ciclo de juego (*Game Loop*), gestionando cambios de estado (pausa, reanudación, colisiones, pérdida de vida), eventos del ciclo de vida y un sistema de guardado rápido en formato JSON estructurado.

---

## 🏗️ Arquitectura del Software
El sistema está diseñado bajo el paradigma de Programación Orientada a Objetos (POO), estructurado en las siguientes clases:

* **`EntidadVideojuego`**: Clase base que encapsula las propiedades espaciales comunes de todos los elementos del juego (`x, y, ancho, alto`), su estado vital (`vida`), nombre y la ruta de su `imagen` para la futura interfaz.
* **`Jugador`**: Clase hija que hereda de `EntidadVideojuego`. Añade atributos específicos como la `puntuacion` y lógica propia para `sumarPuntos()`.
* **`MotorJuego`**: El núcleo del motor. Controla el estado general de la partida (`estado`) y gestiona una lista dinámica de entidades, coordinando el ciclo de actualización (`actualizar()`), colisiones y el volcado de datos (`quickSave()`).
* **`GestorEntradas`**: Clase encargada de simular las interacciones físicas del usuario en el dispositivo, traduciendo comandos a métodos como `moverJugador()` o `pulsarBotonAccion()`.
* **`Main`**: Punto de entrada de la aplicación en Java. Coordina la ejecución secuencial de la prueba, instanciando el motor y simulando el flujo de juego visible en la consola.

---

## 📊 Diagramas UML (Mermaid)

### 1. Diagrama de Clases
A continuación se detalla la estructura estática del sistema, sus métodos públicos, atributos privados y relaciones de asociación y herencia:

```mermaid
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