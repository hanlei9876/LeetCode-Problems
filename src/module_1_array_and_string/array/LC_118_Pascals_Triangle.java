package module_1_array_and_string.array;

import java.util.*;

public class LC_118_Pascals_Triangle {

    // solution 1: iterating each row in the triangle
    // time: O(n^2)
    // space: O(1), we don't take result array into account
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangle = new ArrayList<List<Integer>>();

        for (int i = 0; i < numRows; i++) {
            List<Integer> arr = new ArrayList<>();

            for (int j = 0; j <= i; j++) {
                if (triangle.size() == 0) {
                    arr.add(j, 1);
                    break;
                }

                // i >= 1
                if (j == 0) // handle left-most element
                    arr.add(j, triangle.get(i - 1).get(0));
                else if (j == i) // handle right-most element
                    arr.add(j, triangle.get(i - 1).get(j - 1));
                else {
                    int sum = triangle.get(i - 1).get(j - 1) + triangle.get(i - 1).get(j);
                    arr.add(j, sum);
                }
            }

            triangle.add(arr);
        }

        return triangle;
    }

    // solution 1 (simplified): iterating each row in the triangle
    // for each row, including the first row,
    //      the first element and the last element are always 1
    //      the rest of the element follows the formula
    // time: O(n^2)
    // space: O(1), we don't take result array into account
    public List<List<Integer>> generate_2(int numRows) {
        List<List<Integer>> triangle = new ArrayList<List<Integer>>();

        for (int i = 0; i < numRows; i++) {
            List<Integer> arr = new ArrayList<>();

            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i)
                    arr.add(j, 1);
                else {
                    int sum = triangle.get(i - 1).get(j - 1) + triangle.get(i - 1).get(j);
                    arr.add(j, sum);
                }
            }

            triangle.add(arr);
        }

        return triangle;
    }

}
