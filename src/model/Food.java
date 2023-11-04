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
        int x = random.nextInt(gridWidth);
        int y = random.nextInt(gridHeight);
        position = new Point(x, y);
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
