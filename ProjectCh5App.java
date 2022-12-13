package gr.aueb.cf.projects;

import java.util.Scanner;

public class ProjectCh5App {
    final static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        boolean quit = false;
        String response;

        do {
            printMenu();
            response = getChoice();
            try {
                if (response.matches("[qQ]")){
                    quit = true;
                }else {
                    printChoice(response);
                }
            }catch (IllegalArgumentException e) {
                System.out.println("invalid choice");
            }
        }while (!quit);
    }

    public static void printMenu() {
        System.out.println("select one of the following");
        System.out.println("1.print H");
        System.out.println("2.print V");
        System.out.println("3.print HV");
        System.out.println("4.print HV Asc");
        System.out.println("5.print HV Desc");
        System.out.println("Q or q to quit");
    }

    public static String getChoice() {
        return in.nextLine().trim();
    }

    public static void printStarsH(int n) {
        for (int i = 1; i <= n; i++) {
            System.out.print("*");
        }
    }

        public static void printStarsV(int n) {
            for (int i = 1; i <= n; i++) {
                System.out.println("*");
            }
        }

    public static void printStarsHV(int n) {
        for (int i = 1; i <= n; i++) {
            printStarsH(n);
            System.out.println();
        }
    }

    public static void printStarsHVAsc(int n) {
        for (int i = 1; i <= n; i++) {
            printStarsH(i);
            System.out.println();
        }
    }

    public static void printStarsHVDesc(int n) {
        for (int i = n; i >= 1; i--) {
            printStarsH(i);
            System.out.println();
        }
    }

    public static void printChoice(String s) throws IllegalArgumentException {
        int choice;
        int n = 0;

        try {
            choice = Integer.parseInt(s);
            if ((choice >= 1) && (choice <= 5)) {
                System.out.println("insert the number of stars");
                n = Integer.parseInt(getChoice());
            }

            switch (choice) {
                case 1:
                    printStarsH(n);
                    System.out.println();
                    break;
                case 2:
                    printStarsV(n);
                    System.out.println();
                    break;
                case 3:
                    printStarsHV(n);
                    break;
                case 4:
                    printStarsHVAsc(n);
                    break;
                case 5:
                    printStarsHVDesc(n);
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        }catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw e;
        }
    }

}
