package module_1_array_and_string.array;

public class LC_48_Rotate_Image {

    // find a way to memorize the solution
    // time: O(N^2), total number of cells
    // space: O(1)
    public void rotate(int[][] matrix) {
        int n = matrix.length;

        for (int layer = 0; layer < n/2; layer++) {
            // assume first & last are the first and last position of the left-upper pointer
            int first = layer;
            int last = n - 1 - layer;

            // below, i is the pointer of the four-cell-group
            for (int i = first; i < last; i++) {
                // we could ONLY use: i, first, last to represent the indexes
                //     - i is variable
                //     - first & last are constant

                /*
                mat[][]>>>>>>        mat[][]
                                         |
                                         |
                ^                       \|/
                ^
                ^
                mat[][]        <<<<<<<<mat[][]
                */


                int temp = matrix[first][i]; // starting element
                matrix[first][i] = matrix[last - i + first][first]; // left >> top
                matrix[last - i + first][first] = matrix[last][last - i + first]; // bottom >> left
                matrix[last][last - i + first] = matrix[i][last]; // right >> bottom
                matrix[i][last] = temp; // top >> right
            }
        }
    }

    static void print_2D_array(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; arr[i] !=  null || j < arr[i].length ; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

    }
}
