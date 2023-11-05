package view;

public class GameConfig {
    public static int WINDOW_WIDTH = 800;  
    public static int WINDOW_HEIGHT = 600; 
    public static int TIMER_DELAY = 100;

    public static final int SCREEN_WIDTH = 1920; 
    public static final int SCREEN_HEIGHT = 1080; 

    public static int getCenteredX() {
        return (SCREEN_WIDTH - WINDOW_WIDTH) / 2;
    }

    public static int getCenteredY() {
        return (SCREEN_HEIGHT - WINDOW_HEIGHT) / 2;
    }

    private GameConfig() {
        
    }
    
    
}
