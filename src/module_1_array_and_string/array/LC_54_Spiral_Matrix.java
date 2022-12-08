package module_1_array_and_string.array;

import java.util.ArrayList;
import java.util.List;

public class LC_54_Spiral_Matrix {

    // solution 1: simulation - set up boundaries
    // time: O(M*N)
    // space: O(1)
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();

        int left = 0;
        int right = matrix[0].length - 1;
        int top = 0;
        int bottom = matrix.length - 1;

        while (true) {
            // left >> right
            for (int i = left; i <= right; i++)
                res.add(matrix[top][i]);
            top++;
            if (left > right || top > bottom)
                break;

            // top >> bottom
            for (int i = top; i <= bottom; i++)
                res.add(matrix[i][right]);
            right--;
            if (left > right || top > bottom)
                break;

            // right >> left
            for (int i = right; i >= left; i--)
                res.add(matrix[bottom][i]);
            bottom--;
            if (left > right || top > bottom)
                break;

            // bottom >> top
            for (int i = bottom; i >= top; i--)
                res.add(matrix[i][left]);
            left++;
            if (left > right || top > bottom)
                break;
        }

        return res;
    }
}
