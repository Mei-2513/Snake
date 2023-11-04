package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SnakeThread extends Thread {
    private SnakeModel model;
    private int movementDelay; // Velocidad de movimiento de la serpiente

    public SnakeThread(SnakeModel model) {
        this.model = model;
        loadConfigurations(); // Carga las configuraciones al inicio del hilo
    }

    private void loadConfigurations() {
        try (BufferedReader reader = new BufferedReader(new FileReader("config.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("SPEED")) {
                    // Extrae la velocidad del juego
                    movementDelay = Integer.parseInt(line.split("=")[1].trim());
                }
                // Otros casos para otras configuraciones si es necesario
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        // Implementa cómo se mueve la serpiente y crece.
        while (true) {
            model.move();
            if (model.checkCollision()) {
                // Manejar colisiones
                // Mostrar un mensaje de fin de juego
                // Guardar la puntuación, etc.
                break;
            }
            try {
                Thread.sleep(movementDelay); // Utiliza la velocidad cargada desde el archivo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

