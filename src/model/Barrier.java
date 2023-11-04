package model;



import java.awt.Point;
import java.util.Random;

public class Barrier {
    private Point position; // Posición de la barrera

    public Barrier() {
        // Generar la posición inicial de la barrera
        generateNewBarrier();
    }

    public Point getPosition() {
        // Obtener la posición de la barrera
        return position;
    }

    public void generateNewBarrier() {
        // Generar una nueva posición para la barrera
        Random random = new Random();
        int x = random.nextInt(GameConfig.GRID_WIDTH);
        int y = random.nextInt(GameConfig.GRID_HEIGHT);
        position = new Point(x, y);
    }
}
