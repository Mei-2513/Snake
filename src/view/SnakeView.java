package view;

import javax.swing.JPanel;

import model.SnakeModel;

import javax.swing.*;
import java.awt.*;
import java.util.List;


public class SnakeView extends JPanel {
    private SnakeModel model;

    public SnakeView(SnakeModel model) {
        this.model = model;
        // Configura la vista, crea la ventana del juego, dibuja el tablero, la serpiente, la comida y las barreras.
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Dibuja el juego, la serpiente, la comida y las barreras en la ventana.
    }
}
