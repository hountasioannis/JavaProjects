package gr.aueb.cf.miniprojects;

/**
 * kadane's algorithm
 */
public class Project02 {

    public static void main(String[] args) {
        int[] arr= {-2, 1, -3, 4, -1, 2, 1,-5, 4};
        System.out.println(maximumSubArraySum(arr));
    }

    public static int maximumSubArraySum(int[] arr) {
        int local_max = 0;
        int global_max = Integer.MIN_VALUE;


        for (int i = 0; i < arr.length; i++) {
            local_max = Math.max(arr[i], arr[i] + local_max);
            if (local_max > global_max) {
                global_max = local_max;
            }
        }

        return global_max;
    }
}
