package module_3_recursion_I_and_II;

public class Reverse_Print_String {

    // iteration
    public static void reversePrint_1(char[] str) {
        for (int i = str.length - 1; i >= 0; i--) {
            System.out.print(str[i]);
        }
    }

    // recursion-1: separate 1st char from the remaining substring
    // step-1: reversely print the substring
    // step-2: print 1st char
    public static void reversePrint_2(char[] str) {
        helper(0, str);
    }

    private static void helper(int index, char[] s) {
        // base case
        if (index == s.length - 1) {
            System.out.print(s[index]);
            return;
        }

        // recurrence relation: step 1
        int next = index + 1;
        helper(next, s);

        // recurrence relation: step 2
        System.out.print(s[index]);
    }


    // recursion-2: separate last char from the remaining substring
    // step-1: print the last char
    // step-2: reversely print the substring
    public static void reversePrint_3(char[] str) {
        helper_2(str.length - 1, str);
    }

    public static void helper_2(int index, char[] s) {
        // base case
        if (index == 0) {
            System.out.print(s[index]);
            return;
        }

        // recurrence relation: step 1
        System.out.print(s[index]);

        // recurrence relation: step 2
        int next = index - 1;
        helper_2(next, s);
    }


    public static void testPrint() {
        System.out.println("");
        System.out.println("a");
        System.out.println("b");
        return;
    }

    public static void main(String[] args) {
        char[] s = {'a', 'b', 'c', 'd', 'e'};
        // reversePrint_1(s);

        reversePrint_2(s);
        System.out.println();
        reversePrint_3(s);
    }
}
