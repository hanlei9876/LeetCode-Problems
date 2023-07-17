package module_3_recursion_I_and_II;

import java.util.ArrayList;
import java.util.List;

// n & k are 1-based
public class LC_779_Kth_Symbol_in_Grammar {

    // solution 1: brute force - space limit exceeded
    // time: O( 2^0 + 2^1 + 2^2 + ... + 2^(n-2) ) = O(2^n)
    // space: O(2^(n-1) + 2^n) = O(2^n)
    public int kthGrammar(int n, int k) {
        if (n == 1) {
            return 0;
        }

        // n >= 2
        List<Integer> prev = new ArrayList<>();
        prev.add(1);
        for (int i = 2; i <= n; i++) {
            List<Integer> curr = new ArrayList<>();

            int len = (int) Math.pow(2, i - 2); // last row's length
            for (int j = 0; j < len; j++) {
                int value = prev.get(j);
                if (value == 0) {
                    curr.add(0);
                    curr.add(1);
                } else {
                    curr.add(1);
                    curr.add(0);
                }
            }

            prev = curr;
        }

        return prev.get(k-1);
    }

    // solution 2: recursion - derive from its parent value
    // time: O(n) - n is the level of row
    // space: O(n)
    public int kthGrammar_2(int n, int k) {
        // base case
        if (n == 1) {
            return 0;
        }

        // recursion relation
        if (k % 2 == 1) {
            int parentIndx = (k + 1) / 2;
            int parentVal = kthGrammar_2(n - 1, parentIndx);
            int result = (parentVal == 0) ? 0 : 1;
            return result;
        } else {
            int parentIndx = k / 2;
            int parentVal = kthGrammar_2(n - 1, parentIndx);
            int result = (parentVal == 0) ? 1 : 0;
            return result;
        }
    }


    public static void main(String[] args) {
        int a = 1;
        System.out.println(a>>1);
    }
}
