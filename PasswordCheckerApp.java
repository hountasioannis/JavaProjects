package gr.aueb.cf.ch2;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordCheckerApp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s1 = "";

        System.out.println("ΔΗΜΙΟΥΡΓΗΣΤΕ ΚΩΔΙΚΟ");
        System.out.println("ΜΕ ΤΟΥΣ ΕΞΗΣ ΠΕΡΙΟΡΙΣΜΟΥΣ:");
        System.out.println("Τουλάχιστον 8 χαρακτήρες");
        System.out.println("Τουλάχιστον 1 μικρό γράμμα");
        System.out.println("Τουλάχιστον 1 κεφαλαίο γράμμα");
        System.out.println("Τουλάχιστον 1 αριθμό");
        System.out.println("Τουλάχιστον 1 ειδικό χαρακτήρα #?!@$%^&*-");
        String s = sc.nextLine();

        Pattern pattern = Pattern.compile("(?=.*?[a-z])(?=.*?[A-Z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-])^.{8,}$");
        Matcher matcher = pattern.matcher(s);

        while (matcher.find()) {
            s1 = matcher.group();
        }

        if (s1.isEmpty()) {
            System.out.println("password is not valid");
        } else {
            System.out.println(s1 + " is ok!!");
        }
    }
}
