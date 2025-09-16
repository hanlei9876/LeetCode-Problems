package module_1_array_and_string.array;

public class LC_344_Reverse_String {

    // solution 1: iteration
    // time: O(N), where N = array's length
    // space: O(1)
    public void reverseString(char[] s) {
        int i = 0;
        int j = s.length - 1;
        while (i < j) {
            char temp = s[i];
            s[i] = s[j];
            s[j] = temp;

            i++;
            j--;
        }
    }

    // solution 2: recursion
    // time: O(N) - N/2 swaps
    // space: O(N) - recursion stack height
    public void reverseString_1(char[] s) {
        helper(0, s.length - 1, s);
    }

    private void helper(int i, int j, char[] s) {
        // base case
        if (i >= j) {
            return;
        }

        // occurrence relation 1: swap elements on i & j
        char temp = s[i];
        s[i] = s[j];
        s[j] = temp;

        // occurrence relation 2: reverse the rest of the string
        i++;
        j--;
        helper(i, j, s);
    }
}
