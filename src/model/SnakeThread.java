package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import view.SnakeView;

public class SnakeThread extends Thread {
    private SnakeModel model;
    private int movementDelay; 
    private SnakeView view;
  
    public SnakeThread(SnakeModel model, SnakeView view) {
        this.model = model;
        this.view = view; 
        loadConfigurations(); 
    }


    private void loadConfigurations() {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/resources/config.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("SPEED")) {
                   
                    movementDelay = Integer.parseInt(line.split("=")[1].trim());
                }
              
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (true) {
            model.move();
            if (model.checkCollision()) {
             
                break;
            }
            model.checkFoodCollision(); 
            view.repaint(); 
            try {
                Thread.sleep(movementDelay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



}

