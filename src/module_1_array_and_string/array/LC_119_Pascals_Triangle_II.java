package module_1_array_and_string.array;

import java.util.ArrayList;
import java.util.List;

public class LC_119_Pascals_Triangle_II {

    // solution 1: iteration
    // always maintain two dynamic arrays to calculate the latest row
    // time: O(k^2), k is the row index. This solution will walk through each row from the beginning
    // space: O(k), in the worst case where we're producing the last row
    public List<Integer> getRow(int rowIndex) {
        // initialize the first row: [1]
        List<Integer> lastRow = new ArrayList<>();
        lastRow.add(1);

        for (int i = 1; i <= rowIndex; i++) {
            List<Integer> currRow = new ArrayList<>();

            for (int j = 0; j < i + 1; j++) {
                if (j == 0 || j == i) {
                    currRow.add(j,1);
                } else {
                    int sum = lastRow.get(j - 1) + lastRow.get(j);
                    currRow.add(j, sum);
                }
            }

            lastRow = currRow;
        }

        return lastRow;
    }

    // solution-2: math formula
    // time: O(N), N is the row number
    // space: O(N), to hold the resulting row
    public List<Integer> getRow_2(int n) {
        List<Integer> row = new ArrayList<>();
        row.add(1);

        for (int k = 1; k <= n; k++) {
            int element = (int) ((row.get(row.size() - 1) * (long) (n - k + 1)) / k); // cast from int to long, then back to int

            row.add(element);
        }

        return row;
    }

    // solution-3: math formula (optimize the time)
    // time: O(N), N is the row number
    // space: O(N), to hold the resulting row
    public List<Integer> getRow_3(int n) {
        List<Integer> row = new ArrayList<>();
        row.add(1);

        for (int k = 1; k <= n/2; k++) {
            int element = (int) ((row.get(row.size() - 1) * (long) (n - k + 1)) / k);

            row.add(element);
        }

        for (int i = n/2 + 1; i <= n; i++) {
            row.add( row.get(n - i) );
        }

        return row;
    }

    // solution-4: brute force recursion
    // for complexity. let k = rowIndex
    // time: O(2^k)
    // the total time is k*(1+2+4+8+...)
    //      = k*(2^0 + 2^1 + 2^2 + 2^3 + ...2^k)
    //      = O( k*(2^0 + 2^1 + 2^2 + 2^3 + ...2^k) )
    //      = O(2^k)
    // space: O(k) + O(K) = O(k). fist is ArrayList, second is maximum height of call stack
    public List<Integer> getRow_4(int rowIndex) {
        List<Integer> row = new ArrayList<>();

        for (int i = 0; i <= rowIndex; i++) {
            int value = getValue(rowIndex, i);
            row.add(value);
        }

        return row;
    }

    // row i & column j are 0-based
    private int getValue(int i, int j) {
        if (j == 0 || j == i) {
            return 1;
        }

        int value = getValue(i - 1, j - 1) + getValue(i - 1, j);
        return value;
    }



    public static void main(String[] args) {
        int a = 145422675;
        int b = 16;

        // int range: -2147483648 to 2147483647

        System.out.println(a * b); // -1968204496
        System.out.println(a * (long) b); // 2326762800
        System.out.println((long) a * b); // 2326762800
    }
}
