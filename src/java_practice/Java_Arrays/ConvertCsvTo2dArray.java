package java_practice.Java_Arrays;

import Helper_Classes.TreeNode;

import java.util.Arrays;

public class ConvertCsvTo2dArray {

    // Given a CSV file (represented as a Java String), convert the data into a Java 2D array (return String[][])
    // String input =
    //  “id, name, age
    //   1,Lucy,18
    //   2,Jack,20
    //   3,Henry,78”

    public static String[][] convertCSVto2DArray(String s) {
        // split input string to lines
        String[] lines = s.split("\n");

        // determine rows and columns
        int rows = lines.length;
        int cols = lines[0].split(",").length;

        // create result 2D array
        String[][] result = new String[rows][cols];

        // populate values to the 2D array - alternative 1
        /*for (int i = 0; i < rows; i++) {
            String[] row = lines[i].split(",");
            for (int j = 0; j < cols; j++) {
                result[i][j] = row[j];
            }
        }*/

        // populate values to the 2D array - alternative 2
        for (int i = 0; i < rows; i++) {
            result[i] = lines[i].split(",");
        }

        return result;
    }

    public static void main(String[] args) {
        String[][] arr = new String[2][3]; // null...
        print2DArray(arr);

        String[] row1 = {"a", "1", "4"};
        Arrays.stream(row1).forEach(System.out::println);

        String[] row2 = new String[3]; // [null, null, null]
        String[] row3 = new String[] {"q", "g"};

        arr[1] = row3;

        System.out.println("new 2D array");
        print2DArray(arr);
        System.out.println(arr[0].length);
        System.out.println(arr[1].length);



        // test the function
        String s = "id, name, age\n" +
                   "1,Lucy,18\n" +
                   "2,Jack,20\n" +
                   "3,Henry,78";

        String[][] result = convertCSVto2DArray(s);
        print2DArray(result);
    }

    public static void print2DArray(String[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}
