package controller;




import model.GameLogic;
import view.GameFrame;
import view.GamePanel;

public class GameController {
    private GameFrame gameFrame;
    private GameLogic gameLogic;

    public GameController(GameFrame gameFrame, GameLogic gameLogic) {
        this.gameFrame = gameFrame;
        this.gameLogic = gameLogic;
    }

    public void startGame() {
        gameFrame.initUI();
        gameFrame.addKeyListener(new GameKeyListener(gameLogic));
        gameLogic.initializeGame();
        gameFrame.setGamePanel(new GamePanel(gameLogic));
        gameFrame.setVisible(true);

        // Inicia los hilos necesarios para el juego
        Thread scoreThread = new ScoreThread(gameLogic);
        Thread foodThread = new FoodThread(gameLogic);
        Thread obstacleThread = new ObstacleThread(gameLogic);
        Thread snakeThread = new SnakeThread(gameLogic);

        scoreThread.start();
        foodThread.start();
        obstacleThread.start();
        snakeThread.start();
    }

    public static void main(String[] args) {
        GameLogic gameLogic = new GameLogic();
        GameFrame gameFrame = new GameFrame();
        GameController gameController = new GameController(gameFrame, gameLogic);
        gameController.startGame();
    }
}
