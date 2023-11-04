package controller;



import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import model.SnakeModel;


public class SnakeController implements KeyListener {
    private SnakeModel model;

    public SnakeController(SnakeModel model) {
        this.model = model;
        // Configura el controlador, registra el control de teclado, maneja las teclas presionadas.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Maneja las teclas presionadas por el jugador, como cambiar la dirección de la serpiente.
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // No es necesario implementar nada aquí, pero se debe incluir por la interfaz KeyListener.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // No es necesario implementar nada aquí, pero se debe incluir por la interfaz KeyListener.
    }
}
