package model;

import java.awt.Point;
import java.util.Random;

public class Obstacle {
    private Point position;

    public Obstacle() {
        // Inicialización de los obstáculos
        respawn();
    }

    public Point getPosition() {
        return position;
    }

    public void respawn() {
        // Implementa la lógica para hacer que los obstáculos aparezcan en una nueva posición
        Random random = new Random();
        int x = random.nextInt(GameConfig.GAME_WIDTH);
        int y = random.nextInt(GameConfig.GAME_HEIGHT);
        position = new Point(x, y);
    }
}
