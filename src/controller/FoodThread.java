package controller;

import model.Food;

public class FoodThread extends Thread {
    private Food food;

    public FoodThread(Food food) {
        this.food = food;
    }

    @Override
    public void run() {
        while (true) {
     
        }
    }
}