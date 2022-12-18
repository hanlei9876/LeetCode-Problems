package module_1_array_and_string.string;

public class LC_186_Reverse_Words_in_a_String_II {

    // time: O(N/2 + N + N/2) = O(N)
    // space: O(1)
    public void reverseWords(char[] s) {
        // step 1: reverse the whole array in place
        // [abc de] >> [ed cba]
        int i = 0, j = s.length - 1;
        while (i < j) {
            char temp = s[i];
            s[i] = s[j];
            s[j] = temp;

            i++;
            j--;
        }

        // step-2: use one-pointer with i-j relay to reverse each word
        // [ed cba] >> [de cba]
        int prev = -1;
        int curr = 0;
        while (curr <= s.length) {
            if (curr == s.length || s[curr] == ' ') {
                int L = prev + 1;
                int R = curr - 1;
                while (L < R) {
                    char temp = s[L];
                    s[L] = s[R];
                    s[R] = temp;

                    L++;
                    R--;
                }

                prev = curr;
            }

            curr++;
        }
    }

}
