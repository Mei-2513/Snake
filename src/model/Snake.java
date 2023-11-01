package model;

import java.awt.Point;
import java.util.LinkedList;

public class Snake {
    private LinkedList<Point> body;
    private Direction direction;
    private int score;

    public Snake() {
        body = new LinkedList<>();
        // Inicialización de la serpiente
        // Por ejemplo, puedes añadir la cabeza de la serpiente en una posición inicial
        body.add(new Point(5, 5)); // Ejemplo de posición inicial
        direction = Direction.RIGHT; // Dirección inicial
        score = 0; // Inicializa el marcador
    }

    public void move() {
        // Implementa el movimiento de la serpiente
        Point newHead = calculateNewHeadPosition();
        if (!collidesWithWall(newHead) && !collidesWithSelf(newHead)) {
            body.addFirst(newHead); // Añade la nueva posición como cabeza
            if (collidesWithFood(newHead)) {
                // La serpiente ha colisionado con la comida, crece y aumenta la puntuación
                grow();
            } else {
                // La serpiente no ha comido, por lo que elimina la cola
                body.removeLast();
            }
        }
    }

    public boolean collidesWithFood(Point foodPosition) {
        // Implementa la lógica de colisión con la comida
        return getHeadPosition().equals(foodPosition);
    }

    public boolean collidesWithObstacle(Point obstaclePosition) {
        // Implementa la lógica de colisión con los obstáculos
        return getHeadPosition().equals(obstaclePosition);
    }

    public boolean collidesWithSelf(Point newHead) {
        // Implementa la lógica de colisión con su propia cola
        for (Point part : body) {
            if (part.equals(newHead)) {
                return true;
            }
        }
        return false;
    }

    public void grow() {
        // Implementa el crecimiento de la serpiente
        Point newHead = calculateNewHeadPosition();
        body.addFirst(newHead);
        score++;
    }

    public int getScore() {
        return score;
    }

    public void setDirection(Direction newDirection) {
        // Cambia la dirección de la serpiente
        if (isDirectionChangeValid(newDirection)) {
            direction = newDirection;
        }
    }

    public Point getHeadPosition() {
        // Devuelve la posición de la cabeza de la serpiente
        return body.getFirst();
    }

    private Point calculateNewHeadPosition() {
        // Calcula la nueva posición de la cabeza de la serpiente según la dirección
        Point head = getHeadPosition();
        int x = head.x;
        int y = head.y;

        if (direction == Direction.UP) {
            y--;
        } else if (direction == Direction.DOWN) {
            y++;
        } else if (direction == Direction.LEFT) {
            x--;
        } else if (direction == Direction.RIGHT) {
            x++;
        }

        return new Point(x, y);
    }

    private boolean collidesWithWall(Point newHead) {
        // Implementa la lógica de colisión con las paredes
        // Asegúrate de que newHead esté dentro de los límites del juego
        // y dependiendo de tu implementación, verifica las colisiones con las paredes
        return false;
    }

    private boolean isDirectionChangeValid(Direction newDirection) {
        // Implementa la lógica para validar cambios de dirección
        // Por ejemplo, evita que la serpiente gire en sentido opuesto
        return !(direction == Direction.UP && newDirection == Direction.DOWN
                || direction == Direction.DOWN && newDirection == Direction.UP
                || direction == Direction.LEFT && newDirection == Direction.RIGHT
                || direction == Direction.RIGHT && newDirection == Direction.LEFT);
    }
}
