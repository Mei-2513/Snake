package model;



import java.awt.Point;
import java.util.Random;

public class Food {
    private Point position; // Posición de la comida
    private int points; // Puntos que se obtienen al comer esta comida

    public Food() {
        // Generar la posición inicial de la comida
        generateNewFood();
        points = GameConfig.DEFAULT_FOOD_POINTS; // Asignar puntos por defecto
    }

    public Point getPosition() {
        // Obtener la posición de la comida
        return position;
    }

    public int getPoints() {
        // Obtener los puntos que se obtienen al comer esta comida
        return points;
    }

    public void generateNewFood() {
        // Generar una nueva posición para la comida
        Random random = new Random();
        int x = random.nextInt(GameConfig.GRID_WIDTH);
        int y = random.nextInt(GameConfig.GRID_HEIGHT);
        position = new Point(x, y);
    }
}

