package module_1_array_and_string.array;

public class LC_344_Reverse_String {

    // time: O(N), where N = array's length
    // space: O(1)
    public void reverseString(char[] s) {
        int i = 0;
        int j = s.length - 1;
        while (i <= j) {
            char temp = s[i];
            s[i] = s[j];
            s[j] = temp;

            i++;
            j--;
        }
    }

}
