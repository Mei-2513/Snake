package model;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class Barrier {
    private Point position; 
    private int gridWidth; 
    private int gridHeight; 

    public Barrier() {
        loadConfigurations("src/resources/barrier_config.txt"); 
        generateNewBarrier();
    }

    public Point getPosition() {
        
        return position;
    }

    public void generateNewBarrier() {
        Random random = new Random();
        int x = random.nextInt(gridWidth);
        int y = random.nextInt(gridHeight);
        position = new Point(x, y);
    }
    public boolean foodCollidesWithBarrier(int x, int y) {
        Point barrierPos = this.position;
        if (barrierPos != null) {
            int barrierX = (int) Math.round(barrierPos.getX());
            int barrierY = (int) Math.round(barrierPos.getY());
            return x == barrierX && y == barrierY;
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
                }
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}