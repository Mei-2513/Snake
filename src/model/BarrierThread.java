package model;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BarrierThread extends Thread {
    private Barrier barrier;
    private int barrierGenerationDelay;
	private SnakeModel model; 

    public BarrierThread(Barrier barrier, SnakeModel model) {
        this.barrier = barrier;
        loadConfigurations(); 
        this.model = model;
    }

    private void loadConfigurations() {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/resources/barrier_config.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("BARRIER_GENERATION_DELAY")) {
                   
                    barrierGenerationDelay = Integer.parseInt(line.split("=")[1].trim());
                }
               
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (true) {
            barrier.generateNewBarrier();

       
            Point barrierPosition = barrier.getPosition();

        
            model.getBarriers().add(barrierPosition);

            try {
                Thread.sleep(barrierGenerationDelay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
