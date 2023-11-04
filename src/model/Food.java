package model;


import java.awt.Point;
import java.util.Random;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Food {
    private Point position; 
    private int points; 
    private int gridWidth; 
    private int gridHeight; 

    public Food() {
        loadConfigurations("src/resources/food_config.txt"); 
        generateNewFood();
    }

    public Point getPosition() {
       
        return position;
    }

    public int getPoints() {
        
        return points;
    }

    public void generateNewFood() {
        Random random = new Random();
        int foodX, foodY;

        do {
            foodX = random.nextInt(gridWidth);
            foodY = random.nextInt(gridHeight);
        } while (foodCollidesWithSnake(foodX, foodY) || foodCollidesWithBarrier(foodX, foodY));

        position = new Point(foodX, foodY);
    }

    
    public boolean foodCollidesWithSnake(int x, int y) {
        Point foodPos = this.position; 
        if (foodPos != null) {
            int foodX = (int) Math.round(foodPos.getX());
            int foodY = (int) Math.round(foodPos.getY());
            return x == foodX && y == foodY;
        }
        return false; 
    }


    public boolean foodCollidesWithBarrier(int x, int y) {
        Point foodPos = this.position; 
        if (foodPos != null) {
            int foodX = (int) Math.round(foodPos.getX());
            int foodY = (int) Math.round(foodPos.getY());
            return x == foodX && y == foodY;
        }
        return false; 
    }




    
    

    private void loadConfigurations(String configFilePath) {
       
        try (BufferedReader reader = new BufferedReader(new FileReader(configFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("GRID_WIDTH")) {
                  
                    gridWidth = Integer.parseInt(line.split("=")[1].trim());
                } else if (line.startsWith("GRID_HEIGHT")) {
                  
                    gridHeight = Integer.parseInt(line.split("=")[1].trim());
                } else if (line.startsWith("FOOD_POINTS")) {
                    
                    points = Integer.parseInt(line.split("=")[1].trim());
                }
               
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}