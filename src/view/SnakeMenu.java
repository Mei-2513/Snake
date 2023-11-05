package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SnakeMenu extends JFrame {
    public SnakeMenu() {
        setTitle("Snake Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JButton playButton = new JButton("Play"); 
        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });

        JPanel panel = new JPanel();
        panel.add(playButton);
        add(panel);

        pack();
        setLocationRelativeTo(null);
    }

    private void startGame() {
        SnakeGame game = new SnakeGame();
        game.startGame();
        game.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SnakeMenu().setVisible(true);
        });
    }
}
