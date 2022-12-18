package module_1_array_and_string.string;

public class LC_344_Reverse_String {

    // the only information wee need to reverse a given string is the two pointers: L, R
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
}
