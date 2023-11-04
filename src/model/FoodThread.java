package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FoodThread extends Thread {
    private Food food;
    private int foodGenerationDelay; 

    public FoodThread(Food food) {
        this.food = food;
        loadConfigurations(); 
    }

    private void loadConfigurations() {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/resources/food_config.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("FOOD_GENERATION_DELAY")) {
                    
                    foodGenerationDelay = Integer.parseInt(line.split("=")[1].trim());
                }
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        
        while (true) {
            food.generateNewFood();
            try {
                Thread.sleep(foodGenerationDelay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


