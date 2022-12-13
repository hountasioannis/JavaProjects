package gr.aueb.cf.projects;

public class MaxCarsApp {

    public static void main(String[] args) {
        //δήλωση και αρχικοποίηση μεταβλητών
        int[][] arr = {{1012, 1016},
                {1317, 1417},
                {1015, 1025},
                {1022, 1126}
        };
        int maxValue = 0;

        //εντολές
        maxValue = maxParkCars(arr);

        //εκτύπωση αποτελέσματος
        System.out.println("ο μέγιστος αριθμός αυτοκινήτων που είναι σταθμευμένα είναι: " + maxValue);
    }

    /**
     * βρίσκει τον μέγιστο αριθμό οχημάτων που
     * σταθμεύουν το ίδιο χρονικό διάστημα
     *
     * @param arr ο πίνακας με τις ώρες άφιξης-αναχώρησης
     * @return ο αριθμός σταθμευμένων αυτοκινήτων το ίδιο χρονικό διάστημα
     */
    public static int maxParkCars(int[][] arr){
        int  tmp;
        int tmp2;
        int count = 0;
        int maxValue = 0;
        int[][] arrOut;

        if (arr == null) throw new IllegalArgumentException();

        arrOut = new int[2 * arr.length][2];

        for (int i =0; i < arr.length; i++ ){
            arrOut[2 * i][0] = arr[i][0];
            arrOut[2 * i][1] = 1;
            arrOut[2 * i + 1][0] = arr[i][1];
            arrOut[2 * i + 1][1] = 0;
        }

        for (int i = arrOut.length - 1; i >= 0; i--){
            for (int j = 0; j < i; j++){
                if (arrOut[j][0] > arrOut[j + 1][0]) {
                    tmp = arrOut[i][0];
                    arrOut[i][0] = arrOut[j][0];
                    arrOut[j][0] = tmp;
                    tmp2 = arrOut[i][1];
                    arrOut[i][1] = arrOut[j][1];
                    arrOut[j][1] = tmp2;
                }
            }
        }

        for (int i = 0; i < arrOut.length; i++){
            if (arrOut[i][1] == 1) {
                count++;
            }else {
                if (count > maxValue){
                    maxValue = count;
                }
                count--;
            }
        }

        return maxValue;
    }
}
