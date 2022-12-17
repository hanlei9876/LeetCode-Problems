package module_1_array_and_string.string;

import java.util.ArrayList;
import java.util.List;

public class LC_412_Fizz_Buzz {

    // time: O(n)
    // space: O(1), if result array is not included
    public List<String> fizzBuzz(int n) {
        List<String> arr = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                arr.add("FizzBuzz");
            } else if (i % 3 == 0) {
                arr.add("Fizz");
            } else if (i % 5 == 0) {
                arr.add("Buzz");
            } else {
                arr.add(Integer.toString(i));
            }
        }

        return arr;
    }

    public static void main(String[] args) {
        int a = 5;
        String s1 = Integer.toString(a);
        System.out.println(s1); // 5

        int b = -5;
        String s2 = Integer.toString(b);
        System.out.println(s2); // -5

    }
}
