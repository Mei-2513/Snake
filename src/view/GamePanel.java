package view;


import model.GameConfig;
import model.GameLogic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel implements ActionListener {
    private final GameLogic gameLogic;
    private Timer timer;

    public GamePanel(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
        setPreferredSize(new Dimension(GameConfig.GAME_WIDTH, GameConfig.GAME_HEIGHT));
        setBackground(Color.BLACK);

        
        timer = new Timer(GameConfig.GAME_SPEED, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
 
        gameLogic.updateGame();
        repaint(); 
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        

      
        g.setColor(Color.GREEN);
        for (Point bodyPart : gameLogic.getSnakeBody()) {
            g.fillRect(bodyPart.x, bodyPart.y, GameConfig.SNAKE_SIZE, GameConfig.SNAKE_SIZE);
        }

    
        g.setColor(Color.RED);
        for (Point food : gameLogic.getFoodPositions()) {
            g.fillRect(food.x, food.y, GameConfig.FOOD_SIZE, GameConfig.FOOD_SIZE);
        }
    }
}

