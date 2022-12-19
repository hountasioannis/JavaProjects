package gr.aueb.cf.miniprojects;

import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * theater booking App
 */
public class Project05 {
    static boolean[][] arr=new boolean[30][12];
    final static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        int choiceRow = 0;
        char choiceColumn = ' ';
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] = false;
            }
        }

        while (true){
            printInstructions();
            System.out.println("insert the number of row");

            while (true) {
                if (in.hasNextInt()) {
                    choiceRow = row();
                } else {
                    System.out.println("choose an int");
                    in.nextLine();
                    continue;
                }

                try {
                checkChoiceRow(choiceRow);
                } catch (IllegalArgumentException e) {
                    System.out.println("give an int between 0 and 30");
                    in.nextLine();
                    continue;
                }
                break;
            }


            if (choiceRow == -1) {
                break;
            }

            System.out.println("insert the letter of column");
            in.nextLine();

            while (true) {
                try {
                    choiceColumn = column();
                    checkChoiceColumn(choiceColumn);
                    break;
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("give a letter between A and L");
                }
            }

            cancel(choiceColumn,choiceRow);
            book(choiceColumn,choiceRow);

        }

        System.out.println("thank you for visiting our app");
    }

    public static void book(char column, int row) {
        if (arr[row][(int)column -65] == false) {
            arr[row][(int)column -65] = true;
            System.out.println("your seat is booked");
        } else return;
    }

    public static void cancel(char column, int row) {
        if (arr[row][(int)column - 65] == true) {
            System.out.println("the seat is already booked, choose another seat");
        } else return;
    }

    public static void printInstructions() {
        System.out.println("To book a seat, insert  the number of row (int)");
        System.out.println("and the letter of the column (char)");
        System.out.println("or 0 (zero) for exit");
    }

    public static int row() {
        int row = in.nextInt();
        return row - 1;
    }

    public static void checkChoiceRow(int choiceRow) {
        if ((choiceRow < -1) || (choiceRow > 29)) {
            throw new IllegalArgumentException();
        }
    }

    public static char column() {
        char column = in.nextLine().charAt(0);
        return column;
    }

    public static void checkChoiceColumn(char choiceColumn) {
        if ((choiceColumn < 'A') || (choiceColumn > 'L')) {
            throw new IndexOutOfBoundsException();
        }
    }
}

