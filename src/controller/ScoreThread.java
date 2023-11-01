package controller;

import model.GameLogic;

public class ScoreThread extends Thread {
    private final GameLogic gameLogic;

    public ScoreThread(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
    }

    @Override
    public void run() {
        while (!gameLogic.isGameOver()) {
            // Actualiza el puntaje en intervalos regulares
            gameLogic.updateScore();

            // Pausa para la siguiente actualización del puntaje
            try {
                Thread.sleep(GameConfig.SCORE_UPDATE_INTERVAL);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}