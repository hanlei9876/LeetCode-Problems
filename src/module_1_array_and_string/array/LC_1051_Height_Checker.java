package module_1_array_and_string.array;

import java.util.Arrays;

public class LC_1051_Height_Checker {

    // solution-1: intuitive solution
    //   (1) copy input array to a new array
    //   (2) sort new array
    //   (3) one pass to compare input array again the new array
    //
    // time: O(NlogN + 2N) = O( N*(logN + 2) ) = O(NlogN)
    // space: O(N)
    public int heightChecker(int[] heights) {
        int[] exp = new int[heights.length];
        for (int i = 0; i < heights.length; i++) {
            exp[i] = heights[i];
        }

        Arrays.sort(exp);

        int count = 0;
        for (int i = 0; i < heights.length; i++) {
            if (exp[i] != heights[i]) {
                count++;
            }
        }

        return count;
    }

    // solution-2: use array map to optimize time complexity & space complexity (see the independent note)
    // this is because of the constraint "1 <= heights[i] <= 100" in the problem
    // time: O(N*100) = O(N)
    // space: O(100) = O(1)
    public int heightChecker_2(int[] heights) {
        int[] map = new int[101]; // we only use the indices from 1 to 100 to represent elements of input array
        for (int e : heights) {
            map[e]++;
        }

        int count = 0;
        for (int i = 0; i < heights.length; i++) {
            int j = 0;
            while (map[j] == 0) {
                j++;
            }

            if (heights[i] != j) {
                count++;
            }

            map[j]--;
        }

        return count;
    }

}
