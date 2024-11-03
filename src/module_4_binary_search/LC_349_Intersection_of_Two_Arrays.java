package module_4_binary_search;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LC_349_Intersection_of_Two_Arrays {



    public static void main(String[] args) {
        // HashSet.retainAll()
        // time: O(N) - N is the larger set, as it will iterate through both of the collections
        // space: O(1) - do it in place

        // test 1 - Integers
        Set<Integer> set1 = new HashSet<>();
        set1.add(1);
        set1.add(2);
        set1.add(3);

        Set<Integer> set2 = new HashSet<>();
        set2.add(2);
        set2.add(7);

        System.out.println("before");
        System.out.println(set1); // [1, 2, 3]
        System.out.println(set2); // [2, 7]

        set1.retainAll(set2);

        System.out.println("after");
        System.out.println(set1); // [2]
        System.out.println(set2); // [2, 7]


        // test 2 - String
        Set<String> set3 = new HashSet<>();
        set3.add("ask");
        set3.add("me");
        set3.add("hello");

        Set<String> set4 = new HashSet<>();
        set4.add("me");
        set4.add("xxxxxx");

        System.out.println("before");
        System.out.println(set3); // [ask, me, hello]
        System.out.println(set4); // [me, xxxxxx]

        set3.retainAll(set4);

        System.out.println("after");
        System.out.println(set3); // [me]
        System.out.println(set4); // [me, xxxxxx]



        // test 3: HashMap.put(key, value)
        // what will happen if we run map.puy(2, 1) multiple times
        Map<Integer, Integer> map = new HashMap<>();
        map.put(2, 1);
        System.out.println(map);
        map.put(7, 1);
        System.out.println(map);
        map.put(2, 1);
        System.out.println(map);
    }
}
