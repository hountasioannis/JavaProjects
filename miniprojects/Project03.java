package gr.aueb.cf.miniprojects;


/**
 * deep copy vs shallow copy
 */
public class Project03 {

    public static void main(String[] args) {
        int[][] arr = { { 1, 2 },
                { 4, 5 },
                { 7, 8 }
        };
        int[][] shallowArray = new int[3][2];
        int[][] deepArray = new int[3][2];

        System.out.println("shallowCopyTest");
        shallowArray = shallowCopy(arr);
        printArray(arr);
        System.out.println();
        printArray(shallowArray);
        System.out.println();

        arr[0][0] = 99;
        arr[2][1] = 100;

        System.out.println("after the change");
        printArray(arr);
        System.out.println();
        printArray(shallowArray);
        System.out.println();

        System.out.println("deepCopyTest");
        deepArray = deepCopy(arr);
        printArray(arr);
        System.out.println();
        printArray(deepArray);
        System.out.println();

        arr[1][1] = 50;
        arr[0][1] = 150;

        System.out.println("after the change");
        printArray(arr);
        System.out.println();
        printArray(deepArray);
        System.out.println();
    }

    public static int[][] shallowCopy(int[][] arr) {
        int[][] shallowClone = new int[3][2];

        System.arraycopy(arr, 0, shallowClone, 0, arr.length);

        return shallowClone;
    }

    public static  int[][] deepCopy(int[][] arr) {
        int[][] deepClone = new int[3][2];

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                deepClone[i][j] = arr[i][j];
            }
        }

        return deepClone;
    }

    public static void printArray(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
        }
    }
}
