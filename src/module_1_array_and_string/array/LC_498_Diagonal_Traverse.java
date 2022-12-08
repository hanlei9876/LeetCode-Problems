package module_1_array_and_string.array;

import java.util.ArrayList;

public class LC_498_Diagonal_Traverse {

    // solution - 1: traverse diagonal, & reverse parts of the diagonals
    // time: O(M * N)
    // space: O(min(M, N))
    public int[] findDiagonalOrder(int[][] mat) {
        int M = mat.length;
        int N = mat[0].length; // M, N >= 1

        int[] res = new int[M * N];
        int index = 0;
        ArrayList<Integer> temp = new ArrayList<>();

        for (int d = 0; d < M + N - 1; d++) {
            int r = d < N ? 0 : d - N + 1;
            int c = d < N ? d : N - 1;

            if (d % 2 != 0) {
                while (r < M && c >= 0) {
                    res[index] = mat[r][c];
                    index++;

                    r++;
                    c--;
                }
            } else {
                temp.clear();

                while (r < M && c >= 0) {
                    temp.add(mat[r][c]);

                    r++;
                    c--;
                }

                for (int i = temp.size() - 1; i >= 0; i--) {
                    res[index] = temp.get(i);
                    index++;
                }
            }
        }

        return res;
    }

    // solution - 2: simulation
    // time: O(M * N)
    // space: O(1)
    public int[] findDiagonalOrder_2(int[][] mat) {
        int M = mat.length, N = mat[0].length;
        int[] res = new int[M * N];

        int i = 0, j = 0;
        for (int p = 0; p < M * N; p++) {
            res[p] = mat[i][j];

            if ((i + j) % 2 == 0) { // diagonal is going up
                // we must first evaluate j, then followed by i
                if (j == N - 1) { // j reach boundary, i might or might not reach boundary
                    i++;
                } else if (i == 0) { // i reach boundary, while j doesn't reach boundary
                    j++;
                } else { // both i & j don't reach boundary
                    i--;
                    j++;
                }
            } else { // diagonal is going down
                // we must first evaluate j, then followed by j
                if (i == M - 1) {
                    j++;
                } else if (j == 0) {
                    i++;
                } else {
                    i++;
                    j--;
                }
            }
        }

        return res;
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
        // printMatrix_2(mat);
        System.out.println("");
        printMatrix_4(mat);

        ArrayList<Integer> a = new ArrayList<>(); // ArrayList comes with overridden toString() method
        a.add(1);
        a.add(2);
        System.out.println(a); // [1, 2]
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
        int M = mat.length; // how many rows we have
        int N = mat[0].length; // how many columns we have

        // traverse each diagonal
        for (int p = 0; p < M + N - 1; p++) {
            // calculate the coprdiantes r, c
            int r = p < N ? 0 : p - N + 1;
            int c = p < N ? p : N - 1;

            while (r < M && c >= 0) {
                System.out.print(mat[r][c] + " ");

                r++;
                c--;
            }
            System.out.println("");
        }
    }

    // diagonal traverse, reverse order in even diagonal
    static void printMatrix_4(int[][] mat) {
        int M = mat.length;
        int N = mat[0].length;

        ArrayList<Integer> temp = new ArrayList<>();

        for (int d = 0; d < M + N - 1; d++) {
            int r = d < N ? 0 : d - N + 1;
            int c = d < N ? d : N - 1;

            if (d % 2 != 0) {
                while (r < M && c >= 0) {
                    System.out.print(mat[r][c] + " ");
                    r++;
                    c--;
                }
                System.out.println("");
            } else {
                temp.clear();

                while (r < M && c >= 0) {
                    temp.add(mat[r][c]);
                    r++;
                    c--;
                }

                for (int i = temp.size() - 1; i >= 0; i--) {
                    System.out.print(temp.get(i) + " ");
                }
                System.out.println("");
            }
        }
    }
}
