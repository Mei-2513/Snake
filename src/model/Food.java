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
    private int foodDuration;
    private long foodLastEatenTime;
    private boolean foodGenerated;

    public Food() {
        loadFoodConfigurations("src/resources/food_config.txt");
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
        } while (foodCollidesWithSnake(foodX, foodY));

        position = new Point(foodX, foodY);
        setFoodLastEatenTime(System.currentTimeMillis()); // Reinicia el tiempo de comida
        setFoodGenerated(true);
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


    public void loadFoodConfigurations(String configFilePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(configFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("GRID_WIDTH")) {
                    gridWidth = Integer.parseInt(line.split("=")[1].trim());
                } else if (line.startsWith("GRID_HEIGHT")) {
                    gridHeight = Integer.parseInt(line.split("=")[1].trim());
                } else if (line.startsWith("FOOD_POINTS")) {
                    points = Integer.parseInt(line.split("=")[1].trim());
                } else if (line.startsWith("FOOD_DURATION")) {
                    foodDuration = Integer.parseInt(line.split("=")[1].trim()) * 1000; // Duración en segundos
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void checkFoodDuration() {
        long currentTime = System.currentTimeMillis();
        if (foodLastEatenTime > 0 && currentTime - foodLastEatenTime >= foodDuration * 1000) {
            generateNewFood();
        }
    }


	public int getFoodDuration() {
		return foodDuration;
	}

	public void setFoodDuration(int foodDuration) {
		this.foodDuration = foodDuration;
	}

	public long getFoodLastEatenTime() {
		return foodLastEatenTime;
	}

	public void setFoodLastEatenTime(long foodLastEatenTime) {
		this.foodLastEatenTime = foodLastEatenTime;
	}

	public boolean isFoodGenerated() {
		return foodGenerated;
	}

	public void setFoodGenerated(boolean foodGenerated) {
		this.foodGenerated = foodGenerated;
	}
}