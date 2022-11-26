package module_1_array_and_string.string;

public class LC_28_Find_the_Index_of_the_First_Occurrence_in_a_String {

    // time: O(M*N) for the worst case
    // space: O(1)
    public int strStr(String haystack, String needle) {
        int hay_len = haystack.length();
        int nee_len = needle.length();

        if (hay_len < nee_len)
            return -1;

        for (int i = 0;  i < hay_len; i++) {
            int j = i;
            while (j - i < nee_len) {
                if (j == hay_len)
                    return -1;

                if (haystack.charAt(j) != needle.charAt(j-i))
                    break;

                j++;
            }

            if((j - i) == nee_len)
                return i;
        }

        return -1;
    }
}
