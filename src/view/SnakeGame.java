package view;

import javax.swing.*;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.SnakeController;
import model.Barrier;
import model.BarrierThread;
import model.SnakeModel;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SnakeGame {
    public static void main(String[] args) {
    
        JFrame frame = new JFrame("Snake Game");
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
     
        loadWindowDimensions("src/resources/config.txt");
        frame.setSize(GameConfig.WINDOW_WIDTH, GameConfig.WINDOW_HEIGHT);

     
        SnakeModel model = new SnakeModel();
        SnakeView view = new SnakeView(model);
        SnakeController controller = new SnakeController(model);
        Barrier barrier = new Barrier();
        BarrierThread barrierThread = new BarrierThread(barrier, model);

        model.setBarrier(barrier);
        

   
        frame.add(view);
        frame.addKeyListener(controller);
        frame.setVisible(true);
        
        barrierThread.start();

     
        Timer timer = new Timer(GameConfig.TIMER_DELAY, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.move();
                Point head = model.getSnake().get(0); 
                if (model.checkCollision()) {
               
                } else if (model.getFood().foodCollidesWithSnake(head.x, head.y)) {
                  
                    model.increaseScore(model.getFood().getPoints()); 
                    model.getFood().generateNewFood(); 
                } else if (model.getBarriers().stream().anyMatch(barrierPos -> model.getFood().foodCollidesWithBarrier(barrierPos.x, barrierPos.y))) {
                 
                }
                view.repaint();
            }
        });
        timer.start();



        frame.setVisible(true); 
    }

    private static void loadWindowDimensions(String configFilePath) {
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
}