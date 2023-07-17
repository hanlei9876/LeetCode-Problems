package module_3_recursion_I_and_II;

import java.util.ArrayList;
import java.util.List;

// n & k are 1-based
public class LC_779_Kth_Symbol_in_Grammar {

    // solution 1: brute force
    public int kthGrammar(int n, int k) {
        if (n == 1) {
            return 0;
        }

        // n >= 2
        List<Integer> prev = new ArrayList<>();
        prev.add(0);
        List<Integer> curr = new ArrayList<>();

        for (int i = 2; i <= n; i++) {
            int num = (int) Math.pow(2, i - 2); // the total amount of previous row
            for (int j = 0; i < num; j++) {
                if (prev.get(j) == 0) {
                    curr.add(0);
                    curr.add(1);
                } else {
                    curr.add(0);
                    curr.add(1);
                }
            }

            prev = curr;
            curr.clear();
        }

        return prev.get(k - 1);
    }

}
