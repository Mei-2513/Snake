package model;



public class ScoreThread extends Thread {
    private SnakeModel model;

    public ScoreThread(SnakeModel model) {
        this.model = model;
    }

    public void run() {
        // Implementa cómo se actualiza el puntaje con cada alimento consumido.
        while (true) {
            // Actualiza la puntuación según la lógica del juego
            try {
                Thread.sleep(GameConfig.SCORE_UPDATE_DELAY); // Ajusta la velocidad de actualización
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

