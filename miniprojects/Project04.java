package gr.aueb.cf.miniprojects;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * tic-tac-toe game
 */
public class Project04 {
        static String[] arr;
        static String turn;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        arr = new String[9];
        turn = "X";
        String winner = null;

        for (int i = 0; i < 9; i++) {
            arr[i] = String.valueOf(i + 1);
        }

        printBoard();
        System.out.println("X will play first. Enter a slot number to place X in:");

        while (winner == null) {
            int numInput = 0;

            try {
                numInput = in.nextInt();
                if (!(numInput > 0 && numInput <= 9)) {
                    System.out.println(
                            "Invalid input; re-enter slot number:");
                    continue;
                }
            }
            catch (InputMismatchException e) {
                System.out.println(
                        "Invalid input; re-enter slot number:");
                in.nextLine();
                continue;
            }

            if (arr[numInput - 1].equals(
                    String.valueOf(numInput))) {
                arr[numInput - 1] = turn;

                if (turn.equals("X")) {
                    turn = "O";
                } else {
                    turn = "X";
                }

                printBoard();
                winner = checkWinner();
            } else {
                System.out.println(
                        "Slot already taken, re-enter slot number:");
            }
        }

        if (winner.equalsIgnoreCase("draw")) {
            System.out.println("It's a draw! Thanks for playing.");
        } else {
            System.out.println(
                    "Congratulations! " + winner + "'s have won! Thanks for playing.");
        }
        in.close();
    }



        static String checkWinner()
        {
            for (int i = 0; i < 8; i++) {
                String line = null;

                switch (i) {
                    case 0:
                        line = arr[0] + arr[1] + arr[2];
                        break;
                    case 1:
                        line = arr[3] + arr[4] + arr[5];
                        break;
                    case 2:
                        line = arr[6] + arr[7] + arr[8];
                        break;
                    case 3:
                        line = arr[0] + arr[3] + arr[6];
                        break;
                    case 4:
                        line = arr[1] + arr[4] + arr[7];
                        break;
                    case 5:
                        line = arr[2] + arr[5] + arr[8];
                        break;
                    case 6:
                        line = arr[0] + arr[4] + arr[8];
                        break;
                    case 7:
                        line = arr[2] + arr[4] + arr[6];
                        break;
                }

                if (line.equals("XXX")) {
                    return "X";
                } else if (line.equals("OOO")) {
                    return "O";
                }
            }

            for (int i = 0; i < 9; i++) {
                if (arr[i].contains(
                        String.valueOf(i + 1))) {
                    break;
                } else if (i == 8) {
                    return "draw";
                }
            }

            System.out.println(
                    turn + "'s turn; enter a slot number to place " + turn + " in:");
            return null;
        }


        static void printBoard()
        {
            System.out.println("| " + arr[0] + " | " + arr[1] + " | " + arr[2] + " |");
            System.out.println("| " + arr[3] + " | " + arr[4] + " | " + arr[5] + " |");
            System.out.println("| " + arr[6] + " | " + arr[7] + " | " + arr[8] + " |");
        }
}
