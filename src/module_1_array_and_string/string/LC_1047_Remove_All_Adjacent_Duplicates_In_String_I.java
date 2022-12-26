package module_1_array_and_string.string;

import java.util.HashSet;
import java.util.Set;

// reference to LC_1209_Remove_All_Adjacent_Duplicates_in_String_II
public class LC_1047_Remove_All_Adjacent_Duplicates_In_String_I {

    // solution 1: (time exceeded): use hash set to hold all possible duplicate strings from "aa" to "zz"
    // time: O((N/2) * (26*N)) >> O(N^2). Here we have an onion : while -> for -> replace
    //      - while is executed not more then N/2N/2N/2 times,
    //      - for is always run 26 times
    //      - replace has O(N).
    // space: O(N), caused by s = s.replace(dup, ""), which creates a copy of the string
    public String removeDuplicates(String s) {
        // prepare the duplicate set from "aa" to "zz"
        Set<String> dupSet = new HashSet<>();
        for (char c = 'a'; c <= 'z'; c++) {
            StringBuilder sb = new StringBuilder();
            sb.append(c);
            sb.append(c);
            dupSet.add(sb.toString());
        }

        // use String's built-in method s = s.replace("aa", "") to remove duplicates in the string
        int prevLen = -1;
        while (prevLen != s.length()) {
            prevLen = s.length();

            for (String dup : dupSet) {
                // remove duplicates in the string
                // time: O(N)
                // space: O(N)
                s = s.replace(dup, "");
            }
        }

        return s;
    }

    // solution 2: use stack - StringBuilder as Stack
    // time: O((N)
    // space: O(N - D/2) >> O(N - D), D is the total
    public String removeDuplicates_2(String s) {
        // use StringBuilder as a stack
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            int len = sb.length();

            if (len == 0 || s.charAt(i) != sb.charAt(len - 1)) {
                sb.append(s.charAt(i));
            } else {
                sb.deleteCharAt(len - 1);
            }
        }

        return sb.toString();
    }


    public static void main(String[] args) {
        String s = "aeedef";
        System.out.println(s.replace('e', 'x')); // axxdxf
        System.out.println(s.replace("e", "x1")); // ax1x1dx1f

        System.out.println(s.replace("e", "")); // adf


        String k = "aeeeds";
        k = k.replace("ee", "xx");
        System.out.println(k);

        StringBuilder sb = new StringBuilder();
        int len = sb.length();
        System.out.println(len); // 0
        System.out.println(sb.charAt(0)); // StringIndexOutOfBoundsException
    }
}
