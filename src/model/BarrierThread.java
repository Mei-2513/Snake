package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BarrierThread extends Thread {
    private Barrier barrier;
    private int barrierGenerationDelay; 

    public BarrierThread(Barrier barrier) {
        this.barrier = barrier;
        loadConfigurations(); 
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
            try {
                Thread.sleep(barrierGenerationDelay); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
