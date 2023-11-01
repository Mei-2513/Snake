package model;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class GameLogic {
    private Snake snake;
    private Food food;
    private Obstacle obstacle;
    private boolean isGameOver;
    private int level;
    private int speed;
    private int score;

    public GameLogic() {
        snake = new Snake();
        food = new Food();
        obstacle = new Obstacle();
        isGameOver = false;
        level = 1;
        speed = GameConfig.BASE_SPEED;
        score = 0;
    }

    public Snake getSnake() {
        return snake;
    }

    public Food getFood() {
        return food;
    }

    public Obstacle getObstacle() {
        return obstacle;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public int getLevel() {
        return level;
    }

    public int getSpeed() {
        return speed;
    }

    public int getScore() {
        return score;
    }

    public void update() {
        if (!isGameOver) {
            snake.move();

            if (snake.collidesWithFood(food.getPosition())) {
                food.respawn();
                snake.grow();
                score += food.getFoodValue();
            }

            if (snake.collidesWithObstacle(obstacle.getPosition()) || snake.selfCollision()) {
                isGameOver = true;
            }

            // Lógica para cambiar de nivel
            if (score >= GameConfig.SCORES_FOR_LEVEL_UP * level) {
                levelUp();
            }
        }
    }

    private void levelUp() {
        level++;
        speed += GameConfig.SPEED_INCREMENT;
    }
}
