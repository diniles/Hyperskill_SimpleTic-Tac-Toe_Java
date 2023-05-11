package tictactoe;

import java.util.Scanner;

public class Main {
    public static char[][] fillEmptyField() {
        char[][] field = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = '_';
            }
        }
        return field;
    }

    public static char[][] fillField() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        char[][] field = new char[3][3];
        int x = 0;
        for (int i = 0; i < 3; i++) {
            field[0][i] = input.charAt(x);
            x++;
        }
        for (int i = 0; i < 3; i++) {
            field[1][i] = input.charAt(x);
            x++;
        }
        for (int i = 0; i < 3; i++) {
            field[2][i] = input.charAt(x);
            x++;
        }
        return field;
    }

    public static void printField(char[][] field) {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("|");
            for (int j = 0; j < 3; j++) {
                System.out.print(" " + field[i][j]);
            }
            System.out.println(" |");
        }
        System.out.println("---------");
    }

    public static boolean checkRows(char[][] field, char player) {
        boolean result = false;
        for (int i = 0; i < field.length; i++) {
            if (field[i][0] == player && field[i][1] == player && field[i][2] == player) {
                result = true;
            }
        }
        return result;
    }

    public static boolean checkColumns(char[][] field, char player) {
        boolean result = false;
        for (int i = 0; i < field.length; i++) {
            if (field[0][i] == player && field[1][i] == player && field[2][i] == player) {
                result = true;
            }
        }
        return result;
    }

    public static boolean checkDiagonal(char[][] field, char player) {
        boolean result = false;
        if (field[0][0] == player && field[1][1] == player && field[2][2] == player) {
            result = true;
        } else if (field[2][0] == player && field[1][1] == player && field[0][2] == player) {
            result = true;
        }
        return result;
    }

    public static boolean winsX(char[][] field) {
        char player = 'X';
        boolean result = false;
        if (checkRows(field, player) || checkColumns(field, player) || checkDiagonal(field, player)) result = true;
        return result;
    }

    public static boolean winsO(char[][] field) {
        char player = 'O';
        boolean result = false;
        if (checkRows(field, player) || checkColumns(field, player) || checkDiagonal(field, player)) result = true;
        return result;
    }

    public static boolean checkImpossible(char[][] field) {
        boolean result = false;
        if (winsX(field) && winsO(field)) {
            result = true;
        } else {
            int countX = 0;
            int countO = 0;
            for (int i = 0; i < field.length; i++) {
                for (int j = 0; j < field[i].length; j++) {
                    if (field[i][j] == 'X') countX++;
                    if (field[i][j] == 'O') countO++;
                }
            }
            if (Math.abs(countX - countO) > 1) result = true;
        }
        return result;
    }

    public static boolean checkEmpty(char[][] field) {
        boolean result = false;
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (field[i][j] == '_') result = true;
            }
        }
        return result;
    }


    public static boolean gameNotFinished(char[][] field) {
        boolean result = false;
        if (!winsX(field) && !winsO(field) && checkEmpty(field)) result = true;
        return result;
    }

    public static boolean gameDraw(char[][] field) {
        boolean result = false;
        if (!winsX(field) && !winsO(field) && !checkEmpty(field)) result = true;
        return result;
    }


    public static boolean checkEmptyUserInput(char[][] field, int x, int y) {
        return field[x - 1][y - 1] == '_';
    }

    public static char[][] userInput(char[][] field, char player) {
        Scanner scanner = new Scanner(System.in);
        int x;
        int y;
        while (true) {
            try {
                String[] pieces = scanner.nextLine().split(" ");
                x = Integer.parseInt(pieces[0]);
                y = Integer.parseInt(pieces[1]);
            } catch (NumberFormatException e) {
                System.out.println("You should enter numbers!");
                continue;
            }
            if (x < 1 || x > 3 || y < 1 || y > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
            } else if (!checkEmptyUserInput(field, x, y)) {
                System.out.println("This cell is occupied! Choose another one!");
            } else {
                field[x - 1][y - 1] = player;
                break;
            }
        }
        return field;
    }

    public static void main(String[] args) {
        char player = 'X';
        char[][] field = fillEmptyField();
        while (true) {
            printField(field);
            field = userInput(field, player);
            printField(field);
            player = player == 'X' ? 'O' : 'X';
            if (gameDraw(field)) {
                System.out.println("Draw");
                break;
            } else if (winsX(field)) {
                System.out.println("X wins");
                break;
            } else if (winsO(field)) {
                System.out.println("O wins");
                break;
            }
        }
    }
}