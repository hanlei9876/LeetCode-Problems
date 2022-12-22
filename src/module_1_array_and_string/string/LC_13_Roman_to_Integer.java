package module_1_array_and_string.string;

import java.util.HashMap;
import java.util.Map;

public class LC_13_Roman_to_Integer {

    // time: O(1), since the string's length is limited to 3999
    // space: O(1)
    public int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int sum = 0;
        int i = 0;
        int j = 1;
        while (i < s.length()) {
            if (j >= s.length() || map.get(s.charAt(i)) >= map.get(s.charAt(j))) {
                sum = sum + map.get(s.charAt(i));
                i++;
                j++;
            } else {
                int sub = map.get(s.charAt(j)) - map.get(s.charAt(i));
                sum = sum + sub;
                i = i + 2;
                j = j + 2;
            }
        }

        return sum;
    }

}
