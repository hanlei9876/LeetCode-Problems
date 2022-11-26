package module_1_array_and_string.array;

public class Array_2D_Practice {

    static void print_2D_array(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; arr[i] !=  null || j < arr[i].length ; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] a = new int[2][4]; // all the values have been initialized with 0
        System.out.println(a.length); // 2
        print_2D_array(a);

        int[][] b = new int[2][];
        System.out.println(b[0]); // null
        System.out.println(b[1]); // null
        b[0] = new int[3];
        b[1] = new int[4];
        print_2D_array(b);
    }
}
