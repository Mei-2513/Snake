package view;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.SnakeModel;


import java.awt.*;



public class SnakeView extends JPanel {
    private SnakeModel model;
    private static final int CELL_SIZE = 20;
    private JLabel scoreLabel;
    
    
    

    public SnakeView(SnakeModel model) {
        this.model = model;
        scoreLabel = new JLabel("Score: 0");
        this.add(scoreLabel, BorderLayout.NORTH);
        
    
    }
    public void updateScore(int score) {
        scoreLabel.setText("Score: " + score); 
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.GREEN);
        for (Point point : model.getSnake()) {
            g.fillRect(point.x * CELL_SIZE, point.y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
        }

        Point foodPosition = model.getFood().getPosition();

        if (foodPosition != null) {
            g.setColor(Color.RED);
            g.fillRect(foodPosition.x * CELL_SIZE, foodPosition.y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
        }

        g.setColor(Color.BLUE);
        for (Point barrierPosition : model.getBarriers()) {
            g.fillRect(barrierPosition.x * CELL_SIZE, barrierPosition.y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
        }
    }
}