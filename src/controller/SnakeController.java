package controller;



import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import model.SnakeModel;
import view.SnakeView;


public class SnakeController implements KeyListener {
    private SnakeModel model;
    private SnakeView view;

    public SnakeController(SnakeModel model, SnakeView view) {
        this.model = model;
        this.view = view;
        view.addKeyListener(this); 
        view.setFocusable(true);
        view.setFocusTraversalKeysEnabled(false);
    }
    
    public SnakeController(SnakeModel model) {
        this.model = model;
        
    }


    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
            model.changeDirection(SnakeModel.UP); 
        } else if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
            model.changeDirection(SnakeModel.DOWN); 
        } else if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
            model.changeDirection(SnakeModel.LEFT); 
        } else if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
            model.changeDirection(SnakeModel.RIGHT);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
       
    }
}
