package module_1_array_and_string.string;

import java.util.Stack;

// reference to LC_1047_Remove_All_Adjacent_Duplicates_In_String_I
public class LC_1209_Remove_All_Adjacent_Duplicates_in_String_II {

    // solution 1 (time exceeded): directly remove k duplicates on the string, until no k duplicates exist
    // in each iteration, we only delete the first occurrence of k adjacent duplicates
    // convert string to string builder, which become mutable
    // time: O((N/k)*N) = O(N^2/k). N/k is the iteration of outer loop. we will loop no more than N/k times
    // space: O(N), caused by the string builder
    public String removeDuplicates(String s, int k) {
        StringBuilder sb = new StringBuilder(s); // convert string to string builder

        // from now on, we will only manipulate string builder
        int prevLen = -1;
        while (prevLen != sb.length()) {
            prevLen = sb.length();

            int count = 0;
            for (int i = 0; i < sb.length(); i++) {
                if (i == 0 || sb.charAt(i - 1) != sb.charAt(i)) {
                    count = 1;
                } else {
                    count++;
                }

                if (count == k) {
                    sb.delete(i - k + 1, i + 1);
                    break;
                }
            }
        }

        return sb.toString();
    }

    // solution 2: optimize solution 1 with count array
    // s = "a a b b b a c d"
    // count[] = [0, 0, 0, 0, 0, 0, 0, 0]
    // time: O(N)
    // space: O(2N) >> O(N), caused by String builder & count array
    public String removeDuplicates_2(String s, int k) {
        StringBuilder sb = new StringBuilder(s);
        int[] count = new int[sb.length()];

        int i = 0;
        while (i < sb.length()) {
            if (i == 0 || sb.charAt(i - 1) != sb.charAt(i)) {
                count[i] = 1;
            } else {
                count[i] = count[i - 1] + 1;
            }

            if (count[i] == k) {
                sb.delete(i - k + 1, i + 1);
                i = i - k;
            }

            i++;
        }

        return sb.toString();
    }

    // solution 3: optimize solution 1 with stack
    // use stack to replace solution-2's count array
    // time: O(N)
    // space: O(2N) >> O(N), caused by String builder & stack
    public String removeDuplicates_3(String s, int k) {
        StringBuilder sb = new StringBuilder(s);
        Stack<Integer> stack = new Stack<>();

        int i = 0;
        while (i < sb.length()) {
            if (i == 0 || sb.charAt(i - 1) != sb.charAt(i)) {
                stack.push(1);
            } else {
                int top = stack.pop();
                stack.push(top + 1);
            }

            if (stack.peek() == k) {
                sb.delete(i - k + 1, i + 1);
                i = i - k;
                stack.pop();
            }

            i++;
        }

        return sb.toString();
    }

}
