package ru.geekbrains;

public class HomeWorkApp {
    private static int countCats = 0;
    private static int countDogs = 0;

    public static void main(String[] args) {
        Animal[] animals = new Animal[10];
        animals[0] = new Cat("Васька");
        animals[1] = new Dog("Мухтар");
        animals[2] = new Cat("Бизилио");
        animals[3] = new Dog("Гретта");
        animals[4] = new Cat("Милисса");
        animals[5] = new Dog("Барбос");
        animals[6] = new Cat("Дымок");
        animals[7] = new Dog("Лорд");
        animals[8] = new Cat("Мурка");
        animals[9] = new Cat("Кузя");

        for (Animal animal: animals) {
            animal.run(150);
            animal.swim(10);

            if (animal instanceof Cat) {
                countCats += 1;
            } else if (animal instanceof Dog) {
                countDogs += 1;
            }
        }

        System.out.printf(
                "Количество животных %d, из них собак - %d, котов - %d\n",
                animals.length,
                countDogs,
                countCats
        );
    }
}
