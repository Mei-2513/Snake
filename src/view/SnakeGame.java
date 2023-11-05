package view;

import javax.swing.*;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.SnakeController;
import model.Barrier;
import model.BarrierThread;
import model.Food;
import model.SnakeModel;

public class SnakeGame extends JFrame {
    private SnakeModel model;
    private SnakeView view;
    private SnakeController controller;
    private Timer timer; 

    public SnakeGame() {
        setTitle("Snake Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        model = new SnakeModel();
        view = new SnakeView(model);
        controller = new SnakeController(model);
        
        Barrier barrier = new Barrier();
        BarrierThread barrierThread = new BarrierThread(barrier, model);
        barrierThread.start();

        model.setBarrier(new Barrier()); 
        model.setFood(new Food()); 

        add(view);
        addKeyListener(controller);

        
        setSize(GameConfig.WINDOW_WIDTH, GameConfig.WINDOW_HEIGHT);

        setLocationRelativeTo(null);

   

        timer = new Timer(GameConfig.TIMER_DELAY, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.move();
                Point head = model.getSnake().get(0);
                if (model.checkCollision()) {
                    if (model.getBarriers().stream().anyMatch(barrierPos -> barrierPos.equals(head))) {
                      
                        timer.stop(); 
                    } else {
                        
                    }
                } else if (model.getFood().foodCollidesWithSnake(head.x, head.y)) {
                    model.increaseScore(model.getFood().getPoints());
                    model.getFood().generateNewFood();
                } else if (model.getBarriers().stream().anyMatch(barrierPos -> model.getFood().foodCollidesWithBarrier(barrierPos.x, barrierPos.y))) {
                    
                    timer.stop(); 
                }
                model.getFood().checkFoodDuration(); 
                view.repaint();
            }
        });
    }
    public void startGame() {
       
        timer.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SnakeGame game = new SnakeGame();
            game.startGame(); 
            game.setVisible(true);
        });
    }
}
