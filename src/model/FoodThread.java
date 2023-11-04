package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FoodThread extends Thread {
    private Food food;
    private int foodGenerationDelay; // Intervalo de generación de alimentos

    public FoodThread(Food food) {
        this.food = food;
        loadConfigurations(); // Carga las configuraciones al inicio del hilo
    }

    private void loadConfigurations() {
        try (BufferedReader reader = new BufferedReader(new FileReader("food_config.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("FOOD_GENERATION_DELAY")) {
                    // Extrae el intervalo de generación de alimentos
                    foodGenerationDelay = Integer.parseInt(line.split("=")[1].trim());
                }
                // Otros casos para otras configuraciones si es necesario
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        // Implementa la lógica para generar comida en intervalos regulares.
        while (true) {
            food.generateNewFood();
            try {
                Thread.sleep(foodGenerationDelay); // Utiliza el intervalo cargado desde el archivo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


