package ru.geekbrains;

import java.util.Scanner;

public class HomeWorkApp {
    private static final int AI_LEVEL = 7;
    private static final int SIZE = 5;
    private static final int DOTS_TO_WIN = 4;
    private static final char PLAYER_SYMBOL = 'X';
    private static final char AI_SYMBOL = 'O';
    private static final char BLANK_SYMBOL = '.';
    private static final char[][] BOARD = new char[SIZE][SIZE];
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        initialBoard();
        printBoard();

        while (true) {

            playerTurn();
            printBoard();

            if (checkWin(PLAYER_SYMBOL)) {
                System.out.println("Вы выиграли!!!");
                break;
            }

            if (isBoardFull()) {
                System.out.println("Ничья!!!");
                break;
            }

            System.out.println("Ходит ИИ");
            aiTurn();
            printBoard();

            if (checkWin(AI_SYMBOL)) {
                System.out.println("Победил искуственный интеллект!!!");
                break;
            }

            if (isBoardFull()) {
                System.out.println("Ничья!!!");
                break;
            }
        }

        System.out.println("Игра окончена!!!");
    }

    private static void aiTurn() {
        int[] bestStep = bestMove();
        BOARD[bestStep[0]][bestStep[1]] = AI_SYMBOL;
    }

    private static int[] bestMove() {
        int bestScore = Integer.MIN_VALUE;
        int[] bestStep = {-1, -1};

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (BOARD[row][col] == BLANK_SYMBOL) {
                    //Проверяем, если следующий ход игрока выиграшный, блокируем его
                    BOARD[row][col] = PLAYER_SYMBOL;
                    if (checkWin(PLAYER_SYMBOL)) {
                        bestStep[0] = row;
                        bestStep[1] = col;
                        return bestStep;
                    }
                    //Ищем оптимальный ход по алгоритму минимакс
                    BOARD[row][col] = AI_SYMBOL;
                    int score = minimaxWithAlphaBetta(BOARD, AI_LEVEL, Integer.MIN_VALUE, Integer.MAX_VALUE, false);
                    BOARD[row][col] = BLANK_SYMBOL;
                    if (score > bestScore) {
                        bestStep[0] = row;
                        bestStep[1] = col;
                        bestScore = score;
                    }
                }
            }
        }

        return bestStep;
    }

    /**
     * Реализация алгоритма minimax с альфа/бетта отсечением
     */
    private static int minimaxWithAlphaBetta(char[][] board, int depth, int alpha, int betta, boolean isMaximizing) {

        if (isBoardFull() || depth <= 0) return 0;
        if (checkWin(PLAYER_SYMBOL)) return -10;
        if (checkWin(AI_SYMBOL)) return 10;

        if (isMaximizing) {
            int highestVal = Integer.MIN_VALUE;
            for (int row = 0; row < SIZE; row++) {
                for (int col = 0; col < SIZE; col++) {
                    if (board[row][col] == BLANK_SYMBOL) {
                        board[row][col] = AI_SYMBOL;
                        highestVal = Math.max(highestVal,
                                minimaxWithAlphaBetta(board, depth - 1, alpha, betta, false));
                        board[row][col] = BLANK_SYMBOL;
                        alpha = Math.max(alpha, highestVal);
                        if (alpha >= betta) {
                            return highestVal;
                        }
                    }
                }
            }
            return highestVal;
        } else {
            int lowestVal = Integer.MAX_VALUE;
            for (int row = 0; row < SIZE; row++) {
                for (int col = 0; col < SIZE; col++) {
                    if (board[row][col] == BLANK_SYMBOL) {
                        board[row][col] = PLAYER_SYMBOL;
                        lowestVal = Math.min(lowestVal,
                                minimaxWithAlphaBetta(board, depth - 1, alpha, betta, true));
                        board[row][col] = BLANK_SYMBOL;
                        betta = Math.min(betta, lowestVal);
                        if (betta <= alpha) {
                            return lowestVal;
                        }
                    }
                }
            }
            return lowestVal;
        }
    }

    private static boolean isBoardFull() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (BOARD[row][col] == BLANK_SYMBOL) {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean checkLine(int x, int y, int dx, int dy, char symbol) {
        for (int i = 0; i < DOTS_TO_WIN; i++) {
            if (BOARD[x + i * dx][y + i * dy] != symbol)
                return false;
        }
        return true;
    }

    public static boolean checkWin(char symbol) {
        int offsetCount = SIZE - DOTS_TO_WIN + 1;

        for (int offsetRow = 0; offsetRow < offsetCount; offsetRow++) {
            for (int offsetCol = 0; offsetCol < offsetCount; offsetCol++) {
                for (int row = 0; row < DOTS_TO_WIN; row++) {
                    // проверяем строки
                    if (checkLine(row + offsetRow, offsetCol, 0, 1, symbol)) return true;

                    // проверяем столбцы
                    if (checkLine(offsetCol, row + offsetRow, 1, 0, symbol)) return true;
                }

                // проверяем диагонали
                if (checkLine(offsetRow, offsetCol, 1, 1, symbol)) return true;
                if (checkLine(offsetRow, DOTS_TO_WIN - offsetCol - 1 + (offsetCount - 1), 1, -1, symbol))
                    return true;
            }
        }

        return false;
    }

    private static void playerTurn() {
        int x;
        int y;

        do {
            System.out.println("Ваш ход, введите координаты х и у:");
            x = SCANNER.nextInt() - 1;
            y = SCANNER.nextInt() - 1;
        } while (!checkPlayerInput(x, y));

        BOARD[y][x] = PLAYER_SYMBOL;
    }

    private static boolean checkPlayerInput(int x, int y) {
        return x >= 0 && x <= SIZE && y >= 0 && y <= SIZE && BOARD[y][x] == BLANK_SYMBOL;
    }

    private static void initialBoard() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                BOARD[row][col] = BLANK_SYMBOL;
            }
        }
    }

    private static void printBoard() {
        for (int row = 0; row <= SIZE; row++) {
            System.out.print(row + " ");
        }

        System.out.println();

        for (int row = 0; row < SIZE; row++) {
            System.out.print((row + 1) + " ");

            for (int col = 0; col < SIZE; col++) {
                System.out.print(BOARD[row][col] + " ");
            }

            System.out.println();
        }
    }
}
