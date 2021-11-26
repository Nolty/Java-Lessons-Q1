package ru.geekbrains;

public class Cat extends Animal {

    public Cat(String nickname) {
        super(nickname, 200, 0);
    }

    @Override
    public void swim(int distance) {
        System.out.printf("%s не умеет плавать\n", this.getNickname());
    }

}
