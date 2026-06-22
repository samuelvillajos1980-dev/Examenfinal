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
    > ⚠️ **Guarda el archivo** en VS Code presionando `Ctrl + S` antes de pasar al siguiente paso.

---

### Paso 2: Ejecutar la actualización en tu PowerShell
Ahora abre de nuevo tu terminal (PowerShell) para limpiar los `.class` (así evitas penalizaciones) y empujar los cambios definitivos a GitHub de forma ordenada:

```powershell
# 1. Borramos los archivos .class compilados que se subieron por error
git rm *.class

# 2. Guardamos el README arreglado
git add README.md

# 3. Hacemos el commit con formato profesional
git commit -m "docs: fix mermaid diagram syntax formatting and remove binary class files"

# 4. Lo subimos de manera limpia a main
git push origin main --force

# 5. Pasamos los cambios exactos a develop para que ambas ramas queden idénticas
git checkout develop
git merge main
git push origin develop --force

# 6. Regresamos a main por comodidad
git checkout main