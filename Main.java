package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        char[][] matrix = new char[3][3];
        for (int i=0; i<3; i++) {
            for (int j = 0; j < 3; j++) {
                matrix[i][j] = ' ';
            }
        }
        printBoard(matrix);
        char player = 'X';
        while (true) {
            Scanner scan = new Scanner(System.in);
            print("Enter cells: ");
            String myPosition = scan.nextLine();
            String[] myArr = myPosition.split(" ");
            try {
                Integer.parseInt(myArr[0]);
                Integer.parseInt(myArr[1]);
            } catch (Exception e) {
                println("You should enter numbers!");
                continue;
            }
            int row = Integer.parseInt(myArr[0]);
            int col = Integer.parseInt(myArr[1]);
            if (row > 3 || col > 3) {
                println("Coordinates should be from 1 to 3!");
                continue;
            } else if (matrix[row - 1][col - 1] == 'X' || matrix[row - 1][col - 1] == 'O') {
                println("This cell is occupied! Choose another one!");
                continue;
            }
            matrix[row - 1][col - 1] = player;
            switch (player) {
                case 'X': player = 'O'; break;
                case 'O': player = 'X'; break;
            }
            printBoard(matrix);
            boolean winCheck = checkWinner(matrix, true);
            boolean checkDraw = checkDraw(matrix);
            if (winCheck) { break; }
            if (checkDraw) { break; }
        }
    }
    public static void println(String str){
        System.out.println(str);
    }
    public static void print(String str){
        System.out.print(str);
    }
    public static void printBoard(char[][] matrix) {
        println("---------");
        for ( char[] line : matrix) {
            print("| ");
            for (char aChar : line) {
                print(String.valueOf(aChar));
                System.out.print(" ");
            }
            println("|");
        }
        println("---------");
    }
    public static boolean checkWinner(char[][] matrix, boolean flag) {
        String X = "XXX";
        String O = "OOO";
        String[] winnerLines = new String[]{
                String.valueOf(new char[]{matrix[0][0], matrix[0][1], matrix[0][2]}),
                String.valueOf(new char[]{matrix[1][0], matrix[1][1], matrix[1][2]}),
                String.valueOf(new char[]{matrix[2][0], matrix[2][1], matrix[2][2]}),
                String.valueOf(new char[]{matrix[0][0], matrix[1][0], matrix[2][0]}),
                String.valueOf(new char[]{matrix[0][1], matrix[1][1], matrix[2][1]}),
                String.valueOf(new char[]{matrix[0][2], matrix[1][2], matrix[2][2]}),
                String.valueOf(new char[]{matrix[0][0], matrix[1][1], matrix[2][2]}),
                String.valueOf(new char[]{matrix[0][2], matrix[1][1], matrix[2][0]})
        };
        for (String winnerLine : winnerLines) {
            if (winnerLine.equals(X)) {
                if (flag) println("X wins");
                return true;
            } else if (winnerLine.equals(O)) {
                if (flag) println("O wins");
                return true;
            }
        }
        return false;
    }
    public static boolean checkDraw (char[][] matrix) {
        boolean checkWin = checkWinner(matrix, false);
        int countX = 0;
        int countO = 0;
        for (char[] line : matrix) {
            for (char ch : line) {
                if (ch == 'O') { countO++; }
                if (ch == 'X') { countX++; }
            }
        }
        if (!checkWin && countO + countX == 9) {
            println("Draw");
            return true;
        }
        return false;
    }
}
