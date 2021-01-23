package tictactoe;

import java.util.Scanner;

public class Main {
    static char[][] field = new char[3][3];
    public static void main(String[] args) {

       //Scanner scanner = new Scanner(System.in);
        //String inputGrid = "";

        char currentPlayer = 'X';

        /*System.out.println("Enter a string of 9 symbols with only X, O or _");

        while(!checkInput(inputGrid)) {
            inputGrid = scanner.nextLine();
            field = convertStringToMatrix(inputGrid);
        }*/
        initializeBoard();
        printField(field);
        while(gameState(field))  {
            System.out.println("Player " + currentPlayer + ", Enter coordinated for your move");
            while (!userMove(currentPlayer)) {
                System.out.println();
            }
            currentPlayer = changePlayer(currentPlayer);
            printField(field);
        }



    }
    /*public static char[][] convertStringToMatrix(String string) {
        //System.out.println("---------");
        char[][] output = new char[3][3];
        int idx = 0;
        for (int i = 0; i < 3; i++) {
            //System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                output[i][j] = string.charAt(idx);
                //System.out.print(string.charAt(idx) + " ");
                idx++;
            }
            //System.out.print("|");
            //System.out.print("\n");
        }

        //System.out.println("---------");
        return output;
    }*/
    public static void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = '_';
            }
        }
    }
    public static void printField(char[][] field) {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.print("|");
            System.out.print("\n");
        }
        System.out.println("---------");
    }

    /*public static boolean checkInput(String input) {
        if(input.length() != 9) {
            System.out.println("Please enter correct input");
            return false;
        } else {
            for (int i = 0; i < input.length(); i++) {
                if(input.charAt(i) != 'X' && input.charAt(i) !='O' && input.charAt(i) != '_') {
                    System.out.println("Please enter correct input");
                    return false;
                }
            }
        }
        return true;
    }*/

    public static boolean gameState(char[][] field) {
        int countX = 0;
        int countO = 0;
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if (field[i][j] == 'X') {
                    countX++;
                } else if (field[i][j] == 'O'){
                    countO++;
                }
            }
        }
        if(countX - countO > 1 || countO - countX > 1 || checkForWin(field, 'X') && checkForWin(field, 'O')) {
            System.out.println("Impossible");
            return false;
        }

        if (checkForWin(field, 'X')) {
            System.out.println("X wins");
            return false;
        } else if (checkForWin(field, 'O')){
            System.out.println("O wins");
            return false;
        } else {
            if(isBoardFull(field)) {
                System.out.println("Draw");
                return false;
            } else {
            System.out.println("Game not finished");
            return true;
            }
        }
    }

    public static boolean checkForWin(char[][] field, char player) {
        return checkRowsForWin(field, player) ||
                checkColumnsForWin(field, player) ||
                checkDiagonalsForWin(field, player);
    }

    public static boolean checkRowsForWin(char[][] field, char player) {
        for (int i = 0; i < 3; i++) {
            if(checkRowCol(field[i][0], field[i][1], field[i][2], player)) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkColumnsForWin(char[][] field, char player) {
        for (int i = 0; i < 3; i++) {
            if(checkRowCol(field[0][i], field[1][i], field[2][i], player)) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkDiagonalsForWin(char[][] field, char player) {
        return (checkRowCol(field[0][0],field[1][1], field[2][2], player) ||
                checkRowCol(field[0][2],field[1][1], field[2][0], player));
    }

    public static boolean checkRowCol(char c1, char c2, char c3, char player) {
        return (c1 != '_' && c1 == player && c1 == c2 && c2 == c3);
    }

    public static boolean isBoardFull(char[][] field) {
        for (int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if (field[i][j] == '_') {
                    return false;
                }
            }
        }
        return true;
    }

    public static char changePlayer(char currentPlayer) {
        if (currentPlayer == 'X') {
            currentPlayer = 'O';
        } else {
            currentPlayer = 'X';
        }
        return currentPlayer;
    }

    public static boolean userMove(char currentPlayer) {
        Scanner scanner = new Scanner(System.in);

        try {
            String r = scanner.next();
            String c = scanner.next();
            int row = Integer.parseInt(r) - 1;
            int col = Integer.parseInt(c) - 1;
            if ((row >= 0) && (row < 3)) {
                if ((col >= 0) && (col < 3)) {
                    if (field[row][col] == '_') {
                        field[row][col] = currentPlayer;
                        return true;
                    } else {
                        System.out.println("This cell is occupied! Choose another one!");
                        return false;
                    }
                } else {
                    System.out.println("Coordinates should be from 1 to 3!");
                    return false;
                }
            } else {
                System.out.println("Coordinates should be from 1 to 3!");
                return false;
            }
        } catch (NumberFormatException e) {
            System.out.println("You should enter numbers!");
        }



        return false;


    }
}
