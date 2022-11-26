package module_1_array_and_string.string;

public class LC_14_Longest_Common_Prefix {

    // solution-1: vertical scanning
    // time: O(M*N).
    // Worst case: all strings are the same and have equal length of M
    // Best case: M == length of the least common prefix
    // space: O(M), it comes from the space for StringBuilder
    public String longestCommonPrefix(String[] strs) {
        int strL = strs[0].length();
        int arrL = strs.length;

        // edge cases
        if (arrL == 1 || strL == 0)
            return strs[0];

        StringBuilder sb = new StringBuilder("");

        for (int i = 0; i < strL; i++) {
            for (int j = 1; j < arrL; j++) {
                if (i >= strs[j].length() || strs[j].charAt(i) != strs[0].charAt(i)) // make sure not to be out of bound
                    return sb.toString();
            }

            sb.append(strs[0].charAt(i)); // this is time-consuming
        }

        return sb.toString();
    }

    // solution 2: vertical scanning (optimize the space from String builder)
    // replace string builder with the index i
    // time: O(M*N).
    // Worst case: all strings are the same and have equal length of M
    // Best case: M == length of the least common prefix
    // space: O(1)
    public String longestCommonPrefix_2(String[] strs) {
        int strL = strs[0].length();
        int arrL = strs.length;

        // edge cases
        if (arrL == 1 || strL == 0)
            return strs[0];

        int i = 0;
        while (i < strL) {
            for (int j = 1; j < arrL; j++) {
                if (i >= strs[j].length() || strs[j].charAt(i) != strs[0].charAt(i))
                    return strs[0].substring(0, i);
            }

            i++;
        }

        return strs[0];
    }
}
