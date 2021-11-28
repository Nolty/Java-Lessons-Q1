package ru.geekbrains;

public class Plate {
    private int food;

    public Plate(int food) {
        this.food = food;
    }

    public boolean decreaseFood(int food) {
        if (food > 0 && this.food >= food) {
            this.food -= food;
            return true;
        }

        return false;
    }

    public void increaseFood(int food) {
        this.food += Math.max(food, 0);
    }

    public int getFood() {
        return food;
    }

    public void info() {
        System.out.printf("В тарелке находится %d ед. еды\n", food);
    }
}
