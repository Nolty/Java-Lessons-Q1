package ru.geekbrains;

public class HomeWorkApp {
    private static final String[] catNameArray = {
            "Соня", "Клеопатра", "Цунами", "Забияка","Матильда", "Кнопка", "Масяня",
            "Царапка", "Серсея", "Ворсинка"
    };

    public static void main(String[] args) {
        Plate plate = new Plate(100);
        plate.info();

        Cat[] cats = new Cat[catNameArray.length];
        for (int i = 0; i < cats.length; i++) {
            cats[i] = new Cat(catNameArray[i], getRandomNumber(1, 30));
            System.out.printf("%s имеет аппетит %d ед.\n", cats[i].getName(), cats[i].getAppetite());
        }

        System.out.println();
        System.out.println("Кормим котов:");
        for (Cat cat: cats) {
            cat.eat(plate);
            if (cat.isSatiety()) {
                System.out.printf("%s покушал и сыт\n", cat.getName());
            } else {
                System.out.printf("%s не хватило еды и он голоден\n", cat.getName());
            }
        }

        System.out.println();
        System.out.printf("В тарелке осталось %d ед. еды\n", plate.getFood());
        plate.increaseFood(50);
        System.out.printf("Добавили еды и в тарелке стало %d ед. еды\n", plate.getFood());
    }

    private static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
