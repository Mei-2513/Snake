package model;

import java.awt.Point;
import java.util.Random;

public class Obstacle {
    private Point position;

    public Obstacle() {
      
        respawn();
    }

    public Point getPosition() {
        return position;
    }

    public void respawn() {
      
        Random random = new Random();
        int x = random.nextInt(GameConfig.GAME_WIDTH);
        int y = random.nextInt(GameConfig.GAME_HEIGHT);
        position = new Point(x, y);
    }
}
