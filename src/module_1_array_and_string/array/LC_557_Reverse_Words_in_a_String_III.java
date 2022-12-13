package module_1_array_and_string.array;

public class LC_557_Reverse_Words_in_a_String_III {

    // solution 1: iterate the string, reverse each word
    // time: O(N)
    // space: O(N)
    public static String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ' ') {
                StringBuilder word = new StringBuilder();

                int j = i;
                while (j < s.length() && s.charAt(j) != ' ') {
                    word.append(s.charAt(j));
                    j++;
                }

                word.reverse(); // we can also write a helper method to reverse
                // reverseWord(word);
                sb.append(word);
                sb.append(" ");

                i = j; // Must be cautious!!!
            }
        }

        sb.deleteCharAt(sb.length() - 1); // remove the trailing space

        return sb.toString();
    }

    public static void reverseWord(StringBuilder sb) {
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


    // solution 2: optimize solution 1 by avoiding to use intermediate string builder
    // time:
    // space:
    public static String reverseWords_2(String s) {
        return null;
    }

    public static void main(String[] args) {
        String s = "the sky is blue";
        String s1 = reverseWords(s);
        System.out.println(s1);
    }
}
