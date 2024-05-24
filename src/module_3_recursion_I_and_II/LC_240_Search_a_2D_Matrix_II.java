package module_3_recursion_I_and_II;

// constraints:
//  m == matrix.length
//  n == matrix[i].length
//  1 <= n, m <= 300

// solution-1: divide & conquer - use midpoint of row & column as pivot
// time: O()
// space: O()
public class LC_240_Search_a_2D_Matrix_II {

    // the instance variables can be shared between search() & searchRec(), without having to pass matrix to searchRec()
    private int[][] matrix;
    private int target;

    public boolean searchMatrix(int[][] matrix, int target) {
        // according to constraints, matrix != null & have 1 element at least

        this.matrix = matrix;
        this.target = target;

        // each boundary is inclusive
        return searchRec(0, matrix.length - 1, 0, matrix[0].length - 1);
    }

    private boolean searchRec(int up, int down, int left, int right) {
        // base case - out of boundaries
        if (up > down || left > right) {
            return false;
        } else if (target < matrix[up][left] || target > matrix[down][right]) {
            return false;
        }

        // recurrence relation
        // calculate (i, j) of pivot
        int mid_i = up + (down - up) / 2;
        int mid_j = left + (right - left) / 2;

        if (matrix[mid_i][mid_j] == target) {
            // if we found the target, stop search and return immediately
            return true;
        }

        if (matrix[mid_i][mid_j] < target) {
            // search 3 zones
            boolean zone_1 = searchRec(mid_i + 1, down, left, mid_j);
            boolean zone_2 = searchRec(up, mid_i, mid_j + 1, right);
            boolean zone_3 = searchRec(mid_i + 1, down, mid_j + 1, right);
            // merge results
            return zone_1 || zone_2 || zone_3;
        } else {
            // search 3 zones
            boolean zone_1 = searchRec(mid_i, down, left, mid_j - 1);
            boolean zone_2 = searchRec(up, mid_i - 1, mid_j, right);
            boolean zone_3 = searchRec(up, mid_i - 1, left, mid_j - 1);
            // merge results
            return zone_1 || zone_2 || zone_3;
        }
    }
}

// solution-2: divide & conquer - optimize searching zones - this method is what I need to learn
// see note for the data structure and its key features used in this problem
// time: O()
// space: O()
class LC_240_Search_a_2D_Matrix_II_2 {
    // the instance variables can be shared between search() & searchRec(), without having to pass matrix to searchRec()
    private int[][] matrix;
    private int target;

    public boolean searchMatrix(int[][] matrix, int target) {
        // according to constraints, matrix != null & have 1 element at least

        this.matrix = matrix;
        this.target = target;

        // each boundary is inclusive
        return searchRec(0, matrix.length - 1, 0, matrix[0].length - 1);
    }

    private boolean searchRec(int up, int down, int left, int right) {
        // base case
        if (up > down || left > right) {
            return false;
        }

        if (target < matrix[up][left] || target > matrix[down][right]) {
            return false;
        }

        // recurrence relation
        int mid_j = left + (right - left) / 2;

        int i = up;
        while (i <= down && matrix[i][mid_j] <= target) {
            if (matrix[i][mid_j] == target) {
                return true;
            }
            i++;
        }
        // now the pivot is found: matrix[i][mid_j]

        // we will need to search 2 zones
        boolean zone_1 = searchRec(i, down, left, mid_j - 1);
        boolean zone_2 = searchRec(up, i - 1, mid_j + 1, right);

        return zone_1 || zone_2;
    }
}
