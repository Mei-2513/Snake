package controller;

import model.Obstacle;

public class ObstacleThread extends Thread {
    private Obstacle obstacle;

    public ObstacleThread(Obstacle obstacle) {
        this.obstacle = obstacle;
    }

    @Override
    public void run() {
        while (true) {
         
        }
    }
}
