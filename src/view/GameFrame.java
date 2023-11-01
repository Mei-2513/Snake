package view;


import javax.swing.*;

public class GameFrame extends JFrame {
    private GamePanel gamePanel;

    public GameFrame() {
        setTitle("Snake Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void initUI() {
        gamePanel = new GamePanel(null);
        add(gamePanel);
        pack();
        setLocationRelativeTo(null);
    }

    public void setGamePanel(GamePanel gamePanel) {
        if (this.gamePanel != null) {
            remove(this.gamePanel);
        }
        this.gamePanel = gamePanel;
        add(this.gamePanel);
        revalidate();
    }
}

