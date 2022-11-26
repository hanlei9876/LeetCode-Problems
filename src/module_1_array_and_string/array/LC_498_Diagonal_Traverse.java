package module_1_array_and_string.array;

public class LC_498_Diagonal_Traverse {

    public int[] findDiagonalOrder(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length; // m, n >= 1

        int[] res = new int[m * n];




        return null;
    }

    public static void main(String[] args) {
        // initialize a matrix with incremental values starting from 1
        int[][] mat = new int[4][5];
        int k = 1;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                mat[i][j] = k;
                k++;
            }
        }

        printMatrix_1(mat);
        printMatrix_2(mat);
    }

    // row by row, left to right in each row
    static void printMatrix_1(int[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println("");
        }
    }

    // traverse row by row, change order in each row
    static void printMatrix_2(int[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            if (i % 2 == 0) {
                for (int j = 0; j < mat[i].length; j++)
                    System.out.print(mat[i][j] + " ");
            } else {
                for (int j = mat[i].length - 1; j >= 0; j--)
                    System.out.print(mat[i][j] + " ");
            }
            System.out.println("");
        }
    }

    // diagonal traverse, same order in each diagonal
    static void printMatrix_3(int[][] mat) {

    }
}
