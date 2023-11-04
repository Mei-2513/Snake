package view;

import javax.swing.JPanel;

import model.SnakeModel;

import javax.swing.*;
import java.awt.*;
import java.util.List;


public class SnakeView extends JPanel {
    private SnakeModel model;
    private static final int CELL_SIZE = 20;

    public SnakeView(SnakeModel model) {
        this.model = model;
    
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
 
        g.setColor(Color.GREEN);
        for (Point point : model.getSnake()) {
            g.fillRect(point.x * CELL_SIZE, point.y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
        }
        
    
        Point foodPosition = model.getFood().getPosition();
        g.setColor(Color.RED);
        g.fillRect(foodPosition.x * CELL_SIZE, foodPosition.y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
        
      
    }

}
