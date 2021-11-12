package ru.geekbrains;

import java.util.Arrays;

public class HomeWorkApp {

    public static void main(String[] args) {
        invertArray();
        fillArray();
        changeArray();
        fillDiagonals();
        fillArray(10, 9);
        findMinMaxValueInArray();

        System.out.println("Задание 7: ");
        int[] arr = {2, 2, 2, 1, 2, 2, 10, 1};
        System.out.println(isLeftAndRightPartEquals(arr));

        System.out.println("Задание 8: ");
        int[] arr1 = {1, 2, 3, 4, 5, 6, 7, 8};
        offsetArray(arr1, 3);
        offsetArray(arr1, -3);
    }

    public static void invertArray() {
        int[] arr = { 1, 0, 1, 0, 0, 1 };
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i] == 0 ? 1: 0;
        }

        System.out.println("Задание 1:");
        printArray(arr);
    }

    public static void fillArray() {
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }

        System.out.println("Задание 2:");
        printArray(arr);
    }

    public static void changeArray() {
        int[] arr = { 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 };
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 6) {
                arr[i] *= 2;
            }
        }

        System.out.println("Задание 3:");
        printArray(arr);
    }

    public static void fillDiagonals() {
        int[][] arr = new int[5][5];

        for (int i = 0; i < arr.length; i++) {
            arr[i][i] = 1;
            arr[arr.length - i - 1][i] = 1;
        }

        System.out.println("Задание 4:");
        printArray(arr);
    }

    public static void fillArray(int len, int initialValue) {
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = initialValue;
        }

        System.out.println("Задание 5:");
        printArray(arr);
    }

    public static void findMinMaxValueInArray() {
        int[] arr = { 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 };
        int max = arr[0];
        int min = arr[0];

        for (int j : arr) {
            if (j > max) {
                max = j;
            } else if (j < min) {
                min = j;
            }
        }

        System.out.println("Задание 6:");
        System.out.printf("min=%d; max=%d\n", min, max);
    }

    public static boolean isLeftAndRightPartEquals(int[] arr) {
        int sumLeft = 0;
        int sumRight;

        for (int i = 0; i < arr.length; i++) {
            sumLeft += arr[i];
            sumRight = 0;

            for (int j = i + 1; j < arr.length; j++) {
                sumRight += arr[j];
            }

            if (sumLeft == sumRight) {
                return true;
            }
        }

        return false;
    }

    /**
     * Циклический сдвиг элементов масива.
     * @param arr одномерный массив
     * @param shift значение сдвига (shift > 0 - вправо, shift < 0 - влево)
     */
    public static void offsetArray(int[] arr, int shift) {
        System.out.printf("Значение сдвига = %d\n", shift);
        if(shift < 0)
            shift = arr.length + shift;

        int d = gcd(arr.length, shift);

        System.out.print("Заданный массив: ");
        printArray(arr);

        for(int i = 0; i < d; i++) {
            int temp = arr[i];
            for(int j = i - shift + arr.length; j != i; j = (j - shift + arr.length) % arr.length)
                arr[(j + shift) % arr.length] = arr[j];
            arr[i + shift] = temp;
        }

        System.out.print("Результирующий массив: ");
        printArray(arr);
    }

   private static int gcd(int a, int b) {
        while(b != 0) {
            int c = a;
            a = b;
            b = c % a;
        }

        return a;
    }

    /**
     * Печать одномерных массивов в консоль
     * @param arr ссылка на одномерный масив
     */
    public static void printArray(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    /**
     * Печать двумерных массивов в табличной форме, в консоль
     * @param arr ссылка на двумерный масив
     */
    public static void printArray(int[][] arr) {
        for (int[] ints : arr) {
            System.out.println(Arrays.toString(ints));
        }
    }
}
