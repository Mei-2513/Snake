package model;



import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
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

    public static final int UP = 0;
    public static final int RIGHT = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 3;

    public SnakeModel() {
        loadConfigurations("src/resources/config.txt"); 
       
        snake = new ArrayList<>();
        snake.add(new Point(5, 5)); 
        direction = RIGHT;
        score = 0;
        food = new Food();
        
    }
    

    public Food getFood() {
        return food;
    
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
        Point foodPos = food.getPosition();

     
        int headX = (int) Math.round(head.getX());
        int headY = (int) Math.round(head.getY());
        int foodX = (int) Math.round(foodPos.getX());
        int foodY = (int) Math.round(foodPos.getY());

       
        if (headX == foodX && headY == foodY) {
          
            increaseScore(food.getPoints()); 
            growSnake(); 
            food.generateNewFood(); 
        }
    }






    private void growSnake() {
       
        Point tail = snake.get(snake.size() - 1);
        snake.add(new Point(tail));
    }
}
