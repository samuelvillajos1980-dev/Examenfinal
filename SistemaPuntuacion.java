public class SistemaPuntuacion {
    private int score;

    public SistemaPuntuacion() {
        this.score = 0;
    }

    public void sumarPuntos(int puntos) {
        this.score += puntos;
        System.out.println("[SCORE] ¡Puntuacion actualizada! Total: " + this.score + " pts.");
    }

    public int getScore() { return score; }
}