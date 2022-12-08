package module_1_array_and_string.array;

import java.util.ArrayList;
import java.util.List;

public class Array_Practice {
    public static void main(String[] args) {
        // 1. an array is null
        int[] arr1 = null;
        // System.out.println("arr1's length? " + arr1.length); // Null pointer exception


        // 2. an array's capacity is 0
        int[] arr2 = new int[0];
        System.out.println("arr2's length? " + arr2.length); // length == 0

        // the loop is never executed
        for (int a : arr2) {
            System.out.println("loop is executed");
        }


        int[] arr3 = new int[3];
        for (int a : arr3) {
            System.out.println(a);
        }

        int[] arr4 = {1, 2, 4};
        System.out.println(arr4); // [I@22f71333

        List<Integer> arrList = new ArrayList<>();
        System.out.println(arrList.size()); // 0
    }
}
