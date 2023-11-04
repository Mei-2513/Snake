package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.SnakeController;
import model.SnakeModel;
import view.SnakeView;

public class SnakeGame {
    public static void main(String[] args) {
        // Crea la ventana del juego
        JFrame frame = new JFrame("Snake Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(GameConfig.WINDOW_WIDTH, GameConfig.WINDOW_HEIGHT);

        // Crea el modelo, vista y controlador
        SnakeModel model = new SnakeModel();
        SnakeView view = new SnakeView(model);
        SnakeController controller = new SnakeController(model);

        // Configura la vista en el marco
        frame.add(view);
        frame.addKeyListener(controller);

        // Configura un temporizador para actualizar la vista
        Timer timer = new Timer(GameConfig.TIMER_DELAY, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.move();
                if (model.checkCollision()) {
                    // Manejar colisiones
                    // Mostrar un mensaje de fin de juego
                    // Guardar la puntuación, etc.
                }
                view.repaint();
            }
        });
        timer.start();

        // Muestra la ventana del juego
        frame.setVisible(true);
    }
}

