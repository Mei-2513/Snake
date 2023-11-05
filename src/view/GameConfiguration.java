package view;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GameConfiguration {
	private int snakeLength;
    private int speed;
    private int foodDuration;
    private int foodGenerationDelay;
    private int barrierDuration;
    private int barrierGenerationDelay;
	
	
    public void setSnakeLength(int length) {
        this.snakeLength = length;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setFoodDuration(int duration) {
        this.foodDuration = duration;
    }

    public void setFoodGenerationDelay(int delay) {
        this.foodGenerationDelay = delay;
    }

    public void setBarrierDuration(int duration) {
        this.barrierDuration = duration;
    }

    public void setBarrierGenerationDelay(int delay) {
        this.barrierGenerationDelay = delay;
    }
	
    public void applyDifficulty(String difficulty) {
        try {
          
            BufferedReader reader = new BufferedReader(new FileReader("src/resources/difficulties.txt"));

            // Lee el archivo línea por línea
            String line;
            while ((line = reader.readLine()) != null) {
             
                String[] parts = line.split("=");
                String difficultyName = parts[0];
                String[] configs = parts[1].split(",");

                
                if (difficultyName.equalsIgnoreCase(difficulty)) {
                    for (String config : configs) {
                   
                        if (config.contains("=")) {
                            
                            String[] configParts = config.split("=");
                            String key = configParts[0];
                            String value = configParts[1];

                            // Aplica la configuración
                            switch (key) {
                                case "SNAKE_LENGTH":
                                    setSnakeLength(Integer.parseInt(value));
                                    break;
                                case "SPEED":
                                    setSpeed(Integer.parseInt(value));
                                    break;
                                case "FOOD_DURATION":
                                    setFoodDuration(Integer.parseInt(value));
                                    break;
                                case "FOOD_GENERATION_DELAY":
                                    setFoodGenerationDelay(Integer.parseInt(value));
                                    break;
                                case "BARRIER_DURATION":
                                    setBarrierDuration(Integer.parseInt(value));
                                    break;
                                case "BARRIER_GENERATION_DELAY":
                                    setBarrierGenerationDelay(Integer.parseInt(value));
                                    break;
                            }
                        }
                    }
                    break;
                }
            }

          
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
