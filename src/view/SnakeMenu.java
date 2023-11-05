package view;

import javax.swing.*;

import model.SnakeModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;

public class SnakeMenu {
    private JFrame frame;
    private JTextField nameField;
    private JButton startButton;
    private GameConfiguration config;
    private int choice;

    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem scoreHistoryMenuItem;

    public SnakeMenu() {
    	config = new GameConfiguration();
        initialize();
    }

    private void initialize() {
    

    	    
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel nameLabel = new JLabel("Nombre:");
        nameLabel.setBounds(10, 10, 46, 14);
        frame.getContentPane().add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(66, 7, 86, 20);
        frame.getContentPane().add(nameField);
        nameField.setColumns(10);

        startButton = new JButton("Iniciar juego");
        startButton.setBounds(10, 58, 117, 23);
        frame.getContentPane().add(startButton);

        JLabel developerInfo = new JLabel("Nombre completo: Silvia Juliana Rodriguez Rodriguez");
        developerInfo.setBounds(10, 90, 200, 14);
        frame.getContentPane().add(developerInfo);

        JLabel studentId = new JLabel("ID del estudiante: 202023822");
        studentId.setBounds(10, 110, 200, 14);
        frame.getContentPane().add(studentId);

        JLabel universityName = new JLabel("Nombre de la universidad: UPTC");
        universityName.setBounds(10, 130, 200, 14);
        frame.getContentPane().add(universityName);

        JLabel universityLogo = new JLabel(new ImageIcon("path/to/university/logo.png"));
        universityLogo.setBounds(10, 150, 100, 100);
        frame.getContentPane().add(universityLogo);

  
        menuBar = new JMenuBar();
        menu = new JMenu("Menú");
        scoreHistoryMenuItem = new JMenuItem("Historial de puntuaciones");
        scoreHistoryMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/resources/History.ser"))) {
                    while (true) {
                        Score score = (Score) ois.readObject();
                        System.out.println("Fecha y hora: " + score.getDateTime());
                        System.out.println("Nombre del jugador: " + score.getPlayerName());
                        System.out.println("Puntuación: " + score.getScore());
                        System.out.println("-----------------------------------");
                    }
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });
        menu.add(scoreHistoryMenuItem);
        menuBar.add(menu);
        frame.setJMenuBar(menuBar);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                System.out.println("Has iniciado el juego como " + name);
                String difficulty = selectDifficulty();
                config.applyDifficulty(difficulty);
                SnakeModel model = new SnakeModel(null);
                SnakeView view = new SnakeView(model);
                SnakeGame game = new SnakeGame(model, config);
                
            }
        });

    }

    private String selectDifficulty() {
        String difficulty = "";
        Object[] options = {"Fácil", "Medio", "Difícil"};

        choice = JOptionPane.showOptionDialog(frame,
                                              "Selecciona la dificultad:",
                                              "Dificultad",
                                              JOptionPane.DEFAULT_OPTION,
                                              JOptionPane.INFORMATION_MESSAGE,
                                              null,
                                              options,
                                              options[0]);

        switch (choice) {
            case 0:
                difficulty = "Easy";
                break;
            case 1:
                difficulty = "Medium";
                break;
            case 2:
                difficulty = "Hard";
                break;
            default:
                JOptionPane.showMessageDialog(frame, "Opción no válida. Por favor, intenta de nuevo.");
        }

        return difficulty;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SnakeMenu window = new SnakeMenu();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
