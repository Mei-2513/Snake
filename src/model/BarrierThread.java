package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BarrierThread extends Thread {
    private Barrier barrier;
    private int barrierGenerationDelay; // Intervalo de generación de barreras

    public BarrierThread(Barrier barrier) {
        this.barrier = barrier;
        loadConfigurations(); // Carga las configuraciones al inicio del hilo
    }

    private void loadConfigurations() {
        try (BufferedReader reader = new BufferedReader(new FileReader("barrier_config.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("BARRIER_GENERATION_DELAY")) {
                    // Extrae el intervalo de generación de barreras
                    barrierGenerationDelay = Integer.parseInt(line.split("=")[1].trim());
                }
                // Otros casos para otras configuraciones si es necesario
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        // Implementa la lógica para que las barreras aparezcan y desaparezcan en intervalos regulares.
        while (true) {
            barrier.generateNewBarrier();
            try {
                Thread.sleep(barrierGenerationDelay); // Utiliza el intervalo cargado desde el archivo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
