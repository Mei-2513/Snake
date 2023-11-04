package model;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ScoreThread extends Thread {
    private SnakeModel model;
    private int scoreUpdateDelay; 

    public ScoreThread(SnakeModel model) {
        this.model = model;
        loadConfigurations(); 
    }

    private void loadConfigurations() {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/resources/config.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("SCORE_UPDATE_DELAY")) {
                 
                    scoreUpdateDelay = Integer.parseInt(line.split("=")[1].trim());
                }
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
      
        while (true) {
       
            model.increaseScore(model.getFood().getPoints());
            try {
                Thread.sleep(scoreUpdateDelay); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

