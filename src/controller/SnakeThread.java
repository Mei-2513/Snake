package controller;

import model.Snake;

public class SnakeThread extends Thread {
    private Snake snake;

    public SnakeThread(Snake snake) {
        this.snake = snake;
    }

    @Override
    public void run() {
        while (true) {
           
            snake.move();
        }
    }
}