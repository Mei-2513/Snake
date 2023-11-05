package view;

import javax.swing.*;

import controller.SnakeController;
import model.Barrier;
import model.BarrierThread;
import model.Food;
import model.FoodThread;
import model.SnakeModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.awt.Point;

public class SnakeGame {
    private SnakeModel model;
    private SnakeView view;
    private SnakeController controller;

    public SnakeGame() {
        model = new SnakeModel();
        view = new SnakeView(model);
        controller = new SnakeController(model);

        JFrame frame = new JFrame("Snake Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loadWindowDimensions("src/resources/config.txt");
        frame.setSize(GameConfig.WINDOW_WIDTH, GameConfig.WINDOW_HEIGHT);

        Barrier barrier = new Barrier();
        BarrierThread barrierThread = new BarrierThread(barrier, model);
        Food food = new Food();
        FoodThread foodThread = new FoodThread(food);

        model.setBarrier(barrier);
        model.setFood(food);

        frame.add(view);
        frame.addKeyListener(controller);
        frame.setVisible(true);

        barrierThread.start();
        foodThread.start();

        Timer timer = new Timer(GameConfig.TIMER_DELAY, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.move();
                checkBarrierCollision(); 
                checkFoodCollision(); 
                checkSelfCollision(); 
                view.repaint();
            }
        });
        timer.start();

        frame.setVisible(true);
    }

    private void loadWindowDimensions(String configFilePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(configFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("WINDOW_WIDTH")) {
                    GameConfig.WINDOW_WIDTH = Integer.parseInt(line.split("=")[1].trim());
                } else if (line.startsWith("WINDOW_HEIGHT")) {
                    GameConfig.WINDOW_HEIGHT = Integer.parseInt(line.split("=")[1].trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void checkBarrierCollision() {
        List<Point> barriers = model.getBarriers();
        Point head = model.getSnake().get(0);

        
        for (Point barrierPos : barriers) {
            if (head.equals(barrierPos)) {
                
                gameOver();
            }
        }
    }

    private void checkFoodCollision() {
        Point head = model.getSnake().get(0);
        Food food = model.getFood();

        if (head.equals(food.getPosition())) {
            model.increaseScore(food.getPoints());
            model.getFood().generateNewFood();
        }
    }

    private void checkSelfCollision() {
        List<Point> snake = model.getSnake();
        Point head = snake.get(0);

        for (int i = 1; i < snake.size(); i++) {
            if (head.equals(snake.get(i))) {
               
                gameOver();
            }
        }
    }

    private void gameOver() {
        
        JOptionPane.showMessageDialog(view, "Game Over");
       
        model.resetGame();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SnakeGame();
            }
        });
    }
}

