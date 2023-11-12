package module_3_recursion_I_and_II;

import java.util.HashMap;
import java.util.Map;

public class LC_509_Fibonacci_Number {

    private Map<Integer, Integer> cache = new HashMap<>();

    // solution-1: recursion without memoization
    // for complexity, given fib(n), there will be n level of the call tree
    // time: O(2^n), total amount of nodes in the balanced tree
    // space: O(n)
    public int fib(int n) {
        // base case
        if (n < 2) {
            return n;
        }

        return fib(n - 1) + fib(n - 2);
    }

    // solution-2: recursion with memoization
    // for complexity, given fib(n), there will be n level of the call tree
    // time: O(n), total amount of nodes in the balanced tree
    // space: O(n) + O(n) = O(n). first is max height of call stack, second is cache size
    public int fib_1(int n) {
        // always look up cache first to know if the result has been calculated already
        if (cache.containsKey(n)) {
            return cache.get(n);
        }

        int result;
        if (n < 2) { // base case - we also cache the base cases' results to avoid calling fib(0) & fib(1)
            result = n;
        } else {
            result = fib_1(n - 1) + fib_1(n - 2);
        }

        cache.put(n, result); // memorization - what we store in the cache is the result for n

        return result;
    }

    // solution-3: iteration, bottom-up
    // use integer array
    // time: O(N)
    // space: O(N)
    public int fib_2(int n) {
        if (n < 2) {
            return n;
        }

        int[] cache = new int[n + 1];
        cache[0] = 0;
        cache[1] = 1;

        for (int i = 2; i <= n; i++) {
            cache[i] = cache[i - 1] + cache[i - 2];
        }

        return cache[n];
    }

    // solution-4: iteration, optimize space to O(1)
    // time: O(N)
    // space: O(1)
    public int fib_3(int n) {
        if (n < 2) {
            return n;
        }

        int prev1 = 1;
        int prev2 = 0;
        int curr = 0;
        for (int i = 2; i <= n; i++) {
            curr = prev1 + prev2;
            prev2 = prev1;
            prev1 = curr;
        }

        return curr;
    }

    // solution-5: math (optimal solution) - golden ratio formula (see my note)
    // time: O(logN), caused by pow(goldenRation, N)
    // space: O(1)
    public int fib_4(int n) {
        double goldenRatio = (1 + Math.sqrt(5)) / 2;
        return (int) Math.round(Math.pow(goldenRatio, n) / Math.sqrt(5));
    }

    public static void main(String[] args) {
        double a = 3.5896896;
        double b = 3.4234;

        System.out.println(Math.round(a)); // 4
        System.out.println(Math.round(b)); // 3
    }
}
