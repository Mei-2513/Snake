package model;



import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import view.SnakeView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;



public class SnakeModel {
    private List<Point> snake;
    private int direction;
    private int score;
    private int movementDelay;
    private int gridWidth;
    private int gridHeight;
    private Food food;
    private List<Point> barriers;
    private Barrier barrier;
    private SnakeView view;
    private static final int INITIAL_SNAKE_X = 5; 
    private static final int INITIAL_SNAKE_Y = 5;
    private int snakeSize;
    private Point tail; 
    public static final int B_WIDTH = 800;
    public static final int B_HEIGHT = 600;


    public static final int UP = 0;
    public static final int RIGHT = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 3;

    public SnakeModel(SnakeView view) {
        this.view = view;
        loadConfigurations("src/resources/config.txt");
        snake = new ArrayList<>();
        snake.add(new Point(5, 5));
        barriers = new ArrayList<>();
        barrier = new Barrier();
        direction = RIGHT;
        score = 0;
        food = new Food();
        
    }
    
    
    public void setFood(Food food) {
        this.food = food;
    }
    public Barrier getBarrier() {
        return barrier;
    }
    
    public void setBarrier(Barrier barrier) {
        this.barrier = barrier;
    }

    

    public Food getFood() {
        return food;
    
}

    public List<Point> getSnake() {
        return snake;
    }
    public List<Point> getBarriers() {
        return barriers;
    }

    public int getDirection() {
        return direction;
    }

    public int getScore() {
        return score;
    }
    public void move() {
        checkFoodCollision();
        Point head = snake.get(0);
        Point newHead = new Point(head);
        if (direction == UP) {
            newHead.y = (newHead.y - 1 + B_HEIGHT) % B_HEIGHT;
        } else if (direction == RIGHT) {
            newHead.x = (newHead.x + 1) % B_WIDTH;
        } else if (direction == DOWN) {
            newHead.y = (newHead.y + 1) % B_HEIGHT;
        } else if (direction == LEFT) {
            newHead.x = (newHead.x - 1 + B_WIDTH) % B_WIDTH;
        }

        snake.add(0, newHead);
        if (!food.getPosition().equals(newHead)) {
            snake.remove(snake.size() - 1);
        }
    }

    public void changeDirection(int newDirection) {
    
        if (Math.abs(newDirection - direction) != 2) {
            direction = newDirection;
        }
    }

    public boolean checkCollision() {
      
        Point head = snake.get(0);
        if (head.x < 0 || head.x >= gridWidth || head.y < 0 || head.y >= gridHeight) {
            return true; 
        }
        for (int i = 1; i < snake.size(); i++) {
            if (head.equals(snake.get(i))) {
                return true; 
            }
        }
        return false;
    }

    public void increaseScore(int points) {
        score += points;
        if (view != null) {
            view.updateScore(score); 
        }
    }


    private void loadConfigurations(String configFilePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(configFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("SPEED")) {
                    movementDelay = Integer.parseInt(line.split("=")[1].trim());
                } else if (line.startsWith("GRID_WIDTH")) {
                    gridWidth = Integer.parseInt(line.split("=")[1].trim());
                } else if (line.startsWith("GRID_HEIGHT")) {
                    gridHeight = Integer.parseInt(line.split("=")[1].trim());
                }
             
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void checkFoodCollision() {
        Point head = snake.get(0);
        Food food = this.getFood();

        if (head.equals(food.getPosition())) {
            increaseScore(food.getPoints());
            growSnake(); 
            food.generateNewFood();
        }
    }



   



    public void growSnake() {
        Point tail = snake.get(snake.size() - 1); 
        Point newSegment = new Point(tail.x + 1, tail.y); 
        snake.add(newSegment); 
    }





    

    public boolean checkBarrierCollision(int x, int y) {
        for (Point barrier : barriers) {
            if (barrier.x == x && barrier.y == y) {
                return true;
            }
        }
        return false;
    }
    public void resetGame() {
       
        snake.clear();  
        snake.add(new Point(INITIAL_SNAKE_X, INITIAL_SNAKE_Y));  

        
        food.generateNewFood();  

        
        score = 0;  

       
    }

}