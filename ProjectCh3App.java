package gr.aueb.cf.projects;

import java.util.Scanner;

public class ProjectCh3App {
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
        System.out.println("1.insert");
        System.out.println("2.update");
        System.out.println("3.delete");
        System.out.println("4.select");
        System.out.println("Q or q to quit");
    }

    public static String getChoice() {
        return in.nextLine().trim();
    }

    public static void printChoice(String s) throws IllegalArgumentException {
        int choice;

        try {
            choice = Integer.parseInt(s);

            switch (choice) {
                case 1:
                    System.out.println("insert");
                    break;
                case 2:
                    System.out.println("update");
                    break;
                case 3:
                    System.out.println("delete");
                    break;
                case 4:
                    System.out.println("select");
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
