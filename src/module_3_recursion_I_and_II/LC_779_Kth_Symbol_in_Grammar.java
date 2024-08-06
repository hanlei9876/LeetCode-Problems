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

    // solution 2: recursion - derive from its parent value (not recommended)
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

    // solution 3: recursion - pattern - flip parent bits
    // time: O(n) - n is the level of row
    // space: O(n)
    public int kthGrammar_3(int n, int k) {
        // base case
        if (n == 1) {
            return 0;
        }

        // recursion relation
        int len = (int) Math.pow(2, n - 1);
        if (k <= len / 2) {
            int result = kthGrammar_3(n - 1, k);
            return result;
        } else {
            int result = kthGrammar_3(n - 1, k - (len / 2));
            return (result == 0) ? 1 : 0;
        }
    }

    // solution 4: recursion to iteration - without using stack for space optimization
    // use loop to iterate from bottom to top, so as to get answer
    // time: O(n)
    // space: O(1)
    public int kthGrammar4(int n, int k) {
        if (n == 1) return 0;

        // below, we start to maintain val, n, k
        int val = 0; // we assume val == 0
        // iterate from n to 1
        while (n > 1) {
            int len = (int) Math.pow(2, n - 1);
            if (k <= len/2) {
                // do nothing
            } else {
                // val = 1 - val;
                val = (val == 0) ? 1 : 0; // flip val
                k = k - len/2;
            }

            n = n - 1;
        }

        return (val == 0) ? 0 : 1;
    }


    public static void main(String[] args) {
        int a = 1;
        System.out.println(a>>1); // 0
    }
}

// solution 5: recursion - binary search tree - (NOT YET implemented, need to do this after learning binary search tree)
// time: O(n)
// space: O(n)
class LC_779_Solution_4 {
    public int kthGrammar(int n, int k) {
        return depthFirstSearch(n, k, 0);
    }

    public int depthFirstSearch(int n, int k, int val) {
        // base case
        if (n == 1) {
            return val;
        }

        // recursion relation
        int num = (int) Math.pow(2, n - 1);

        if (k <= num/2) {
            int rootVal = (val == 0) ? 0: 1;
            return depthFirstSearch(n - 1, k, rootVal);
        } else {
            int rootVal = (val == 0) ? 1 : 0;
            return depthFirstSearch(n - 1, k - num/2, rootVal);
        }
    }
}
