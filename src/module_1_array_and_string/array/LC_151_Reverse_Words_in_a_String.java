package module_1_array_and_string.array;

public class LC_151_Reverse_Words_in_a_String {

    // solution 1: iterate the string in reverse order,
    // while removing leading spaces, trailing spaces, and duplicate spaces in the middle
    // time: O(N)
    // space: O(N), take String Builder into account
    public static String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();

        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) != ' ') {
                StringBuilder word = new StringBuilder();

                int j = i;
                while (j >= 0 && s.charAt(j) != ' ') {
                    word.append(s.charAt(j));
                    j--;
                }

                // temp.reverse(); // alternatively, could use my help method
                reverseStringBuilder(word);
                sb.append(word);
                sb.append(' ');

                i = j;
            }
        }

        // delete the trailing space in sb
        int len = sb.length();
        sb.deleteCharAt(len - 1);

        return sb.toString();
    }

    static void reverseStringBuilder(StringBuilder sb) {
        int i = 0;
        int j = sb.length() - 1;

        while (i < j) {
            char temp = sb.charAt(i);
            sb.setCharAt(i, sb.charAt(j));
            sb.setCharAt(j, temp);

            i++;
            j--;
        }
    }

    public static void main(String[] args) {
        String s = "the sky is blue";
        String s1 = reverseWords(s);
        System.out.println(s1);
    }
}
