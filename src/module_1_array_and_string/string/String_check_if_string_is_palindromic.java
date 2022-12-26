package module_1_array_and_string.string;

public class String_check_if_string_is_palindromic {

    // definition of a Palindromic String:
    // It reads the same char sequence from either forward or backward

    // constraint: input string's length >= 1
    public static boolean validate_Palindromic_String(String s) {
        int i = 0;
        int j = s.length() - 1;
        while(i < j) {
            if (s.charAt(i) != s.charAt(j))
                return false;

            i++;
            j--;
        }

        return true;
    }

    public static void main(String[] args) {
        String s = "babad";
        System.out.println(validate_Palindromic_String(s));
    }
}
