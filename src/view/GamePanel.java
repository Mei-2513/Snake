package view;


import model.GameLogic;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private final GameLogic gameLogic;

    public GamePanel(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
        setPreferredSize(new Dimension(GameConfig.GAME_WIDTH, GameConfig.GAME_HEIGHT));
        setBackground(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
    }
}

