package ru.geekbrains;

public class HomeWorkApp {

    public static void main(String[] args) {
        System.out.println(within10and20(6, 5));

        isPositiveOrNegative(0);

        System.out.println(isNegative(0));

        printWordNTimes("строка", 5);

        System.out.println("Высокосный год: " + isLeapYear(2024));
    }

    public static boolean within10and20(int x1, int x2) {
        int summa = x1 + x2;
        return summa >= 10 && summa <= 20;
    }

    public static void isPositiveOrNegative(int x) {
        if (x >= 0) {
            System.out.println("число " + x + " положительное");
        } else {
            System.out.println("число " + x + " отрицательное");
        }
    }

    public static boolean isNegative(int x) {
        return x >= 0;
    }

    public static void printWordNTimes(String word, int times) {
        for (int i = 0; i < times; i++) {
            System.out.println(word);
        }
    }

    public static boolean isLeapYear(int year) {
        return ((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0);
    }
}
