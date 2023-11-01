package view;



import javax.swing.JFrame;

public class GameFrame extends JFrame {
    private GamePanel gamePanel;

    public GameFrame() {
        setTitle("Snake Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Inicializa el panel del juego
        gamePanel = new GamePanel();
        add(gamePanel);

        pack(); // Ajusta automáticamente el tamaño de la ventana
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void initUI() {
        setLocationRelativeTo(null); // Centra la ventana en la pantalla
    }
}

