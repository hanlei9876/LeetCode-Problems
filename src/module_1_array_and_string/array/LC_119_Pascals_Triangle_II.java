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
}
