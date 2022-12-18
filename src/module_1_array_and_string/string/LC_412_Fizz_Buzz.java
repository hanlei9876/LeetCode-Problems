package module_1_array_and_string.string;

import java.util.*;

public class LC_412_Fizz_Buzz {

    // solution 1: intuitive approach
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

    // solution 2: string concatenation - make code neater with fewer conditions in code
    // time: O(n)
    // space: O(1), if result array is not included
    public List<String> fizzBuzz_2(int n) {
        List<String> arr = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            String s = "";

            if (i % 3 == 0)
                s =  s + "Fizz";

            if (i % 5 == 0)
                s = s + "Buzz";

            if (s.equals(""))
                s = s + Integer.toString(i);

            arr.add(s);
        }

        return arr;
    }

    // solution 3: use hash table to dynamically maintain the mapping between numbers & FizzBuzz
    // Challenge: In hashmap, there is no order guarantee amongst all the key-value pairs.
    // So, we need to use an extra Array/ArrayList to keep the order of the keys when iterating the hashmap.
    // time: O(n)
    // space: O(1), if result array and hash map are not included
    public List<String> fizzBuzz_3(int n) {
        Map<Integer, String> map = new HashMap<>();
        map.put(3, "Fizz");
        map.put(5, "Buzz");

        // if we don't use ArrayList, instead, we can use a TreeMap. Because the keys will be sorted, regardless of put() order
        List<Integer> keyList = new ArrayList<>();
        keyList.add(3);
        keyList.add(5);

        List<String> arr = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            String s = "";

            for (int key : keyList) {
                if (i % key == 0)
                    s = s + map.get(key);
            }

            if (s.equals(""))
                s = s + Integer.toString(i);

            arr.add(s);
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
