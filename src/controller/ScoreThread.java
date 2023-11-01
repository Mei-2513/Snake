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
           
            gameLogic.updateScore();

           
            try {
                Thread.sleep(GameConfig.SCORE_UPDATE_INTERVAL);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}