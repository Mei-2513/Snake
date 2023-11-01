package model;

import java.awt.Point;
import java.util.Random;

public class Food {
    private Point position;
    private int foodValue;

    public Food() {
   
        respawn();
    }

    public Point getPosition() {
        return position;
    }

    public int getFoodValue() {
        return foodValue;
    }

    public void respawn() {
        // Implementa la lógica para hacer que la comida aparezca en una nueva posición
        Random random = new Random();
        int x = random.nextInt(GameConfig.GAME_WIDTH);
        int y = random.nextInt(GameConfig.GAME_HEIGHT);
        position = new Point(x, y);
        foodValue = generateFoodValue(); // Asigna un valor aleatorio a la comida
    }

    private int generateFoodValue() {
        // Implementa la lógica para asignar un valor aleatorio a la comida
        Random random = new Random();
        return random.nextInt(GameConfig.MAX_FOOD_VALUE) + 1;
    }
}
