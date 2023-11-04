package model;



import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class SnakeModel {
    private List<Point> snake; // Lista de puntos que representan el cuerpo de la serpiente
    private int direction; // Dirección actual de la serpiente (0: arriba, 1: derecha, 2: abajo, 3: izquierda)
    private int score; // Puntuación del jugador

    public static final int UP = 0;
    public static final int RIGHT = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 3;

    public SnakeModel() {
        // Inicializar el juego, crear la serpiente, establecer dirección inicial, etc.
        snake = new ArrayList<>();
        snake.add(new Point(5, 5)); // Agregar el punto inicial de la serpiente
        direction = RIGHT;
        score = 0;
    }

    public List<Point> getSnake() {
        return snake;
    }

    public int getDirection() {
        return direction;
    }

    public int getScore() {
        return score;
    }

    public void move() {
        // Mover la serpiente en la dirección actual
        Point head = snake.get(0);
        Point newHead = new Point(head);
        if (direction == UP) {
            newHead.y--;
        } else if (direction == RIGHT) {
            newHead.x++;
        } else if (direction == DOWN) {
            newHead.y++;
        } else if (direction == LEFT) {
            newHead.x--;
        }
        snake.add(0, newHead);
        snake.remove(snake.size() - 1);
    }

    public void changeDirection(int newDirection) {
        // Cambiar la dirección de la serpiente
        if (Math.abs(newDirection - direction) != 2) {
            direction = newDirection;
        }
    }

    public boolean checkCollision() {
        // Verificar colisiones, como con la pared o con el propio cuerpo
        Point head = snake.get(0);
        if (head.x < 0 || head.x >= GameConfig.GRID_WIDTH || head.y < 0 || head.y >= GameConfig.GRID_HEIGHT) {
            return true; // Colisión con la pared
        }
        for (int i = 1; i < snake.size(); i++) {
            if (head.equals(snake.get(i))) {
                return true; // Colisión con el propio cuerpo
            }
        }
        return false;
    }

    public void increaseScore(int points) {
        // Incrementar la puntuación del jugador
        score += points;
    }
}
