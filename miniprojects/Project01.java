package gr.aueb.cf.miniprojects;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;


/**
 * production of combinations for lottery game
 */
public class Project01 {

    public static void main(String[] args) {
        try (Scanner in = new Scanner(new File("C:\\Users\\Public\\fourtyninenumbers.txt"));
             PrintStream ps = new PrintStream("C:\\Users\\Public\\COMBINATIONS.txt");) {
            final int N = 6;
            int[] arr = new int[49];
            int[] row = new int[6];

            for (int i = 0; i < arr.length; i++){
                arr[i] = in.nextInt();
            }
              Arrays.sort(arr);

            for (int i = 0; i <= arr.length - N; i++) {
                for (int j = i + 1; j <= arr.length - N + 1; j++) {
                    for (int k = j + 1; k <= arr.length - N + 2; k++) {
                        for (int l = k + 1; l <= arr.length - N + 3; l++) {
                            for (int m = l + 1; m <= arr.length - N + 4; m++) {
                                for (int n = m + 1; n < arr.length; n++) {
                                    row[0] = arr[i];
                                    row[1] = arr[j];
                                    row[2] = arr[k];
                                    row[3] = arr[l];
                                    row[4] = arr[m];
                                    row[5] = arr[n];


                                    if ((!isEven(row)) && (!isOdd(row)) && (!isContiguous(row)) && (!isSameEnding(row)) && (!isSameTen(row))) {
                                      ps.printf("%d\t%d\t%d\t%d\t%d\t%d\n", row[0], row[1], row[2], row[3], row[4], row[5]);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }catch (IOException e) {
            System.out.println("I/O Error");
        }
    }

    public static boolean isEven(int[] row) {
        int count = 0;

        for (int num : row) {
            if (num % 2 == 0) count++;
        }

        return (count > 4);
    }

    public static boolean isOdd(int[] row) {
        int count = 0;

        for (int num : row) {
            if (num % 2 != 0) count++;
        }

        return (count > 4);
    }

    public static boolean isContiguous(int[] row) {
        int count = 0;

        for (int i = 0; i < row.length - 1; i++) {
            if (row[i + 1] - row[i] == 1) count++;
        }

        return (count > 1);
    }

    public static boolean isSameEnding(int[] row) {
        int[] lastDigit = new int[6];
        int[] count = new int[3];

        for (int i = 0; i < row.length; i++) {
            lastDigit[i] = row[i] % 10;
        }

        for (int i = 0; i <= lastDigit.length - 4; i++) {
            for (int j = i + 1; j < lastDigit.length; j++) {
                if (lastDigit[i] == lastDigit[j]) count[i]++;
            }
        }

        return ((count[0] > 2) || (count[1] > 2) || (count[2] > 2));
    }

    public static boolean isSameTen(int[] row) {
        int[] lastDigit = new int[6];
        int[] count = new int[3];

        for (int i = 0; i < row.length; i++) {
            lastDigit[i] = row[i] / 10;
        }

        for (int i = 0; i <= lastDigit.length - 4; i++) {
            for (int j = i + 1; j < lastDigit.length; j++) {
                if (lastDigit[i] == lastDigit[j]) count[i]++;
            }
        }

        return ((count[0] > 2) || (count[1] > 2) || (count[2] > 2));
    }
}
