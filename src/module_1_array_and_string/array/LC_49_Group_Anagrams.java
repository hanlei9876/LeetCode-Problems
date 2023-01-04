package module_1_array_and_string.array;

import java.util.*;

public class LC_49_Group_Anagrams {

    // solution-1: HashMap + Sorted String as key
    // time: O(N * (klogk))
    // space: O(N*k), the total information content from HashMap
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List> map = new HashMap<String, List>(); // space: O(N)

        for (String str : strs) { // time: O(N)
            char[] chars = str.toCharArray(); // time O(k), space O(k)
            Arrays.sort(chars); // time: O(klogk)
            String key = String.valueOf(chars); // time: O(k)

            if (!map.containsKey(key)) {
                List<String> list = new ArrayList<>();
                list.add(str);
                map.put(key, list);
            } else {
                List<String> list = map.get(key);
                list.add(str);
                map.put(key, list);
            }
        }

        return new ArrayList(map.values()); // time: O(N), space:O(N)
    }

    // solution-2: HashMap + frequency char array as key
    // time: O(N*(k+k)) >> O(N*k)
    // space: O(N*k), the total information content from HashMap
    public List<List<String>> groupAnagrams_2(String[] strs) {
        Map<String, List> map = new HashMap<String, List>();

        for (String str : strs) {
            int[] arr = new int[26];

            for (int i = 0; i < str.length(); i++) {
                arr[str.charAt(i) - 'a']++;
            }

            StringBuilder sb = new StringBuilder();
            for (int e : arr) {
                sb.append('#');
                sb.append(e);
            }
            String key = sb.toString();

            if (!map.containsKey(key)) {
                List<String> list = new ArrayList<>();
                list.add(str);
                map.put(key, list);
            } else {
                List<String> list = map.get(key);
                list.add(str);
                map.put(key, list);
            }
        }

        return new ArrayList(map.values());
    }


    public static void main(String[] args) {
        // Arrays.toString() vs String.valueOf()
        char[] arr = new char[]{'a', '{', 'b'};

        System.out.println(Arrays.toString(arr)); // [a, {, b]
        System.out.println(String.valueOf(arr)); // a{b

        int a = 5;
        String s = "x" + 5;
        System.out.println(s); // x5

    }
}
