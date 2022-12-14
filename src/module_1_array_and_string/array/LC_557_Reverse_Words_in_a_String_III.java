package module_1_array_and_string.array;

public class LC_557_Reverse_Words_in_a_String_III {

    // solution 1: iterate the string, reverse each word
    // result string builder + intermediate string builder for reversing words
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


    // solution 2: two pointers (prev, curr) + result string builder, optimize solution 1 by avoiding to use intermediate string builder for word reverse
    //      user two pointers to dynamically maintain word's boundary, so we can reversely traverse each word
    // time: O(N + N) >> O(N). Each char will be iterated twice. once by curr, once by i
    // space: O(N), this is caused by the sting builder to hold result
    public static String reverseWords_2(String s) {
        StringBuilder sb = new StringBuilder();

        int prev = -1; // initialize
        int curr = 0;
        while (curr <= s.length()) { // include the out-of-bound
            if (curr == s.length() || s.charAt(curr) == ' ') {
                // reversely traverse the word and append to sb
                for (int i = curr - 1; i > prev; i--)
                    sb.append(s.charAt(i));

                if (curr != s.length())
                    sb.append(' ');

                prev = curr;
            }

            curr++;
        }

        return sb.toString();
    }

    // solution 3 (alternative to solution 2): convert String to Char Array
    //      two pointers (prev, curr) + char array,
    //      user two pointers to dynamically maintain word's boundary, do reverse in-place in char array
    // Regarding time & space complexity of string's built-in method - toCharArray():
    //      It takes O(n) time to return the result, where n is the length of the string.
    //      It also takes O(n) space, as it creates a defensive copy of the original string.
    // time: O(N) + O(N + N/2) = O(N). In-place reverse only cost N/2 time
    // space: O(N) + O(N) = O(N), caused by char array
    public static String reverseWords_3(String s) {
        char[] arr = s.toCharArray(); // O(N) time + O(N) space

        int prev = -1;
        int curr = 0;
        while (curr <= arr.length) { // include out-of-boundary
            if (curr == arr.length || arr[curr] == ' ') {
                // reverse word in-place
                int i = prev + 1;
                int j = curr - 1;
                while (i < j) {
                    char temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;

                    i++;
                    j--;
                }

                prev = curr;
            }

            curr++;
        }

        return new String(arr);
    }




    public static void main(String[] args) {
        String s = "the sky is blue";
        String s1 = reverseWords(s);
        System.out.println(s1);
    }
}
