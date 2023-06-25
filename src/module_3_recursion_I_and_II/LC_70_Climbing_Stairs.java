package module_3_recursion_I_and_II;

import java.util.HashMap;
import java.util.Map;

// climbing stairs is equivalent to a Fibonacci sequence.
// so, the same as LC-509 - Fibonacci number
public class LC_70_Climbing_Stairs {

    // solution-1： iteration
    // use array to as cache
    // time: O(N)
    // space: O(N)
    public int climbStairs(int n) {
        if (n < 2) {
            return 1;
        }

        int[] cache = new int[n + 1];
        cache[0] = 1;
        cache[1] = 1;
        for (int i = 2; i <= n; i++) {
            cache[i] = cache[i - 1] + cache[i - 2];
        }

        return cache[n];
    }

    // solution-2： iteration with optimized space
    // use 2 variables to hold the previous two results
    // time: O(N)
    // space: O(1)
    public int climbStairs_1(int n) {
        if (n < 2) {
            return 1;
        }

        int curr = 0;
        int prev1 = 1;
        int prev2 = 1;
        for (int i = 2; i <= n; i++) {
            curr = prev1 + prev2;
            prev2 = prev1;
            prev1 = curr;
        }

        return curr;
    }

    // solution-3： brute force recursion
    // time:
    // space:
    public int climbStairs_2(int n) {
        if (n < 2) {
            return 1;
        }

        return climbStairs_2(n - 1) + climbStairs_2(n - 2);
    }

    // solution-4：recursion with memoization (optimized time)
    // time:
    // space:
    private Map<Integer, Integer> cache = new HashMap<>();

    public int climbStairs_3(int n) {
        if (cache.containsKey(n)) {
            return cache.get(n);
        }

        if (n < 2) {
            cache.put(n, 1);
            return 1;
        }

        int result = climbStairs_3(n - 1) + climbStairs_3(n - 2);
        cache.put(n, result);

        return result;
    }

}
