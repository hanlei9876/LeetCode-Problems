package module_1_array_and_string.string;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LC_3_Longest_Substring_Without_Repeating_Characters {

    // solution-1: brute force
    // time: O(N^3)
    // space: O( min(M, N) ), caused by HashSet. M - size of char set, N - size of string


    // solution-2: sliding window
    // use HashSet to check duplicates for a window
    // time: O(2N) >> O(N)
    // space: O( min(M, N) ), caused by HashSet. M - size of char set, N - size of string
    public int lengthOfLongestSubstring(String s) {
        int maxLen = 0;
        Set<Character> set = new HashSet<>();

        int L = 0;
        int R = 0;
        while (R < s.length()) {
            while (set.contains(s.charAt(R))) { // window is invalid
                set.remove(s.charAt(L));
                L++;
            }

            set.add(s.charAt(R));

            maxLen = Math.max(maxLen, R - L + 1);

            R++;
        }

        return maxLen;
    }

    // solution-3: sliding window (optimized)
    // optimize time from O(2N) to O(N), using HashMap
    // avoid L pointer sliding to form a valid window. Instead, enable L pointer to jump to the new location to form a valid window
    // time: O(N), caused by right pointer only
    // space: O(min(M, N)), caused by HashMap. M - size of char set, N - size of string
    public int lengthOfLongestSubstring_2(String s) {
        int maxLen = 0;
        Map<Character, Integer> map = new HashMap<>();

        int L = 0;
        int R = 0;
        while (R < s.length()) {
            if (map.containsKey(s.charAt(R))) {
                if (L <= map.get(s.charAt(R)))
                    L = map.get(s.charAt(R)) + 1;
            }

            maxLen = Math.max(maxLen, R - L + 1);
            map.put(s.charAt(R), R);

            R++;
        }

        return maxLen;
    }

    // solution-4: sliding window (optimized) + char array
    // Discussed with interviewer, if we are told the charset is limited to letters, or ASCII code, we can use charset instead as HaspMap.
    //      int[26] for Letters 'a' - 'z' or 'A' - 'Z'
    //      int[128] for ASCII
    //      int[256] for Extended ASCII
    // time: O(N), caused by right pointer only
    // space: O(1), the charset is constant sized
    public int lengthOfLongestSubstring_3(String s) {
        int maxLen = 0;
        int[] charArr = new int[256];

        int L = 0;
        int R = 0;
        while (R < s.length()) {
            char r = s.charAt(R);

            if (charArr[r] > 0) {
                if (L < charArr[r] - 1) {
                    L = charArr[r];
                }
            }

            maxLen = Math.max(maxLen, R - L + 1);
            charArr[r] = R + 1;

            R++;
        }

        return maxLen;
    }




    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("ss", 1);
        map.put("xx", 3);
        System.out.println(map); // {ss=1, xx=3}

        map.put("ss", 100);
        System.out.println(map); // {ss=100, xx=3}

    }
}
