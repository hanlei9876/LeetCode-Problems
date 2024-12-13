package module_4_binary_search;

public class LC_74_Search_a_2D_Matrix {

    // solution 0: linear search (brute force)
    // time: O(M*N)
    // space: O(1)

    // solution 1: binary search on the unfolded (sorted) array - optimal
    // time: O(logMN) -- log(M) + log(N) = log(M * N)
    // space: O(1)
    public boolean searchMatrix(int[][] matrix, int target) {
        int M = matrix.length;
        if (M == 0)
            return false;
        int N = matrix[0].length;

        int L = 0;
        int R = M * N - 1;

        while (L <= R) {
            int mid = L + (R - L) / 2;
            int row = mid / N;
            int col = mid % N;

            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] < target) {
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }

        return false;
    }

    // solution 2: two binary searches
    // time: O(logM + logN) >> O(logMN)
    // space: O(1)
    public boolean searchMatrix_2(int[][] matrix, int target) {
        int M = matrix.length;
        if (M == 0)
            return false;
        int N = matrix[0].length;

        // first binary search to find the target row
        int targetRow = -1;

        int L = 0;
        int R = M - 1;
        while (L <= R) {
            int mid = L + (R - L) / 2;
            int firstRowValue = matrix[mid][0];
            int lastRowValue = matrix[mid][N - 1];

            if (firstRowValue <= target && target <= lastRowValue) {
                targetRow = mid;
                break;
            } else if (target < firstRowValue) {
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }

        if (targetRow == -1)  // when target < (>) any value in matrix
            return false;

        // second binary search in the target row
        int l = 0;
        int r = N - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;

            if (matrix[targetRow][mid] == target) {
                return true;
            } else if (matrix[targetRow][mid] < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return false;
    }
}
