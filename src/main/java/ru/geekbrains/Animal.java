package ru.geekbrains;

public abstract class Animal {
    private String nickname;
    private final int maxRunDistance;
    private final int maxSwimDistance;

    public Animal(String nickname, int maxRunDistance, int maxSwimDistance) {
        this.nickname = nickname;
        this.maxRunDistance = maxRunDistance;
        this.maxSwimDistance = maxSwimDistance;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getMaxRunDistance() {
        return maxRunDistance;
    }

    public int getMaxSwimDistance() {
        return maxSwimDistance;
    }

    public void run(int distance) {
        if (distance > 0 && distance <= maxRunDistance) {
            System.out.printf("%s пробежал(а) %d метров\n", this.nickname, distance);
        } else {
            System.out.printf("Расстояния должно быть больше 0 и меньше %d\n", maxRunDistance);
        }
    }

    public void swim(int distance) {
        if (distance > 0 && distance <= maxSwimDistance) {
            System.out.printf("%s проплыл(а) %d метров\n", this.nickname, distance);
        } else {
            System.out.printf("Расстояния должно быть больше 0 и меньше %d\n", maxSwimDistance);
        }
    }
}
